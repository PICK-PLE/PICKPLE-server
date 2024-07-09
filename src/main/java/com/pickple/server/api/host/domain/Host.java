package com.pickple.server.api.host.domain;

import com.pickple.server.api.moim.domain.Moim;
import com.pickple.server.api.user.domain.User;
import com.pickple.server.global.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "hosts")
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Host extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;

    private String nickname;

    private String imageUrl;

    @OneToMany(mappedBy = "host", cascade = CascadeType.REMOVE)
    private List<Moim> moims = new ArrayList<>();

    @Builder
    public Host(
            final User user,
            final String nickname,
            final String imageUrl
    ){
        this.user = user;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
    }
}
