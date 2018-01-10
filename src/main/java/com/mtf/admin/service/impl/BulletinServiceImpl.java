package com.mtf.admin.service.impl;

import com.mtf.admin.entity.Bulletin;
import com.mtf.admin.mapper.adminmanager.BulletinMapper;
import com.mtf.admin.service.BulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BulletinServiceImpl implements BulletinService {

    @Autowired
    private BulletinMapper bulletinMapper;

    @Override
    public List<Bulletin> findAll() {
        return bulletinMapper.findAll();
    }

    @Override
    public int save(Bulletin bulletin) {
        return bulletinMapper.save(bulletin);
    }

    @Override
    public int update(Bulletin bulletin) {
        return bulletinMapper.update(bulletin);
    }

    @Override
    public int delete(Integer id) {
        return bulletinMapper.delete(id);
    }
}
