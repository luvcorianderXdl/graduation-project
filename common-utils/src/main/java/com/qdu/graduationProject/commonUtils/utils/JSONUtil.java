package com.qdu.graduationProject.commonUtils.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xdl
 * @create 2023/3/17 15:20
 */
public class JSONUtil {

    private JSONUtil() {

    }

    // Student  Map   List
    public static void toJson(HttpServletResponse resp, Object obj) {
        try {
            resp.setContentType("text/html;charset=utf-8");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(resp.getWriter(), obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
