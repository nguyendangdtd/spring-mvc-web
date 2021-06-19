/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.dao.CustomerRepository;
import com.mycompany.entity.Customer;
import com.mycompany.service.CustomerServiceIF;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Demo webservice
 *
 * @author XV
 */
@RestController
@RequestMapping("/rest/customer")
public class CustomerRestController {

	@Autowired
	private CustomerServiceIF customerService;

	@Autowired
	private CustomerRepository customerDaoIF;

	@GetMapping()
	public Object listCustomers() {
		return customerDaoIF.findAll();
	}

	@GetMapping(value = "/{id}")
	public Object getCustomer(@PathVariable("id") int id) {
		return customerService.getCustomer(id);
	}

	@PostMapping
	public Object saveCustomer(@RequestBody Customer customer) {
		customerService.saveCustomer(customer);
		return new HashMap().put("message", "success");
	}

	@PutMapping
	public Object updateCustomer(@RequestBody Customer customer) {
		HashMap rt = new HashMap();
		Customer customerRt = customerService.getCustomer(customer.getId());
		if (customerRt != null) {
			customerService.saveCustomer(customer);
			rt.put("message", "success");
		} else {
			rt.put("message", "customer not found");
		}
		return rt;
	}

	@DeleteMapping(value = "/{id}")
	public Object deleteCustomer(@PathVariable("id") int id) {
		HashMap rt = new HashMap();
		Customer customerRt = customerService.getCustomer(id);
		if (customerRt != null) {
			customerService.deleteCustomer(id);
			rt.put("message", "success");
		} else {
			rt.put("message", "customer not found");
		}
		return rt;
	}
}
