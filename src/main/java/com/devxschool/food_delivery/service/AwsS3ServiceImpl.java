package com.devxschool.food_delivery.service;

import com.devxschool.food_delivery.models.Images;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iot.model.CannedAccessControlList;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

@Service
public class AwsS3ServiceImpl implements CloudService{

    private S3Client s3Client;
    private String bucketName="lagmanhouse";

    public AwsS3ServiceImpl(){
        init();
    }

    @Override
    public void init() {
        Region region = Region.US_EAST_2;
        s3Client = S3Client
                .builder()
                .region(region)
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .build();
    }

    @Override
    public void upload(MultipartFile multipartFile) {
        String key = "food_"+System.currentTimeMillis();
        try {
            s3Client.putObject(PutObjectRequest.builder()
                            .acl(String.valueOf(CannedAccessControlList.PUBLIC_READ))
                            .bucket(bucketName).key(key)
                            .build(),
                    RequestBody.fromFile(convertFileFromMultipartFile(multipartFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Images> listAll() {
        List<S3Object> s3Objects = listBucketObjects(s3Client, bucketName);

        return s3Objects
                .stream()
                .map(s -> new Images(s.key(), "https://lagmanhouse.s3.us-east-2.amazonaws.com/"+s.key()))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String key) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest
                .builder()
                .bucket(bucketName)
                .key(key)
                .build();
        s3Client.deleteObject(deleteObjectRequest);
    }

    private File convertFileFromMultipartFile(MultipartFile multipartFile) throws IOException {
        File convertedFile = new File(multipartFile.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(convertedFile);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();
        return convertedFile;
    }

    public List<S3Object> listBucketObjects(S3Client s3, String bucketName) {

        List<S3Object> objects =  new ArrayList<>();
        try {
            ListObjectsRequest listObjects = ListObjectsRequest
                    .builder()
                    .bucket(bucketName)
                    .build();

            ListObjectsResponse res = s3.listObjects(listObjects);
            objects = res.contents();

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        return objects;
    }
}
