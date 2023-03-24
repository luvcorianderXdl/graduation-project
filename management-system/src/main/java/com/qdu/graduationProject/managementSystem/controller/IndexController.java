package com.qdu.graduationProject.managementSystem.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xdl
 * @date 2023/3/13 11:21
 */
@Controller
public class IndexController {

    public static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    // 设置项目默认的首页（欢迎页）
    // http://localhost:8080/
    // 如果是80端口号，可以不用写
    // http://localhost/
    @RequestMapping("/")
    public String index() {
//        System.out.println("IndexController.index");
        logger.debug("IndexController.index");
        return "index";
    }
}
