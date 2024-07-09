package com.pickple.server.api.host.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "hosts")
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Host {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String nickname;

    private String imageUrl;

    public static Host of(
            final Long userId,
            final String nickname,
            final String imageUrl
    ){
        return Host.builder()
                .userId(userId)
                .nickname(nickname)
                .imageUrl(imageUrl)
                .build();
    }
}
