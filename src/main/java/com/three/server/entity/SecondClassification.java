package com.three.server.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecondClassification {
    private Integer id;
    private String name;
    private String description;
    private Integer fatherClassification;
    private Integer isDeleted;
}
