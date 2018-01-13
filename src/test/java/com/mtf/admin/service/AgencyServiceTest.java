package com.mtf.admin.service;

import com.mtf.admin.common.vo.PersonalInfoVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("k")
public class AgencyServiceTest {

    @Autowired
    private AgencyService agencyService;

    @Test
    public void createAgency() {
    }

    @Test
    public void findOneByLoginKey() {
    }

    @Test
    public void findByLogin() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void personalInfo() {
        PersonalInfoVO personalInfoVO = agencyService.personalInfo(17, 1);
        System.out.println(personalInfoVO);
    }
}