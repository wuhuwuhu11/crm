package com.bjpowernode.crm.exception.resolver;

import com.bjpowernode.crm.exception.AjaxRequestException;
import com.bjpowernode.crm.exception.InterceptorException;
import com.bjpowernode.crm.exception.LoginException;
import com.bjpowernode.crm.exception.TranditionRequestException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义的异常处理器
 *      页面的控制器通过抛出对应的异常
 *      在异常处理器当中,决定失败请求结果的返回值
 *          1. 返回json数据
 *              @ResponseBody
 *          2. 跳转到某个页面
 *              return 字符串
 */
@ControllerAdvice
public class MyExceptionResolver {

    /**
     * 当抛出登录异常时,就会自动捕获该异常
     *      返回Json数据
     *          {code:1,msg:xxx}
     * @param e
     * @return
     */
    @ExceptionHandler(value= LoginException.class)
    @ResponseBody
    public Object loginExceptionResolver(Exception e){

        e.printStackTrace();

        Map<String,Object> map = new HashMap<>();
        map.put("code",1);
        map.put("msg",e.getMessage());

        return map;

    }

    /**
     * 当抛出拦截异常时,就会自动捕获该异常
     *      跳转到登录页面
     * @param e
     * @return
     */
    @ExceptionHandler(value= InterceptorException.class)
    public String interceptorExceptionResolver(Exception e){

        System.out.println("处理没登陆");

        e.printStackTrace();

        //在跳转到登录页面时,实现自动登录操作
        return "redirect:/settings/user/toLogin.do";

    }

    /**
     * 当发送ajax请求时,出现异常,捕获
     *      返回也是json数据
     * @param e
     * @return
     */
    @ExceptionHandler(value= AjaxRequestException.class)
    @ResponseBody
    public Object ajaxRequestExceptionResolver(Exception e){

        e.printStackTrace();

        System.out.println("---------------------====================================");

        Map<String,Object> map = new HashMap<>();
        map.put("code",1);
        map.put("msg",e.getMessage());

        return map;

    }

    /**
     * 当发送传统请求,出现异常时,被捕获
     *      跳转到失败页面...也可以重定向跳转到登录页面
     * @param e
     * @return
     */
    @ExceptionHandler(value= TranditionRequestException.class)
    public String traditionRequestExceptionResolver(Exception e){

        e.printStackTrace();

        return "redirect:/fail.jsp";

    }

    /**
     * 当抛出其他异常时,被捕获,跳转到失败页面或者也可以跳转到登录页面
     * @param e
     * @return
     */
    @ExceptionHandler(value= Exception.class)
    public String exceptionResolver(Exception e){

        e.printStackTrace();

        return "redirect:/fail.jsp";

    }
}





































