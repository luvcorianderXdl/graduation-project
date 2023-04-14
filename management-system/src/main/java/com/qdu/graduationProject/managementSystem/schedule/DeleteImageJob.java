package com.qdu.graduationProject.managementSystem.schedule;

import com.qdu.graduationProject.commonUtils.utils.QiniuUtil;
import com.qdu.graduationProject.managementSystem.service.SectionService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xdl
 * @date 2023/4/14 10:30
 */
@Component
@Configuration
@EnableScheduling
public class DeleteImageJob {

    @Resource
    private SectionService sectionService;

//    //每分钟测试
//    @Scheduled(cron = "0 */1 * * * ?")

    /**
     * 每两天执行一次,时间为第一天的凌晨四点
     */
    @Scheduled(cron = "0 0 4 1-31/2 * ?")
    public void deleteImage() {
        List<String> sectionImagesInDB = sectionService.getImagesByUseFlag();
        QiniuUtil.deleteImagesNotUse(sectionImagesInDB);
    }
}
