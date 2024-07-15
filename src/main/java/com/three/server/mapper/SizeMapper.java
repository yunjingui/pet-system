package com.three.server.mapper;

import com.three.server.entity.Size;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SizeMapper {
    int insert(Size size);
    int deleteById(Integer id);
    int deleteByGoodId(Integer goodId);
    int update(Size size);
    List<Size> selectAll();
    Size selectById(Integer id);
    List<Size> selectByGoodId(Integer goodId);


}
