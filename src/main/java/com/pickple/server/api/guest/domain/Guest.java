package com.pickple.server.api.guest.domain;

import com.pickple.server.api.applicant.domain.Submitter;
import com.pickple.server.api.user.domain.User;
import com.pickple.server.global.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "guests")
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Guest extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.REMOVE)
    private List<Submitter> submitters = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;

    private String nickname;

    private String imageUrl;

    @Builder
    public Guest(
            final User user,
            final String nickname,
            final String imageUrl
    ){
        this.user = user;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
    }
}
