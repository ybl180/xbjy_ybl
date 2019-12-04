package com.ybl.sys.service.impl;

import com.ybl.sys.dao.UserDao;
import com.ybl.sys.entity.TimeCondition;
import com.ybl.sys.entity.Page;
import com.ybl.sys.entity.User;
import com.ybl.sys.service.UserService;
import com.ybl.utils.DateUtil;
import com.ybl.utils.MDUtil;

import java.util.List;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/2 16:47
 * @desciption
 */
public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDao();

    /***
     *@desciption 分页展示用户列表
     *@author ybl
     *@date 2019/12/2 16:48
     *@param [account, page]
     *@return java.util.List<com.ybl.sys.entity.User>
     */
    @Override
    public List<User> listAll(String account, Page page, TimeCondition condition) {
        return userDao.listAll(account, page, condition);
    }

    /***
     *@desciption 计算查询总数据
     *@author ybl
     *@date 2019/12/2 17:11
     *@param [account]
     *@return java.lang.Integer
     */
    public Integer count(String account, TimeCondition condition) {
        return userDao.count(account, condition);
    }

    /***
     *@desciption 添加用户
     *@author ybl
     *@date 2019/12/2 19:14
     *@param [user]
     *@return void
     */
    public void addUser(User user) {
        //密码加密
        user.setPassword(MDUtil.md5(user.getPassword()));
        user.setCreateTime(DateUtil.getDateStr());
        userDao.addUser(user);
    }

    /***
     *@desciption 根据id删除用户
     *@author ybl
     *@date 2019/12/2 19:45
     *@param [id]
     *@return void
     */
    public void delByIdUser(Integer id) {
        userDao.delByIdUser(id);
    }

    /***
     *@desciption 通过id查询用户
     *@author ybl
     *@date 2019/12/3 08:59
     *@param [id]
     *@return com.ybl.sys.entity.User
     */
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    /***
     *@desciption 更新用户信息
     *@author ybl
     *@date 2019/12/3 09:19
     *@param [user]
     *@return void
     */
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    /***
     *@desciption 修改密码
     *@author ybl
     *@date 2019/12/3 16:45
     *@param [user]
     *@return void
     */
    public void updatePassword(User user) {
        user.setPassword(MDUtil.md5(user.getPassword()));
        userDao.updatePassword(user);
    }

    /***
     *@desciption 登录验证
     *@author ybl
     *@date 2019/12/4 18:07
     *@param [user]
     *@return java.util.List<com.ybl.sys.entity.User>
     */
    public List<User> checkLogin(User user) {
        try {
            return userDao.checkLogin(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
