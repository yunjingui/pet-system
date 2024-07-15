package com.three.server.service;

import com.three.server.entity.User;

import java.util.List;

public interface UserService {
    Boolean register(User user);
    Boolean login(String username, String password);
    Boolean deleteUserById(Integer id);
    Boolean changePassword(String username, String password);
    List<User> findAllUsers();
    User findUserByUsername(String username);

}
