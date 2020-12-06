/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.entity.Customer;
import com.mycompany.service.CustomerServiceIF;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author XV
 */
@Controller
@RequestMapping("/admin/customer")
public class AdminCustomerController {

    @Autowired
    private CustomerServiceIF customerService;

    @GetMapping(value = "/list")
    public String listCustomers(HttpServletRequest request, Model theModel) {
	List< Customer> customers = customerService.getCustomers();
        PagedListHolder pagedListHolder = new PagedListHolder(customers);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(5);
		theModel.addAttribute("pagedListHolder", pagedListHolder);
                
	return "list-customer";
    }
}
