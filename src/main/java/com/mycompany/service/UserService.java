package com.mycompany.service;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.dao.RoleRepository;
import com.mycompany.dao.UseRepository;
import com.mycompany.entity.Role;
import com.mycompany.entity.User;

@Service
public class UserService {

	@Autowired
	UseRepository useRepository;

	@Autowired
	RoleRepository roleRepository;

	@PostConstruct
	public void insertDefaultUserInfo() {

		try {
			Role adminRole = new Role();
			adminRole.setName("ROLE_ADMIN");

			Role userRole = new Role();
			userRole.setName("ROLE_USER");

			roleRepository.saveAll(Arrays.asList(adminRole, userRole));

			User admin = new User();
			admin.setEnabled(true);
			admin.setUsername("admin");
			admin.setPassword("admin");
			admin.setRoles(Arrays.asList(adminRole, userRole));

			User user = new User();
			user.setEnabled(true);
			user.setUsername("nguyen");
			user.setPassword("nguyen");
			user.setRoles(Arrays.asList(userRole));

			useRepository.saveAll(Arrays.asList(admin, user));
		} catch (Exception e) {
			// have error then log
		}

	}

}
