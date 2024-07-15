package com.three.server.common.uploadAndGenerateUrl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class AliyunOssService {
    // OSS访问域名
    private final String endpoint = "oss-cn-hangzhou.aliyuncs.com";
    // AccessKey ID和AccessKey Secret
    private final String accessKeyId = "";
    private final String accessKeySecret = "";
    // Bucket名称
    private final String bucketName = "pet-sym";

    public String start(MultipartFile file) {
        if (file.isEmpty()) {
            return null;
        }
        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 文件路径和名称，这里以上传时间作为文件名
            String fileKey = "pet_sys_images/" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
            // 上传文件
            ossClient.putObject(bucketName, fileKey, file.getInputStream());

            return "https://" + bucketName + "." + endpoint + "/" + fileKey;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // 关闭OSSClient
            ossClient.shutdown();
        }
    }

    public String start2( ) {
        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 文件路径和名称，这里以上传时间作为文件名
            String fileKey = "pet_sys_images/" + System.currentTimeMillis() + "_" + "test.jpg";
            // 上传文件
            ossClient.putObject(bucketName, fileKey, new File("C:/Users/72848/Pictures/q.jpg"));

            return "https://" + bucketName + "." + endpoint + "/" + fileKey;
        } finally {
            // 关闭OSSClient
            ossClient.shutdown();
        }
    }

    public static void main(String[] args) {
        AliyunOssService aliyunOssService = new AliyunOssService();
        System.out.println(aliyunOssService.start2());
    }
}
