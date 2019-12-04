package com.ybl.sys.control;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/2 11:27
 * @desciption
 */
public class UserServletTest extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodStr = req.getRequestURI();
        String[] methodArr = methodStr.split("/");
        methodStr = methodArr[methodArr.length - 1];
        switch (methodStr) {
            case "list":
                this.list(req, resp);
                break;
            case "add":
                break;

        }
    }

    public void list(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("list");
    }
}
