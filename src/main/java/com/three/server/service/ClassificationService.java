package com.three.server.service;

import com.three.server.controller.ClassificationController;
import com.three.server.entity.Classification;
import com.three.server.mapper.ClassificationMapper;

import java.util.List;

public interface ClassificationService {
    Boolean addClassification(Classification classification);

    Boolean deleteClassification(Integer id);

    Boolean updateClassification(Classification classification);

    List<Classification> findAll();

    List<Classification> findByName(String name);
    Classification findById(Integer id);
}
