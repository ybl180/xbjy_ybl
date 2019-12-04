package com.ybl.sys.dao;

import com.ybl.sys.entity.TimeCondition;
import com.ybl.sys.entity.Page;
import com.ybl.sys.entity.User;
import com.ybl.utils.DBUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/2 14:43
 * @desciption
 */
public class UserDao {
    JdbcTemplate template = new JdbcTemplate(DBUtil.getDataSource());

    public List<User> listAll(String account, Page page, TimeCondition condition) {
        String sql = "SELECT " +
                "d.NAME deptName," +
                "u.id id," +
                "u.account account," +
                "u.NAME name," +
                "u.age," +
                "u.sex," +
                "u.birth_date," +
                "u.create_time" +
                " FROM " +
                "sys_user u " +
                "LEFT JOIN sys_dept d ON u.dept_id = d.id" +
                " where " +
                "u.account like ? and u.create_time BETWEEN ? and ? limit ?,?";
        return template.query(sql, new BeanPropertyRowMapper<>(User.class), "%" + account + "%", condition.getStartTime(), condition.getEndTime(), (page.getPageCurrent() - 1) * page.getPageSize(), page.getPageSize());
    }

    public Integer count(String account, TimeCondition condition) {
        String sql = "select count(*) from sys_user where account like ? and create_time between ? and ?";
        return template.queryForObject(sql, Integer.class, "%" + account + "%", condition.getStartTime(), condition.getEndTime());
    }

    public void addUser(User user) {
        String sql = "insert into sys_user (dept_id,account,password,name,email,age,sex,birth_date,create_time,create_by) values (?,?,?,?,?,?,?,?,?,?)";
        template.update(sql, user.getDeptId(), user.getAccount(), user.getPassword(), user.getName(), user.getEmail(), user.getAge(), user.getSex(), user.getBirthDate(), user.getCreateTime(), user.getCreateBy());
    }

    public void delByIdUser(Integer id) {
        String sql = "delete from sys_user where id=?";
        template.update(sql, id);
    }

    public User getUserById(Integer id) {
        String sql = "select u.*,d.name deptName from sys_user u left join sys_dept d ON u.dept_id = d.id where u.id=?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }

    public void updateUser(User user) {
        String sql = "update sys_user set dept_id=?,account=?,password=?,name=?,email=?,age=?,sex=?,birth_date=? where id=?";
        template.update(sql, user.getDeptId(), user.getAccount(), user.getPassword(), user.getName(), user.getEmail(), user.getAge(), user.getSex(), user.getBirthDate(), user.getId());
    }

    public void updatePassword(User user) {
        //账号要唯一
        String sql = "update sys_user set password=?where account=?";
        template.update(sql, user.getPassword(), user.getAccount());
    }

    public List<User> checkLogin(User user) {
        String sql = "select * from sys_user where account=? and password=?";
        return template.query(sql, new BeanPropertyRowMapper<>(User.class), user.getAccount(), user.getPassword());
    }

}
