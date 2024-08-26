package com.pickple.server.api.submitter.domain;

import com.pickple.server.api.guest.domain.Guest;
import com.pickple.server.global.common.domain.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Getter
@Builder
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

    @Size(max = 300)
    private String intro;

    @Size(max = 300)
    private String goal;

    private String link;

    @Size(max = 15)
    private String nickname;

    @JdbcTypeCode(SqlTypes.JSON)
    private SubmitterCategoryInfo categoryList;

    @Size(max = 300)
    private String plan;

    private String email;

    private String userTitle;

    private String submitterState;

    public void updateSubmitterState(String submitterState) {
        this.submitterState = submitterState;
    }
}
