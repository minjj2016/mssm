package com.maliao.crudDemo.controller;

import com.maliao.crudDemo.entity.Users2;
import com.maliao.crudDemo.dao.Users2Mapper;
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

    @RequestMapping(value = "queryAll")
    @ResponseBody
    public List<Users2> queryAll(){
        return users2Mapper.selectAll();
    }

}
