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

    @Builder
    public Guest(
            final Long userId,
            final String nickname,
            final String imageUrl
    ){
        this.userId = userId;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
    }
}
