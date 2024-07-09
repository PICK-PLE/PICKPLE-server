package com.pickple.server.api.moim.domain;

import com.pickple.server.api.host.domain.Host;
import com.pickple.server.api.moim.domain.enums.Category;
import com.pickple.server.api.moim.domain.enums.MoimState;
import com.pickple.server.api.moimapplication.domain.MoimSubmission;
import com.pickple.server.api.notice.domain.Notice;
import com.pickple.server.global.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "moims")
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)

public class Moim extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private Host host;

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

    @OneToMany(mappedBy = "moim", cascade = CascadeType.REMOVE)
    private List<Notice> notices = new ArrayList<>();

    @OneToMany(mappedBy = "moim", cascade = CascadeType.REMOVE)
    private List<MoimSubmission> moimSubmissions = new ArrayList<>();

    @Builder
    public Moim(
            final Host host,
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
        this.host = host;
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
