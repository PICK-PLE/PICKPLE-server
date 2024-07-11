package com.pickple.server.api.moim.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;

@Getter
public class DateInfo {

    @JsonFormat(pattern = "yyyy.MM.dd")
    private LocalDate date;

    private String dayOfWeek;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;

}
