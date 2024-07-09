package com.pickple.server.api.applicant.domain;

import com.pickple.server.api.guest.domain.Guest;
import com.pickple.server.api.moim.domain.enums.Category;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Getter
@Table(name = "submitters")
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Submitter {

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
    private Category category;

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
                     final Category category,
                     final String plan,
                     final String email,
                     final SubmitterState submitterState
    ){
        this.guest = guest;
        this.intro = intro;
        this.goal = goal;
        this.link = link;
        this.nickname = nickname;
        this.category = category;
        this.plan = plan;
        this.email = email;
        this.submitterState = submitterState;
    }
}
