package com.maliao.crudDemo.controller;

import com.maliao.crudDemo.entity.Users2;
import com.maliao.crudDemo.dao.Users2Mapper;
import com.maliao.crudDemo.entity.Users3;
import com.maliao.crudDemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by minjj on 2016/10/23 0023.
 */
@Controller
@RequestMapping(value = "/crud")
public class UserController {

    @Autowired
    private Users2Mapper users2Mapper;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "queryAll")
    @ResponseBody
    public List<Users2> queryAll(){
        return users2Mapper.selectAll();
    }

    @RequestMapping(value = "queryList")
    @ResponseBody
    public List<Users3> queryAllUser3(){
        List<Users3> list = null;
        try {
            list = userService.queryAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
