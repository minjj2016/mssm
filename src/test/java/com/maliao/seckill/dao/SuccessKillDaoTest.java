package com.maliao.seckill.dao;

import com.maliao.seckill.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by minjj on 2016/9/19 0019.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉spring配置文件的位置
@ContextConfiguration({"classpath:conf/spring-db.xml"})
public class SuccessKillDaoTest {

    @Resource
    private SuccessKillDao successKillDao;
    @Test
    public void testInsertSuccessKilled() throws Exception {
        long id = 1000L;
        long phone = 18068873115L;

       int i = successKillDao.insertSuccessKilled(id,phone);
        System.out.println(i);

    }

    @Test
    public void testQuaryByIdWithSeckill() throws Exception {

        long id = 1000L;
        long phone = 18068873115L;
        SuccessKilled successKilled = successKillDao.quaryByIdWithSeckill(id,phone);
        System.out.println(successKilled.getSeckill());
        System.out.println(successKilled);
    }
}