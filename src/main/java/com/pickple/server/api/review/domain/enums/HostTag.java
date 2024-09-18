package com.pickple.server.api.review.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HostTag {

    GOOD_TIME_MANAGEMENT("⏰ 시간 관리를 잘해요"),

    ACCURATE_INFORMATION("📢 정확한 정보를 제공해요"),

    GOOD_ANSWERS("🙋🏻 질문에 잘 답해줘요"),

    GOOD_ATMOSPHERE_LEAD("🙌🏻 분위기를 잘 이끌어요"),

    CLEAR_EXPLANATION("✅ 설명이 명확해요"),

    THOROUGH_PREPARATION("🔎 준비가 철저해요"),

    GOOD_VOICE("🗣 목소리가 좋아요"),

    EXISTING_EXPERTISE("📚 전문성이 있어요"),

    SMOOTH_PROGRESS("✈️ 진행이 매끄러워요"),

    GOOD_DELIVERY("✉️ 전달력이 좋아요"),

    APPROPRIATE_SPEED("⏳ 진행 속도가 적당해요"),

    WELL_REFLECTED_FEEDBACK("👀 참여자의 반응을 잘 반영해요");

    private final String description;

}
