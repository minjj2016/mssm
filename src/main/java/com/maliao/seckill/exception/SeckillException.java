package com.maliao.seckill.exception;

/**
 * 秒杀相关的异常
 * Created by minjj on 2016/9/19 0019.
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
