package com.bjpowernode.crm.settings.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {


    private Integer id;
    private String loginAct;
    private String name;
    private String loginPwd;
    private String email;
    private String lockState;
    private String expireTime;
    private String deptno;
    private String allowIps;
    private String createTime;
    private String createBy;
    private String editTime;
}
