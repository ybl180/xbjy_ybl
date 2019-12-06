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

    /***
    *@desciption  查出所有的部门
    *@author ybl
    *@date 2019/12/5 17:08
    *@param [req, resp]
    *@return void
    */
    public void list(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Dept> depts = deptService.listAll();
        String str = JSON.toJSONString(depts);
        PrintWriter out = resp.getWriter();
        out.append(str);
    }

    /***
     *@desciption  模糊条件，分页 查出所有的部门
     *@author ybl
     *@date 2019/12/5 17:08
     *@param [req, resp]
     *@return void
     */
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
        for (Dept aDeptList : deptList) {
            //每个部门下的人数
            aDeptList.setDeptCount(deptService.deptCountById(aDeptList.getId()));
        }

        request.setAttribute("deptList", deptList);
        request.setAttribute("deptName", deptName);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/view/sys/dept/listDept.jsp").forward(request, response);
    }

    /***
    *@desciption  通过id逻辑删除部门 （部门下有人不能删除）
    *@author ybl
    *@date 2019/12/5 17:09
    *@param
    *@return
    */
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        if (id == null) {
            return;
        }
        PrintWriter out = response.getWriter();
        Integer deptCountById = deptService.deptCountById(Integer.valueOf(id));
        if (deptCountById == 0) {
            deptService.deleteDeptById(Integer.valueOf(id));
            //删除成功
            out.append("200");
        } else {
            //删除失败
            out.append("400");
        }
    }

    /***
    *@desciption 添加部门
    *@author ybl
    *@date 2019/12/6 12:16
    *@param [request, response]
    *@return void
    */
    public void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String deptName = request.getParameter("name");
        Dept dept = new Dept();
        dept.setName(deptName);
        dept.setCreateBy(this.getLoginUser().getId());
        deptService.addDept(dept);
        response.sendRedirect("/sys/dept/listAll");
    }

    /***
    *@desciption  通过id获取部门的信息
    *@author ybl
    *@date 2019/12/6 12:16
    *@param [request, response]
    *@return void
    */
    public void getDeptById(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        if (id == null) {
            return;
        }
        Dept deptById = deptService.getDeptById(Integer.valueOf(id));
        request.setAttribute("dept", deptById);
        request.getRequestDispatcher("/view/sys/dept/updateDept.jsp").forward(request, response);
    }

    /***
    *@desciption 更新部门信息
    *@author ybl
    *@date 2019/12/6 12:17
    *@param [request, response]
    *@return void
    */
    public void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        String id = request.getParameter("id");
        if (id == null) {
            return;
        }
        Dept dept = new Dept();
        dept.setName(name);
        dept.setId(Integer.valueOf(id));
        dept.setCreateBy(super.getLoginUser().getId());
        deptService.updateDept(dept);
        response.sendRedirect("/sys/dept/listAll");
    }
}
