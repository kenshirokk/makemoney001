package com.mtf.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AccountsInfo implements Serializable {

    private Integer UserID;
    private Integer GameID;
    private Integer ProtectID;
    private Integer PasswordID;
    private Integer SpreaderID;
    private String Accounts;
    private String NickName;
    private String RegAccounts;
    private String UnderWrite;
    private String PassPortID;
    private String Compellation;
    private String LogonPass;
    private String InsurePass;
    private Integer FaceID;
    private Integer CustomID;
    private Integer Present;
    private Integer UserMedal;
    private Integer Experience;
    private Integer LoveLiness;
    private Integer UserRight;
    private Integer MasterRight;
    private Integer ServiceRight;
    private Integer MasterOrder;
    private Integer MemberOrder;
    private Date MemberOverDate;
    private Date MemberSwitchDate;
    private Integer CustomFaceVer;
    private Integer Gender;
    private Integer Nullity;
    private Date NullityOverDate;
    private Integer StunDown;
    private Integer MoorMachine;
    private Integer IsAndroid;
    private Integer WebLogonTimes;
    private Integer GameLogonTimes;
    private Integer PlayTimeCount;
    private Integer OnLineTimeCount;
    private String LastLogonIP;
    private Date LastLogonDate;
    private String LastLogonMobile;
    private String LastLogonMachine;
    private String RegisterIP;
    private Date RegisterDate;
    private String RegisterMobile;
    private String RegisterMachine;

}
