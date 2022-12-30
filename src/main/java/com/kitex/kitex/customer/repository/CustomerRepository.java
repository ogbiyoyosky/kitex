package com.kitex.kitex.customer.repository;

import com.kitex.kitex.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> { }
