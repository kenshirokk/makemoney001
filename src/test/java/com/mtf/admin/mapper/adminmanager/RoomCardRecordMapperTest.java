package com.mtf.admin.mapper.adminmanager;

import com.mtf.admin.common.constant.Constant;
import com.mtf.admin.entity.RoomCardRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("k")
@Transactional
public class RoomCardRecordMapperTest {

    @Autowired
    private RoomCardRecordMapper roomCardRecordMapper;

    @Test
    public void save() {
        RoomCardRecord rcr = new RoomCardRecord();
        rcr.setFromAgencyId(1);
        rcr.setRecord_type(Constant.RECORD_TYPE_USER);
        rcr.setToAgencyId(12);
        rcr.setQuantity(150);
        int i = roomCardRecordMapper.save(rcr);
        System.out.println(i);
    }

    @Test
    public void getQuantitySumByFromAgencyId() {
        Long sum = roomCardRecordMapper.getQuantitySumByFromAgencyId(1);
        System.out.println(sum);
    }
}