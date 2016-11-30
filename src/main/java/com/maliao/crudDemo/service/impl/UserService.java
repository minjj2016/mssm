package com.maliao.crudDemo.service.impl;

import com.maliao.base.dao.IBaseDao;
import com.maliao.crudDemo.entity.Users3;
import com.maliao.crudDemo.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
@Service
public class UserService implements IUserService{

    @Resource
    private IBaseDao baseDao;
    @Override
    public List<Users3> queryAll() throws Exception {
        return (List<Users3>) baseDao.findAll("Users3Mapper.selectAll");
    }
}
