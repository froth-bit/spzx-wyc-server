package com.itperson.spzx.manager.service.Impl;

import cn.hutool.core.date.DateUtil;
import com.itperson.spzx.common.exception.PersonException;
import com.itperson.spzx.manager.properties.MinioProperties;
import com.itperson.spzx.manager.service.FileUploadService;
import com.itperson.spzx.model.vo.common.ResultCodeEnum;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    MinioProperties minioProperties;

    @Override
    public String fileUpload(MultipartFile file) {
        try {
            // 创建minioClient对象
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint(minioProperties.getEndpointUrl())
                            .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                            .build();

            // Make 'asiatrip' bucket if not exist.
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket("spzx-bucket").build());
            if (!found) {
                // Make a new bucket called 'asiatrip'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("spzx-bucket").build());
            } else {
                System.out.println("Bucket 'spzx-bucket' 存在");
            }

            //做文件区别
            String dateDir = DateUtil.format(new Date(), "yyyyMMdd");
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String filename = dateDir+"/"+uuid+file.getOriginalFilename();

            //文件上传
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket("spzx-bucket")
                            .object(filename)
                            .stream(file.getInputStream(),file.getSize(),-1)
                            .build());

            return minioProperties.getEndpointUrl()+"/"+minioProperties.getBucketName()+"/"+filename;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersonException(ResultCodeEnum.SYSTEM_ERROR);
        }
    }

}
