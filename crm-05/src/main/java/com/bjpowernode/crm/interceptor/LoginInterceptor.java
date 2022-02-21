package com.bjpowernode.crm.interceptor;

import com.bjpowernode.crm.exception.InterceptorException;
import com.bjpowernode.crm.settings.domain.User;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限校验
 *      权限校验有多钟多样的方式
 *      集中式的项目可以通过当前方式进行实现
 *
 *      分布式的项目我们如果这样实现则会出现问题
 *          将用户存入到Session时,有多个分布式的服务器,此时会造成Session无法统一的问题
 *          需要通过第三方的SpringSession进行解决,它通过第三方的缓存技术统一存储Session中的数据
 *          任何的一台服务器,都可以从第三方的缓存中,获取到登录的用户对象,保持一致性
 *
 *      分布式的项目我们还可以通过token的方式进行实现
 *          token : 令牌,将自己的身份信息通过自己公司定义加密规则,生成一段字符串
 *                  这段字符串我们可以加密和解密操作,获取明文数据从而进行权限校验
 *          这种方式的好处就是无需其他的框架和第三方技术的支持,即可解决权限校验的问题
 *
 *      权限校验的框架
 *          Shiro/SpringSecurity/OAuth2等等都是第三方的权限校验框架
 *              连接数据库进行查询权限和角色,RBAC权限控制模型...
 *
 *          SSO单点登录...
 */
public class LoginInterceptor implements HandlerInterceptor {
    //控制器访问前的拦截方法
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        //获取登录的用户
        User user = (User) httpServletRequest.getSession().getAttribute("user");

        //除了登录操作和跳转到用户页面的请求外,全部拦截
        //除了登录操作和跳转用户页面操作和十天免登陆操作

        //没有从session中获取到用户信息,证明没有登录
        if(ObjectUtils.isEmpty(user)){
            //拦截该请求
            throw new InterceptorException();
        }

        //返回true,所有请求全部放行
        return true;
    }
    //控制器访问后的拦截方法
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    //页面加载完成前的回调方法
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
