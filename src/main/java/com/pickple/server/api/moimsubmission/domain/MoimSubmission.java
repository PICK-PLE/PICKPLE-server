package com.pickple.server.api.moimsubmission.domain;

import com.pickple.server.api.moim.domain.Moim;
import com.pickple.server.global.common.domain.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Getter
@Table(name = "moimSubmission")
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MoimSubmission extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long guestId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moim_id")
    private Moim moim;

    @JdbcTypeCode(SqlTypes.JSON)
    private String answerList;

    @JdbcTypeCode(SqlTypes.JSON)
    private String accountList;

    @Enumerated(EnumType.STRING)
    private MoimSubmissionState moimSubmissionState;

    @Builder
    public MoimSubmission(
            final Long guestId,
            final Moim moim,
            final String answerList,
            final String accountList,
            final MoimSubmissionState moimSubmissionState
    ) {
        this.guestId = guestId;
        this.moim = moim;
        this.answerList = answerList;
        this.accountList = accountList;
        this.moimSubmissionState = moimSubmissionState;
    }
}
