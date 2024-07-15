package com.pickple.server.api.moim.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ImageInfo {

    @NotBlank(message = "이미지가 비어있습니다.")
    private String imageUrl1;

    private String imageUrl2;

    private String imageUrl3;

}
