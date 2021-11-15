package com.example.bloodpressureapp.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Bloodpressure {
	
	//database stuff:
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bloodpressureid;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	private int upperPressure;
	private int lowerPressure;
	private int pulse;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="userid")
	private User user;

	public Bloodpressure() {}
	
	public Bloodpressure(LocalDate date, User user, int upperPressure, int lowerPressure, int pulse) {
		super();
		this.date = date;
		this.user = user;
		this.upperPressure = upperPressure;
		this.lowerPressure = lowerPressure;
		this.pulse = pulse;
	}


	public Long getBloodpressureid() {
		return bloodpressureid;
	}

	public void setBloodpressureid(Long bloodpressureid) {
		this.bloodpressureid = bloodpressureid;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getUpperPressure() {
		return upperPressure;
	}

	public void setUpperPressure(int upperPressure) {
		this.upperPressure = upperPressure;
	}

	public int getLowerPressure() {
		return lowerPressure;
	}

	public void setLowerPressure(int lowerPressure) {
		this.lowerPressure = lowerPressure;
	}

	public int getPulse() {
		return pulse;
	}

	public void setPulse(int pulse) {
		this.pulse = pulse;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	


}
