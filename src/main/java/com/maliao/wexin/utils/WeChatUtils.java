package com.maliao.wexin.utils;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * Created by minjj on 2016/10/14 0014.
 */
public class WeChatUtils {

    private static final String token = "mtec";

    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String arr[] = new String[]{token, timestamp, nonce};
        //排序
        Arrays.sort(arr);
        //生成字符串
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            str.append(arr[i]);
        }

        String temp = getSHA1(str.toString());
        return temp.equals(signature);

    }


    /**
     * sha1加密法
     * @param str
     * @return
     */
    private static String getSHA1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        MessageDigest mdTemp;
        try {
            mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte b0 = md[i];
                buf[k++] = hexDigits[b0 >>> 4 & 0xf];
                buf[k++] = hexDigits[b0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }
}
