package com.pickple.server.global.common.external.s3.dto;

public record PreSignedUrlResponse(
        String fileName,
        String url
) {
    public static PreSignedUrlResponse of(
            final String fileName,
            final String url
    ) {
        return new PreSignedUrlResponse(fileName, url);
    }
}
