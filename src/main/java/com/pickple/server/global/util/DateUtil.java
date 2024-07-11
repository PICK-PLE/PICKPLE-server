package com.pickple.server.global.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtil {

    public static int calculateDayOfDay(LocalDate date) {
        LocalDate today = LocalDate.now();
        int dday = (int) ChronoUnit.DAYS.between(today, date) - 3;
        return dday;
    }
}
