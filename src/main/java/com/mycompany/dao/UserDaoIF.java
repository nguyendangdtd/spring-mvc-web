/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.custom.objects.UserInfo;
import com.mycompany.entity.Customer;
import com.mycompany.entity.User;

/**
 *
 * @author XV
 */
@Repository
public interface UserDaoIF extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
