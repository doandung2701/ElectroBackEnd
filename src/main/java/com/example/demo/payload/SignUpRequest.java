package com.example.demo.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignUpRequest {

	@NotBlank
	@Size(min=1)
	public String firstName;
	
	@NotBlank
	@Size(min=1)
	public String lastName;
	
	@NotBlank
	@Email
	public String email;
	
	@NotBlank
	@Size(min=6)
	public String password;
	
	@NotBlank
	@Size(min=4,max=15)
	public String username;

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	public SignUpRequest(@NotBlank @Size(min = 1, max = 4) String firstName,
			@NotBlank @Size(min = 1, max = 4) String lastName, @NotBlank @Email String email,
			@NotBlank @Size(min = 6, max = 20) String password, @NotBlank @Size(min = 4, max = 15) String username) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.username = username;
	}

	public SignUpRequest() {
		super();
	}
	
	
	
}
