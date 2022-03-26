package com.guo.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.guo.oss.service.OssService;
import com.guo.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.UUID;

/**
 * @ClassName OssServiceImpl
 * @Description TODO
 * @Author GuoSheng
 * @Date 2021/5/25  17:22
 * @Version 1.0
 **/
@Service
public class OssServiceImpl implements OssService {
    //上传头像到阿里云oss的方法
    @Override
    public String uploadFileAvatar(MultipartFile file) {

        String endPoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        try {
// 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);

// 上传文件流。
            InputStream inputStream = file.getInputStream();

            //获取文件名称
            String fileOriginalFilename = file.getOriginalFilename();

            //使用uuid在文件名称里面添加唯一值
            String uuid = UUID.randomUUID().toString().replace("-", "");
            fileOriginalFilename = uuid + fileOriginalFilename;

            String datePath = new DateTime().toString("yyyy/MM/dd");

            fileOriginalFilename = datePath + "/" + fileOriginalFilename;

            //调用oss进行上传
            //第一个参数BucketName，第二个参数上传到oss文件的路径和名称,第三个文件输入流
            ossClient.putObject(bucketName, fileOriginalFilename, inputStream);

// 关闭OSSClient。
            ossClient.shutdown();
            //https://edu-guo-1023.oss-cn-beijing.aliyuncs.com/2.jpg?
            // Expires=1621936212&OSSAccessKeyId=TMP.3KeYvgXWdAVQKJYVx6iNo6mrD7ZeTwKEhhkK6f2KJUdwVv2QsSjnZgsSorefgyXhsS87Q9kvhGefuxwxMZpNLeDcU8zcgd
            // &Signature=FQZplCHrOZQInw4WfZULWUUXEyk%3D
            String url = "https://" + bucketName + "." + endPoint + "/" + fileOriginalFilename;

            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
