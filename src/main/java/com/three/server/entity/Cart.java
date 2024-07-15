package com.three.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private Integer customer; // customer id
    private Integer good; // good id
    private Integer size; // size id
    private Integer quantity;

    public Cart(Integer customer, Integer good, Integer size) {
        this.customer = customer;
        this.good = good;
        this.size = size;
    }
}
