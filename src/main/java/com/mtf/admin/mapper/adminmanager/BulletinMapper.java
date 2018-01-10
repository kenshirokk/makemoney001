package com.mtf.admin.mapper.adminmanager;

import com.mtf.admin.entity.Bulletin;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BulletinMapper {

    /**
     * 查询所有公告
     * @return
     */
    List<Bulletin> findAll();

    /**
     * 保存公告
     * @param bulletin
     * @return
     */
    int save(Bulletin bulletin);

    /**
     * 更新公告
     * @param bulletin
     * @return
     */
    int update(Bulletin bulletin);

    /**
     * 根据id删除公告
     * @param id
     * @return
     */
    int delete(Integer id);
}
