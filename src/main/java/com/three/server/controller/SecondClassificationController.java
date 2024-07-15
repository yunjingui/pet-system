package com.three.server.controller;

import com.three.server.common.responseBodyCustom.ApiResponse;
import com.three.server.entity.SecondClassification;
import com.three.server.service.SecondClassificationService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/secondClassification")
@RestController
@RequiredArgsConstructor
public class SecondClassificationController {
    private final SecondClassificationService secondClassificationService;

    @PostMapping("/add")
    public ApiResponse<Void> addSecondClassification(@RequestBody SecondClassification secondClassification){
        return secondClassificationService.add(secondClassification) ? ApiResponse.success() : ApiResponse.fail(500, "添加失败");
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deleteSecondClassification(@PathVariable Integer id){
        return secondClassificationService.deleteById(id) ? ApiResponse.success() : ApiResponse.fail(500, "删除失败");
    }

    @PutMapping("/update")
    public ApiResponse<Void> updateSecondClassification(@RequestBody SecondClassification secondClassification){
        return secondClassificationService.update(secondClassification) ? ApiResponse.success() : ApiResponse.fail(500, "更新失败");
    }

    @GetMapping("/findAll")
    public ApiResponse<List<SecondClassification>> findAll(){
        List<SecondClassification> secondClassifications = secondClassificationService.findAll();
        if (secondClassifications.isEmpty()){
            return ApiResponse.fail(500, "没有二级分类");
        }
        return ApiResponse.success(secondClassifications);
    }

    @GetMapping("/findById/{id}")
    public ApiResponse<SecondClassification> findById(@PathVariable Integer id){
        SecondClassification secondClassification = secondClassificationService.findById(id);
        if (secondClassification == null){
            return ApiResponse.fail(500, "没有该二级分类");
        }
        return ApiResponse.success(secondClassification);
    }

    @GetMapping("/findByName/{name}")
    public ApiResponse<List<SecondClassification>> findByName(@PathVariable String name){
        List<SecondClassification> secondClassifications = secondClassificationService.findByName(name);
        if (secondClassifications.isEmpty()){
            return ApiResponse.fail(500, "没有该二级分类");
        }
        return ApiResponse.success(secondClassifications);
    }

    @GetMapping("/findByFather/{id}")
    public ApiResponse<List<SecondClassification>> findByFatherClassification(@PathVariable Integer id){
        List<SecondClassification> secondClassifications = secondClassificationService.findByFatherClassification(id);
        if (secondClassifications.isEmpty()){
            return ApiResponse.fail(500, "没有该一级分类");
        }
        return ApiResponse.success(secondClassifications);
    }


}
