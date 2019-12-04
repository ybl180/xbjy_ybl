package com.ybl.sys.control;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ybl.sys.entity.Menu;
import com.ybl.sys.service.impl.MenuServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/11/29 17:23
 * @desciption
 */
@WebServlet("/sys/menu")
public class MenuServlet extends HttpServlet {
    private MenuServiceImpl menuService = new MenuServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Menu> list = menuService.listAll();

        List<Menu> listParent = new ArrayList<>();
        List<Menu> listSon = new ArrayList<>();


        listParent = list.stream().filter(menu -> {
            return menu.getType().equals("1");
        }).collect(Collectors.toList());

        listSon = list.stream().filter(menu -> menu.getType().equals("2")).collect(Collectors.toList());

        Map<String,List> map=new HashMap<>();
        map.put("parent",listParent);
        map.put("son",listSon);

        String str = JSON.toJSONString(map);
        PrintWriter out = resp.getWriter();
        out.write(str);
    }
}
