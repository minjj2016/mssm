package com.maliao.seckill.controller;


import com.maliao.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by minjj on 2016/7/6.
 */
@Controller
public class BaseController {
    @Autowired
    protected SeckillService seckillService;
}
