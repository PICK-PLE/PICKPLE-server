package com.pickple.server.api.moim.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;

@Getter
public class DateInfo {

    @NotNull(message = "날짜가 비어있습니다.")
    @JsonFormat(pattern = "yyyy.MM.dd")
    private LocalDate date;

    @NotBlank(message = "요일이 비어있습니다.")
    private String dayOfWeek;

    @NotNull(message = "시작 시간이 비어있습니다.")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @NotNull(message = "종료 시간이 비어있습니다.")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;

}
