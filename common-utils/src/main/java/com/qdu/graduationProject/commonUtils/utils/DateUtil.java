package com.qdu.graduationProject.commonUtils.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author xdl
 * @date 2023/3/23 9:43
 */
public class DateUtil {
    public static Timestamp getCurrentTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);
        String timestampStr = formattedNow.replace(".0", "");
        return Timestamp.valueOf(timestampStr);
    }

    public static void main(String[] args) {
        Timestamp currentTimestamp = getCurrentTimestamp();
        System.out.println("当前时间对象：" + currentTimestamp);
    }
}
