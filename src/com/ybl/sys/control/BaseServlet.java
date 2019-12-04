package com.ybl.sys.control;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/2 11:06
 * @desciption
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        String uri = req.getRequestURI();
        String[] methodArr = uri.split("/");
        uri = methodArr[methodArr.length - 1];

//        Class clazz = UserServlet.class;
        Class clazz = this.getClass();
        try {
            Method method = clazz.getDeclaredMethod(uri, HttpServletRequest.class, HttpServletResponse.class);
            //暴力反射
            method.setAccessible(true);
            //  /sys/user/list
            //  /sys/dept/list
//            method.invoke(clazz.newInstance(), req, resp);
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
