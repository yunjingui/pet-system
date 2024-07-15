package com.three.server.service.impl;

import com.three.server.entity.User;
import com.three.server.mapper.UserMapper;
import com.three.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public Boolean register(User user) {
        return userMapper.insertUser(user) > 0;
    }

    @Override
    public Boolean login(String username, String password) {
        User user = userMapper.selectUserByUsername(username);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }

    @Override
    public Boolean deleteUserById(Integer id) {
        return userMapper.deleteUserById(id) > 0;
    }

    @Override
    public Boolean changePassword(String username, String password) {
        User user = userMapper.selectUserByUsername(username);
        if (user == null) {
            return false;
        }
        user.setPassword(password);
        return userMapper.updateUser(user) > 0;
    }

    @Override
    public List<User> findAllUsers() {
        return userMapper.selectAllUsers();
    }

    @Override
    public User findUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

}
