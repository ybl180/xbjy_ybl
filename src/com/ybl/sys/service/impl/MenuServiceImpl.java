package com.ybl.sys.service.impl;

import com.ybl.sys.dao.MenuDao;
import com.ybl.sys.entity.Menu;
import com.ybl.sys.service.MenuService;

import java.util.List;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/11/29 17:20
 * @desciption
 */
public class MenuServiceImpl implements MenuService {
    private MenuDao menuDao = new MenuDao();

    /***
    *@desciption    展示菜单列表
    *@author ybl
    *@date 2019/12/2 16:48
    *@param []
    *@return java.util.List<com.ybl.sys.entity.Menu>
    */
    @Override
    public List<Menu> listAll() {
        return menuDao.listAll();
    }
}
