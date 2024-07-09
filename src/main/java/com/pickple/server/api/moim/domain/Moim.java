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

    public static Moim of(
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
        return Moim.builder()
                .hostId(hostId)
                .category(category)
                .isOffline(isOffline)
                .spot(spot)
                .dateList(dateList)
                .maxGuest(maxGuest)
                .fee(fee)
                .questionList(questionList)
                .title(title)
                .description(description)
                .imageList(imageList)
                .moimState(moimState)
                .build();
    }
}
