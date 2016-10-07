package com.maliao.seckill.exception;

import com.maliao.seckill.dto.SeckillExcution;

/**
 * 秒杀关闭异常
 * Created by minjj on 2016/9/19 0019.
 */
public class SeckillCloseException extends SeckillException {

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
