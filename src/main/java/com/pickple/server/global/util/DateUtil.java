package com.pickple.server.global.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtil {

    public static int calculateDayOfDay(LocalDate date) {
        LocalDate today = LocalDate.now();
        int dday = (int) ChronoUnit.DAYS.between(today, date) - 3;
        return dday;
    }

    public static int calculateCompletedDay(LocalDate date) {
        LocalDate today = LocalDate.now();
        int day = (int) ChronoUnit.DAYS.between(today, date);
        return day;
    }

    public static String refineDate(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        return localDate.format(formatter);
    }
}
