package com.pickple.server.api.moimsubmission.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AccountInfo {

    @NotBlank(message = "예금주가 비어있습니다.")
    private String holder;

    @NotBlank(message = "은행이 비어있습니다.")
    private String bank;

    @NotBlank(message = "계좌번호가 비어있습니다.")
    private String accountNumber;

}
