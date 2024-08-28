package com.pickple.server.global.common.external.s3;

import com.pickple.server.global.common.external.s3.dto.PreSignedUrlClientRequest;
import com.pickple.server.global.common.external.s3.dto.PreSignedUrlResponse;
import com.pickple.server.global.response.ApiResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Image - PreSigned Url", description = "이미지 업로드 할 Url 받기")
public interface S3ControllerDocs {

    @Operation(summary = "이미지 업로드 presigned url")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20009", description = "presigned url 발급 성공"),
                    @ApiResponse(responseCode = "50001", description = "S3 presigned url을 받아오기에 실패했습니다.")
            }
    )
    ApiResponseDto<List<PreSignedUrlResponse>> getPreSignedUrl(@RequestBody PreSignedUrlClientRequest clientRequest);
}


