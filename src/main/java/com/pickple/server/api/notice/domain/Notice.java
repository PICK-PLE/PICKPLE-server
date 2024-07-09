package com.pickple.server.api.notice.domain;

import com.pickple.server.api.moim.domain.Moim;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "notices")
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moim_id")
    private Moim moim;

    private String title;

    private String content;

    private String imageUrl;


    @Builder
    public Notice(final Moim moim,
                  final String title,
                  final String content,
                  final String imageUrl
    ){
        this.moim = moim;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }
}
