package com.maliao.seckill.dao;

import com.maliao.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by minjj on 2016/9/18 0018.
 *
 *  配置spring与junit整合,junit启动时加载spring容器
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉spring配置文件的位置
@ContextConfiguration({"classpath:conf/spring-db.xml"})
public class SeckillDaoTest {

    @Autowired
    private SeckillDao seckillDao;

    @Test
    public void testReduceNumber() throws Exception {

        Date date = new Date();
        int i = seckillDao.reduceNumber(1000,date);
        System.out.println(i);
    }

    @Test
    public void testQuaryById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillDao.quaryById(id);
        System.out.println(seckill.toString());
    }

    /**
     *  org.apache.ibatis.binding.BindingException: Parameter 'offet' not found. Available parameters are [0, 1, param1, param2]
     *  java没有保存形参的记录
     * @throws Exception
     */
    @Test
    public void testQuaryAll() throws Exception {
        List<Seckill> list = seckillDao.quaryAll(0,100);
        System.out.println(list);
    }
}