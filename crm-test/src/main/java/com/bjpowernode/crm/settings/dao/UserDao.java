package com.bjpowernode.crm.settings.dao;

import com.bjpowernode.crm.settings.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

/*    @Select("select * from user_crm where loginAct=#{loginAct} and loginPwd=#{loginPwd}")
    User findUserByLoginActAndLoginPwd(@Param("loginAct")String loginAct,
                                       @Param("loginPwd") String loginPwd);*/
    User findUserByLoginActAndLoginPwd(String loginAct,
                                       String loginPwd);
}
