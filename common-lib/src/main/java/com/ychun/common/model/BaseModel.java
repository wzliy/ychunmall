package com.ychun.common.model;

import lombok.Data;

import java.util.Date;

@Data
public class BaseModel {
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
