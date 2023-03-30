package com.qdu.graduationProject.commonUtils.utils;

/**
 * @author xdl
 * @date 2023/3/30 14:55
 */
public class UrlPrefixUtil {
    //访问云端的url举例： 访问板块下的1.jpg: http://rsbe1zn2j.hd-bkt.clouddn.com/section/1.jpg
    //上传举例： 上传到板块文件夹内 section/1.jpg
    /**
     * 总前缀
     */
    static String PREFIX = "http://rsbe1zn2j.hd-bkt.clouddn.com/";

    /**
     * 板块文件夹前缀 存放板块的图片
     */
    static String SECTION = "section/";

    /**
     * 用户文件夹前缀 存放用户的头像
     */
    static String USER = "user/";

    public static String getSectionPrefix() {
        return SECTION;
    }

    public static String getFullSectionPrefix() {
        return PREFIX + SECTION;
    }

}
