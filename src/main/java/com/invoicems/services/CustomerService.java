package com.invoicems.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.invoicems.models.Customer;
import com.invoicems.repositories.CustomerRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmailService emailService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Customer registerCustomer(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setCreatedOn(LocalDate.now());
        customer.setVerified(false);

        
        customer.setVerificationOtp(emailService.generateOTP()); 
        customerRepository.save(customer);

        
        emailService.sendVerificationEmail(customer);

        return customer;
    }

    public boolean verifyCustomer(String otp) {
        Optional<Customer> customer = customerRepository.findByVerificationOtp(otp);
        if (customer.isPresent()) {
            customer.get().setVerified(true);
            customerRepository.save(customer.get());
            return true;
        }
        return false;
    }

    public Optional<Customer> login(String email, String password) {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        return customer.filter(value -> passwordEncoder.matches(password, value.getPassword()) && value.isVerified());
    }
    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
    public void updateCustomerVerification(Customer customer) {
        customer.setVerified(true);
        customerRepository.save(customer); 
    }
    //--------------------------------------------------------
    public String generatePasswordResetToken(Customer customer) {
       
        String token = UUID.randomUUID().toString();
        customer.setPasswordResetToken(token);
        customerRepository.save(customer); 
        return token;
    }

    public Optional<Customer> findByPasswordResetToken(String token) {
        return customerRepository.findByPasswordResetToken(token);
    }

    
    public void updateCustomerPassword(Customer customer) {
     
        String hashedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(hashedPassword); 
        customerRepository.save(customer);  
    }

}
