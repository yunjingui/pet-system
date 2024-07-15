package com.three.server.controller;

import com.three.server.common.responseBodyCustom.ApiResponse;
import com.three.server.common.uploadAndGenerateUrl.AliyunOssService;
import com.three.server.entity.Classification;
import com.three.server.entity.Good;
import com.three.server.entity.SecondClassification;
import com.three.server.entity.Size;
import com.three.server.service.ClassificationService;
import com.three.server.service.GoodService;
import com.three.server.service.SecondClassificationService;
import com.three.server.service.SizeService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/good")
@RequiredArgsConstructor
public class GoodController {

    private final GoodService goodService;
    private final AliyunOssService aliyunOssService;
    private final SecondClassificationService secondClassificationService;
    private final ClassificationService classificationService;
    private final SizeService sizeService;

    @PostMapping("/add")
    public ApiResponse<Integer> addGood(@RequestBody Good good) {
        System.out.println(good);
        Integer returnId = goodService.addGood(good);
        System.out.println(returnId);
        return returnId != null ? ApiResponse.success(returnId) : ApiResponse.fail(500, "添加失败");
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deleteGoodById(@PathVariable Integer id) {
        return goodService.deleteGoodById(id) ? ApiResponse.success() : ApiResponse.fail(500, "删除失败");
    }

    @PutMapping("/update")
    public ApiResponse<Void> updateGood(@RequestBody Good good) {
        return goodService.updateGood(good) ? ApiResponse.success() : ApiResponse.fail(500, "更新失败");
    }

    @GetMapping("/findAll")
    public ApiResponse<List<Object>> findAllGoods() {
        List<Good> goods = goodService.findAllGoods();
        return getListApiResponse(goods);
    }

    @GetMapping("/findById/{id}")
    public ApiResponse<Object> findGoodById(@PathVariable Integer id) {
        Good good = goodService.findGoodById(id);
        if (good == null) {
            return ApiResponse.fail(500, "没有商品");
        }
        SecondClassification secondClassificationH = secondClassificationService.findById(good.getSecondClassification());
        if (secondClassificationH == null) {
            return ApiResponse.fail(500, "商品信息错误");
        }
        Classification classification = classificationService.findById(secondClassificationH.getFatherClassification());
        if (classification == null) {
            return ApiResponse.fail(500, "商品信息错误");
        }
        List<Size> sizes = sizeService.findSizeByGoodId(good.getId());
        Object resultGood = new Object() {

            public Integer id = good.getId();
            public String name = good.getName();
            public Double price = good.getPrice();
            public Integer quantity = good.getQuantity();
            public String description = good.getDescription();
            public String avatar = good.getAvatar();
            public Double level = good.getLevel();
            public Integer flow = good.getFlow();
            public Integer seller = good.getSeller();
            public Integer isDeleted = good.getIsDeleted();
            public Timestamp createdAt = good.getCreatedAt();
            public Integer sale = good.getSale();
            public Integer activity = good.getActivity();
            public Double discount = good.getDiscount();
            public List<Size> size = sizes;

            public Integer firstClassificationId = classification.getId();
            public String firstClassification = classification.getName();
            public String firstClassificationDescription = classification.getDescription();
            public Integer secondClassificationId = secondClassificationH.getId();
            public String secondClassification = secondClassificationH.getName();
            public String secondClassificationDescription = secondClassificationH.getDescription();
        };
        System.out.println(resultGood);
        return ApiResponse.success(resultGood);
    }

    @GetMapping("/findByName/{name}")
    public ApiResponse<List<Object>> findGoodsByName(@PathVariable String name) {
        List<Good> goods = goodService.findGoodsByName(name);
        return getListApiResponse(goods);
    }

    private ApiResponse<List<Object>> getListApiResponse(List<Good> goods) {
        if (goods.isEmpty()) {
            return ApiResponse.fail(500, "没有商品");
        }
        List<Object> resultGoods = goodsToObjects(goods);
        if (resultGoods == null) {
            return ApiResponse.fail(500, "商品信息错误");
        }
        return ApiResponse.success(resultGoods);
    }

    @GetMapping("/findBySellerId/{sellerId}")
    public ApiResponse<List<Object>> findGoodsBySellerId(@PathVariable Integer sellerId) {
        List<Good> goods = goodService.findGoodsBySellerId(sellerId);
        return getListApiResponse(goods);
    }

    @PostMapping("/filter")
    public ApiResponse<List<Object>> findGoodsByNameAndClassificationAndLowQuantityAndHighQuantity(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        String name;
        Integer secondClassification,lowest, highest;
        try{
            name = (map.get("name") != "" && map.get("name") != null) ? (String) map.get("name") : null;
            secondClassification = (map.get("secondClassification") != "" && map.get("secondClassification") != null) ? (Integer) map.get("secondClassification") : null;
            lowest = (map.get("lowest") != "" && map.get("lowest") != null) ? (Integer) map.get("lowest") : null;
            highest = (map.get("highest") != "" && map.get("highest") != null) ? (Integer) map.get("highest") : null;
        }catch (Exception e){
            return ApiResponse.fail(500, "参数错误");
        }
        List<Good> goods = goodService.findGoodsByNameAndClassificationAndLowQuantityAndHighQuantity(name, secondClassification, lowest, highest);
        if (goods.isEmpty())
            return ApiResponse.fail(500, "没有商品");
        return getListApiResponse(goods);
    }

    @GetMapping("/rank/{id}")
    public ApiResponse<Double> rankForGood(@PathVariable Integer id) {
        return ApiResponse.success(goodService.rankForGood(id));
    }

    @PutMapping("/goodSalePlus/{id}")
    public ApiResponse<Void> plusGoodSale(@PathVariable Integer id) {
        return goodService.plusGoodSale(id) ? ApiResponse.success() : ApiResponse.fail(500, "增加销量失败");
    }

    @PutMapping("/goodFlowPlus/{id}")
    public ApiResponse<Void> plusGoodFlow(@PathVariable Integer id) {
        return goodService.plusGoodFlow(id) ? ApiResponse.success() : ApiResponse.fail(500, "增加流量失败");
    }

    @GetMapping("/goodIsDiscount/{id}")
    public ApiResponse<Double> isDiscount(@PathVariable Integer id) {
        return ApiResponse.success(goodService.isDiscount(id));
    }

    @PostMapping("/uploadAvatar")
    public ApiResponse<String> uploadAvatar(MultipartFile file) {
        String url = aliyunOssService.start(file);
        System.out.println("图片在这"+url);
        if (url != null) {
//            if(goodService.uploadAvatar((Integer) map.get("id"), url))
                return ApiResponse.success(url);
        }
        return ApiResponse.fail(500, "上传失败，请重试");
    }

    @PostMapping("/image/add")
    public ApiResponse<String> addGoodImage(@RequestBody MultipartFile file) {
        String url = aliyunOssService.start(file);
        if (url != null) {
            return ApiResponse.success(url);
        }
        return ApiResponse.fail(500, "上传失败，请重试");
    }

    @PostMapping("/image/add/{id}/{url}")
    public ApiResponse<Void> addGoodImage(@PathVariable Integer id, @PathVariable String url) {
        return goodService.addGoodImage(id, url) ? ApiResponse.success() : ApiResponse.fail(500, "添加失败");
    }

    @DeleteMapping("/image/delete/{id}")
    public ApiResponse<Void> deleteGoodImage(@PathVariable Integer id) {
        return goodService.deleteGoodImage(id) ? ApiResponse.success() : ApiResponse.fail(500, "删除失败");
    }

    @GetMapping("/image/goodImageDisplay/{id}")
    public ApiResponse<String> goodImageDisplay(@PathVariable Integer id) {
        return ApiResponse.success(goodService.goodImageDisplay(id));
    }

    @GetMapping("/image/findByGoodId/{id}")
    public ApiResponse<List<String>> goodImageDisplayByGoodId(@PathVariable Integer id) {
        return ApiResponse.success(goodService.goodImageDisplayByGoodId(id));
    }

    public List<Object> goodsToObjects(List<Good> goods) {
        List<Object> resultGoods = new ArrayList<>();
        for (Good good : goods) {
            System.out.println(good);
            SecondClassification secondClassificationH = secondClassificationService.findById(good.getSecondClassification());
            if (secondClassificationH == null) {
                return null;
            }
            Classification classification = classificationService.findById(secondClassificationH.getFatherClassification());
            if (classification == null) {
                return null;
            }
            List<Size> sizes = sizeService.findSizeByGoodId(good.getId());
            resultGoods.add(new Object() {
                public Integer id = good.getId();
                public String name = good.getName();
                public Double price = good.getPrice();
                public Integer quantity = good.getQuantity();
                public String description = good.getDescription();
                public String avatar = good.getAvatar();
                public Double level = good.getLevel();
                public Integer flow = good.getFlow();
                public Integer seller = good.getSeller();
                public Integer isDeleted = good.getIsDeleted();
                public Timestamp createdAt = good.getCreatedAt();
                public Integer sale = good.getSale();
                public Integer activity = good.getActivity();
                public Double discount = good.getDiscount();
                public List<Size> size = sizes;

                public Integer firstClassificationId = classification.getId();
                public String firstClassification = classification.getName();
                public String firstClassificationDescription = classification.getDescription();
                public Integer secondClassificationId = secondClassificationH.getId();
                public String secondClassification = secondClassificationH.getName();
                public String secondClassificationDescription = secondClassificationH.getDescription();
            });
        }

        return resultGoods;
    }
}
