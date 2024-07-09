package com.pickple.server.api.moimapplication.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Getter
@Table(name = "moimApplications")
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MoimApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long guestId;

    private Long moimId;

    @JdbcTypeCode(SqlTypes.JSON)
    private String answerList;

    @JdbcTypeCode(SqlTypes.JSON)
    private String accountList;

    public static MoimApplication of(
            final Long guestId,
            final Long moimId,
            final String answerList,
            final String accountList
    ){
        return MoimApplication.builder()
                .guestId(guestId)
                .moimId(moimId)
                .answerList(answerList)
                .accountList(accountList)
                .build();
    }
}
