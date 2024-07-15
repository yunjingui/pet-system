package com.three.server.mapper;

import com.three.server.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserMapper {
    int insertUser(User user);
    int deleteUserById(Integer id);
    int updateUser(User user);
    List<User> selectAllUsers();
    User selectUserByUsername(String username);
}
