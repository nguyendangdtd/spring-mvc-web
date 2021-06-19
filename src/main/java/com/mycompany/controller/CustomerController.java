/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.entity.Customer;
import com.mycompany.service.CustomerServiceIF;

/**
 *
 * @author XV
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerServiceIF customerService;

	@GetMapping(value = "/list")
	public String listCustomers(@RequestParam(name = "p", defaultValue = "0") int page
		, Model model) {
		List<Customer> customers = customerService.getCustomers();
		PagedListHolder pagedListHolder = new PagedListHolder(customers);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(3);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "list-customer";
	}

	@GetMapping("/showForm")
	public String showFormForAdd(Model model) {
		Customer theCustomer = new Customer();
		model.addAttribute("customer", theCustomer);
		return "customer-form";
	}

	@RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
	@PostMapping(value = "/saveCustomer")
	public String saveCustomer(@Valid Customer customer, BindingResult result, Model theModel) {
		if (result.hasErrors()) {
			theModel.addAttribute("customer", customer);
			return "customer-form";
		}
		customerService.saveCustomer(customer);
		return "redirect:/customer/list";
	}

	@GetMapping("/updateForm")
	public String showFormForUpdate(@RequestParam("customerId") int theId,
		Model theModel) {
		Customer theCustomer = customerService.getCustomer(theId);
		theModel.addAttribute("customer", theCustomer);
		return "customer-form";
	}

	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		customerService.deleteCustomer(theId);
		return "redirect:/customer/list";
	}
}
