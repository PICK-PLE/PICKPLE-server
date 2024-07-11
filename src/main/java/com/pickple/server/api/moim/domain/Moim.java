package com.pickple.server.api.moim.domain;

import com.pickple.server.api.host.domain.Host;
import com.pickple.server.api.moim.domain.enums.MoimState;
import com.pickple.server.api.moimsubmission.domain.MoimSubmission;
import com.pickple.server.api.notice.domain.Notice;
import com.pickple.server.global.common.domain.BaseTimeEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Getter
@Table(name = "moims")
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
    private CategoryInfo category;

    private boolean isOffline;

    private String spot;

    @JdbcTypeCode(SqlTypes.JSON)
    private DateInfo dateList;

    private int maxGuest;

    private int fee;

    @JdbcTypeCode(SqlTypes.JSON)
    private QuestionInfo questionList;

    private String title;

    private String description;

    @JdbcTypeCode(SqlTypes.JSON)
    private ImageInfo imageList;

    @Enumerated(EnumType.STRING)
    private MoimState moimState;

    @OneToMany(mappedBy = "moim", cascade = CascadeType.REMOVE)
    private List<Notice> notices = new ArrayList<>();

    @OneToMany(mappedBy = "moim", cascade = CascadeType.REMOVE)
    private List<MoimSubmission> moimSubmissions = new ArrayList<>();

    @Builder
    public Moim(
            final Host host,
            final CategoryInfo category,
            final boolean isOffline,
            final String spot,
            final DateInfo dateList,
            final int maxGuest,
            final int fee,
            final QuestionInfo questionList,
            final String title,
            final String description,
            final ImageInfo imageList,
            final MoimState moimState
    ) {
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
