package com.interswitch.codingassessment.commons.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 7/31/23
 **/
public class DateTimeUtil {
    private DateTimeUtil() {  }

    public static Date getCurrentUTCTime() {

        final TimeZone toTimeZone = TimeZone.getTimeZone("UTC");
        final Calendar calendar = Calendar.getInstance(toTimeZone);

        return (new Date(calendar.getTimeInMillis()));

    }
}
