package com.mtf.admin.mapper.adminmanager;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.mtf.admin.common.vo.MoneyFlowVO;
import com.mtf.admin.common.vo.PerformanceVO;
import com.mtf.admin.common.vo.SellRecordVO;
import com.mtf.admin.entity.Agency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("k")
@Transactional
public class AgencyMapperTest {

    @Autowired
    private AgencyMapper agencyMapper;

    @Test
    public void findOne() {
        Agency one = agencyMapper.findOne(1);
        System.out.println(one);
    }

    @Test
    public void save() throws IOException {
        Agency a = new Agency();
        a.setAgencyType(2);
        agencyMapper.save(a);
    }

    @Test
    public void findAll() {
        Map<String, Object> params = Maps.newHashMap();
//        params.put("agencyId", 16);
//        params.put("parentId", 9);
//        params.put("nickname", "2");
        PageHelper.startPage(1, 22).setOrderBy("id DESC");
        List<Agency> all = agencyMapper.findAll(9, null, params);
        for (Agency agency : all) {
            System.out.println(agency.getAvatar());
        }
    }

    @Test
    public void getCount() {
        Integer count = agencyMapper.getCount(17, 2);
        System.out.println(count);
    }

    @Test
    public void getTotalIncome() {
        Long totalIncome = agencyMapper.getTotalIncome(17, 0);
        System.out.println(totalIncome);
    }

    @Test
    public void update() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("agencyId", 16);
        params.put("agencyType", 2);
        params.put("deleted", 3);
        params.put("disable", 2);
        int i = agencyMapper.update(params);
    }

    @Test
    public void getMoneyFlowVO() {
        List<MoneyFlowVO> moneyFlowVO = agencyMapper.getMoneyFlowVO(17, 2018);
        for (MoneyFlowVO flowVO : moneyFlowVO) {
            System.out.println(flowVO);
        }
    }

    @Test
    public void getSellRecordVO() {
        List<SellRecordVO> sellRecordVO = agencyMapper.getSellRecordVO(17, 10,10);
        for (SellRecordVO vo : sellRecordVO) {
            System.out.println(vo);
        }
    }

    @Test
    public void getPerformanceVO() {
        List<PerformanceVO> performanceVO = agencyMapper.getPerformanceVO(127, 2018);
        for (PerformanceVO vo : performanceVO) {
            System.out.println(vo);
        }
    }
}