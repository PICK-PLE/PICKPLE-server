package com.pickple.server.api.moimsubmission.domain;

public record SubmitterInfo(

        Long submitterId,    //guest id

        String nickname,    //신청자 닉네임

        String submitterImageUrl,   //신청자 프로필 이미지

        String submittedDate,    //신청한 날짜 및 시간

        String state //신청 상태

) {
}
