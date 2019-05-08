package com.androidproject;

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
import java.util.Calendar;
import java.util.Date;

public class EventDecorator implements DayViewDecorator {

    private final Calendar calendar = Calendar.getInstance();
    private final DotSpan highlightDrawable;
    private final CalendarDay dates;

    public EventDecorator() throws ParseException {
        highlightDrawable = new DotSpan(5, R.color.fui_bgGoogle);
        String date = "2019-05-10T10:10:10Z";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parseDate = format.parse(date);


        Log.d("date", "EventDecorator: " + parseDate.toString());
        dates = CalendarDay.from(parseDate);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        day.copyTo(calendar);
        return dates != null && day.equals(dates);
    }

    @Override
    public void decorate(DayViewFacade view) {
    view.addSpan(highlightDrawable);
    }
}
