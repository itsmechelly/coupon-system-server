package com.couponsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.couponsystem.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findCustomerById(int id);

    Customer findCustomerByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);
}
