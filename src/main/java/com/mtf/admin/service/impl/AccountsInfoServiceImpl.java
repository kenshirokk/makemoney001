package com.mtf.admin.service.impl;

import com.mtf.admin.common.vo.AccountsInfoVO;
import com.mtf.admin.entity.Agency;
import com.mtf.admin.entity.CoinRecord;
import com.mtf.admin.entity.RoomCardRecord;
import com.mtf.admin.mapper.adminmanager.AgencyMapper;
import com.mtf.admin.mapper.adminmanager.CoinRecordMapper;
import com.mtf.admin.mapper.adminmanager.RoomCardRecordMapper;
import com.mtf.admin.mapper.qpaccountsdb.AccountsInfoMapper;
import com.mtf.admin.service.AccountsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AccountsInfoServiceImpl implements AccountsInfoService {

    @Autowired
    private AccountsInfoMapper accountsInfoMapper;
    @Autowired
    private AgencyMapper agencyMapper;
    @Autowired
    private CoinRecordMapper coinRecordMapper;
    @Autowired
    private RoomCardRecordMapper roomCardRecordMapper;

    @Override
    public List<AccountsInfoVO> findAll(Integer agencyId, Integer level, Map<String, Object> params) {
        return accountsInfoMapper.findAll(agencyId, level, params);
    }

    @Override
    public int updatePlus(Map<String, Object> params) {
        return accountsInfoMapper.updatePlus(params);
    }

    @Override
    public int update(Map<String, Object> params) {
        return accountsInfoMapper.update(params);
    }

    @Override
    public AccountsInfoVO findOne(Integer userId) {
        return accountsInfoMapper.findOne(userId);
    }

    @Override
    public int updateCoinPlus(CoinRecord coinRecord) throws RuntimeException {
        Agency agency = agencyMapper.findOne(coinRecord.getFromAgencyId());
        if (agency.getAgencyType() > 1 &&(agency.getCoin() == null || coinRecord.getQuantity().compareTo(agency.getCoin()) > 0)) {
            //余额不足
            return -1;
        }
        int i = agencyMapper.updateCoinMinus(coinRecord.getFromAgencyId(), coinRecord.getQuantity());
        int j = accountsInfoMapper.updateCoinPlus(coinRecord.getToUserId(), coinRecord.getQuantity());
        int k = coinRecordMapper.save(coinRecord);
        if(i + j + k > 2){
            throw new RuntimeException();
        }
        return 1;
    }

    @Override
    public int updateRoomCardPlus(RoomCardRecord roomCardRecord) throws RuntimeException {
        Agency agency = agencyMapper.findOne(roomCardRecord.getFromAgencyId());
        if (agency.getAgencyType() > 1 &&(agency.getRoomCard() == null || roomCardRecord.getQuantity().compareTo(agency.getRoomCard()) > 0)) {
            //余额不足
            return -1;
        }
        int i = agencyMapper.updateRoomCardMinus(roomCardRecord.getFromAgencyId(), roomCardRecord.getQuantity());
        int j = accountsInfoMapper.updateRoomCardPlus(roomCardRecord.getToUserId(), roomCardRecord.getQuantity());
        int k = roomCardRecordMapper.save(roomCardRecord);
        if(i + j + k > 2){
            throw new RuntimeException();
        }
        return 1;
    }

}
