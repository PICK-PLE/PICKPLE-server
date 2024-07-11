package com.pickple.server.global.common.external.s3;


import com.pickple.server.global.config.AwsConfig;
import com.pickple.server.global.exception.CustomException;
import com.pickple.server.global.response.enums.ErrorCode;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

@Slf4j
@Component
public class S3Service {

    private static final List<String> IMAGE_EXTENSIONS = Arrays.asList("image/jpeg", "image/png", "image/jpg",
            "image/webp");
    private static final Long MAX_FILE_SIZE = 5 * 1024 * 1024L;

    private static final Long PRE_SIGNED_URL_EXPIRE_MINUTE = 120L;  // 만료시간 2시간

    private final String bucketName;
    private final AwsConfig awsConfig;

    public S3Service(@Value("${aws-property.s3-bucket-name}") final String bucketName, AwsConfig awsConfig) {
        this.bucketName = bucketName;
        this.awsConfig = awsConfig;
    }

    // PreSigned Url을 통한 이미지 업로드
    public List<PreSignedUrlResponse> getUploadPreSignedUrlList(final S3BucketDirectory prefix, int count) {
        List<PreSignedUrlResponse> preSignedUrlList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            final String fileName = generateImageFileName();   // UUID 문자열
            final String key = prefix.value() + fileName;

            try {
                final S3Presigner preSigner = awsConfig.getS3PreSigner();

                PutObjectRequest request = PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key).build();

                PutObjectPresignRequest preSignedUrlRequest = PutObjectPresignRequest.builder()
                        .signatureDuration(Duration.ofMinutes(PRE_SIGNED_URL_EXPIRE_MINUTE))
                        .putObjectRequest(request).build();

                String url = preSigner.presignPutObject(preSignedUrlRequest).url().toString();
                PreSignedUrlResponse preSignedUrlResponse = PreSignedUrlResponse.of(fileName, url);
                preSignedUrlList.add(preSignedUrlResponse);
            } catch (RuntimeException e) {
                throw new CustomException(ErrorCode.PRESIGNED_URL_GET_ERROR);
            }
        }
        return preSignedUrlList;
    }

    // S3 버킷에 업로드된 이미지 삭제
    public void deleteImage(final String key) {
        try {
            final S3Client s3Client = awsConfig.getS3Client();

            s3Client.deleteObject((DeleteObjectRequest.Builder builder) ->
                    builder.bucket(bucketName)
                            .key(key).build());
        } catch (RuntimeException e) {
            throw new CustomException(ErrorCode.IMAGE_DELETE_ERROR);
        }
    }

    private String generateImageFileName() {
        return UUID.randomUUID().toString() + ".jpg";
    }
}
