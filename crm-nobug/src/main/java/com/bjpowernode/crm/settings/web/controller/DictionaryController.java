package com.bjpowernode.crm.settings.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/settings/dictionary")
public class DictionaryController {

    /**
     * 跳转到字典模块首页面
     * @return
     */
    @RequestMapping("/toIndex.do")
    public String toIndex(){
        return "/settings/dictionary/index";
    }

    /**
     * 跳转到字典模块的类型模块的首页面
     * @return
     */
    @RequestMapping("/type/toIndex.do")
    public String toTypeIndex(){
        return "/settings/dictionary/type/index";
    }

    /**
     * 跳转到字典模块的值模块的首页面
     * @return
     */
    @RequestMapping("/value/toIndex.do")
    public String toValueIndex(){
        return "/settings/dictionary/value/index";
    }
}
