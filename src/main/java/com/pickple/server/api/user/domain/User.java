package com.pickple.server.api.user.domain;

import com.pickple.server.api.comment.domain.Comment;
import com.pickple.server.global.common.domain.BaseTimeEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
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
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long socialId;

    private String email;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    private String socialNickname;

    private String role;

    @OneToMany(mappedBy = "commenter", cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

    public void updateRole(String role) {
        this.role = role;
    }

    public static User of(
            final Long socialId,
            final String email,
            final SocialType socialType,
            final String socialNickname,
            final String role
    ) {
        return User.builder()
                .socialId(socialId)
                .email(email)
                .socialType(socialType)
                .socialNickname(socialNickname)
                .role(role)
                .build();
    }
}