package com.maliao.wexin.controller;

import com.maliao.wexin.utils.WeChatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by minjj on 2016/10/14 0014.
 */
@Controller
@RequestMapping(value = "/wexin")
public class WeChatController {

    private static Log log = LogFactory.getLog(WeChatController.class);
    /**
     * 微信
     * @param request
     * @param response
     */
    @RequestMapping(value = "check")
    public void wexinCheck(HttpServletRequest request,HttpServletResponse response){
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        System.out.println("signature:"+signature+",timestamp:"+timestamp+",nonce:"+nonce+",echostr:"+echostr);
        log.info("signature:"+signature+",timestamp:"+timestamp+",nonce:"+nonce+",echostr:"+echostr);

        PrintWriter out = null;
        try {
            out = response.getWriter();
            if(WeChatUtils.checkSignature(signature,timestamp,nonce)){
                out.print(echostr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
