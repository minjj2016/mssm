package com.maliao.hellomvc.dao.impl;

import com.maliao.hellomvc.dao.IStudentDao;
import com.maliao.hellomvc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

/**
 * Created by minjj on 2016/9/16 0016.
 */
@Repository
public class StudentDao implements IStudentDao {

    //自动注入jdbcTemplate
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 使用jdbcTemplate查询用户
     * @param userName 用户名查询条件
     * @return 用户名匹配的User对象
     */
    public User findUserByNameJdbc(final String userName){
        //为了使userName可以再内部类中使用，必须声明为final
        String sqlStr="SELECT * FROM t_user where username= ?";
        final User user = new User();
        //通过匿名内部类定义回调函数 将结果集数据中的数据抽取到User对象中
        jdbcTemplate.query(sqlStr, new Object[]{userName},
                new RowCallbackHandler() {
                    @Override
                    public void processRow(ResultSet rs) throws SQLException {
                        user.setUserName(rs.getString("name"));
                        user.setId(rs.getInt("id"));
                        user.setGender(rs.getInt("gender"));
                    }
                }
        );
        return  user;
    }

    /**
     * 使用jdbcTemplate查询用户
     * @return list<User>对象
     */
    public List findAllUser(){
        //为了使userName可以再内部类中使用，必须声明为final
        String sqlStr="SELECT * FROM t_user";

        //通过匿名内部类定义回调函数 将结果集数据中的数据抽取到User对象中
        List list = jdbcTemplate.query(sqlStr,
               new BeanPropertyRowMapper(User.class));

        return  list;
    }



    //jdbcTemplate.update适合于insert 、update和delete操作；
    /**
     * 第一个参数为执行sql
     * 第二个参数为参数数据
     */
    public void saveUser(User user) {
        jdbcTemplate.update("insert into t_user(username,gender) values(?,?)",
                new Object[]{user.getUserName(), user.getGender()});
    }


    /**
     * 第一个参数为执行sql
     * 第二个参数为参数数据
     * 第三个参数为参数类型
     */
    public void save(User user) {
        jdbcTemplate.update(
                "insert into t_user(username,gender) values(?,?)",
                new Object[]{user.getUserName(),user.getGender()},
                new int[]{java.sql.Types.VARCHAR, Types.INTEGER}
        );
    }

    /**
     * 第一个参数为执行sql
     * 第二个参数为参数数据
     * 第三个参数为参数类型
     */
    public void updateUser(User user) {
        jdbcTemplate.update(
                "update  t_user set username = ?,gender=?  where id =?",
                new Object[]{user.getUserName(),user.getGender(),user.getId()},
                new int[]{java.sql.Types.VARCHAR, java.sql.Types.INTEGER,java.sql.Types.INTEGER}
        );
    }

    /**
     * 第一个参数为执行sql
     * 第二个参数为参数数据
     * 第三个参数为参数类型
     */
    public void delete(int id ) {
        jdbcTemplate.update(
                "delete from t_user where id =?",
                new Object[]{id},
                new int[]{java.sql.Types.INTEGER}
        );
    }


}
