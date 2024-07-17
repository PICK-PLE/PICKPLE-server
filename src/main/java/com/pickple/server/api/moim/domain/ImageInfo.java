package com.pickple.server.api.moim.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ImageInfo {

    @Size(max = 500)
    @NotBlank(message = "이미지가 비어있습니다.")
    private String imageUrl1;

    @Size(max = 500)
    private String imageUrl2;

    @Size(max = 500)
    private String imageUrl3;

}
