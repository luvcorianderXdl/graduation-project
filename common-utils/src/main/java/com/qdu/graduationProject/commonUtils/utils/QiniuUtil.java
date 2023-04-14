package com.qdu.graduationProject.commonUtils.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.model.FileListing;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xdl
 * @date 2023/3/30 11:43
 */
public class QiniuUtil {
    public static final String ACCESS_KEY = "8MIrQqMTlip00gzhTUZLXPv4evpNGZidD6RLckO7";
    public static final String SECRET_KEY = "bRX0Sks9T8I9EQ3MvtEa8SkBuemOrVjmdeRWMJQc";
    public static final String BUCKET = "graduation-project-nylxdl";

    public static final Logger logger = LoggerFactory.getLogger(QiniuUtil.class);

    //上传文件
    public static void upload2Qiniu(byte[] bytes, String fileName) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = fileName;
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String upToken = auth.uploadToken(BUCKET);
        try {
            Response response = uploadManager.put(bytes, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    public static void deleteImagesNotUse(List<String> sectionImagesInDB) {
        List<String> sectionImagesInCloud = getImagesInCloudByFolder(UrlPrefixUtil.getSectionPrefix());
        logger.info("云端数量:" + sectionImagesInCloud.size() + "   数据库数量:" + sectionImagesInDB.size());
        sectionImagesInCloud.removeAll(sectionImagesInDB);
        logger.info("待删除数量:" + sectionImagesInCloud.size());
        for (String image : sectionImagesInCloud) {
            delete(image, UrlPrefixUtil.getSectionPrefix());
        }
        logger.info("删除成功!");
    }

    public static List<String> getImagesInCloudByFolder(String folder) {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        Configuration cfg = new Configuration();
        BucketManager bucketManager = new BucketManager(auth, cfg);

        int limit = 1000;
        String delimiter = "/";
        String marker = "";

        List<String> imagesInCloud = new ArrayList<>();
        try {
            FileListing fileListing = bucketManager.listFiles(BUCKET, folder, marker, limit, delimiter);
            FileInfo[] items = fileListing.items;
            for (FileInfo fileInfo : items) {
                String fileName = fileInfo.key.substring(folder.length());
                imagesInCloud.add(fileName);
            }
            imagesInCloud.remove("");
        } catch (QiniuException e) {
            Response r = e.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
        return imagesInCloud;
    }

    public static void delete(String fileName, String folder) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());
        String key = fileName;
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(BUCKET, folder + key);
            logger.info("删除 -> " + folder + key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }

    public static void main(String[] args) {
        delete("aa.jpeg", UrlPrefixUtil.getSectionPrefix());
    }
}