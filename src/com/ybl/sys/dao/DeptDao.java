package com.ybl.sys.dao;

import com.ybl.sys.entity.Dept;
import com.ybl.sys.entity.Page;
import com.ybl.utils.DBUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/2 18:30
 * @desciption
 */
public class DeptDao {
    JdbcTemplate template = new JdbcTemplate(DBUtil.getDataSource());

    public List<Dept> listAll() {
        String sql = "select * from sys_dept where del_flag=0";
        return template.query(sql, new BeanPropertyRowMapper<>(Dept.class));
    }

    public List<Dept> listDept(String deptName, Page page) {
        String sql = "SELECT " +
                "u.NAME createPerson," +
                "d.* " +
                " FROM " +
                "sys_dept d" +
                " LEFT JOIN sys_user u ON d.create_by = u.id " +
                "where d.name like ? and d.del_flag=0 " +
                " ORDER BY " +
                "d.create_time DESC " +
                " limit ?,?";
        return template.query(sql, new BeanPropertyRowMapper<>(Dept.class), "%" + deptName + "%", (page.getPageCurrent() - 1) * page.getPageSize(), page.getPageSize());
    }

    public Integer count(String deptName) {
        String sql = "select count(*) from sys_dept where name like ? and del_flag=0";
        return template.queryForObject(sql, Integer.class, "%" + deptName + "%");
    }

    public Integer deptCountById(Integer deptId) {
        String sql = "select count(*) from sys_user u left join sys_dept d on d.id=u.dept_id where d.id=? and d.del_flag=0";
        return template.queryForObject(sql, Integer.class, deptId);
    }

    public void deleteDeptById(Integer deptId) {
//        物理删除
//        String sql = "delete from sys_dept where id=?";
        //逻辑删除
        String sql = "update sys_dept set del_flag=1 where id=?";
        template.update(sql, deptId);
    }

    public void addDept(Dept dept) {
        String sql = "insert into sys_dept (name,create_time,create_by,del_flag )values (?,?,?,0)";
        template.update(sql, dept.getName(), dept.getCreateTime(), dept.getCreateBy());
    }

    public Dept getDeptById(Integer id) {
        String sql = "select u.name createPerson, d.* from sys_dept d left join sys_user u on d.create_by=u.id where d.id=?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Dept.class), id);
    }

    public void updateDept(Dept dept) {
        String sql = "update sys_dept set name=?,create_time=? ,create_by=? where id=?";
        template.update(sql, dept.getName(), dept.getCreateTime(), dept.getCreateBy(), dept.getId());
    }
}
