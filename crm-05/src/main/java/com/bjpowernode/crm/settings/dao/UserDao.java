package com.bjpowernode.crm.settings.dao;

import com.bjpowernode.crm.settings.domain.User;

/*
    由于在pom.xml中定义build标签
        在该标签中加载的是java目录下的*.xml配置文件
        该配置文件就是我们mybatis的映射配置文件
        如果没有配置build标签,默认加载的是resources目录下的*.xml映射配置文件
 */
public interface UserDao {

    //通过mybatis提供的增删改查的注解,进行sql的输出
    //@Select @Insert @Update @Delete
    //@Select("select * from tbl_user where loginAct = #{loginAct} and loginPwd = #{loginPwd}")
    //User findUserByLoginActAndLoginPwd(@Param("loginAct") String loginAct,
    //                                   @Param("loginPwd") String loginPwd);

    //select * from tbl_user where loginAct = #{param1} and loginPwd = #{param2}
    User findUserByLoginActAndLoginPwd(String loginAct,
                                       String loginPwd);

}
