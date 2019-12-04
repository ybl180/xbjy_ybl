package com.ybl.filters;

import com.ybl.sys.constants.SysConstants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

        //获取请求路径
        String uri = request.getRequestURI();
        if (uri.endsWith("index.jsp") || uri.endsWith("forget.jsp") || uri.endsWith("/sys/user/forgetPassword")
                || uri.endsWith("/sys/login/login") || uri.endsWith("/sys/login/getPic") || uri.endsWith("/sys/email/sendEmail")) {
            //不拦截
        } else {
            HttpSession session = request.getSession();
            Object obj = session.getAttribute(SysConstants.SESSION_LOGIN_NAME);
            if (obj == null) {
                response.sendRedirect("/index.jsp");
            }
        }
        filterChain.doFilter(request, response);
    }
}
