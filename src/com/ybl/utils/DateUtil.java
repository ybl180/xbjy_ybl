package com.ybl.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/2 19:15
 * @desciption
 */
public class DateUtil {

    public static String getDateStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}
