package com.mtf.admin.service.impl;

import com.mtf.admin.common.constant.Constant;
import com.mtf.admin.common.util.Cryptography;
import com.mtf.admin.common.vo.PersonalInfoVO;
import com.mtf.admin.entity.AccountsInfo;
import com.mtf.admin.entity.Agency;
import com.mtf.admin.mapper.adminmanager.AgencyMapper;
import com.mtf.admin.mapper.qpaccountsdb.AccountsInfoMapper;
import com.mtf.admin.mapper.qpplatformdb.OnLineStreamInfoMapper;
import com.mtf.admin.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AgencyServiceImpl implements AgencyService {

    @Autowired
    private AgencyMapper agencyMapper;
    @Autowired
    private AccountsInfoMapper accountsInfoMapper;
    @Autowired
    private OnLineStreamInfoMapper onLineStreamInfoMapper;

    @Override
    public int createAgency(Integer agencyId, Integer userId, String password, String phone) {
        AccountsInfo user = accountsInfoMapper.findOne(userId);
        if (user == null) {
            //用户不存在
            return 0;
        }
        Agency agency = agencyMapper.findOne(agencyId);
        if (agency == null) {
            //代理不存在  这种情况应该不可能,因为这个id是登陆者
            return 0;
        }

        Agency newAgency = new Agency();
        newAgency.setAgencyType(agency.getAgencyType() < 3 ? agency.getAgencyType() + 1 : agency.getAgencyType());
        newAgency.setParentId(agency.getId());
        newAgency.setParentNickname(agency.getNickname());
        newAgency.setPassword(Cryptography.md5(password));
        newAgency.setNickname(user.getNickName());
//        newAgency.setAvatar("//TODO");
        newAgency.setPhone(phone);
        newAgency.setRoomCard(0);
        newAgency.setCoin(0);
        newAgency.setAgencyBalance(0);
        newAgency.setDisable(0);
        newAgency.setDeleted(0);
        newAgency.setCreateDate(new Date());
        newAgency.setCreatorId(agencyId);
        newAgency.setUserId(user.getUserID());
        return agencyMapper.save(newAgency);
    }

    @Override
    public Agency findOneByLoginKey(String loginKey) {
        return agencyMapper.findOneByLoginKeyAndLoginPwd(loginKey,null);
    }

    @Override
    public Agency findByLogin(String loginKey, String loginPwd) {
        return agencyMapper.findOneByLoginKeyAndLoginPwd(loginKey,loginPwd);
    }

    @Override
    public List<Agency> findAll(Integer agencyId, Integer level, Map<String, Object> params) {
        return agencyMapper.findAll(agencyId, level, params);
    }

    @Override
    public Agency findOne(Integer id) {
        return agencyMapper.findOne(id);
    }

    @Override
    public PersonalInfoVO personalInfo(Integer agencyId, Integer agencyType) {

        Integer level = null;
        if (Constant.AGENCY_TYPE_3.equals(agencyType)) {
            level = 2;
        }
        Agency agency = agencyMapper.findOne(agencyId);

        //出售金币总数
        Long coin = agencyMapper.getCoinSumByAgencyId(agencyId);
        //出售房卡总数
        Long roomCard = agencyMapper.getRoomCardSumByAgencyId(agencyId);
        //在线玩家数量
        Integer onlineUser = onLineStreamInfoMapper.getOnlineUser();
        //代理数量(根据权限)
        Integer agencyCount = agencyMapper.getCount(agencyId, level);
        //玩家数量(根据权限)
        Integer userCount = accountsInfoMapper.getCount(agencyId, level);
        //平台总收入(根据权限)
        Long totalIncome = agencyMapper.getTotalIncome(agencyId, level);

        PersonalInfoVO vo = new PersonalInfoVO();
        vo.setPhone(agency.getPhone());
        vo.setCoinSum(coin);
        vo.setRoomCardSum(roomCard);
        vo.setOnlineUserCount(onlineUser);
        vo.setUserCount(userCount);
        vo.setAgencyCount(agencyCount);
        vo.setTotalIncome(totalIncome);
        return vo;
    }

}
