package com.example.bloodpressureapp;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bloodpressureapp.domain.Bloodpressure;
import com.example.bloodpressureapp.domain.BloodpressureRepository;
import com.example.bloodpressureapp.domain.User;
import com.example.bloodpressureapp.domain.UserRepository;
import com.example.bloodpressureapp.web.UserDetailServiceImpl;

@SpringBootApplication
public class BloodpressureappApplication {

	private static final Logger log = LoggerFactory.getLogger(BloodpressureappApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BloodpressureappApplication.class, args);
	}

	@Bean
	public CommandLineRunner bloodDemo(BloodpressureRepository repository, UserRepository urepository) {
		return (args) -> {
			
			User user1 = new User("Aino", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "aino@aino.com", "ADMIN");

			log.info("save user 1 aino");
			urepository.save(user1);
			
			LocalDate now = LocalDate.now();

			repository.save(new Bloodpressure(now, user1, 130, 85, 100));
			repository.save(new Bloodpressure(now, user1, 123, 91, 100));
			repository.save(new Bloodpressure(now, user1, 135, 89, 155));
			
			User user2 = new User("Bob", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "bob@aino.com", "ADMIN");

			log.info("save user 2 Bob");
			urepository.save(user2);
		

			repository.save(new Bloodpressure(now, user2, 222, 002, 200));


			log.info("fetch all blood pressures");
			for (Bloodpressure bloodpressure : repository.findAll()) {
				log.info(bloodpressure.toString());
			}

		};

	}
}
