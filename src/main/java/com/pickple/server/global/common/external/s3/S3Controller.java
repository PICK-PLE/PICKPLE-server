package com.pickple.server.global.common.external.s3;

import com.pickple.server.global.response.ApiResponseDto;
import com.pickple.server.global.response.enums.SuccessCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class S3Controller implements S3ControllerDocs {
    private final S3Service s3Service;

    @GetMapping("/v1/moim-image-list/upload/{count}")
    @Override
    public ApiResponseDto<List<PreSignedUrlResponse>> getMoimPreSignedUrl(
            @PathVariable int count
    ) {
        return ApiResponseDto.success(SuccessCode.PRESIGNED_URL_GET_SUCCESS,
                s3Service.getUploadPreSignedUrlList(S3BucketDirectory.MOIM_PREFIX, count));
    }

    @GetMapping("/v1/notice-image-list/upload/{count}")
    @Override
    public ApiResponseDto<List<PreSignedUrlResponse>> getNoticePreSignedUrl(
            @PathVariable int count
    ) {
        return ApiResponseDto.success(SuccessCode.PRESIGNED_URL_GET_SUCCESS,
                s3Service.getUploadPreSignedUrlList(S3BucketDirectory.NOTICE_PREFIX, count));
    }
}

