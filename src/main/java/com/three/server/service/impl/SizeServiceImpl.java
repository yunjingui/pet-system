package com.three.server.service.impl;

import com.three.server.entity.Size;
import com.three.server.mapper.SizeMapper;
import com.three.server.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {
    private final SizeMapper sizeMapper;

    @Override
    public Boolean addSize(Size size) {
        return sizeMapper.insert(size) > 0;
    }

    @Override
    public Boolean deleteSizeById(Integer id) {
        return sizeMapper.deleteById(id) > 0;
    }

    @Override
    public Boolean deleteSizeByGoodId(Integer goodId) {
        return sizeMapper.deleteByGoodId(goodId) > 0;
    }

    @Override
    public Boolean updateSize(Size size) {
        return sizeMapper.update(size) > 0;
    }

    @Override
    public List<Size> findAllSize() {
        return sizeMapper.selectAll();
    }

    @Override
    public Size findSizeById(Integer id) {
        return sizeMapper.selectById(id);
    }

    @Override
    public List<Size> findSizeByGoodId(Integer goodId) {
        return sizeMapper.selectByGoodId(goodId);
    }
}
