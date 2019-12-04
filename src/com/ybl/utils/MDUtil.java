package com.ybl.utils;

import javax.xml.transform.Result;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/3 15:14
 * @desciption
 */
public class MDUtil {

    //盐值
    private static final String SALT = "skdjfl";

    public static String md5(String password) {
        String result = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("md5");
//            md = MessageDigest.getInstance("sha");
            md.update((password + SALT).getBytes());
            //加密后的密文(32位),可以多次加密
            result = new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

}
