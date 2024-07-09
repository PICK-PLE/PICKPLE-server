package com.pickple.server.api.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long socialId;

    private String email;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    private String socialNickname;

    public static User of(
            final Long socialId,
            final String email,
            final SocialType socialType,
            final String socialNickname
    ) {
        return User.builder()
                .socialId(socialId)
                .email(email)
                .socialType(socialType)
                .socialNickname(socialNickname)
                .build();
    }
}