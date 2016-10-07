package com.maliao.seckill.service;

import com.maliao.seckill.dto.Exposer;
import com.maliao.seckill.dto.SeckillExcution;
import com.maliao.seckill.entity.Seckill;
import com.maliao.seckill.exception.RepeatKillException;
import com.maliao.seckill.exception.SeckillCloseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by minjj on 2016/9/24 0024.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉spring配置文件的位置
@ContextConfiguration({"classpath:conf/spring.xml","classpath:conf/spring-db.xml"})
public class SeckillServiceTest {

    private Logger logger= LoggerFactory.getLogger(SeckillServiceTest.class);

    @Autowired
    private SeckillService seckillService;

    @Test
    public void testGetSeckillList() throws Exception {
        List<Seckill> seckills=seckillService.getSeckillList();
        for(Seckill seckill :seckills)
            logger.info(seckill.toString());
        System.out.println(seckills.toString());
    }

    @Test
    public void testGetSeckillById() throws Exception {
        logger.info(seckillService.getById(1000).toString());
        System.out.println(seckillService.getById(1000).toString());
    }

    //Exposer{exposed=true, MD5='2189c3429004de9d30f8cef584542dab', seckillId=1000, now=0, start=0, end=0}
    @Test
    public void testExportSeckillUrl() throws Exception {
        long id=1000;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer: "+exposer);
        System.out.println(exposer.toString());
        //Exposer{exposed=true, MD5='2189c3429004de9d30f8cef584542dab', seckillId=1000, now=0, start=0, end=0}
    }

//    // seckillExecutionSeckillExecution{seckillId=1, state=1, stateInfo='秒杀成功', successKilled=SuccessKilled{, userPhone=1307273, state=0, createTime=Tue Jul 05 20:02:00 CST 2016}}
//    @Test
//    public void testExecuteSeckill() throws Exception {
//        try {
//            SeckillExcution seckillExecution = seckillService.executeSeckill(1000, 15151844880L, "2189c3429004de9d30f8cef584542dab");
//            logger.info("seckillExecution"+seckillExecution);
//            System.out.println(seckillExecution.toString());
//        }catch (RepeatKillException e1){
//            logger.error(e1.getMessage());
//            System.out.println(e1.getMessage());
//        }
//        catch (SeckillCloseException e2){
//            logger.error(e2.getMessage());'
//            System.out.println(e2.getMessage());
//        }
//    }

    /**
     * Exception:java.lang.SecurityException: Seckill inner error:sekill date rewrite
     */
    @Test
    public void testExecuteKill(){
        Exposer exposer = seckillService.exportSeckillUrl(1000);
        if(exposer.isExposed()){
            try {
                SeckillExcution seckillExecution = seckillService.executeSeckill(1000, 15151844877L, "2189c3429004de9d30f8cef584542dab");
                logger.info("seckillExecution"+seckillExecution);
                System.out.println(seckillExecution);
            }catch (RepeatKillException e1){
                logger.error(e1.getMessage());
                System.out.println(e1.getMessage());
            }
            catch (SeckillCloseException e2){
                logger.error(e2.getMessage());
                System.out.println(e2.getMessage());
            }
        }else{
            logger.warn("秒杀还没有开启");
            System.out.println("秒杀还没有开启");
        }
    }

    @Test
    public void executeSeckillProcedure(){
        long seckillId=1;
        long phone=123;
        String md5="";
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if(exposer.isExposed()){
            md5=exposer.getMD5();
            SeckillExcution seckillExecution = seckillService.executeSeckill(seckillId, phone, md5);
            logger.info("seckillEecution: "+seckillExecution);
            System.out.println(seckillExecution);
        }
    }
}