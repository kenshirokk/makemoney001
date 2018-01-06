package com.mtf.admin.mapper.qpaccountsdb;

import com.mtf.admin.entity.ShopInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商城管理
 * 商品配置表
 */
@Repository
public interface ShopInfoListMapper {

    List<ShopInfo> findAll();

}
