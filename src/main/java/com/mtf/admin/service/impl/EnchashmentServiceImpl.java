package com.mtf.admin.service.impl;

import com.mtf.admin.common.constant.Constant;
import com.mtf.admin.common.vo.EnchashmentVO;
import com.mtf.admin.entity.Agency;
import com.mtf.admin.entity.Enchashment;
import com.mtf.admin.mapper.adminmanager.AgencyMapper;
import com.mtf.admin.mapper.adminmanager.EnchashmentMapper;
import com.mtf.admin.service.EnchashmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EnchashmentServiceImpl implements EnchashmentService {

    @Autowired
    private EnchashmentMapper enchashmentMapper;
    @Autowired
    private AgencyMapper agencyMapper;

    @Override
    public List<EnchashmentVO> findAll(Map<String, Object> params) {
        return enchashmentMapper.findAll(params);
    }

    @Override
    public int save(Enchashment enchashment) {
        Integer agencyId = enchashment.getAgencyId();
        Agency treasure = agencyMapper.getTreasureById(agencyId);
        Double agencyBalance = treasure.getAgencyBalance();
        if (agencyBalance < enchashment.getMoney()) {
            //提现金额大于余额
            return -1;
        }
        //直接扣除代理余额
        agencyMapper.updateAgencyBalanceSub(enchashment.getAgencyId(), enchashment.getMoney().intValue());
        return enchashmentMapper.save(enchashment);
    }

    @Override
    public int update(Enchashment enchashment) {
        if (Constant.ENCHASHMENT_STATUS_REJECT.equals(enchashment.getApproveStatus())) {
            Enchashment one = enchashmentMapper.findOne(enchashment.getId());
            agencyMapper.updateAgencyBalance(one.getAgencyId(), one.getId());
        }
        return enchashmentMapper.update(enchashment);
    }
}
