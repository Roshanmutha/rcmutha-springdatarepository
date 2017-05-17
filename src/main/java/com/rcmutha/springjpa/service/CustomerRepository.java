package com.rcmutha.springjpa.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rcmutha.springjpa.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
    
    List<Customer> findByLastNameAndFirstName(String lastname, String firstname);
    
    List<Customer> findByLastNameAndAddress(String lastname, String address);
    
}
