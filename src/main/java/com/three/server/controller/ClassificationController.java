package com.three.server.controller;

import com.three.server.common.responseBodyCustom.ApiResponse;
import com.three.server.entity.Classification;
import com.three.server.entity.Good;
import com.three.server.entity.SecondClassification;
import com.three.server.service.ClassificationService;
import com.three.server.service.GoodService;
import com.three.server.service.SecondClassificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/classification")
@RestController
@RequiredArgsConstructor
public class ClassificationController {

    private final ClassificationService classificationService;
    private final SecondClassificationService secondClassificationService;
    private final GoodService goodService;

    @PostMapping("/add")
    public ApiResponse<Void> addClassification(@RequestBody Classification classification) {
        return classificationService.addClassification(classification) ? ApiResponse.success() : ApiResponse.fail(500, "添加失败");
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deleteClassification(@PathVariable Integer id) {
        return classificationService.deleteClassification(id) ? ApiResponse.success() : ApiResponse.fail(500, "删除失败");
    }

    @PutMapping("/update")
    public ApiResponse<Void> updateClassification(@RequestBody Classification classification) {
        return classificationService.updateClassification(classification) ? ApiResponse.success() : ApiResponse.fail(500, "更新失败");
    }

    @GetMapping("/findAll")
    public ApiResponse<List<Object>> findAll() {
        List<Classification> classifications = classificationService.findAll();
        if (classifications.isEmpty()) {
            return ApiResponse.fail(500, "没有分类");
        }
        List<Object> result = new ArrayList<>();
        for (Classification classification : classifications) {
            List<SecondClassification> secondClassifications = secondClassificationService.findByFatherClassification(classification.getId());
            result.add(new Object() {
                public Integer id = classification.getId();
                public String name = classification.getName();
                public String description = classification.getDescription();
                public List<SecondClassification> secondClassification = secondClassifications;
            });
        }
        return ApiResponse.success(result);
    }

    @GetMapping("findByName/{name}")
    public ApiResponse<List<Classification>> findByName(@PathVariable String name) {
        return ApiResponse.success(classificationService.findByName(name));
    }

    @GetMapping("findById/{id}")
    public ApiResponse<Classification> findById(@PathVariable Integer id) {
        return ApiResponse.success(classificationService.findById(id));
    }

    @GetMapping("findGoodByClassificationId/{id}")
    public ApiResponse<List<Object>> findGoodByClassificationId(@PathVariable Integer id) {
        List<SecondClassification> secondClassifications = secondClassificationService.findByFatherClassification(id);
        List<Object> result = new ArrayList<>();
        for (SecondClassification secondClassificationT : secondClassifications) {
            List<Good> goods = goodService.findGoodsBySecondClassification(secondClassificationT.getId());
            for (Good goodT : goods) {
                result.add(goodT);
            }
        }
        if (result.isEmpty()) {
            return ApiResponse.fail(500, "没有商品");
        }
        return ApiResponse.success(result);
    }


}
