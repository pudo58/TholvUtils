package org.tholv.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class DateUtils {
    static private String pattern;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat sdf7 = new SimpleDateFormat("dd-MM-yyyy");
    private SimpleDateFormat sdf8 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private SimpleDateFormat sdf9 = new SimpleDateFormat("yyyy");
    private SimpleDateFormat sdf10 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private SimpleDateFormat sdf4 = new SimpleDateFormat("MM/yyyy");
    private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sdf6 = new SimpleDateFormat("yyyy/MM/dd");
    private SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat sdf5 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private SimpleDateFormat sdf11 = new SimpleDateFormat("yyyyMMdd");
    public static DateUtils getInstance(String pattern) {
        return new DateUtils(pattern);
    }

    public DateUtils(String pattern) {
        this.pattern = pattern;
    }

    public Date toDate(String date) throws ParseException {
        if (pattern.equals("dd/MM/yyyy")) {
            return (sdf.parse(date));
        } else if (pattern.equals("yyyy-MM-dd")) {
            return (sdf2.parse(date));
        } else if (pattern.equals("yyyy-MM-dd HH:mm:ss")) {
            return (sdf3.parse(date));
        } else if (pattern.equals("MM/yyyy")) {
            return (sdf4.parse(date));
        } else if (pattern.equals("yyyy/MM/dd HH:mm:ss")) {
            return (sdf5.parse(date));
        } else if (pattern.equals("yyyy/MM/dd")) {
            return (sdf6.parse(date));
        } else if (pattern.equals("dd-MM-yyyy")) {
            return (sdf7.parse(date));
        } else if (pattern.equals("dd/MM/yyyy HH:mm:ss")) {
            return (sdf8.parse(date));
        } else if (pattern.equals("dd-MM-yyyy HH:mm:ss")) {
            return (sdf10.parse(date));
        } else if (pattern.equals("yyyy")) {
            return (sdf9.parse(date));
        }else if(pattern.equals("yyyyMMdd")){
            return sdf11.parse(date);
        }
        throw new IllegalArgumentException("Pattern not supported");
    }

    public String toString(Date date) {
        if (pattern.equals("dd/MM/yyyy")) {
            return sdf.format(date);
        } else if (pattern.equals("yyyy-MM-dd")) {
            return sdf2.format(date);
        } else if (pattern.equals("yyyy-MM-dd HH:mm:ss")) {
            return sdf3.format(date);
        } else if (pattern.equals("MM/yyyy")) {
            return sdf4.format(date);
        } else if (pattern.equals("yyyy/MM/dd HH:mm:ss")) {
            return sdf5.format(date);
        } else if (pattern.equals("yyyy/MM/dd")) {
            return sdf6.format(date);
        } else if (pattern.equals("dd-MM-yyyy")) {
            return sdf7.format(date);
        } else if (pattern.equals("dd/MM/yyyy HH:mm:ss")) {
            return sdf8.format(date);
        } else if (pattern.equals("dd-MM-yyyy HH:mm:ss")) {
            return sdf10.format(date);
        } else if (pattern.equals("yyyy")) {
            return sdf9.format(date);
        } else if (pattern.equals("yyyy-MM-dd HH:mm:ss.SSS")) {
            return sdf10.format(date);
        }else if(pattern.equals("yyyyMMdd")){
            return sdf11.format(date);
        }
        throw new IllegalArgumentException("Pattern not supported");
    }

    public static Date getTheFirstDayOfTheMonth(Date date) {
        return new Date(date.getYear(), date.getMonth(), 1);
    }

    public static String getCurrentDate() {
        return DateUtils.getInstance("dd/MM/yyyy").toString(new Date());
    }

    public static String getTheFirstDayOfTheMonth(String date, String pattern) {
        try {
            return DateUtils.getInstance(pattern).toString(getTheFirstDayOfTheMonth(DateUtils.getInstance(pattern).toDate(date)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Pattern not supported");
    }

    public static Date getTheLastDayOfTheMonth(String date) {
        LocalDate lastDayOfMonth = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                .with(TemporalAdjusters.lastDayOfMonth());
        return Date.from(lastDayOfMonth.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
    }

    public static String getTheLastDayOfTheMonth(String date, String pattern) {
        return DateUtils.getInstance(pattern).toString(getTheLastDayOfTheMonth(date));
    }

    public static String getTheLastDayOfTheMonth(Date date) {
        LocalDate lastDayOfMonth = LocalDate.parse(DateUtils.getInstance("dd/MM/yyyy").toString(date), DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                .with(TemporalAdjusters.lastDayOfMonth());
        return DateUtils.getInstance("dd/MM/yyyy").toString(Date.from(lastDayOfMonth.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()));
    }

    public static String getTheFirstDayOfTheMonth(Date date, String pattern) {
        LocalDate firstDayOfMonth = LocalDate.parse(DateUtils.getInstance(pattern).toString(date), DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                .with(TemporalAdjusters.firstDayOfMonth());
        return DateUtils.getInstance(pattern).toString(Date.from(firstDayOfMonth.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()));
    }

    public static boolean isTheSameDay(Date date1, Date date2) {
        return date1.getDate() == date2.getDate() && date1.getMonth() == date2.getMonth() && date1.getYear() == date2.getYear();
    }

    public static boolean isTheSameDay(String date1, String date2, String pattern) {
        try {
            return isTheSameDay(DateUtils.getInstance(pattern).toDate(date1), DateUtils.getInstance(pattern).toDate(date2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Invalid date");
    }

    public static boolean isTheSameDay(String date1, String date2) {
        try {
            return isTheSameDay(DateUtils.getInstance("dd/MM/yyyy").toDate(date1), DateUtils.getInstance("dd/MM/yyyy").toDate(date2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Invalid date");
    }

    public static boolean date1BiggerDate2(Date date1, Date date2) {
        return date1.before(date2);
    }

    public static boolean date1BiggerDate2(String date1, String date2, String pattern) {
        try {
            return date1BiggerDate2(DateUtils.getInstance(pattern).toDate(date1), DateUtils.getInstance(pattern).toDate(date2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Invalid date");
    }
    public static String getYear(Date date) {
        return DateUtils.getInstance("yyyy").toString(date);
    }
    public static String getMonthAndYear(Date date) {
        return DateUtils.getInstance("MM/yyyy").toString(date);
    }
    public static boolean isDateEqualsBetween(Date date, Date from, Date to) {
        return date.after(from) && date.before(to);
    }
    public static boolean isDateEqualsBetween(String date, String from, String to, String pattern) {
        try {
            return isDateEqualsBetween(DateUtils.getInstance(pattern).toDate(date), DateUtils.getInstance(pattern).toDate(from), DateUtils.getInstance(pattern).toDate(to));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("pattern not supported");
    }

    /**
     * @param date
     * @param from
     * @param to
     * @return
     */
    public static boolean isDateEqualsBetween(String date, String from, String to) {
        try {
            return isDateEqualsBetween(DateUtils.getInstance("dd/MM/yyyy").toDate(date), DateUtils.getInstance("dd/MM/yyyy").toDate(from), DateUtils.getInstance("dd/MM/yyyy").toDate(to));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("date is invalid");
    }
}
