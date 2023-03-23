package com.qdu.graduationProject.managementSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ManagementSystemApplication {

    private static final Logger LOG = LoggerFactory.getLogger(ManagementSystemApplication.class);

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(ManagementSystemApplication.class);
        Environment environment = app.run(args).getEnvironment();
        LOG.info("启动成功");
        LOG.info("地址:\thttp://localhost:{}", environment.getProperty("server.port"));
    }
}
