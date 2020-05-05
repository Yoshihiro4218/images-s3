package com.yoshi.images.domain.repository;

import com.amazonaws.services.s3.*;
import com.amazonaws.services.s3.model.*;
import com.yoshi.images.app.config.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.*;

import java.io.*;
import java.util.*;

@Component
@AllArgsConstructor
@Slf4j
public class ImageRepository {
    private final AmazonS3 s3;
    private final AmazonS3Properties amazonS3Properties;


    public byte[] readImage(String objectKey) throws IOException {
        GetObjectRequest getObjectRequest = new GetObjectRequest(amazonS3Properties.getBucketName(), objectKey);
        S3Object s3Object = s3.getObject(getObjectRequest);
        S3ObjectInputStream is = s3Object.getObjectContent();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        while (true) {
            int len = is.read(buffer);
            if (len < 0) {
                break;
            }
            os.write(buffer, 0, len);
        }
        return os.toByteArray();
    }

    public String writeImage(String filePath) {
        String objectKey = UUID.randomUUID().toString();
        byte[] imgByte = Base64.decodeBase64((filePath.substring(filePath.indexOf(",") + 1)).getBytes());
        InputStream stream = new ByteArrayInputStream(imgByte);
        PutObjectRequest putObjectRequest = new PutObjectRequest(amazonS3Properties.getBucketName(), objectKey, stream, new ObjectMetadata());
        s3.putObject(putObjectRequest);
        return objectKey;
    }

    public void deleteImage(String objectKey) {
        s3.deleteObject(amazonS3Properties.getBucketName(), objectKey);
    }
}
