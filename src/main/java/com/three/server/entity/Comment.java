package com.three.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Integer id;
    private Integer customer; // 外键
    private Integer order; // 外键
    private Integer good; // 外键
    private Integer level;
    private String description;
    private Integer isDeleted;
    private String createdAt;
}
