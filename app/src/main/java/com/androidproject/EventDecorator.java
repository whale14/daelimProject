package com.androidproject;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class EventDecorator implements DayViewDecorator {

    private final DotSpan highlightDrawable;
    private final HashSet<CalendarDay> dates;

    public EventDecorator(Collection<CalendarDay> dateArray) {
        highlightDrawable = new DotSpan(5, R.color.colorAccent);
//        String date = "2019-05-10T10:10:10Z";
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Date parseDate = format.parse(date);

        dates = new HashSet<>(dateArray);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
    view.addSpan(highlightDrawable);
    }
}
