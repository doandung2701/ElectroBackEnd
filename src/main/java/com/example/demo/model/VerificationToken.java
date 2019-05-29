package com.example.demo.model;

import java.util.Date;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="verificationtoken")
public class VerificationToken {

	public static final int EXPTIME = 86400000; 
	
	@Column(name="token_id")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long tokenId;
	
	@Column(name="token")
	private String token;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="token_ex_date")
	private Date tokenExDate;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	/**
	 * @return the tokenId
	 */
	public Long getTokenId() {
		return tokenId;
	}

	/**
	 * @param tokenId the tokenId to set
	 */
	public void setTokenId(Long tokenId) {
		this.tokenId = tokenId;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the tokenExDate
	 */
	public Date getTokenExDate() {
		return tokenExDate;
	}

	/**
	 * @param tokenExDate the tokenExDate to set
	 */
	public void setTokenExDate(Date tokenExDate) {
		this.tokenExDate = tokenExDate;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	public VerificationToken(String token, Date tokenExDate, User user) {
		super();
		this.token = token;
		this.tokenExDate = tokenExDate;
		this.user = user;
	}

	public VerificationToken() {
		super();
	}
	
	public VerificationToken(User user) {
		this.user = user;
		this.tokenExDate = new Date(System.currentTimeMillis()+EXPTIME);
		this.token = UUID.randomUUID().toString();
	}
	
}
