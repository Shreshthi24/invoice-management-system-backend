package com.invoicems.repositories;




import org.springframework.data.jpa.repository.JpaRepository;

import com.invoicems.models.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByVerificationOtp(String code);
    Optional<Customer> findByEmailAndPassword(String email, String password);

    Optional<Customer> findByPasswordResetToken(String token);

    boolean existsByVerificationOtp(String otp);
    
    
}
