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

    public static Notice of(
            final Long moimId,
            final String title,
            final String content,
            final String imageUrl
    ){
        return Notice.builder()
                .moimId(moimId)
                .title(title)
                .content(content)
                .imageUrl(imageUrl)
                .build();
    }
}
