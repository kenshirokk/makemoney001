package com.mtf.admin.mapper.adminmanager;

import com.mtf.admin.entity.Agency;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgencyMapper {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Agency findOne(Integer id);

    /**
     * 保存
     * @param agency
     * @return
     */
    int save(Agency agency);
    Agency findOneByLoginKeyAndLoginPwd(@Param("loginKey") String loginKey, @Param("loginPwd")String loginPwd);

    List<Agency> findByParentId(Integer parentId);
}
