package com.three.server.service.impl;

import com.three.server.entity.SecondClassification;
import com.three.server.mapper.SecondClassificationMapper;
import com.three.server.service.SecondClassificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SecondClassificationServiceImpl implements SecondClassificationService {

    private final SecondClassificationMapper secondClassificationMapper;

    @Override
    public Boolean add(SecondClassification secondClassification) {
        return secondClassificationMapper.insert(secondClassification) > 0;
    }

    @Override
    public Boolean deleteById(Integer id) {
        return secondClassificationMapper.deleteById(id) > 0;
    }

    @Override
    public Boolean update(SecondClassification secondClassification) {
        return secondClassificationMapper.update(secondClassification) > 0;
    }

    @Override
    public List<SecondClassification> findAll() {
        return secondClassificationMapper.selectAll();
    }

    @Override
    public SecondClassification findById(Integer id) {
        return secondClassificationMapper.selectById(id);
    }

    @Override
    public List<SecondClassification> findByFatherClassification(Integer fatherClassification) {
        return secondClassificationMapper.selectByFatherClassification(fatherClassification);
    }

    @Override
    public List<SecondClassification> findByName(String name) {
        return secondClassificationMapper.selectByName(name);
    }
}
