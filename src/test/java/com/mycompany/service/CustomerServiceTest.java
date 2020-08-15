/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.entity.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 *
 * @author XV
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/jpa-config.xml"})
public class CustomerServiceTest {

    @Autowired
    CustomerServiceIF customerService;

    @Test
    public void validateWrongMaxLengthSupport() {
        Customer customer = new Customer();
        customer.setEmail("nguyendang@gmail.com");
        customer.setFirstName("AnhNguyen");
        customer.setLastName("Dang");

        int numberOfRowsBefore = customerService.getCustomers().size();
        customerService.saveCustomer(customer);
        int numberOfRowsAfter = customerService.getCustomers().size();
        Assert.assertEquals(numberOfRowsBefore, numberOfRowsAfter);
    }

    @Test
    public void validateRightMaxLengthSupport() {
        Customer customer = new Customer();
        customer.setEmail("nguyendang@gmail.com");
        customer.setFirstName("Anh");
        customer.setLastName("Dang");

        int numberOfRowsBefore = customerService.getCustomers().size();
        customerService.saveCustomer(customer);
        int numberOfRowsAfter = customerService.getCustomers().size();
        Assert.assertEquals(numberOfRowsAfter, numberOfRowsBefore + 1);

    }


    /*@Configuration
    @ComponentScan("com.mycompany.*")
    static class ContextConfiguration {

    }*/
}
