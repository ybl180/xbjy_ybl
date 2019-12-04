package com.ybl.sys.control;

import com.ybl.sys.constants.SysConstants;
import com.ybl.sys.entity.TimeCondition;
import com.ybl.sys.entity.Page;
import com.ybl.sys.entity.User;
import com.ybl.sys.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/2 11:17
 * @desciption
 */
@WebServlet("/sys/user/*")
public class UserServlet extends BaseServlet {
    UserServiceImpl userService = new UserServiceImpl();

    public void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取起止时间
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        TimeCondition condition = new TimeCondition();
        condition.setStartTime(startTime);
        condition.setEndTime(endTime);

        //获取查询的账号
        String account = request.getParameter("account");
        account = account == null ? "" : account;

        String pageStr = request.getParameter("page");
        pageStr = pageStr == null ? String.valueOf(1) : pageStr;
        Integer pageCurrent = Integer.valueOf(pageStr);

        Integer count = userService.count(account, condition);
        Page page = new Page();
        page.setCount(count);
        page.setPageCurrent(pageCurrent);

        List<User> list = userService.listAll(account, page, condition);
        request.setAttribute("list", list);
        request.setAttribute("account", account);
        request.setAttribute("page", page);
        request.setAttribute("condition", condition);
        request.getRequestDispatcher(getServletContext().getContextPath() + "/view/sys/user/list.jsp").forward(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = new User();
        Map<String, String[]> map = request.getParameterMap();
        BeanUtils.populate(user, map);
        //设置创建人
        user.setCreateBy(super.getLoginUser().getId());
        userService.addUser(user);
        response.sendRedirect("/sys/user/list");
    }

    private void deleteById(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        if (id == null) {
            return;
        }
        userService.delByIdUser(Integer.valueOf(id));
        response.sendRedirect("/sys/user/list");
    }

    private void getUserById(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        User user = userService.getUserById(Integer.valueOf(id));
        request.setAttribute("user", user);
        request.getRequestDispatcher("/view/sys/user/update.jsp").forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = new User();
        Map<String, String[]> map = request.getParameterMap();
        BeanUtils.populate(user, map);
        userService.updateUser(user);
        response.sendRedirect("/sys/user/list");
    }

    private void forgetPassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //判断验证码是否正确
        String code = request.getParameter("code");
        HttpSession session = request.getSession();
        Object obj = session.getAttribute(SysConstants.SESSION_EMAIL_CODE);
        //比较前端输入的code和session中的code
        if(obj==null||!code.equals(obj.toString())){
            response.sendRedirect("/view/sys/user/forget.jsp");
            return;
        }

        String account = request.getParameter("account");
        String password = request.getParameter("password");
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        userService.updatePassword(user);
        response.sendRedirect("/index.jsp");
    }
}
