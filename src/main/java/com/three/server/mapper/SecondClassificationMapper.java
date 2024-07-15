package com.three.server.mapper;

import com.three.server.entity.SecondClassification;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SecondClassificationMapper {
    int insert(SecondClassification secondClassification);
    int deleteById(Integer id);
    int update(SecondClassification secondClassification);
    List<SecondClassification> selectAll();
    List<SecondClassification> selectByName(String name);
    SecondClassification selectById(Integer id);
    List<SecondClassification> selectByFatherClassification(Integer fatherClassification);
}
