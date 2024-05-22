package com.scm.demo.service;

import java.util.List;

import com.scm.demo.Entities.User;

public interface UserService {

    User saveUser(User user);

    User getUserById(Integer id);

    User getUserByEmail(String email);

    User updateUser(User user);

    void deleteUser(Integer id);

    Boolean userExists(Integer id);

    Boolean userExists(String email);

    List<User> getAllUsers();
}
