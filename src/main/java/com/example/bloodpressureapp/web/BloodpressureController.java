package com.example.bloodpressureapp.web;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bloodpressureapp.domain.Bloodpressure;
import com.example.bloodpressureapp.domain.BloodpressureRepository;
import com.example.bloodpressureapp.domain.User;
import com.example.bloodpressureapp.domain.UserRepository;

@Controller
public class BloodpressureController {
	
	@Autowired
	private BloodpressureRepository repository;
	
	@Autowired
	private UserRepository urepository;
	
    
    @GetMapping("/bloodpressures")
    public String bloodpressureList(Model model, Principal principal) {
    	//spring stores a principal object in session, and this has authentication details in it.
    	User user = urepository.findByUsername(principal.getName());
        model.addAttribute("pressures", repository.findByUser(user));
        return "bloodpressureList";
    }
	
	//list all 
    @RequestMapping(value="/allBloodPressures", method = RequestMethod.GET)
    public @ResponseBody List<Bloodpressure> bloodPressureRest() {	
        return (List<Bloodpressure>) repository.findAll();
    }
    
    //find bloodpressure by id
    @RequestMapping(value="/bloodpressure/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Bloodpressure> findBloodpressureById(@PathVariable("id") Long pressureId) {	
    	return repository.findById(pressureId);
    }
    
    //add 
    @RequestMapping("/addbloodpressure")
    public String addpressure(Model model){
    	model.addAttribute("bloodpressure", new Bloodpressure());
        return "addBloodpressure";
    }
    
    @PostMapping(value = "/savePressure/{username}")
    public String savepressure(Bloodpressure bloodpressure, @PathVariable("username") String username, Model model){
    	User user = urepository.findByUsername(username);
    	bloodpressure.setUser(user);
        repository.save(bloodpressure);
        model.addAttribute("pressures", repository.findByUser(user));
        
        return "bloodpressureList";
    } 
    
    //edit
    @RequestMapping(value= "/editBloodpressure/{id}")
    public String edit(@PathVariable("id") Long pressureId, Model model) {
    	Optional<Bloodpressure> bloodpressure = repository.findById(pressureId);
    	model.addAttribute("bloodpressure", bloodpressure);
    	return "editBloodpressure";
    }
    
    //delete
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/deleteBloodpressure/{id}", method = RequestMethod.GET)
    public String deleteBloodpressure(@PathVariable("id") Long pressureId, Model model) {
    	repository.deleteById(pressureId);
        return "redirect:../bloodpressures";
    }

}
