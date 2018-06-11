package com.dbal;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.*;

public abstract class Util {

    private static final Logger LOGGER = Logger.getLogger(Util.class.getName());

    private Util() {

    }

    public static void logInfo(final Object obj) {
        lazyPrint(obj);
    }

    public static void logWarn(final Object obj) {
        lazyPrint(obj);
    }

    private static void lazyPrint(final Object obj){
        LOGGER.log(Level.SEVERE, () -> String.format("%s", obj));
    }

    public static void logDebug(final Object obj) {
        lazyPrint(obj);
    }

    public static void logError(final Object obj) {
        lazyPrint(obj);
    }

    public static void logException(final Exception e) {
    }

    public static Date getCurrentDate() {
        Calendar calendar = getCalendar();
        return calendar.getTime();
    }

    private static Calendar getCalendar() {
        final TimeZone tz = TimeZone.getTimeZone("Asia/Taipei");
        final Locale loc = Locale.TAIWAN;

        return Calendar.getInstance(tz, loc);
    }

}
