package com.three.server.service;

import com.three.server.entity.Cart;

import java.util.List;

public interface CartService {
    Boolean add(Cart cart);
    Boolean deleteByCustomerAndGoodAndSize(Integer customer, Integer good, Integer size);
    Boolean deleteByCustomer(Integer customer);
    List<Cart> selectByCustomer(Integer customer);
}
