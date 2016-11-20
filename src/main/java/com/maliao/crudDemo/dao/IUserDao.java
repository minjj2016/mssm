package com.maliao.crudDemo.dao;

import com.maliao.crudDemo.entity.Users;

import java.util.List;

/**
 * Created by minjj on 2016/10/23 0023.
 */
public interface IUserDao {

    //查询所有用户信息
    List<Users>  quaryAll();

    //添加用户信息

}
