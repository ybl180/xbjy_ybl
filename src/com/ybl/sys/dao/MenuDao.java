package com.ybl.sys.dao;

import com.ybl.sys.entity.Menu;
import com.ybl.utils.DBUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/11/29 17:14
 * @desciption
 */
public class MenuDao {
    JdbcTemplate template = new JdbcTemplate(DBUtil.getDataSource());

    public List<Menu> listAll() {
        String sql = "select * from sys_menu order by order_by";
        return template.query(sql, new BeanPropertyRowMapper<>(Menu.class));
    }
}
