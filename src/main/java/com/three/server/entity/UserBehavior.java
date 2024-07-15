package com.three.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBehavior {
    private Integer id;
    private Integer userId;
    private Integer goodId;
    private String behaviorType;
    private Timestamp time;

    public UserBehavior(Integer customer, Integer good, String behaviorType) {
        this.userId = customer;
        this.goodId = good;
        this.behaviorType = behaviorType;
    }
}