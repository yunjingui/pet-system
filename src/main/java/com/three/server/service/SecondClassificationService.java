package com.three.server.service;

import com.three.server.entity.SecondClassification;

import java.util.List;

public interface SecondClassificationService {
    Boolean add(SecondClassification secondClassification);
    Boolean deleteById(Integer id);
    Boolean update(SecondClassification secondClassification);
    List<SecondClassification> findAll();
    SecondClassification findById(Integer id);
    List<SecondClassification> findByFatherClassification(Integer fatherClassification);
    List<SecondClassification> findByName(String name);
}
