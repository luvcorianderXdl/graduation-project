package com.qdu.graduationProject.managementSystem.controller;

import com.qdu.graduationProject.commonUtils.utils.JSONResult;
import com.qdu.graduationProject.commonUtils.utils.QiniuUtil;
import com.qdu.graduationProject.commonUtils.utils.UrlPrefixUtil;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @author xdl
 * @date 2023/3/30 15:27
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

    public static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @RequestMapping("/upload2section")
    @ResponseBody
    public JSONResult uploadImage(MultipartFile file) {
        String name = UUID.randomUUID().toString().replace("-", "");
        //原始文件名 a.jpg
        String fileName = file.getOriginalFilename();
        //扩展名 jpg
        String extension = FilenameUtils.getExtension(fileName);
        //上传到目标文件夹的前缀 section/
        String prefix = UrlPrefixUtil.getSectionPrefix();
        //转换后的文件名(存入云端和数据库) e43678927c52445898f757979c077d06.jpg
        String newFileName = name + "." + extension;
        //存云端的路径和文件名  section/e43678927c52445898f757979c077d06.jpg
        String uploadName = prefix + newFileName;
        try {
            QiniuUtil.upload2Qiniu(file.getBytes(), uploadName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info(fileName + " -> " + newFileName + " :上传成功!");
        //将新的文件名返回前端,作为表单元素存到数据库
        return JSONResult.ok("上传成功", newFileName);
    }
}
