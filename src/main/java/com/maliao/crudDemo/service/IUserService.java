package com.maliao.crudDemo.service;

import com.maliao.crudDemo.entity.Users3;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
public interface IUserService {

    List<Users3>  queryAll() throws Exception;
}
