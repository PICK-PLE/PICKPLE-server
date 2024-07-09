package com.pickple.server.api.moimapplication.domain;

import com.pickple.server.api.moim.domain.Moim;
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
public class MoimSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long guestId;

    private Long moimId;

    @JdbcTypeCode(SqlTypes.JSON)
    private String answerList;

    @JdbcTypeCode(SqlTypes.JSON)
    private String accountList;

    @Builder
    public MoimSubmission(
            final Long guestId,
            final Long moimId,
            final String answerList,
            final String accountList
    ){
        this.guestId = guestId;
        this.moimId = moimId;
        this.answerList = answerList;
        this.accountList = accountList;
    }
}
