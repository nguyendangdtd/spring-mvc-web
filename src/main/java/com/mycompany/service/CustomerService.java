/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.dao.CustomerDaoIF;
import com.mycompany.entity.Customer;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author XV
 */
@Service
public class CustomerService implements CustomerServiceIF{

    @Autowired
    CustomerDaoIF customerDaoIF; 
    
    @Override
    public List<Customer> getCustomers() {
	return (List)customerDaoIF.findAll();
    }

    @Override
    public void saveCustomer(Customer theCustomer) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Set<ConstraintViolation<Customer>> violations = factory.getValidator().validate(theCustomer);
        if (violations.isEmpty()) {
            customerDaoIF.save(theCustomer);
        } else {
            System.out.println("validate wrong, do not execute database script");
        }
    }

    @Override
    public Customer getCustomer(int theId) {
	Optional<Customer> customerOpt = customerDaoIF.findById(theId);
	return customerOpt.isPresent() ? customerOpt.get() : null;
    }

    @Override
    public void deleteCustomer(int theId) {
	customerDaoIF.deleteById(theId);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerDaoIF.findByEmail(email);
    }
    
}
