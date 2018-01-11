package com.mtf.admin.service.impl;

import com.mtf.admin.entity.Enchashment;
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

    @Override
    public List<Enchashment> findAll(Map<String, Object> params) {
        return enchashmentMapper.findAll(params);
    }

    @Override
    public int save(Enchashment enchashment) {
        return enchashmentMapper.save(enchashment);
    }

    @Override
    public int update(Enchashment enchashment) {
        return enchashmentMapper.update(enchashment);
    }
}
