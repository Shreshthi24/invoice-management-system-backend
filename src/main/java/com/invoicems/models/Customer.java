package com.invoicems.models;


import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customer_id;
  
    @Column(unique = true)
    private String email;

    private String firstName;
    private String lastName;
    private String password;
    private String gstnNo;
    private String customerLevel;
    private LocalDate createdOn;
    private boolean verified;
    private String verificationOtp;
 
    private String passwordResetToken;

       
	public String getPasswordResetToken() {
		return passwordResetToken;
	}
	public void setPasswordResetToken(String passwordResetToken) {
		this.passwordResetToken = passwordResetToken;
	}
	public Long getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Long customer_id) {
		customer_id = customer_id;
	}
	public String getEmail() {
		return email;
	} 
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGstnNo() {
		return gstnNo;
	}
	public void setGstnNo(String gstnNo) {
		this.gstnNo = gstnNo;
	}
	public String getCustomerLevel() {
		return customerLevel;
	}
	public void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}
	public LocalDate getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public String getVerificationOtp() {
		return verificationOtp;
	}
	public void setVerificationOtp(String verificationOtp) {
		this.verificationOtp = verificationOtp;
	}
   
    
}
