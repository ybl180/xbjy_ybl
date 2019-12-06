package com.ybl.sys.control;

import com.alibaba.fastjson.JSON;
import com.ybl.sys.constants.SysConstants;
import com.ybl.sys.entity.User;
import com.ybl.sys.service.impl.UserServiceImpl;
import com.ybl.utils.ImgCodeUtil;
import com.ybl.utils.MDUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/4 16:22
 * @desciption
 */
@WebServlet("/sys/login/*")
public class LoginServlet extends BaseServlet {
    private UserServiceImpl userService = new UserServiceImpl();

    /***
     *@desciption 登录
     *@author ybl
     *@date 2019/12/5 16:52
     *@param [request, response]
     *@return void
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String picCode = request.getParameter("picCode");
        String remember = request.getParameter("remember");

        //判断图片验证码是否正确
        HttpSession session = request.getSession();
        Object obj = session.getAttribute(SysConstants.SESSION_PIC_CODE_NAME);
        if (obj == null || !obj.toString().equalsIgnoreCase(picCode)) {
            //验证码不正确
            response.sendRedirect("/index.jsp");
            return;
        }

        User user = new User();
        user.setAccount(account);
        user.setPassword(MDUtil.md5(password));
        List<User> list = userService.checkLogin(user);

        if (list == null || !(list.size() == 1)) {
            //登录失败
            response.sendRedirect("/index.jsp");
        } else { //登录成功
            //在线人数
            ServletContext application = getServletContext();
            Object objCount = application.getAttribute(SysConstants.APPLICATION_LOGIN_COUNT);
            int count = 1;
            if (objCount != null) {
                count = Integer.valueOf(objCount.toString()) + 1;
            }
            application.setAttribute(SysConstants.APPLICATION_LOGIN_COUNT, count);


            //将用户信息存入session
            session.setAttribute(SysConstants.SESSION_LOGIN_NAME, list.get(0));

            if ("checked".equals(remember)) {
                User loginUser = list.get(0);
                loginUser.setPassword(password);

                //cookie的值不能存特殊字符（" , :），所以提供2种解决方案
                String jsonStr = JSON.toJSONString(loginUser);
                //方案一：全部替换掉这些特殊字符
                // jsonStr=jsonStr.replace("\"","+");
                // jsonStr=jsonStr.replace(":","=");
                // jsonStr=jsonStr.replace(",","*");
                // jsonStr=jsonStr.replace(" ","%");
                //方案二：先编码，取值的时候再解码
                String jsonEncode = URLEncoder.encode(jsonStr, "utf-8");

                Cookie cookieLoginUser = new Cookie(SysConstants.COOKIE_LOGIN_USER, jsonEncode);
                //设置时间7天有效（单位秒）
//                cookieLoginUser.setMaxAge(7 * 24 * 60 * 60);
                cookieLoginUser.setMaxAge(120);
                //任何请求都可以获取cookie
                cookieLoginUser.setPath("/");
                response.addCookie(cookieLoginUser);
            }
            //跳转页面
            response.sendRedirect("/view/common/home.jsp");
        }
    }

    /***
     *@desciption 登出
     *@author ybl
     *@date 2019/12/5 16:12
     *@param [request, respose]
     *@return void
     */
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //在线人数减一
        ServletContext application = getServletContext();
        Object objCount = application.getAttribute(SysConstants.APPLICATION_LOGIN_COUNT);
        Integer count = Integer.valueOf(objCount.toString()) - 1 < 0 ? 0 : Integer.valueOf(objCount.toString()) - 1;
        application.setAttribute(SysConstants.APPLICATION_LOGIN_COUNT, count);

        //清除session中的值
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(0);
        session.removeAttribute(SysConstants.SESSION_LOGIN_NAME);

        //清除cookie中的值
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (SysConstants.COOKIE_LOGIN_USER.equals(cookies[i].getName())) {
                    cookies[i].setMaxAge(0);
                    cookies[i].setPath("/");
                    response.addCookie(cookies[i]);
                }
            }
        }

        response.sendRedirect("/index.jsp");
    }


    /***
     *@desciption 获取图片验证码
     *@author ybl
     *@date 2019/12/5 16:53
     *@param [request, response]
     *@return void
     */
    public void getPic(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ImgCodeUtil imgCodeUtil = new ImgCodeUtil();
        //获取验证码图片
        BufferedImage image = imgCodeUtil.getImage();

        ////获取验证码文本内容
        String code = imgCodeUtil.getText();

        //把图片验证码存入session
        HttpSession session = request.getSession();
        session.setAttribute(SysConstants.SESSION_PIC_CODE_NAME, code);

        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(image, "jpeg", sos);
        sos.flush();
        sos.close();
    }

}
