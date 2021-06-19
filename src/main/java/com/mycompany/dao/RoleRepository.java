package com.mycompany.dao;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
