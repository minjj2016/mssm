package com.maliao.mAdmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/11/30.
 */
@RequestMapping("/mAdmin")
@Controller
public class LoginController {

    @RequestMapping("/index")
    public String indexPage(){
        return "mAdmin/common/index";
    }
}
