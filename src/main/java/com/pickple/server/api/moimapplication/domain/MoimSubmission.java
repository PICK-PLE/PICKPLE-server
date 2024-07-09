package com.pickple.server.api.moimapplication.domain;

import com.pickple.server.api.moim.domain.Moim;
import com.pickple.server.global.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
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

    @Builder
    public MoimSubmission(
            final Long guestId,
            final Moim moim,
            final String answerList,
            final String accountList
    ){
        this.guestId = guestId;
        this.moim = moim;
        this.answerList = answerList;
        this.accountList = accountList;
    }
}
