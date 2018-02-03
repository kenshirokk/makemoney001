package com.mtf.admin.service.impl;

import com.google.zxing.WriterException;
import com.mtf.admin.common.constant.Constant;
import com.mtf.admin.common.util.Cryptography;
import com.mtf.admin.common.util.QrCode;
import com.mtf.admin.common.vo.*;
import com.mtf.admin.entity.Agency;
import com.mtf.admin.entity.CoinRecord;
import com.mtf.admin.entity.RoomCardRecord;
import com.mtf.admin.mapper.adminmanager.AgencyMapper;
import com.mtf.admin.mapper.adminmanager.CoinRecordMapper;
import com.mtf.admin.mapper.adminmanager.RoomCardRecordMapper;
import com.mtf.admin.mapper.qpaccountsdb.AccountsInfoMapper;
import com.mtf.admin.mapper.qpplatformdb.OnLineStreamInfoMapper;
import com.mtf.admin.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AgencyServiceImpl implements AgencyService {

    @Value("${config.uploadPath}")
    private String uploadPath;

    @Value("${config.inviteUrl}")
    private String inviteUrl;

    @Autowired
    private AgencyMapper agencyMapper;
    @Autowired
    private AccountsInfoMapper accountsInfoMapper;
    @Autowired
    private OnLineStreamInfoMapper onLineStreamInfoMapper;
    @Autowired
    private CoinRecordMapper coinRecordMapper;
    @Autowired
    private RoomCardRecordMapper roomCardRecordMapper;

    @Override
    public int createAgency(Integer agencyId, Integer userId, String password, String phone) throws IOException,
            WriterException {
        AccountsInfoVO user = accountsInfoMapper.findOne(userId);
        if (user == null) {
            //用户不存在
            return 0;
        }
        Agency agency = agencyMapper.findOne(agencyId);
        if (agency == null) {
            //代理不存在  这种情况应该不可能,因为这个id是登陆者
            return 0;
        }

        Agency newAgency = new Agency();
        newAgency.setId(user.getUserID());
        newAgency.setAgencyType(agency.getAgencyType() < 3 ? agency.getAgencyType() + 1 : agency.getAgencyType());
        newAgency.setParentId(agency.getId());
        newAgency.setParentNickname(agency.getNickname());
        newAgency.setPassword(Cryptography.md5(password));
        newAgency.setNickname(user.getNickName());
        newAgency.setAvatar(user.getCustomFace());
        newAgency.setPhone(phone);
        newAgency.setRoomCard(0);
        newAgency.setCoin(0);
        newAgency.setAgencyBalance(0);
        newAgency.setDisable(0);
        newAgency.setDeleted(0);
        newAgency.setCreateDate(new Date());
        newAgency.setCreatorId(agencyId);
        newAgency.setUserId(user.getUserID());

        //生成 二维码
        Path path = QrCode.genQrCode(inviteUrl + user.getUserID(), 500, 500, "jpg", user.getUserID() + ".jpg",
                uploadPath);
        //添加 头像logo
        path = QrCode.addLogo(path, user.getCustomFace(), "jpg", uploadPath);

        //读取背景图片
        ClassPathResource classPathResource = new ClassPathResource("background.jpg");
        InputStream inputStream = classPathResource.getInputStream();
        File file = new File("background.jpg");
        Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        Path result = file.toPath();

        //添加二维码
        result = QrCode.addLogo(result, path, "jpg", path.getFileName().toString(), uploadPath);

        result = QrCode.addWord(result, "昵称: " + user.getNickName(), "jpg", 170, 300, 30);
        result = QrCode.addWord(result, "邀请码: " + user.getUserID(), "jpg", 170, 350, 30);

        newAgency.setQrcode(result.getFileName().toString());

        return agencyMapper.save(newAgency);
    }

    @Override
    public Agency findOneByLoginKey(String loginKey) {
        return agencyMapper.findOneByLoginKeyAndLoginPwd(loginKey, null);
    }

    @Override
    public Agency findByLogin(String loginKey, String loginPwd) {
        return agencyMapper.findOneByLoginKeyAndLoginPwd(loginKey, loginPwd);
    }

    @Override
    public List<Agency> findAll(Integer agencyId, Integer level, Map<String, Object> params) {
        return agencyMapper.findAll(agencyId, level, params);
    }

    @Override
    public Agency findOne(Integer id) {
        return agencyMapper.findOne(id);
    }

    @Override
    public PersonalInfoVO personalInfo(Integer agencyId, Integer agencyType) {

        Integer level = null;
        if (Constant.AGENCY_TYPE_3.equals(agencyType)) {
            level = 2;
        }
        Agency agency = agencyMapper.findOne(agencyId);

        //出售金币总数
        Long coin = agencyMapper.getCoinSumByAgencyId(agencyId);
        //出售房卡总数
        Long roomCard = agencyMapper.getRoomCardSumByAgencyId(agencyId);
        //在线玩家数量
        Integer onlineUser = onLineStreamInfoMapper.getOnlineUser();
        //代理数量(根据权限)
        Integer agencyCount = agencyMapper.getCount(agencyId, level);
        //玩家数量(根据权限)
        Integer userCount = accountsInfoMapper.getCount(agencyId, level);
        //平台总收入(根据权限)
        Long totalIncome = agencyMapper.getTotalIncome(agencyId, level);

        PersonalInfoVO vo = new PersonalInfoVO();
        vo.setPhone(agency.getPhone());
        vo.setCoinSum(coin);
        vo.setRoomCardSum(roomCard);
        vo.setOnlineUserCount(onlineUser);
        vo.setUserCount(userCount);
        vo.setAgencyCount(agencyCount);
        vo.setTotalIncome(totalIncome);
        return vo;
    }

    @Override
    public int updateCoinPlus(CoinRecord coinRecord) throws RuntimeException {
        Agency agency = agencyMapper.findOne(coinRecord.getFromAgencyId());
        if (agency.getAgencyType() > 1 && (agency.getCoin() == null || coinRecord.getQuantity().compareTo(agency
                .getCoin()) > 0)) {
            //余额不足
            return -1;
        }
        int i = agencyMapper.updateCoinMinus(coinRecord.getFromAgencyId(), coinRecord.getQuantity());
        int j = agencyMapper.updateCoinPlus(coinRecord.getToAgencyId(), coinRecord.getQuantity());
        int k = coinRecordMapper.save(coinRecord);
        if (i + j + k < 3) {
            throw new RuntimeException();
        }
        return 1;
    }

    @Override
    public int updateRoomCardPlus(RoomCardRecord roomCardRecord) throws RuntimeException {
        Agency agency = agencyMapper.findOne(roomCardRecord.getFromAgencyId());
        if (agency.getAgencyType() > 1 && (agency.getRoomCard() == null || roomCardRecord.getQuantity().compareTo
                (agency.getRoomCard()) > 0)) {
            //余额不足
            return -1;
        }
        int i = agencyMapper.updateRoomCardMinus(roomCardRecord.getFromAgencyId(), roomCardRecord.getQuantity());
        int j = agencyMapper.updateRoomCardPlus(roomCardRecord.getToAgencyId(), roomCardRecord.getQuantity());
        int k = roomCardRecordMapper.save(roomCardRecord);
        if (i + j + k < 3) {
            throw new RuntimeException();
        }
        return 1;
    }

    @Override
    public int update(Map<String, Object> params) {
        return agencyMapper.update(params);
    }

    @Override
    public Agency finByUserId(Integer userId) {
        return agencyMapper.finByUserId(userId);
    }

    @Override
    public List<MoneyFlowVO> getMoneyFlowVO(Integer agencyId, Integer year) {
        return agencyMapper.getMoneyFlowVO(agencyId, year);
    }

    @Override
    public List<SellRecordVO> getSellRecordVO(Integer agencyId, Integer directAgencyId,Integer directPlayerId) {
        return agencyMapper.getSellRecordVO(agencyId, directAgencyId,directPlayerId);
    }

    @Override
    public List<PerformanceVO> getPerformanceVO(Integer agencyId, Integer year) {
        return agencyMapper.getPerformanceVO(agencyId, year);
    }

}
