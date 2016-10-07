package com.maliao.seckill.service;

import com.maliao.seckill.dto.Exposer;
import com.maliao.seckill.dto.SeckillExcution;
import com.maliao.seckill.entity.Seckill;
import com.maliao.seckill.exception.RepeatKillException;
import com.maliao.seckill.exception.SeckillCloseException;
import com.maliao.seckill.exception.SeckillException;

import java.util.List;

/**
 * 业务接口 : 站在"使用者的角度"设计接口
 * 主要从三个方面：方法定义粒度、参数、返回类型。
 * Created by minjj on 2016/9/19 0019.
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     * @return
     */
    List<Seckill>  getSeckillList();

    /**
     * 查询单个秒杀活动
     * @param id
     * @return
     */
    Seckill getById(long id);


    /**
     * 秒杀开启时输出秒杀接口url,否则输入秒杀开启时间和系统时间
     * @param seckIllId
     */
    Exposer exportSeckillUrl(long seckIllId);

    /**
     * 执行秒杀从操作
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExcution executeSeckill(long seckillId,long userPhone,String md5) throws SeckillException,RepeatKillException,SeckillCloseException;
}
