package com.ybl.sys.service;

import com.ybl.sys.entity.Dept;
import com.ybl.sys.entity.Page;

import java.util.List;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/2 18:35
 * @desciption
 */
public interface DeptService {
    /***
     *@desciption 所有部门
     *@author ybl
     *@date 2019/12/2 18:36
     *@param []
     *@return java.util.List<com.ybl.sys.entity.Dept>
     */
    public List<Dept> listAll();

    /***
     *@desciption 模糊分页查询部门
     *@author ybl
     *@date 2019/12/3 19:06
     *@param [deptName, page]
     *@return java.util.List<com.ybl.sys.entity.Dept>
     */
    public List<Dept> listDept(String deptName, Page page);

    /***
     *@desciption 计算查询总数据
     *@author ybl
     *@date 2019/12/3 19:06
     *@param [deptName]
     *@return java.lang.Integer
     */
    public Integer count(String deptName);

    /***
     *@desciption 部门下的人数
     *@author ybl
     *@date 2019/12/3 19:56
     *@param [deptId]
     *@return java.lang.Integer
     */
    public Integer deptCountById(Integer deptId);

    /***
     *@desciption 通过id删除部门
     *@author ybl
     *@date 2019/12/3 19:44
     *@param [deptId]
     *@return void
     */
    public void deleteDeptById(Integer deptId);

    /***
     *@desciption 添加部门
     *@author ybl
     *@date 2019/12/4 10:49
     *@param [dept]
     *@return void
     */
    public void addDept(Dept dept);

    /***
    *@desciption 通过id获取部门
    *@author ybl
    *@date 2019/12/4 12:04
    *@param [id]
    *@return com.ybl.sys.entity.Dept
    */
    public Dept getDeptById(Integer id) ;

    /***
    *@desciption 更改部门信息
    *@author ybl
    *@date 2019/12/4 12:04
    *@param [dept]
    *@return void
    */
    public void updateDept(Dept dept) ;
}
