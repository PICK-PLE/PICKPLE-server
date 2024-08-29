package com.pickple.server.api.moimsubmission.dto.response;

import com.pickple.server.api.moim.domain.QuestionInfo;
import com.pickple.server.api.moimsubmission.domain.AnswerInfo;
import lombok.Builder;

@Builder
public record MoimSubmissionAllResponse(
        Long moimSubmissionId,       //신청 순서를 위한 모임신청id
        String date,                 //신청날짜
        String moimSubmissionState,  //모임 신청상태
        Long guestId,                //게스트 id
        String guestNickname,        //게스트 닉네임
        String kakaoNickname,        //카카오에서 받아오는 닉네임
        Long moimId,                 //모임 id
        String moimTitle,            //모임명
        String hostNickname,         //스픽커명
        QuestionInfo questionList,   //질문 리스트
        AnswerInfo answerList        //질문에 해당하는 답변리스트
) {
}