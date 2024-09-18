package com.pickple.server.api.moim.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {

    NJOB("njob"),   //n잡

    INVESTMENT("investment"),   //재테크

    STARTUP("startup"), //창업

    EMPLOYMENT("employment"),   //취업

    PRODUCTIVITY("productivity"),   //생산성

    SPEECH("speech"),   //스피치

    SELF("self"),   //자기계발

    MARKETING("marketing"), //마케팅

    EDUCATION("education"), //교육

    IT("it");   //it

    public final String category;

}
