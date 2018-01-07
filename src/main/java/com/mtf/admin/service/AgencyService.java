package com.mtf.admin.service;

import com.mtf.admin.entity.Agency;

public interface AgencyService {

    int createAgency(Integer agencyId, Integer userId, String password, String phone);

    Agency findOneByLoginKey(String loginKey);

    Agency findByLogin(String loginKey,String loginPwd);
}
