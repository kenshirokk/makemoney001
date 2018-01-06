package com.mtf.admin.mapper.adminmanager;

import com.mtf.admin.entity.Agency;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyMapper {

    Agency findOne(Integer id);
}
