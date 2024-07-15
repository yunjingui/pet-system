package com.three.server.service;

import com.three.server.entity.Good;

import javax.swing.text.StyledEditorKit;
import java.util.List;

public interface GoodService {
    Integer addGood(Good good);
    Boolean deleteGoodById(Integer id);
    Boolean updateGood(Good good);
    List<Good> findAllGoods();
    Good findGoodById(Integer id);
    List<Good> findGoodsByName(String name);
    List<Good> findGoodsBySellerId(Integer sellerId);
    List<Good> findGoodsBySecondClassification(Integer secondClassification);
    List<Good> findGoodsByNameAndClassificationAndLowQuantityAndHighQuantity(String name, Integer classification, Integer lowest, Integer highest);
    Double rankForGood(Integer id); // 查看商品评分

    Boolean plusGoodSale(Integer id); // 增加商品销量+1
    Boolean plusGoodFlow(Integer id); // 增加商品流量+1

    Double isDiscount(Integer id); // 查看商品是否有折扣

    Boolean uploadAvatar(Integer id, String avatar); // 上传商品头像

    Boolean addGoodImage(Integer id, String imageUrl); // 上传商品图片
    Boolean deleteGoodImage(Integer id); // 删除商品图片

    String goodImageDisplay(Integer id); // 展示商品图片
    List<String> goodImageDisplayByGoodId(Integer id); // 展示商品所有图片

}
