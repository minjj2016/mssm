package com.maliao.seckill.dto;

/**
 * 暴露秒杀地址DTO
 * Created by minjj on 2016/9/19 0019.
 */
public class Exposer {

    //是否开启秒杀
    private boolean exposed;

    private String MD5;
    //秒杀商品id
    private long seckillId;

    //系统当前时间
    private long now;
    //秒杀开启时间
    private long start;
    //秒杀结束时间
    private long end;

    public Exposer(boolean exposed, String MD5, long seckillId) {
        this.exposed = exposed;
        this.MD5 = MD5;
        this.seckillId = seckillId;
    }

    public Exposer(boolean exposed,  long seckillId,long now, long start, long end) {
        this.exposed = exposed;
        this.seckillId = seckillId;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposer(boolean exposed, long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMD5() {
        return MD5;
    }

    public void setMD5(String MD5) {
        this.MD5 = MD5;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "exposed=" + exposed +
                ", MD5='" + MD5 + '\'' +
                ", seckillId=" + seckillId +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
