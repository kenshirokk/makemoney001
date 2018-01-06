package com.mtf.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OnLineStreamInfo implements Serializable {

    private Integer ID;
    private String MachineID;
    private String MachineServer;
    private Date InsertDateTime;
    private Integer OnLineCountSum;
    private String OnLineCountKind;

}
