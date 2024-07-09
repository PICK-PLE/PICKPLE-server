package com.pickple.server.api.guest.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "guests")
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String nickname;

    private String imageUrl;

    public static Guest of(
            final Long userId,
            final String nickname,
            final String imageUrl
    ){
        return Guest.builder()
                .userId(userId)
                .nickname(nickname)
                .imageUrl(imageUrl)
                .build();
    }
}
