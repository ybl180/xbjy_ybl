package com.ybl.sys.control;

import com.ybl.sys.constants.SysConstants;
import com.ybl.utils.EmailUtil;
import sun.misc.Request;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/3 16:21
 * @desciption
 */
@WebServlet("/sys/email/*")
public class EmailServlet extends BaseServlet {

    public void sendEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取邮箱地址
        String emailName = request.getParameter("email");
        //随机生成4位数字验证码
        int code = (int) ((Math.random() + 1) * 1000);

        //发送验证码
        EmailUtil.sendEmail(emailName, String.valueOf(code));

        //把验证码存到session中
        HttpSession session = request.getSession();
        session.setAttribute(SysConstants.SESSION_EMAIL_CODE, code);
        //设置有效时间60秒
        session.setMaxInactiveInterval(60);

        PrintWriter out = response.getWriter();
        out.append("验证码已发送");
    }
}
