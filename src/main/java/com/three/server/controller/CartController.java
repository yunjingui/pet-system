package com.three.server.controller;

import com.three.server.common.responseBodyCustom.ApiResponse;
import com.three.server.entity.Cart;
import com.three.server.entity.Good;
import com.three.server.entity.Size;
import com.three.server.service.CartService;
import com.three.server.service.GoodService;
import com.three.server.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final GoodService goodService;
    private final SizeService SizeService;
    @PostMapping("/add")
    public ApiResponse<Void> addCart(@RequestBody Cart cart) {
        System.out.println(cart);
        return cartService.add(cart) ? ApiResponse.success() : ApiResponse.fail(500, "添加购物车失败");
    }

    @DeleteMapping("/delete/{customerId}/{goodId}/{sizeId}")
    public ApiResponse<Void> deleteCart(@PathVariable Integer customerId, @PathVariable Integer goodId, @PathVariable Integer sizeId) {
        return cartService.deleteByCustomerAndGoodAndSize(customerId, goodId, sizeId) ? ApiResponse.success() : ApiResponse.fail(500, "删除购物车失败");
    }

    @DeleteMapping("/delete/{customerId}")
    public ApiResponse<Void> deleteCart(@PathVariable Integer customerId) {
        return cartService.deleteByCustomer(customerId) ? ApiResponse.success() : ApiResponse.fail(500, "删除购物车失败");
    }

    @GetMapping("/findAll/{customerId}")
    public ApiResponse<List<Object>> findAllCarts(@PathVariable Integer customerId) {
        List<Cart> carts = cartService.selectByCustomer(customerId);
        if (carts.isEmpty()) {
            return ApiResponse.fail(500, "购物车为空");
        }
        List<Object> result = new ArrayList<>();
        for (Cart cart : carts) {
            Good good1 = goodService.findGoodById(cart.getGood());
            Size size1 = SizeService.findSizeById(cart.getSize());
            result.add(new Object() {
                public Integer customer = customerId;
                public Good good = good1;
                public Size size = size1;
                public Integer quantity = cart.getQuantity();
            });
        }
        if (result.isEmpty()) {
            return ApiResponse.fail(500, "购物车为空");
        }
        return ApiResponse.success(result);
    }
}
