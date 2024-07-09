package com.pickple.server.api.notice.domain;

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

    private Long moimId;

    private String title;

    private String content;

    private String imageUrl;


    @Builder
    public Notice(final Long moimId,
                  final String title,
                  final String content,
                  final String imageUrl
    ){
        this.moimId = moimId;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }
}
