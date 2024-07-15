package com.three.server.service;

import com.three.server.entity.Treasure;

import java.util.List;

public interface TreasureService {
    Boolean add(Treasure treasure);
    Boolean delete(Treasure treasure);
    Boolean deleteByCustomerAndGood(Integer customer, Integer good);
    List<Treasure> findAllByCustomer(Integer customer);
    Integer findAllQuantityByGood(Integer good);
    Integer findIfCollected(Integer customer, Integer good);
}
