package com.pickple.server.api.applicant.domain;

import com.pickple.server.api.moim.domain.enums.Category;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Getter
@Table(name = "applicants")
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Applicant{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long guestId;

    private String intro;

    private String goal;

    private String link;

    private String nickname;

    @JdbcTypeCode(SqlTypes.JSON)
    private Category category;

    private String plan;

    private String email;

    @Enumerated(EnumType.STRING)
    private ApplicantState applicantState;

    public static Applicant of(
            final Long guestId,
            final String intro,
            final String goal,
            final String link,
            final String nickname,
            final Category category,
            final String plan,
            final String email,
            final ApplicantState applicantState
    ) {
        return Applicant.builder()
                .guestId(guestId)
                .intro(intro)
                .goal(goal)
                .link(link)
                .nickname(nickname)
                .category(category)
                .plan(plan)
                .email(email)
                .applicantState(applicantState)
                .build();
    }
}
