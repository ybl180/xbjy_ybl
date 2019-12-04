package com.ybl.sys.service.impl;

import com.sun.xml.internal.bind.v2.model.core.ID;
import com.ybl.sys.dao.DeptDao;
import com.ybl.sys.entity.Dept;
import com.ybl.sys.entity.Page;
import com.ybl.sys.service.DeptService;
import com.ybl.utils.DateUtil;

import java.util.List;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/2 18:37
 * @desciption
 */
public class DeptServiceImpl implements DeptService {
    DeptDao deptDao = new DeptDao();

    /***
     *@desciption 所有部门
     *@author ybl
     *@date 2019/12/2 18:39
     *@param []
     *@return java.util.List<com.ybl.sys.entity.Dept>
     */
    @Override
    public List<Dept> listAll() {
        return deptDao.listAll();
    }

    /***
     *@desciption 模糊分页查询部门
     *@author ybl
     *@date 2019/12/3 18:24
     *@param []
     *@return java.util.List<com.ybl.sys.entity.Dept>
     */
    public List<Dept> listDept(String deptName, Page page) {
        return deptDao.listDept(deptName, page);
    }

    /***
     *@desciption 计算查询总数据
     *@author ybl
     *@date 2019/12/3 19:06
     *@param [deptName]
     *@return java.lang.Integer
     */
    public Integer count(String deptName) {
        return deptDao.count(deptName);
    }

    /***
     *@desciption  部门下的人数
     *@author ybl
     *@date 2019/12/3 19:56
     *@param [deptId]
     *@return java.lang.Integer
     */
    public Integer deptCountById(Integer deptId) {
        return deptDao.deptCountById(deptId);
    }

    /***
     *@desciption 通过id删除部门
     *@author ybl
     *@date 2019/12/3 19:44
     *@param [deptId]
     *@return void
     */
    public void deleteDeptById(Integer deptId) {
        deptDao.deleteDeptById(deptId);
    }

    /***
    *@desciption   添加部门
    *@author ybl
    *@date 2019/12/4 12:05
    *@param [dept]
    *@return void
    */
    public void addDept(Dept dept){
        dept.setCreateTime(DateUtil.getDateStr());
        deptDao.addDept(dept);
    }

    /***
     *@desciption 通过id获取部门
     *@author ybl
     *@date 2019/12/4 12:04
     *@param [id]
     *@return com.ybl.sys.entity.Dept
     */
    public Dept getDeptById(Integer id) {
        return deptDao.getDeptById(id);
    }

    /***
     *@desciption 更改部门信息
     *@author ybl
     *@date 2019/12/4 12:04
     *@param [dept]
     *@return void
     */
    public void updateDept(Dept dept) {
        dept.setCreateTime(DateUtil.getDateStr());
        deptDao.updateDept(dept);
    }
}
