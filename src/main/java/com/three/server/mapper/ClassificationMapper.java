package com.three.server.mapper;

import com.three.server.entity.Classification;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassificationMapper {
    int insert(Classification classification);
    int deleteById(Integer id);
    int update(Classification classification);
    List<Classification> selectAll();
    List<Classification> selectByName(String name);
    Classification selectById(Integer id);
}
