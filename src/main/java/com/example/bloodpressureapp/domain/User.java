package com.example.bloodpressureapp.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="usertable")
public class User {

	//database stuff:
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userid", nullable=false, updatable=false)
	private Long userid;
	
	@Column(name= "username", nullable=false, updatable=false)
	private String username;
	
	@Column(name = "passwordHash", nullable = false)
    private String passwordHash;
	
	@Column(name = "email", nullable = false)
    private String email;

	@Column(name = "role", nullable = false)
    private String role;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Bloodpressure> pressures;

	public User() {
	}

	public User(String username, String passwordHash, String email, String role) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.email = email;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Bloodpressure> getPressures() {
		return pressures;
	}

	public void setPressures(List<Bloodpressure> pressures) {
		this.pressures = pressures;
	}	

}
