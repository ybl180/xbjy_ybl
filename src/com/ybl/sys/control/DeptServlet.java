package com.ybl.sys.control;

import com.alibaba.fastjson.JSON;
import com.ybl.sys.entity.Dept;
import com.ybl.sys.entity.Page;
import com.ybl.sys.service.impl.DeptServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/2 18:40
 * @desciption
 */
@WebServlet("/sys/dept/*")
public class DeptServlet extends BaseServlet {
    DeptServiceImpl deptService = new DeptServiceImpl();

    public void list(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Dept> depts = deptService.listAll();
        String str = JSON.toJSONString(depts);
        PrintWriter out = resp.getWriter();
        out.append(str);
    }

    public void listAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取查询部门名
        String deptName = request.getParameter("deptName");
        deptName = deptName == null ? "" : deptName;

        //设置分页
        Integer count = deptService.count(deptName);
        String pageCurrent = request.getParameter("pageCurrent");
        pageCurrent = pageCurrent == null ? String.valueOf(1) : pageCurrent;
        Page page = new Page();
        page.setPageCurrent(Integer.valueOf(pageCurrent));
        page.setCount(count);

        List<Dept> deptList = deptService.listDept(deptName, page);

        request.setAttribute("deptList", deptList);
        request.setAttribute("deptName", deptName);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/view/sys/dept/list.jsp").forward(request, response);
    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        if (id == null) {
            return;
        }
        Integer deptCountById = deptService.deptCountById(Integer.valueOf(id));
        if (deptCountById == 0) {
            deptService.deleteDeptById(Integer.valueOf(id));
        }
        request.getRequestDispatcher("/sys/dept/listAll").forward(request, response);
//        PrintWriter out = response.getWriter();
//        out.append("400");
    }
}
