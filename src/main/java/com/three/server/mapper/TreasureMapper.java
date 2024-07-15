package com.three.server.mapper;

import com.three.server.entity.Treasure;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TreasureMapper {
    int insert(Treasure treasure);
    int delete(Treasure treasure);
    int deleteByCustomerAndGood(@Param("customer") Integer customer, @Param("good") Integer good);
    List<Treasure> selectAllByCustomer(Integer customer);
    Integer selectAllQuantityByGood(Integer good);
    Integer selectIfCollected(@Param("customer") Integer customer, @Param("good") Integer good);
}
