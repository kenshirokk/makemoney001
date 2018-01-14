package com.mtf.admin.common.vo;

import lombok.Data;

@Data
public class PageParam {

    private int pageNum = 1;
    private int pageSize = Integer.MAX_VALUE;
}
