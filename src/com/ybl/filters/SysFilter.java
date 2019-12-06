package com.ybl.filters;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ybl.sys.constants.SysConstants;
import com.ybl.sys.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/11/29 17:38
 * @desciption
 */
@WebFilter("/*")
public class SysFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        //拦截"http://localhost:8080/"请求
        StringBuffer url = request.getRequestURL();
        if ("http://localhost:8080/".contentEquals(url)) {
            response.sendRedirect("/index.jsp");
            return;
        }

        //获取请求路径
        String uri = request.getRequestURI();
        if (uri.endsWith("index.jsp")) {
            //获取所有的cookie信息
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    if (SysConstants.COOKIE_LOGIN_USER.equals(cookies[i].getName())) {
                        //获取cookie中的值
                        String cookieValue = cookies[i].getValue();
                        //解码
                        String jsonStr = URLDecoder.decode(cookieValue, "utf-8");
                        //把json字符串转换成java对象
                        User loginUser = JSON.parseObject(jsonStr, new TypeReference<User>() {
                        });
                        //把从cookie中取出的登陆信息放入到session中
                        session.setAttribute(SysConstants.SESSION_LOGIN_NAME, loginUser);

                        //登录成功
                        response.sendRedirect("/view/common/home.jsp");
                    }
                }
            }

        } else if (uri.endsWith("forget.jsp") || uri.endsWith("/sys/user/forgetPassword") || uri.endsWith("/sys/login/login")
                || uri.endsWith("/sys/login/getPic") || uri.endsWith("/sys/email/sendEmail")) {
            //不拦截
        } else {
            Object obj = session.getAttribute(SysConstants.SESSION_LOGIN_NAME);
            if (obj == null) {
                response.sendRedirect("/index.jsp");
            }
        }
        filterChain.doFilter(request, response);
    }
}
