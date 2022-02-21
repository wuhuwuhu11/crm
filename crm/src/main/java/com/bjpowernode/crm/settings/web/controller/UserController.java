package com.bjpowernode.crm.settings.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/settings/user")
public class UserController {

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("/toLogin.do")
    public String toLogin(){
        //applicationContext-web.xml
        //拼接视图解析器的前缀+返回值+后缀名称,找到对应的jsp页面
        //前缀 = /WEB-INF/jsp
        //返回值 = /login
        //后缀 = .jsp
        return "/login";
    }
}
