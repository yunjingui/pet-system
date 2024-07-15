package com.three.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Good {
    private Integer id;
    private String name;
    private Integer secondClassification; // 外键
    private Double price;
    private Integer quantity;
    private String description;
    private String avatar; // 图片地址
    private Double level; // 评分
    private Integer flow; // 流量
//    private String size;
    private Integer seller; // 外键
    private Integer isDeleted;
    private Timestamp createdAt;

    private Integer sale; // 销量
    private Integer activity; // 促销活动
    private Double discount; // 折扣
}
