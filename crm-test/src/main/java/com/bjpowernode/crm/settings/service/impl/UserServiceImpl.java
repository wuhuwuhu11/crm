package com.bjpowernode.crm.settings.service.impl;

import com.bjpowernode.crm.exception.LoginException;
import com.bjpowernode.crm.settings.dao.UserDao;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public User findUserByLoginActAndLoginPwd(String loginAct, String loginPwd) {
        return userDao.findUserByLoginActAndLoginPwd(loginAct, loginPwd);
    }

    @Override
    public Map<String, Object> findUserByLoginActAndLoginPwdCondition(String loginAct, String md5Pwd, String ip) throws LoginException {

        User user = userDao.findUserByLoginActAndLoginPwd(loginAct, md5Pwd);

        Map<String, Object> resultMap = new HashMap<>();

        if (user == null) {
//            用户名或密码错误
/*            resultMap.put("code", 1);
            resultMap.put("msg", "用户名或密码错误...");
            resultMap.put("data", null);*/
            throw new LoginException("用户名或密码错误...");
        }
//        获取过期时间
        String expireTime = user.getExpireTime();


//        获取当前时间
        String now = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());

        /*
         * 校验用户是否过期
         * compareTo,比较日期时间
         * 如果返回的是1代表未过期，证明过期时间大于现在的时间
         * 如果返回的是0代表马上过期，因为过期时间等于现在的时间
         * 如果返回的是-1代表已经过期，因为现在的时间大于过期时间*/
        if (!StringUtils.isEmpty(expireTime)) {
            if (expireTime.compareTo(now) <= 0) {
//                代表用户已过期
/*                resultMap.put("code", 1);
                resultMap.put("msg", "当前用户已过期。。。");
                resultMap.put("data", null);
                return resultMap;*/
                throw new LoginException("当前用户已过期...");
            }
        }

        //校验用户是否被锁定
        //锁定状态为空,代表用不锁定
        String lockState = user.getLockState();

        if(!org.springframework.util.StringUtils.isEmpty(lockState)) {
            if ("1".equals(lockState)) {
                //用户被锁定
                //resultMap.put("code", 1);
                //resultMap.put("msg", "当前用户已锁定...");
                //resultMap.put("data", null);
                //return resultMap;
                throw new LoginException("当前用户已锁定...");
            }
        }


//        校验用户是否IP被限制
//        IP允许访问列表为空，代表允许任意访问
        String allowIps = user.getAllowIps();


        if (!StringUtils.isEmpty(allowIps)) {

            if (!allowIps.contains(ip)){
/*            //ip受限
            resultMap.put("code", 1);
            resultMap.put("msg", "当前用户IP地址受限...");
            resultMap.put("data", null);
            return resultMap;*/
                throw new LoginException("当前用户IP地址受限...");
        }
    }
            //登陆成功
            resultMap.put("code",1);
            resultMap.put("msg","登录成功...");
            resultMap.put("data",user);
            return  resultMap;
    }
}
