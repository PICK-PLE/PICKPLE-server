package com.pickple.server.api.moim.domain;

import com.pickple.server.api.host.domain.Host;
import com.pickple.server.api.moimsubmission.domain.AccountInfo;
import com.pickple.server.api.moimsubmission.domain.MoimSubmission;
import com.pickple.server.api.notice.domain.Notice;
import com.pickple.server.global.common.domain.BaseTimeEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    private CategoryInfo categoryList;

    private boolean isOffline;

    private String spot;

    @JdbcTypeCode(SqlTypes.JSON)
    private DateInfo dateList;

    private int maxGuest;

    private int fee;

    @JdbcTypeCode(SqlTypes.JSON)
    private AccountInfo accountList;

    @JdbcTypeCode(SqlTypes.JSON)
    private QuestionInfo questionList;

    @Size(max = 28)
    private String title;

    @Size(max = 2000)
    private String description;

    @Valid
    @NotNull
    @JdbcTypeCode(SqlTypes.JSON)
    private ImageInfo imageList;

    private String moimState;

    @OneToMany(mappedBy = "moim", cascade = CascadeType.REMOVE)
    private List<Notice> notices = new ArrayList<>();

    @OneToMany(mappedBy = "moim", cascade = CascadeType.REMOVE)
    private List<MoimSubmission> moimSubmissions = new ArrayList<>();
}
