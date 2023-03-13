package com.qdu.graduationProject.wechatApplet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class WechatAppletApplication {

    private static final Logger LOG = LoggerFactory.getLogger(WechatAppletApplication.class);

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(WechatAppletApplication.class);
        Environment environment = app.run(args).getEnvironment();
        LOG.info("启动成功");
        LOG.info("地址:\thttp://localhost:{}", environment.getProperty("server.port"));
    }
}
