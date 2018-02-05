package com.mtf.admin.service;

import com.google.zxing.WriterException;
import com.mtf.admin.common.vo.MoneyFlowVO;
import com.mtf.admin.common.vo.PerformanceVO;
import com.mtf.admin.common.vo.PersonalInfoVO;
import com.mtf.admin.common.vo.SellRecordVO;
import com.mtf.admin.entity.Agency;
import com.mtf.admin.entity.CoinRecord;
import com.mtf.admin.entity.RoomCardRecord;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface AgencyService {

    /**
     * 创建代理
     * @param agencyId
     * @param userId
     * @param password
     * @param phone
     * @return
     */
    int createAgency(Integer agencyId, Integer userId, String password, String phone) throws IOException, WriterException;
    Agency findOneByLoginKey(String loginKey);
    Agency findByLogin(String loginKey,String loginPwd);

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
    List<Agency> findAll(Integer agencyId, Integer level, Map<String, Object> params);

    /**
     * 首页 个人信息
     * @param agencyId
     * @param agencyType
     * @return
     */
    PersonalInfoVO personalInfo(Integer agencyId, Integer agencyType);

    Agency findOne(Integer id);

    /**
     * 增加金币
     * @param coinRecord
     * @return
     */
    int updateCoinPlus(CoinRecord coinRecord) throws RuntimeException;

    /**
     * 增加房卡
     * @param roomCardRecord
     * @return
     */
    int updateRoomCardPlus(RoomCardRecord roomCardRecord) throws RuntimeException;

    /**
     * 更新代理级别
     * @param params
     * @return
     */
    int update(Map<String, Object> params);

    Agency finByUserId(Integer userId);

    /**
     * 流水查询
     * @param agencyId
     * @param year
     * @return
     */
    List<MoneyFlowVO> getMoneyFlowVO(Integer agencyId, Integer year);

    /**
     * 流水查询 详情
     * @param year
     * @param month
     * @param agencyId
     * @param userId
     * @return
     */
    List<SellRecordVO> getMoneyFlowVODetail(Integer year, Integer month, Integer agencyId, Integer userId);

    /**
     * 出售记录
     * @param agencyId
     * @param directAgencyId
     * @return
     */
    List<SellRecordVO> getSellRecordVO(Integer agencyId, Integer directAgencyId,Integer directPlayerId);

    /**
     * 业绩
     * @param agencyId
     * @param year
     * @return
     */
    List<PerformanceVO> getPerformanceVO(Integer agencyId, Integer year);
}
