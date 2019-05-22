package com.androidproject.utils;

import com.prolificinteractive.materialcalendarview.CalendarDay;

public class Utils {
    public static CalendarDay badLongDateToCalendarDay(long badLongDate) {
        String strDate = String.valueOf(badLongDate);

        int year = Integer.parseInt(strDate.substring(0, 4));
        int month = Integer.parseInt(strDate.substring(4, 6)) -1;
        int day = Integer.parseInt(strDate.substring(6, 8));

        return CalendarDay.from(year, month, day);
    }
}
