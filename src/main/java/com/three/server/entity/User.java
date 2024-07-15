package com.three.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String username; // 暂定邮箱
    private String password;
    private Integer type; // admin(0)，seller(1)，customer(2)
    private Integer isDeleted; // 0正常，1被删除

    public User(String username, String password, Integer type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }
}
