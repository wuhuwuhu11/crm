package com.bjpowernode.crm.settings.service;

import com.bjpowernode.crm.exception.LoginException;
import com.bjpowernode.crm.settings.domain.User;

import java.util.Map;

public interface UserService {

    //该注解的含义,代表方法已过期,调用时,会带有删除线
    @Deprecated
    User findUserByLoginActAndLoginPwd(String loginAct, String loginPwd);

    Map<String,Object> findUserByLoginActAndLoginPwdCondition(String loginAct,
                                                              String md5Pwd,
                                                              String ip) throws LoginException;
}
