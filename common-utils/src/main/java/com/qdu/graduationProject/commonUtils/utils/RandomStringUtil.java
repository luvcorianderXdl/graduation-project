package com.qdu.graduationProject.commonUtils.utils;

/**
 * @author xdl
 * @create 2023/3/9 14:37
 */
public class RandomStringUtil {
    public static String randomString(int length) {
        String xx = "";
        for (short i = '0'; i <= '9'; i++) {
            xx += (char) i;
        }
        for (short i = 'a'; i <= 'z'; i++) {
            xx += (char) i;
        }
        for (short i = 'A'; i <= 'Z'; i++) {
            xx += (char) i;
        }
        char yy[] = new char[length];
        for (int i = 0; i < yy.length; i++) {
            int index = (int) (Math.random() * xx.length());
            yy[i] = xx.charAt(index);
        }
        String result = new String(yy);
        return result;
    }

//    public static void main(String[] args) {
//        System.out.println(RandomStringUtil.randomString(10));
//    }
}
