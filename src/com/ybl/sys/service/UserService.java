package com.ybl.sys.service;

import com.ybl.sys.entity.TimeCondition;
import com.ybl.sys.entity.Page;
import com.ybl.sys.entity.User;

import java.util.List;

public interface UserService {
    /***
     *@desciption 分页展示用户列表
     *@author ybl
     *@date 2019/12/2 16:46
     *@param [account, page]
     *@return java.util.List<com.ybl.sys.entity.User>
     */
    public List<User> listAll(String account, Page page, TimeCondition condition);

    /***
     *@desciption 计算查询总数据
     *@author ybl
     *@date 2019/12/2 17:10
     *@param [account]
     *@return java.lang.Integer
     */
    public Integer count(String account, TimeCondition condition);

    /***
     *@desciption 添加用户
     *@author ybl
     *@date 2019/12/2 19:14
     *@param [user]
     *@return void
     */
    public void addUser(User user);


    /***
     *@desciption 根据id删除用户
     *@author ybl
     *@date 2019/12/2 19:45
     *@param [id]
     *@return void
     */
    public void delByIdUser(Integer id);

    /***
     *@desciption 通过id查询用户
     *@author ybl
     *@date 2019/12/3 08:59
     *@param [id]
     *@return com.ybl.sys.entity.User
     */
    public User getUserById(Integer id);

    /***
     *@desciption 更新用户信息
     *@author ybl
     *@date 2019/12/3 09:18
     *@param [user]
     *@return void
     */
    public void updateUser(User user);

    /***
    *@desciption  修改密码
    *@author ybl
    *@date 2019/12/3 16:45
    *@param [user]
    *@return void
    */
    public void updatePassword(User user) ;

    /***
    *@desciption  登录验证
    *@author ybl
    *@date 2019/12/4 18:07
    *@param [user]
    *@return java.util.List<com.ybl.sys.entity.User>
    */
    public List<User> checkLogin(User user) ;

}
