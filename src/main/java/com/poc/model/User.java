package com.poc.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Where;

import lombok.Data;

@Entity
@Table(name = "poc_user")
@Where(clause = "deleted=false")
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private long id;
	
	@NotEmpty(message = "Please enter first name")
	@Size(min = 1, message = "Please enter a valid first name")
	@Column(name = "name")
	private String name;
	
	@NotEmpty(message = "Please enter surname")
	@Size(min = 1, message = "Please enter a valid surname")
	@Column(name = "surname")
	private String surname;
	
	@NotEmpty(message = "Please enter email")
	@Email(message = "Please enter a valid email")
	@Column(name = "email")
	private String email;
	
	@NotEmpty(message = "Please enter city")
	@Size(min = 2, message = "Please enter a valid city name")
	@Column(name = "city")
	private String city;
	
	@NotEmpty(message = "Please enter pincode")
	@Pattern(regexp = "^[0-9]{6}", message = "Please enter valid pincode of 6 digits")
	@Column(name = "pincode")
	private String pincode;
	
	@NotNull(message = "Please enter D.O.B in \'yyyy-MM-dd format\'")
	@Column(name = "dob")
	private Date dob;
	
	@NotNull(message = "Please enter joining date in \'yyyy-MM-dd format\'")
	@Column(name="joindate")
	private Date joindate;
	
	@Column(name="deleted")
	private boolean deleted = Boolean.FALSE;

}
