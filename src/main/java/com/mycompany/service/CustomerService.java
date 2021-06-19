/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.dao.CustomerRepository;
import com.mycompany.entity.Customer;

/**
 *
 * @author XV
 */
@Service
public class CustomerService implements CustomerServiceIF {

	//@Autowired
	//private PasswordEncoder passwordEncoder;

	@Autowired
	CustomerRepository customerDaoIF;

	@Override
	public List<Customer> getCustomers() {
		return (List) customerDaoIF.findAll();
	}

	@Override
	public void saveCustomer(@Valid Customer theCustomer) {
		customerDaoIF.save(theCustomer);
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
