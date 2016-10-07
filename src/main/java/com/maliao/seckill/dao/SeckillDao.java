package com.maliao.seckill.dao;

import com.maliao.seckill.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by minjj on 2016/9/17 0017.
 */
public interface SeckillDao {

    /**
     * 减库存
     *
     * @param seckillId
     * @param killTime
     * @return
     */
    int reduceNumber(@Param("seckillId")long seckillId,@Param("killTime")Date killTime);

    /**
     * 查询秒杀商品
     *
     * @param seckillId
     * @return
     */
    Seckill quaryById(long seckillId);

    /**
     * 根据偏移量查询秒杀商品列表
     *
     * @param offet
     * @param limit
     * @return
     */
    List<Seckill> quaryAll(@Param("offet") int offet, @Param("limit") int limit);
}
