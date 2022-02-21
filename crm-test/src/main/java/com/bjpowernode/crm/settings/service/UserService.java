package com.bjpowernode.crm.settings.service;

import com.bjpowernode.crm.exception.LoginException;
import com.bjpowernode.crm.settings.domain.User;

import java.util.Map;

public interface UserService {

    User findUserByLoginActAndLoginPwd(String loginAct ,String loginPwd);

  /*  Map<String,Object> findUserByLoginActAndLoginPwdCondition(String loginAct,
                                                              String loginPwd,
                                                              String ip);*/
    Map<String,Object> findUserByLoginActAndLoginPwdCondition(String loginAct,
                                                              String md5Pwd,
                                                              String ip) throws LoginException;
}
