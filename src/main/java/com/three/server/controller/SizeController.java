package com.three.server.controller;

import com.three.server.common.responseBodyCustom.ApiResponse;
import com.three.server.entity.Size;
import com.three.server.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/size")
@RequiredArgsConstructor
public class SizeController {
    private final SizeService sizeService;
    @PostMapping("/add")
    public ApiResponse<Void> addSize(@RequestBody Size size) {
        return sizeService.addSize(size) ? ApiResponse.success() : ApiResponse.fail(500, "添加规格失败");
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deleteSize(@PathVariable Integer id) {
        return sizeService.deleteSizeById(id) ? ApiResponse.success() : ApiResponse.fail(500, "删除规格失败");
    }

    @PutMapping("/update")
    public ApiResponse<Void> updateSize(@RequestBody Size size) {
        return sizeService.updateSize(size) ? ApiResponse.success() : ApiResponse.fail(500, "更新规格失败");
    }

    @GetMapping("/findAll")
    public ApiResponse<List<Size>> findAllSizes() {
        return ApiResponse.success(sizeService.findAllSize());
    }

    @GetMapping("/findById/{id}")
    public ApiResponse<Size> findSizeById(@PathVariable Integer id) {
        return ApiResponse.success(sizeService.findSizeById(id));
    }

    @GetMapping("/findByGoodId/{goodId}")
    public ApiResponse<List<Size>> findSizeByGoodId(@PathVariable Integer goodId) {
        return ApiResponse.success(sizeService.findSizeByGoodId(goodId));
    }


}
