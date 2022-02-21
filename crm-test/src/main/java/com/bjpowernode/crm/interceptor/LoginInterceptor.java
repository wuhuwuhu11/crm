package com.bjpowernode.crm.interceptor;

import com.bjpowernode.crm.exception.InterceptorException;
import com.bjpowernode.crm.settings.domain.User;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    //控制器访问前的拦截方法
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        //获取登录的用户
        User user = (User) httpServletRequest.getSession().getAttribute("user");

        //除了登录操作和跳转到用户页面的请求外，全部拦截
        //除了登录操作和跳转用户页面操作和十天免登录操作


        //没有从session中获取到用户信息，证明没有登录
        if (ObjectUtils.isEmpty(user)){
            //拦截该请求
            throw new InterceptorException();
        }
        //返回true，所有请求全部放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
