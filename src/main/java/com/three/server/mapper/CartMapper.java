package com.three.server.mapper;

import com.three.server.entity.Cart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    int insert(Cart cart);
    int deleteByCustomerAndGoodAndSize(Cart cart);
    int deleteByCustomer(Integer customer);
    List<Cart> selectByCustomer(Integer customer);
}
