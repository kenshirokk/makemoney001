package com.mtf.admin.mapper.adminmanager;

import com.mtf.admin.entity.Agency;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyMapper {

    Agency findOne(Integer id);
    int save(Agency agency);

    Agency findOneByLoginKeyAndLoginPwd(@Param("loginKey") String loginKey, @Param("loginPwd")String loginPwd);
}
