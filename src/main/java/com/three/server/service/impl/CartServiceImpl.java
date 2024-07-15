package com.three.server.service.impl;

import com.three.server.entity.Cart;
import com.three.server.mapper.CartMapper;
import com.three.server.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartMapper cartMapper;
    @Override
    public Boolean add(Cart cart) {
        return cartMapper.insert(cart) > 0;
    }

    @Override
    public Boolean deleteByCustomerAndGoodAndSize(Integer customer, Integer good, Integer size) {
        return cartMapper.deleteByCustomerAndGoodAndSize(new Cart(customer, good, size)) > 0;
    }

    @Override
    public Boolean deleteByCustomer(Integer customer) {
        return cartMapper.deleteByCustomer(customer) > 0;
    }

    @Override
    public List<Cart> selectByCustomer(Integer customer) {
        return cartMapper.selectByCustomer(customer);
    }
}
