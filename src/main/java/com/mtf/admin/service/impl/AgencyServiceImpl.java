package com.mtf.admin.service.impl;

import com.mtf.admin.common.util.Cryptography;
import com.mtf.admin.entity.AccountsInfo;
import com.mtf.admin.entity.Agency;
import com.mtf.admin.mapper.adminmanager.AgencyMapper;
import com.mtf.admin.mapper.qpaccountsdb.AccountsInfoMapper;
import com.mtf.admin.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AgencyServiceImpl implements AgencyService {

    @Autowired
    private AgencyMapper agencyMapper;
    @Autowired
    private AccountsInfoMapper accountsInfoMapper;

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
        newAgency.setAvatar("//TODO");
        newAgency.setPhone(phone);
        newAgency.setRoomCard(0);
        newAgency.setCoin(0);
        newAgency.setAgencyBalance(0);
        newAgency.setSalaryBalance(0);
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
    public List<Agency> findByParentId(Integer parentId) {
        return agencyMapper.findByParentId(parentId);
    }
}
