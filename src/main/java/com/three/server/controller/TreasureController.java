package com.three.server.controller;

import com.three.server.common.responseBodyCustom.ApiResponse;
import com.three.server.entity.Good;
import com.three.server.entity.Treasure;
import com.three.server.service.GoodService;
import com.three.server.service.TreasureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/treasure")
public class TreasureController {
    private final TreasureService treasureService;
    private final GoodService goodService;

    @PostMapping("/add")
    public ApiResponse<Void> addTreasure(@RequestBody Treasure treasure) {
        return treasureService.add(treasure) ? ApiResponse.success() : ApiResponse.fail(500, "添加宝藏失败");
    }

    @DeleteMapping("/delete/{customerId}/{goodId}")
    public ApiResponse<Void> deleteTreasureByCustomerAndGood(@PathVariable Integer customerId, @PathVariable Integer goodId) {
        return treasureService.deleteByCustomerAndGood(customerId, goodId) ? ApiResponse.success() : ApiResponse.fail(500, "删除宝藏失败");
    }

    public List<Object> responseList(List<Treasure> treasures) {
        if (Objects.isNull(treasures)) {
            return null;
        }
        List<Object> responseList = new ArrayList<>();
        for (Treasure treasure : treasures) {
            responseList.add(new Object() {
                public Integer customerId = treasure.getCustomer();
                public Good good = goodService.findGoodById(treasure.getGood());
                public String createdAt = treasure.getCreatedAt().toString();
            });
        }
        return responseList;
    }

    @GetMapping("/findAll/{customerId}")
    public ApiResponse<List<Object>> findAllTreasureByCustomer(@PathVariable Integer customerId) {
        List<Treasure> treasures = treasureService.findAllByCustomer(customerId);
        if (Objects.isNull(treasures)) {
            return ApiResponse.fail(500, "查询收藏失败");
        }
        return ApiResponse.success(responseList(treasures));
    }

    @GetMapping("/findAllQuantity/{goodId}")
    public ApiResponse<Integer> findAllQuantityByGood(@PathVariable Integer goodId) {
        return ApiResponse.success(treasureService.findAllQuantityByGood(goodId));
    }

    @GetMapping("/findIfCollected/{customerId}/{goodId}")
    public ApiResponse<Integer> findIfCollected(@PathVariable Integer customerId, @PathVariable Integer goodId) {
        return ApiResponse.success(treasureService.findIfCollected(customerId, goodId));
    }


}
