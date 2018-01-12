package com.mtf.admin.mapper.adminmanager;

import com.mtf.admin.common.constant.Constant;
import com.mtf.admin.entity.CoinRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("k")
public class CoinRecordMapperTest {

    @Autowired
    private CoinRecordMapper coinRecordMapper;

    @Test
    public void save() {
        CoinRecord cr = new CoinRecord();
        cr.setFromAgencyId(1);
        cr.setRecord_type(Constant.RECORD_TYPE_AGENCY);
        cr.setToAgencyId(12);
        cr.setQuantity(300);
        int i = coinRecordMapper.save(cr);
        System.out.println(i);
    }

    @Test
    public void getQuantitySumByFromAgencyId() {
        Long sum = coinRecordMapper.getQuantitySumByFromAgencyId(1);
        System.out.println(sum);
    }
}