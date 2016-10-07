package com.maliao.seckill.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.maliao.seckill.dto.Exposer;
import com.maliao.seckill.dto.SeckillExcution;
import com.maliao.seckill.dto.SeckillResult;
import com.maliao.seckill.entity.Seckill;
import com.maliao.seckill.enums.SeckillStatEnum;
import com.maliao.seckill.exception.RepeatKillException;
import com.maliao.seckill.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by minjj on 2016/7/6.
 */
@Controller
@RequestMapping("/seckill")//url：/模块/资源/{id}/细分/seckill/list
public class SeckillController extends BaseController{

    private Gson gson=new Gson();

    private Logger logger= LoggerFactory.getLogger(SeckillController.class);

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        List<Seckill> seckillList = seckillService.getSeckillList();
        model.addAttribute("list",seckillList);
        return "seckill/list";
    }

    @RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable Long seckillId, Model model){
        if(seckillId==null){
            return "redirect:/seckill/list";
        }
        Seckill seckillById = seckillService.getById(seckillId);
        if(seckillById==null){
            return "forward:/seckill/list";
        }
        logger.info("detail");
        model.addAttribute("seckill",seckillById);
        return "seckill/detail";
    }

    @RequestMapping(value = "/{seckillId}/exposer",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public SeckillResult exposer(@PathVariable Long seckillId){
        SeckillResult<Exposer> seckillResult;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            seckillResult=new SeckillResult<Exposer>(true,exposer);
        }catch (Exception e){
            logger.error(e.getMessage());
            seckillResult=new SeckillResult<Exposer>(false,e.getMessage());
        }
        return seckillResult;
    }

    @RequestMapping(value = "/{seckillId}/{md5}/execution",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public SeckillResult<SeckillExcution> excute(@PathVariable Long seckillId, @PathVariable String md5,
                         @CookieValue(value = "killphone",required = false) Long userPhone){
        if(userPhone==null){
            SeckillResult<SeckillExcution> seckillResult = new SeckillResult<SeckillExcution>(false, "注册");
            return seckillResult;
        }
        SeckillResult<SeckillExcution> seckillResult;
        try {
            SeckillExcution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
            //改为存储过程去秒杀
//            SeckillExecution seckillExecution = seckillService.executeSeckillProcedure(seckillId, userPhone, md5);
            seckillResult=new SeckillResult<SeckillExcution>(true,seckillExecution);
            return seckillResult;
        }catch (RepeatKillException e1){
            logger.error(e1.getMessage());
            SeckillExcution seckillExecution=new SeckillExcution(seckillId, SeckillStatEnum.REPEAT_KILL);
            seckillResult=new SeckillResult<SeckillExcution>(true,seckillExecution);
            return (seckillResult);
        }
        catch (SeckillCloseException e2){
            logger.error(e2.getMessage());
            SeckillExcution seckillExecution=new SeckillExcution(seckillId, SeckillStatEnum.END);
            seckillResult=new SeckillResult<SeckillExcution>(true,seckillExecution);
            return (seckillResult);
        }
        catch (Exception e){
            logger.error(e.getMessage());
            seckillResult=new SeckillResult<SeckillExcution>(true,e.getMessage());
            return (seckillResult);
        }
    }

    @RequestMapping(value = "/time/now",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public  String time(){
        Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        SeckillResult<Date> seckillResult;
        seckillResult=new SeckillResult<Date>(true,new Date());
        return gson.toJson(seckillResult);
    }
}
