/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.mycompany.entity.Customer;

/**
 *
 * @author XV
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { CustomerServiceTest.TestConfiguration.class }) // cho ni la doc configuration base class
public class CustomerServiceTest {

	@Autowired
	CustomerServiceIF customerService;

	@Test
	public void saveCustomerCheck() {
		Customer customer = new Customer();
		customer.setEmail("nguyendang@gmail.com");
		customer.setFirstName("Anh");
		customer.setLastName("Dang");

		int numberOfRowsBefore = customerService.getCustomers().size();
		customerService.saveCustomer(customer);
		int numberOfRowsAfter = customerService.getCustomers().size();
		Assert.assertEquals(numberOfRowsAfter, numberOfRowsBefore + 1);

	}


	/*
	Đây là configuration base class
		+ Có thể tạo bean trực tiếp trong ni
		+ Hoặc chỉ cho nó biết chỗ đang chưa POJOs để nó tự tạo packgae scan
		+ Hoặc cho load file xml để nó đọc xml tạo beans cũng được luôn
		+ Cuối cùng khi chạy unit test thì load cái class này lên
		+ Có beans tha hồ autowire mà dùng thôi
	 */
	@Configuration
	@ComponentScan(basePackages = "com.mycompany.service")
	@ImportResource("classpath:jpa-config.xml")
	static class TestConfiguration {
	}
}
