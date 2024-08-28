package com.pickple.server.global.common.external.s3.controller;

import com.pickple.server.global.common.external.s3.S3Service;
import com.pickple.server.global.common.external.s3.dto.PreSignedUrlClientRequest;
import com.pickple.server.global.common.external.s3.dto.PreSignedUrlResponse;
import com.pickple.server.global.response.ApiResponseDto;
import com.pickple.server.global.response.enums.SuccessCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class S3Controller implements S3ControllerDocs {
    private final S3Service s3Service;

    @GetMapping("/v2/image/upload")
    @Override
    public ApiResponseDto<List<PreSignedUrlResponse>> getPreSignedUrl(
            @RequestBody PreSignedUrlClientRequest request
    ) {
        return ApiResponseDto.success(SuccessCode.PRESIGNED_URL_GET_SUCCESS,
                s3Service.getUploadPreSignedUrlList(request));
    }
}

