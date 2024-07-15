package com.three.server.service;

import com.three.server.entity.Size;

import java.util.List;

public interface SizeService {
    Boolean addSize(Size size);
    Boolean deleteSizeById(Integer id);
    Boolean deleteSizeByGoodId(Integer goodId);
    Boolean updateSize(Size size);
    List<Size> findAllSize();
    Size findSizeById(Integer id);
    List<Size> findSizeByGoodId(Integer goodId);
}
