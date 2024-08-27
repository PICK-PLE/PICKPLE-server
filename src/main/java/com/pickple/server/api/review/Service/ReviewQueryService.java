package com.pickple.server.api.review.Service;

import com.pickple.server.api.review.domain.enums.HostTag;
import com.pickple.server.api.review.domain.enums.MoimTag;
import com.pickple.server.api.review.dto.TagGetResponse;
import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryService {
    public TagGetResponse getAllTags() {
        return TagGetResponse.builder()
                .moimTag(Arrays.stream(MoimTag.values())
                        .map(MoimTag::getDescription)
                        .collect(Collectors.toList()))
                .hostTag(Arrays.stream(HostTag.values())
                        .map(HostTag::getDescription)
                        .collect(Collectors.toList()))
                .build();
    }
}
