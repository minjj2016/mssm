package com.maliao.seckill.service.impl;

import com.maliao.seckill.dao.SeckillDao;
import com.maliao.seckill.dao.SuccessKillDao;
import com.maliao.seckill.dto.Exposer;
import com.maliao.seckill.dto.SeckillExcution;
import com.maliao.seckill.entity.Seckill;
import com.maliao.seckill.entity.SuccessKilled;
import com.maliao.seckill.enums.SeckillStatEnum;
import com.maliao.seckill.exception.RepeatKillException;
import com.maliao.seckill.exception.SeckillCloseException;
import com.maliao.seckill.exception.SeckillException;
import com.maliao.seckill.service.SeckillService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by minjj on 2016/9/19 0019.
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private Log log = LogFactory.getLog(SeckillServiceImpl.class);

    @Resource
    private SeckillDao seckillDao;
    @Resource
    private SuccessKillDao successKillDao;

    //MD5盐值 MD5加盐加密
    private final String salt = "salt";

    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.quaryAll(0, 10);
    }

    @Override
    public Seckill getById(long id) {
        return seckillDao.quaryById(id);
    }

    @Override
    public Exposer exportSeckillUrl(long seckIllId) {
        Seckill seckill = seckillDao.quaryById(seckIllId);
        if (null == seckill) {
            return new Exposer(false, seckIllId);
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date date = new Date();
        if (startTime.getTime() > date.getTime() || date.getTime() > endTime.getTime()) {
            return new Exposer(false, seckIllId, date.getTime(), startTime.getTime(), endTime.getTime());
        }
        String md5 = getMd5(seckIllId);
        return new Exposer(true, md5, seckIllId);
    }

    @Override
    public SeckillExcution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {
        try {
            if (null == md5 || !md5.equals(getMd5(seckillId))) {
                throw new SeckillException("sekill date rewrite");
            }
            //减库存并记录秒杀行为
            int updateCount = seckillDao.reduceNumber(seckillId, new Date());
            if (updateCount <= 0) {
                //没有更新成功
                throw new SeckillCloseException("seckill is closed");
            } else {
                //减库存成功记录购买行为
                int insertCount = successKillDao.insertSuccessKilled(seckillId, userPhone);
                if (insertCount <= 0) {
                    throw new RepeatKillException("重复秒杀");
                } else {
                    SuccessKilled successKilled = successKillDao.quaryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExcution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
                }
            }
        }catch (SeckillCloseException e1){
            throw e1;
        }catch (RepeatKillException e2){
            throw e2;
        }catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new SecurityException("Seckill inner error:" + e.getMessage());
        }
    }

    private String getMd5(long seckillId) {
        String base = seckillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
