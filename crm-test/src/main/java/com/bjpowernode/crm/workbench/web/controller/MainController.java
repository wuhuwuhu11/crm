package com.bjpowernode.crm.workbench.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/workbench/main")
public class MainController {
    /**
     * 加载到首页面的内容区域
     */
    @RequestMapping("/toIndex.do")
    public String toIndex(){
        return "/workbench/main/index";
    }
}
