package com.pickple.server.api.moim.domain;

import com.pickple.server.api.moim.domain.enums.Category;
import com.pickple.server.api.moim.domain.enums.MoimState;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Getter
@Table(name = "moims")
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)

public class Moim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long hostId;

    @JdbcTypeCode(SqlTypes.JSON)
    private Category category;

    private boolean isOffline;

    private String spot;

    @JdbcTypeCode(SqlTypes.JSON)
    private String dateList;

    private int maxGuest;

    private int fee;

    @JdbcTypeCode(SqlTypes.JSON)
    private String questionList;

    private String title;

    private String description;

    @JdbcTypeCode(SqlTypes.JSON)
    private String imageList;

    @Enumerated(EnumType.STRING)
    private MoimState moimState;

    @Builder
    public Moim(
            final Long hostId,
            final Category category,
            final boolean isOffline,
            final String spot,
            final String dateList,
            final int maxGuest,
            final int fee,
            final String questionList,
            final String title,
            final String description,
            final String imageList,
            final MoimState moimState
    ){
        this.hostId = hostId;
        this.category = category;
        this.isOffline = isOffline;
        this.spot = spot;
        this.dateList = dateList;
        this.maxGuest = maxGuest;
        this.fee = fee;
        this.questionList = questionList;
        this.title = title;
        this.description = description;
        this.imageList = imageList;
        this.moimState = moimState;
    }
}
