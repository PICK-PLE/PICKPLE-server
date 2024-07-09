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


    @Builder
    public Host(
            final Long userId,
            final String nickname,
            final String imageUrl
    ){
        this.userId = userId;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
    }
}
