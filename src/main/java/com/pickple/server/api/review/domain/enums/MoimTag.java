package com.pickple.server.api.review.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MoimTag {

    SMOOTH_PROGRESS("🎤 진행이 매끄러워요"),

    INTERESTING_CONTENT("🤩 내용이 흥미로워요"),

    HIGH_EXPERTISE("💼 전문성이 뛰어나요"),

    NETWORKING_OPPORTUNITY("💬 네트워킹이 가능해요"),

    DEEP_CONTENT("🤩 내용이 깊이 있어요"),

    USEFUL_CONTENT("👍 내용이 유익해요"),

    GOOD_ATMOSPHERE("✨ 분위기가 좋아요"),

    CLEAN_LOCATION("✨ 장소가 깔끔해요"),

    APPROPRIATE_STAFF("👥 인원이 적절해요"),

    MUCH_INTERACTION("🎯 상호작용이 많아요"),

    NEW_INFORMATION("📌 새로운 정보가 많아요"),

    MANY_EXAMPLES("💡 실제 사례가 많아요");

    private final String description;
}
