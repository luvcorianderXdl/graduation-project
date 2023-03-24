package com.qdu.graduationProject.commonUtils.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xdl
 * @date 2023/3/23 9:43
 */
public class DateUtil {
    public static String getDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = formatter.format(date);
        return formattedDate;
    }
}
