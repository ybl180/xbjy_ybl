package com.ybl.sys.control;

import com.ybl.sys.constants.SysConstants;
import com.ybl.sys.entity.User;
import com.ybl.sys.service.impl.UserServiceImpl;
import com.ybl.utils.ImgCodeUtil;
import com.ybl.utils.MDUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
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

    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String picCode = request.getParameter("picCode");

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
        } else {
            //登录成功,将用户信息存入session
            session.setAttribute(SysConstants.SESSION_LOGIN_NAME, list.get(0));
            response.sendRedirect("/view/common/home.jsp");
        }
    }


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
