package com.bjpowernode.crm.settings.domain;

import lombok.Data;

import java.io.Serializable;

//也可以使用lombok提供的记录日志的注解
//lombok坐标提供的注解
//@Data注解可以帮助我们生成实体类的get/set方法和toString等方法
@Data
public class User implements Serializable {

    private String id;
    private String loginAct;
    private String name;
    private String loginPwd;
    private String email;
    private String lockState;//锁定状态,0代表用户未锁定,可以登录,1代表用户已锁定
    private String expireTime;//过期时间,19位的日期类型的字符串,年月日 时分秒,2022-01-10 22:07:00
    private String deptno;
    private String allowIps;//允许访问的IP列表,"192.168.111.123,127.0.0.1,172.17.11.111"
    private String createTime;
    private String createBy;
    private String editTime;

}
