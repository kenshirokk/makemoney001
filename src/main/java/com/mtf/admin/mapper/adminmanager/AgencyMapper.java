package com.mtf.admin.mapper.adminmanager;

import com.mtf.admin.entity.Agency;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AgencyMapper {

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Agency findOne(Integer id);

    /**
     * 保存
     *
     * @param agency
     * @return
     */
    int save(Agency agency);

    Agency findOneByLoginKeyAndLoginPwd(@Param("loginKey") String loginKey, @Param("loginPwd") String loginPwd);

    /**
     * 超屌的递归查询
     * @param agencyId  指定查询谁的下级代理
     * @param level     指定递归深度 0: 自己, 1: 子级, 2: 子级 孙级
     * @param params    查询条件  and 关系
     *                      {
     *                          agencyId: 代理人id         精确匹配
     *                          nickname: 代理人昵称       模糊匹配
     *                          parentId: 父级(推荐人)     精确匹配
     *                      }
     * @return
     */
    List<Agency> findAll(@Param("agencyId") Integer agencyId,
                         @Param("level") Integer level,
                         @Param("params") Map<String, Object> params);

    /**
     * 根据代理id 获得金币/房卡
     * @param agencyId
     * @return
     */
    Agency getTreasureById(Integer agencyId);

    /**
     * 根据代理id 增加金币
     * @param agencyId
     * @param quantity
     * @return
     */
    int updateCoinPlus(@Param("agencyId") Integer agencyId, @Param("quantity") Integer quantity);

    /**
     * 根据代理id 增加房卡
     * @param agencyId
     * @param quantity
     * @return
     */
    int updateRoomCardPlus(@Param("agencyId") Integer agencyId, @Param("quantity") Integer quantity);

    /**
     * 根据代理id 减少金币
     * @param agencyId
     * @param quantity
     * @return
     */
    int updateCoinMinus(@Param("agencyId") Integer agencyId, @Param("quantity") Integer quantity);

    /**
     * 根据代理id 减少房卡
     * @param agencyId
     * @param quantity
     * @return
     */
    int updateRoomCardMinus(@Param("agencyId") Integer agencyId, @Param("quantity") Integer quantity);

    /**
     * 根据代理id 查询出售金币总数
     * @param agencyId
     * @return
     */
    Long getCoinSumByAgencyId(Integer agencyId);

    /**
     * 根据代理id 查询出售房卡总数
     * @param agencyId
     * @return
     */
    Long getRoomCardSumByAgencyId(Integer agencyId);

    /**
     * 根据权限查询下属代理数量
     * @param agencyId
     * @param level
     * @return
     */
    Integer getCount(@Param("agencyId") Integer agencyId, @Param("level") Integer level);

    /**
     * 根据代理id 获得平台总收入(权限)
     * @param agencyId
     * @param level
     * @return
     */
    Long getTotalIncome(@Param("agencyId") Integer agencyId, @Param("level") Integer level);
}
