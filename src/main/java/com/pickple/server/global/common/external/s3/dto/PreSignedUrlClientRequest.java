package com.pickple.server.global.common.external.s3.dto;

import com.pickple.server.global.common.external.s3.S3BucketDirectory;

public record PreSignedUrlClientRequest(
        S3BucketDirectory prefix,
        int count
) {
}
