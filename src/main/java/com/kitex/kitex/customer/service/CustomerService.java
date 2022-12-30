package com.kitex.kitex.customer.service;

import com.kitex.kitex.customer.entity.Customer;
import com.kitex.kitex.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Component
public class CustomerService {
    private final CustomerRepository customerRepository;

   public Customer findCustomerById(Integer customerId) {
       Optional<Customer> customer =  customerRepository.findById(customerId);
       if(customer.isEmpty()) {
           new ChangeSetPersister.NotFoundException();
       }
       return customer.get();
   }
}
