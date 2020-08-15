/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.entity.Customer;
import java.util.List;

/**
 *
 * @author XV
 */
public interface CustomerServiceIF {
    public List <Customer> getCustomers();
    public void saveCustomer(Customer theCustomer);
    public Customer getCustomer(int theId);
    public Customer getCustomerByEmail(String email);
    public void deleteCustomer(int theId);
}
