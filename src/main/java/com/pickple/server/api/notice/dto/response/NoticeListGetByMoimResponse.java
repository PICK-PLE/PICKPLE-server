package com.pickple.server.api.notice.dto.response;

import lombok.Builder;

@Builder
public record NoticeListGetByMoimResponse(

        Long noticeId,          //공지사항id

        String hostNickName,    //호스트 닉네임

        String hostImageUrl,    //호스트 이미지

        String title,           //공지사항 제목

        String content,         //공지사항 내용

        String date,            //공지사항 등록 일자 및 시간 yyyy.mm.dd hh:mm:ss

        String noticeImageUrl,  //공지사항 이미지

        Long hostId,             //호스트 id

        int commentNumber
) {
}
