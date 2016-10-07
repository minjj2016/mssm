package com.maliao.seckill.exception;

import com.maliao.seckill.dto.SeckillExcution;

/**
 * 重复秒杀异常(运行期异常)
 * Java异常主要是分编译期异常和运行期异常，运行期异常不需要手动try-catch，Spring的声明式事务只接受运行期异常回滚策略，
 * 当抛出非运行期异常时Spring不会帮我们做回滚的。
 * Created by minjj on 2016/9/19 0019.
 */
public class RepeatKillException extends SeckillException {

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepeatKillException(String message) {
        super(message);
    }
}
