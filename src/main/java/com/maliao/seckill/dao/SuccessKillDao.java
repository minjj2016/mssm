package com.maliao.seckill.dao;

import com.maliao.seckill.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * Created by minjj on 2016/9/17 0017.
 */
public interface SuccessKillDao {

    /**
     * 插入购买明细 可以过滤重复秒杀问题
     * @param seckillId
     * @param userPhone
     * @return
     */
    int insertSuccessKilled(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);


    /**
     * 根据id查询SuccessKilled并携带秒杀商品对象
     * @param seckillId
     * @return
     */
    SuccessKilled quaryByIdWithSeckill(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);

}
