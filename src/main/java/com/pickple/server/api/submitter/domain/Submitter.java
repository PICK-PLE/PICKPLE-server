package com.pickple.server.api.submitter.domain;

import com.pickple.server.api.guest.domain.Guest;
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
@Table(name = "submitters")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Submitter extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id")
    private Guest guest;

    private String intro;

    private String goal;

    private String link;

    private String nickname;

    @JdbcTypeCode(SqlTypes.JSON)
    private SubmitterCategoryInfo categoryList;

    private String plan;

    private String email;

    @Enumerated(EnumType.STRING)
    private SubmitterState submitterState;

    @Builder
    public Submitter(final Guest guest,
                     final String intro,
                     final String goal,
                     final String link,
                     final String nickname,
                     final SubmitterCategoryInfo categoryList,
                     final String plan,
                     final String email,
                     final SubmitterState submitterState
    ) {
        this.guest = guest;
        this.intro = intro;
        this.goal = goal;
        this.link = link;
        this.nickname = nickname;
        this.categoryList = categoryList;
        this.plan = plan;
        this.email = email;
        this.submitterState = submitterState;
    }
}
