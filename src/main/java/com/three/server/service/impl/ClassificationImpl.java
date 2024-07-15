package com.three.server.service.impl;

import com.three.server.controller.ClassificationController;
import com.three.server.entity.Classification;
import com.three.server.mapper.ClassificationMapper;
import com.three.server.service.ClassificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassificationImpl implements ClassificationService {

    private final ClassificationMapper classificationMapper;

    @Override
    public Boolean addClassification(Classification classification) {
        return classificationMapper.insert(classification) > 0;
    }

    @Override
    public Boolean deleteClassification(Integer id) {
        return classificationMapper.deleteById(id) > 0;
    }

    @Override
    public Boolean updateClassification(Classification classification) {
        return classificationMapper.update(classification) > 0;
    }
    @Override
    public List<Classification> findAll() {
        return classificationMapper.selectAll();
    }

    @Override
    public List<Classification> findByName(String name) {
        return classificationMapper.selectByName(name);
    }

    @Override
    public Classification findById(Integer id) {
        return classificationMapper.selectById(id);
    }
}
