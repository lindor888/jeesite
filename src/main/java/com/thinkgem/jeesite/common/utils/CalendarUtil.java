
package com.thinkgem.jeesite.common.utils;

import java.util.*;

/**
 * @author markhe TODO To change the template for this generated type comment go
 *         to Window - Preferences - Java - Code Style - Code Templates
 */
public class CalendarUtil {
   /**
    * DOCUMENT ME!
    */
   public static final String SUN = "name.weekday.sun";

   /**
    * DOCUMENT ME!
    */
   public static final String MON = "name.weekday.mon";

   /**
    * DOCUMENT ME!
    */
   public static final String TUE = "name.weekday.tue";

   /**
    * DOCUMENT ME!
    */
   public static final String WED = "name.weekday.wed";

   /**
    * DOCUMENT ME!
    */
   public static final String THUR = "name.weekday.thur";

   /**
    * DOCUMENT ME!
    */
   public static final String FRI = "name.weekday.fri";

   /**
    * DOCUMENT ME!
    */
   public static final String SAT = "name.weekday.sat";

   /**
    * DOCUMENT ME!
    */
   public static final String JAN = "name.month.jan";

   /**
    * DOCUMENT ME!
    */
   public static final String FEB = "name.month.feb";

   /**
    * DOCUMENT ME!
    */
   public static final String MAR = "name.month.mar";

   /**
    * DOCUMENT ME!
    */
   public static final String APR = "name.month.apr";

   /**
    * DOCUMENT ME!
    */
   public static final String MAY = "name.month.may";

   /**
    * DOCUMENT ME!
    */
   public static final String JUN = "name.month.jun";

   /**
    * DOCUMENT ME!
    */
   public static final String JUL = "name.month.jul";

   /**
    * DOCUMENT ME!
    */
   public static final String AUG = "name.month.aug";

   /**
    * DOCUMENT ME!
    */
   public static final String SEP = "name.month.sep";

   /**
    * DOCUMENT ME!
    */
   public static final String OCT = "name.month.oct";

   /**
    * DOCUMENT ME!
    */
   public static final String NOV = "name.month.nov";

   /**
    * DOCUMENT ME!
    */
   public static final String DEC = "name.month.dec";

   /**
    * DOCUMENT ME!
    */
   public static final String Q1 = "name.quarter.q1";

   /**
    * DOCUMENT ME!
    */
   public static final String Q2 = "name.quarter.q2";

   /**
    * DOCUMENT ME!
    */
   public static final String Q3 = "name.quarter.q3";

   /**
    * DOCUMENT ME!
    */
   public static final String Q4 = "name.quarter.q4";

   /**
    * DOCUMENT ME!
    */
   public static final String Q1_SHORT = "name.quarter.short.q1";

   /**
    * DOCUMENT ME!
    */
   public static final String Q2_SHORT = "name.quarter.short.q2";

   /**
    * DOCUMENT ME!
    */
   public static final String Q3_SHORT = "name.quarter.short.q3";

   /**
    * DOCUMENT ME!
    */
   public static final String Q4_SHORT = "name.quarter.short.q4";

   /**
    * DOCUMENT ME!
    */
   public static final int MAXMONTHOFYEAR = 11;

   /**
    * DOCUMENT ME!
    */
   public static final int MAXQUARTEROFYEAR = 3;

   /**
    * DOCUMENT ME!
    */
   public static final int MINQUARTEROFYEAR = 0;

   /**
    * DOCUMENT ME!
    */
   public static final int MINMONTHOFYEAR = 0;

   /**
    * DOCUMENT ME!
    */
   public static final int DAYSOFWEEK = 7;

   /**
    * DOCUMENT ME!
    */
   public static final int MAXDAYOFWEEK = 7;

   /**
    * DOCUMENT ME!
    */
   public static final int MINDAYOFWEEK = 1;

   private static int[] MONTHDAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

   public static final long YEAR = 31622400000L;

   public static final long MONTH = 2678400000L;

   public static final long WEEK = 604800000L;

   public static final long DAY = 86400000L;

   public static final int HOUR = 3600000;

   public static final int MINUTE = 60000;

   public static final int SECOND = 1000;

   private static long TIME1970;

   static {
      Calendar t1970 = Calendar.getInstance();
      t1970.clear();
      t1970.set(1970, 1, 1);
      TIME1970 = t1970.getTimeInMillis();
   }

   /**
    * DOCUMENT ME!
    */
   public static final int FIRSTDAYOFWEEK = 1;

   /**
    * DOCUMENT ME!
    * 
    * @return DOCUMENT ME!
    */
   public static int daysOfMonth(Calendar calendar) {
      int month = calendar.get(Calendar.MONTH);

      if (Calendar.FEBRUARY == month) {
         if (isLeapYear(calendar.get(Calendar.YEAR))) {
            return 29;
         } else {
            return 28;
         }
      } else {
         return MONTHDAYS[month];
      }
   }

   /**
    * DOCUMENT ME!
    * 
    * @return DOCUMENT ME!
    */
   public static boolean isLeapYear(int year) {
      if (year % 100 == 0) {
         if (year % 400 == 0) {
            return true;
         }
      } else {
         if ((year % 4) == 0) {
            return true;
         }
      }

      return false;
   }

   /**
    * DOCUMENT ME!
    * 
    * @param cal
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static int firstMonthDayInWeekDay(Calendar cal) {
      Calendar cal2 = (Calendar) cal.clone();

      int day = cal2.get(Calendar.DATE);

      if (1 != day) {
         cal2.set(Calendar.DATE, 1);
      }

      int ret = cal2.get(Calendar.DAY_OF_WEEK);

      return ret;
   }

   /**
    * DOCUMENT ME!
    * 
    * @param type
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static String getKeyYearOrDate(int type) {
      switch (type) {
      case Calendar.YEAR:
         return "name.calendar.year.label";

      case Calendar.DATE:
         return "name.calendar.date.label";
      }

      return null;
   }

   /**
    * DOCUMENT ME!
    * 
    * @param idx
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static String getShortKeyOfWeek(int idx) {
      return "name.weekday.calendar.ab." + idx;
   }

   public static String getKeyOfWeek(int idx) {
      switch (idx) {
      case 1:
         return SUN;

      case 2:
         return MON;

      case 3:
         return TUE;

      case 4:
         return WED;

      case 5:
         return THUR;

      case 6:
         return FRI;

      case 7:
         return SAT;
      }

      return null;
   }

   /**
    * DOCUMENT ME!
    * 
    * @param idx
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static String getKeyOfMonth(int idx) {
      switch (idx) {
      case 0:
         return JAN;

      case 1:
         return FEB;

      case 2:
         return MAR;

      case 3:
         return APR;

      case 4:
         return MAY;

      case 5:
         return JUN;

      case 6:
         return JUL;

      case 7:
         return AUG;

      case 8:
         return SEP;

      case 9:
         return OCT;

      case 10:
         return NOV;

      case 11:
         return DEC;
      }

      return null;
   }

   /**
    * DOCUMENT ME!
    * 
    * @param idx
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static String getKeyOfQuarter(int idx) {
      switch (idx) {
      case 0:
         return Q1;

      case 1:
         return Q2;

      case 2:
         return Q3;

      case 3:
         return Q4;
      }

      return null;
   }

   /**
    * DOCUMENT ME!
    * 
    * @param year
    *           DOCUMENT ME!
    * @param idx
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static Date getFirstTimeOfQuarter(int year, int idx) {
      Calendar cal = Calendar.getInstance();
      cal.clear();

      switch (idx) {
      case 0:
         cal.set(Calendar.DATE, 1);
         cal.set(Calendar.MONTH, Calendar.JANUARY);
         cal.set(Calendar.YEAR, year);

         return cal.getTime();

      case 1:
         cal.set(Calendar.DATE, 1);
         cal.set(Calendar.MONTH, Calendar.APRIL);
         cal.set(Calendar.YEAR, year);

         return cal.getTime();

      case 2:
         cal.set(Calendar.DATE, 1);
         cal.set(Calendar.MONTH, Calendar.JULY);
         cal.set(Calendar.YEAR, year);

         return cal.getTime();

      case 3:
         cal.set(Calendar.DATE, 1);
         cal.set(Calendar.MONTH, Calendar.OCTOBER);
         cal.set(Calendar.YEAR, year);

         return cal.getTime();
      }

      return cal.getTime();
   }

   /**
    * DOCUMENT ME!
    * 
    * @param year
    *           DOCUMENT ME!
    * @param idx
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static Date getFirstDayOfMonth(int year, int idx) {
      Calendar cal = Calendar.getInstance();
      cal.clear();
      cal.set(Calendar.DATE, 1);
      cal.set(Calendar.MONTH, idx);
      cal.set(Calendar.YEAR, year);

      return cal.getTime();
   }

   /**
    * DOCUMENT ME!
    * 
    * @param calendar
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static Date getFirstDayOfMonth(Calendar calendar) {
      Calendar cal = (Calendar) calendar.clone();
      cal.set(Calendar.DATE, 1);

      return cal.getTime();
   }

   public static Date getFirstDayOfYear(Calendar calendar) {
      Calendar cal = (Calendar) calendar.clone();
      cal.set(Calendar.MONTH, 0);
      cal.set(Calendar.DATE, 1);

      return cal.getTime();
   }

   /**
    * DOCUMENT ME!
    * 
    * @param calendar
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static Date getLastDayOfMonth(Calendar calendar) {
      Calendar cal = (Calendar) calendar.clone();
      cal.set(Calendar.DATE, CalendarUtil.daysOfMonth(cal));

      return cal.getTime();
   }

   public static Date getLastDayOfYear(Calendar calendar) {
      Calendar cal = (Calendar) calendar.clone();
      cal.set(Calendar.MONTH, 11);
      cal.set(Calendar.DATE, CalendarUtil.daysOfMonth(cal));

      return cal.getTime();
   }

   /**
    * DOCUMENT ME!
    * 
    * @param year
    *           DOCUMENT ME!
    * @param idx
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static Date getLastDayOfMonth(int year, int idx) {
      Calendar cal = Calendar.getInstance();
      cal.clear();
      cal.set(Calendar.DATE, 1);
      cal.set(Calendar.MONTH, idx);
      cal.set(Calendar.YEAR, year);
      cal.set(Calendar.DATE, CalendarUtil.daysOfMonth(cal));

      return cal.getTime();
   }

   /**
    * DOCUMENT ME!
    * 
    * @param cal
    *           DOCUMENT ME!
    */
   public static void rollToFirstDayOfMonth(Calendar cal) {
      cal.set(Calendar.DATE, 1);
   }

   /**
    * DOCUMENT ME!
    * 
    * @param cal
    *           DOCUMENT ME!
    */
   public static void rollToFirstDayOfYear(Calendar cal) {
      cal.set(Calendar.MONTH, 0);
      rollToFirstDayOfMonth(cal);
   }

   /**
    * DOCUMENT ME!
    * 
    * @param cal
    *           DOCUMENT ME!
    */
   public static void rollToLastDayOfYear(Calendar cal) {
      cal.set(Calendar.MONTH, 11);
      rollToLastDayOfMonth(cal);
   }

   /**
    * DOCUMENT ME!
    * 
    * @param cal
    *           DOCUMENT ME!
    */
   public static void rollToLastDayOfMonth(Calendar cal) {
      cal.set(Calendar.DATE, CalendarUtil.daysOfMonth(cal));
   }

   /**
    * DOCUMENT ME!
    * 
    * @param cal
    *           DOCUMENT ME!
    */
   public static void rollToFirstTimeOfMonth(Calendar cal) {
      rollToFirstDayOfMonth(cal);
      rollToFirstTimeOfDay(cal);
   }

   /**
    * DOCUMENT ME!
    * 
    * @param cal
    *           DOCUMENT ME!
    */
   public static void rollToFirstTimeOfYear(Calendar cal) {
      rollToFirstDayOfYear(cal);
      rollToFirstTimeOfDay(cal);
   }

   /**
    * DOCUMENT ME!
    * 
    * @param cal
    *           DOCUMENT ME!
    */
   public static void rollToLastTimeOfMonth(Calendar cal) {
      rollToLastDayOfMonth(cal);
      rollToLastTimeOfDay(cal);
   }

   /**
    * DOCUMENT ME!
    * 
    * @param cal
    *           DOCUMENT ME!
    */
   public static void rollToLastTimeOfYear(Calendar cal) {
      rollToLastDayOfYear(cal);
      rollToLastTimeOfDay(cal);
   }

   /**
    * DOCUMENT ME!
    * 
    * @param cal
    *           DOCUMENT ME!
    * @param costMonth
    *           DOCUMENT ME!
    * @param costDay
    *           DOCUMENT ME!
    */
   public static void rollToFirstTimeOfQuarter(Calendar cal, int costMonth, int costDay) {
      Calendar calQuarter = Calendar.getInstance();
      calQuarter.set(Calendar.MONTH, costMonth - 1);
      calQuarter.set(Calendar.DATE, costDay);
      rollToFirstTimeOfDay(calQuarter);

      calQuarter.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 1);
      while (true) {
         if (!cal.before(calQuarter)) {
            cal.setTimeInMillis(calQuarter.getTimeInMillis());

            break;
         } else {
            calQuarter.add(Calendar.MONTH, -3);
         }
      }
   }

   public static void rollToFirstTimeOfMonth(Calendar cal, int costMonth, int costDay) {
      Calendar calQuarter = Calendar.getInstance();
      calQuarter.set(Calendar.MONTH, costMonth - 1);
      calQuarter.set(Calendar.DATE, costDay);
      rollToFirstTimeOfDay(calQuarter);

      calQuarter.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 1);
      while (true) {
         if (!cal.before(calQuarter)) {
            cal.setTimeInMillis(calQuarter.getTimeInMillis());

            break;
         } else {
            calQuarter.add(Calendar.MONTH, -1);
         }
      }
   }

   /**
    * DOCUMENT ME!
    * 
    * @param args
    *           DOCUMENT ME!
    */
   public static void main(String[] args) {
      Calendar cal = Calendar.getInstance();
      cal.set(Calendar.MONTH, 3);
      rollToLastTimeOfQuarter(cal, 1, 1);

      // Fix dst date diff problem.
      Calendar calendar = Calendar.getInstance();
      calendar.set(2008, 2, 1);

      Calendar calendar2 = Calendar.getInstance();
      calendar2.set(2008, 2, 10);

      long date1Time = calendar.getTimeInMillis() + calendar.getTimeZone().getOffset(calendar.getTimeInMillis());
      long date2Time = calendar2.getTimeInMillis() + calendar2.getTimeZone().getOffset(calendar2.getTimeInMillis());

      System.out.println("Timezone:" + System.getProperty("user.timezone"));
      System.out.println("Time 1970:" + TIME1970 / 86400000L);
      System.out.println("Calendar:" + calendar.getTime());
      System.out.println("Calendar2:" + calendar2.getTime());

      System.out.println("Local:" + calendar.getTimeInMillis() + " Offset:" +
         calendar.getTimeZone().getOffset(calendar.getTimeInMillis()) + " OffsetHour:" +
         (calendar.getTimeZone().getOffset(calendar.getTimeInMillis()) / 3600000) + " Date:" + (date1Time / 86400000L) +
         " Date2:" + (calendar.getTimeInMillis() / 86400000L));
      System.out.println("Local:" + calendar2.getTimeInMillis() + " Offset:" +
         calendar2.getTimeZone().getOffset(calendar2.getTimeInMillis()) + " OffsetHour:" +
         (calendar2.getTimeZone().getOffset(calendar2.getTimeInMillis()) / 3600000) + " Date:" +
         (date2Time / 86400000L) + " Date2:" + (calendar2.getTimeInMillis() / 86400000L));

      System.out.println((calendar2.getTimeInMillis() - calendar.getTimeInMillis()) / 86400000L);
      System.out.println((date2Time - date1Time) / 86400000L);

      calendar.clear();
      calendar.set(2008, 2, 9);
      calendar.add(12, 120);
      System.out.println("Local:" + calendar.getTime());
   }

   /**
    * DOCUMENT ME!
    * 
    * @param cal
    *           DOCUMENT ME!
    * @param costMonth
    *           DOCUMENT ME!
    * @param costDay
    *           DOCUMENT ME!
    */
   public static void rollToLastTimeOfQuarter(Calendar cal, int costMonth, int costDay) {

      Calendar calQuarter = Calendar.getInstance();
      calQuarter.set(Calendar.MONTH, costMonth - 1);
      calQuarter.set(Calendar.DATE, costDay);

      rollToFirstTimeOfDay(calQuarter);

      calQuarter.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 1);
      while (true) {
         if (calQuarter.after(cal)) {
            cal.setTimeInMillis(calQuarter.getTimeInMillis() - 2);

            break;
         } else {
            calQuarter.add(Calendar.MONTH, 3);
         }
      }
   }

   public static void rollToLastTimeOfMonth(Calendar cal, int costMonth, int costDay) {

      Calendar calQuarter = Calendar.getInstance();
      calQuarter.set(Calendar.MONTH, costMonth - 1);
      calQuarter.set(Calendar.DATE, costDay);

      rollToFirstTimeOfDay(calQuarter);

      calQuarter.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 1);
      while (true) {
         if (calQuarter.after(cal)) {
            cal.setTimeInMillis(calQuarter.getTimeInMillis() - 2);

            break;
         } else {
            calQuarter.add(Calendar.MONTH, 1);
         }
      }
   }

   /**
    * DOCUMENT ME!
    * 
    * @param cal
    *           DOCUMENT ME!
    * @param costMonth
    *           DOCUMENT ME!
    * @param costDay
    *           DOCUMENT ME!
    */
   public static void rollToLastTimeOfYear(Calendar cal, int costMonth, int costDay) {
      int month = cal.get(Calendar.MONTH) + 1;
      int day = cal.get(Calendar.DATE);

      if (month < costMonth) {
         ;
      } else if (month > costMonth) {
         cal.add(Calendar.YEAR, 1);
      } else {
         if (day < costDay) {
            ;
         } else {
            cal.add(Calendar.YEAR, 1);
         }
      }

      cal.set(Calendar.MONTH, costMonth);
      cal.set(Calendar.DATE, costDay);
      cal.add(Calendar.DATE, -1);
      rollToLastTimeOfDay(cal);
   }

   /**
    * DOCUMENT ME!
    * 
    * @param cal
    *           DOCUMENT ME!
    * @param costMonth
    *           DOCUMENT ME!
    * @param costDay
    *           DOCUMENT ME!
    */
   public static void rollToFirstTimeOfYear(Calendar cal, int costMonth, int costDay) {
      int month = cal.get(Calendar.MONTH) + 1;
      int day = cal.get(Calendar.DATE);

      if (month < costMonth) {
         cal.add(Calendar.YEAR, -1);
      } else if (month > costMonth) {
         ;
      } else {
         if (day < costDay) {
            cal.add(Calendar.YEAR, -1);
         } else {
            ;
         }
      }

      cal.set(Calendar.MONTH, costMonth);
      cal.set(Calendar.DATE, costDay);
      rollToFirstTimeOfDay(cal);
   }

   /**
    * DOCUMENT ME!
    * 
    * @param cal
    *           DOCUMENT ME!
    */
   public static void rollToFirstTimeOfDay(Calendar cal) {
      cal.set(Calendar.HOUR_OF_DAY, 0);
      cal.set(Calendar.MINUTE, 0);
      cal.set(Calendar.SECOND, 0);
      cal.set(Calendar.MILLISECOND, 0);
   }

   public static Date rollToFirstTimeOfDate(Date date) {
      if (date == null) {
         return null;
      } else {
         Calendar cal = Calendar.getInstance();
         cal.setTime(date);
         rollToFirstTimeOfDay(cal);
         return cal.getTime();
      }
   }

   /**
    * DOCUMENT ME!
    * 
    * @param cal
    *           DOCUMENT ME!
    */
   public static void rollToLastTimeOfDay(Calendar cal) {
      cal.set(Calendar.HOUR_OF_DAY, 23);
      cal.set(Calendar.MINUTE, 59);
      cal.set(Calendar.SECOND, 59);
      cal.set(Calendar.MILLISECOND, 998);
   }

   public static Date rollToLastTimeOfDate(Date date) {
      if (date == null) {
         return null;
      } else {
         Calendar cal = Calendar.getInstance();
         cal.setTime(date);
         rollToLastTimeOfDay(cal);
         return cal.getTime();
      }
   }

   /**
    * DOCUMENT ME!
    * 
    * @param cal
    *           DOCUMENT ME!
    */
   public static void rollToLastDayOfWeek(Calendar cal) {
      while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
         cal.add(Calendar.DATE, 1);
      }
   }

   /**
    * DOCUMENT ME!
    * 
    * @param cal
    *           DOCUMENT ME!
    */
   public static void rollToLastTimeOfWeek(Calendar cal) {
      rollToLastDayOfWeek(cal);
      rollToLastTimeOfDay(cal);
   }

   /**
    * DOCUMENT ME!
    * 
    * @param cal
    *           DOCUMENT ME!
    */
   public static void rollToFirstDayOfWeek(Calendar cal) {
      while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
         cal.add(Calendar.DATE, -1);
      }
   }

   /**
    * DOCUMENT ME!
    * 
    * @param cal
    *           DOCUMENT ME!
    */
   public static void rollToFirstTimeOfWeek(Calendar cal) {
      rollToFirstDayOfWeek(cal);
      rollToFirstTimeOfDay(cal);
   }

   /**
    * DOCUMENT ME!
    * 
    * @param cal
    *           DOCUMENT ME!
    * @param monthDay
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static Date getMonthDay(Calendar cal, int monthDay) {
      Calendar cal2 = (Calendar) cal.clone();

      cal2.set(Calendar.DATE, monthDay);

      Date date = cal2.getTime();

      return date;
   }

   /**
    * DOCUMENT ME!
    * 
    * @param cal
    *           DOCUMENT ME!
    * @param monthDay
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static int getWeekNo(Calendar cal, int monthDay) {
      if (0 == monthDay) {
         monthDay = 1;
      }

      Calendar cal2 = (Calendar) cal.clone();
      cal2.set(Calendar.MONTH, 0);
      cal2.set(Calendar.DAY_OF_MONTH, 1);
      int firstWeekday = cal2.get(Calendar.DAY_OF_WEEK);

      int day = cal.get(Calendar.DATE);
      cal.set(Calendar.DATE, monthDay);

      // int ret = cal.get(Calendar.WEEK_OF_YEAR);
      int ret = cal.get(Calendar.DAY_OF_YEAR);
      ret = (ret + firstWeekday - 1) / 7 + 1;
      cal.set(Calendar.DATE, day);

      return ret;
   }


   /**
    * DOCUMENT ME!
    * 
    * @param date
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static Date getDayFirstTime(Date date) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.set(Calendar.HOUR_OF_DAY, 0);
      calendar.set(Calendar.MINUTE, 0);
      calendar.set(Calendar.SECOND, 0);
      calendar.set(Calendar.MILLISECOND, 0);

      return calendar.getTime();
   }

   /**
    * @param date
    * @param type
    * @return
    * @author macroliu
    */
   public static Date getDayFirstTime(Date date, int type) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      switch (type) {
      case Calendar.YEAR:
         calendar.set(Calendar.MONTH, 0);
      case Calendar.MONTH:
         calendar.set(Calendar.DAY_OF_MONTH, 0);
      case Calendar.DATE:
         calendar.set(Calendar.HOUR_OF_DAY, 0);
      case Calendar.HOUR:
         calendar.set(Calendar.MINUTE, 0);
      case Calendar.MINUTE:
         calendar.set(Calendar.SECOND, 0);
      case Calendar.SECOND:
         calendar.set(Calendar.MILLISECOND, 0);
      }

      return calendar.getTime();
   }

   /**
    * DOCUMENT ME!
    * 
    * @param date
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static Date getDayNextDay(Date date) {
      return getDayNextDay(date, 1);
   }

   /**
    * 下n天
    * 
    * @param date
    * @param count
    * @return
    * @author jacobleung
    */
   public static Date getDayNextDay(Date date, int n) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.add(Calendar.DATE, n);

      return calendar.getTime();
   }
   
   public static Calendar getCalendar(Date date) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);      
      
      return calendar;
   }

   /**
    * DOCUMENT ME!
    * 
    * @param date
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static Date getDayPredDay(Date date) {
      return getDayPredDay(date, 1);
   }

   public static Date getDayPredDay(Date date, int n) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.add(Calendar.DATE, -n);

      return calendar.getTime();
   }

   /**
    * DOCUMENT ME!
    * 
    * @param date
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static Date getDayLastTime(Date date) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.set(Calendar.HOUR_OF_DAY, 23);
      calendar.set(Calendar.MINUTE, 59);
      calendar.set(Calendar.SECOND, 59);
      calendar.set(Calendar.MILLISECOND, 998);

      return calendar.getTime();
   }

   /**
    * @param date
    * @param type
    * @return
    * @author macroliu
    */
   public static Date getDayLastTime(Date date, int type) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      switch (type) {
      case Calendar.YEAR:
         calendar.set(Calendar.MONTH, 11);
      case Calendar.MONTH:
         calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
      case Calendar.DATE:
         calendar.set(Calendar.HOUR_OF_DAY, 23);
      case Calendar.HOUR:
         calendar.set(Calendar.MINUTE, 59);
      case Calendar.MINUTE:
         calendar.set(Calendar.SECOND, 59);
      case Calendar.SECOND:
         calendar.set(Calendar.MILLISECOND, 998);
      }

      return calendar.getTime();
   }

   /**
    * DOCUMENT ME!
    * 
    * @param startDate
    *           DOCUMENT ME!
    * @param endDate
    *           DOCUMENT ME!
    * @param date
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static boolean checkDateInclude(Date startDate, Date endDate, Date date) {
      if (null == endDate || null == startDate) {
         return false;
      }
      
      Calendar startDateCalendar = Calendar.getInstance();
      startDateCalendar.setTime(startDate);
      startDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
      startDateCalendar.set(Calendar.MINUTE, 0);
      startDateCalendar.set(Calendar.SECOND, 0);
      startDateCalendar.set(Calendar.MILLISECOND, 0);
      Calendar endDateCalendar = Calendar.getInstance();
      endDateCalendar.setTime(endDate);
      endDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
      endDateCalendar.set(Calendar.MINUTE, 0);
      endDateCalendar.set(Calendar.SECOND, 0);
      endDateCalendar.set(Calendar.MILLISECOND, 0);
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.set(Calendar.HOUR_OF_DAY, 0);
      calendar.set(Calendar.MINUTE, 0);
      calendar.set(Calendar.SECOND, 0);
      calendar.set(Calendar.MILLISECOND, 0);
      return startDateCalendar.getTimeInMillis() <= calendar.getTimeInMillis() && calendar.getTimeInMillis() <= endDateCalendar.getTimeInMillis();
      
      /*
      startDate = CalendarUtil.getDayFirstTime(startDate);
      endDate = CalendarUtil.getDayLastTime(endDate);

      return startDate.getTime() <= date.getTime() && date.getTime() <= endDate.getTime();
      */
   }

   /**
    * DOCUMENT ME!
    * 
    * @param startDate
    *           DOCUMENT ME!
    * @param endDate
    *           DOCUMENT ME!
    * @param cal
    *           DOCUMENT ME!
    * @param monthDay
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static boolean checkDateInclude(Date startDate, Date endDate, Calendar cal, int monthDay) {
      Calendar calendar = (Calendar) cal.clone();
      calendar.set(Calendar.DATE, monthDay);

      Date date = calendar.getTime();

      return checkDateInclude(startDate, endDate, date);
   }

   /**
    * DOCUMENT ME!
    * 
    * @param date1
    *           DOCUMENT ME!
    * @param date2
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static boolean oneBeforeTwo(Date date1, Date date2) {
      return date1.getTime() < date2.getTime();
   }

   /**
    * DOCUMENT ME!
    * 
    * @param date1
    *           DOCUMENT ME!
    * @param date2
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static boolean oneAfterTwo(Date date1, Date date2) {
      return date1.getTime() > date2.getTime();
   }

   /**
    * DOCUMENT ME!
    * 
    * @param date1
    *           DOCUMENT ME!
    * @param date2
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static Date getBeforeDate(Date date1, Date date2) {
      if (oneBeforeTwo(date1, date2)) {
         return date1;
      } else {
         return date2;
      }
   }

   /**
    * DOCUMENT ME!
    * 
    * @param date1
    *           DOCUMENT ME!
    * @param date2
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static Date getAfterDate(Date date1, Date date2) {
      if (oneAfterTwo(date1, date2)) {
         return date1;
      } else {
         return date2;
      }
   }

   /**
    * DOCUMENT ME!
    * 
    * @param org
    *           DOCUMENT ME!
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static boolean dateEquals(Date org, Calendar cal) {
      if (null == org || null == cal) {
         return false;
      }

      // Fix dst date diff problem.
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(org);

      long orgTime = calendar.getTimeInMillis() + calendar.getTimeZone().getOffset(calendar.getTimeInMillis());
      long dateTime = cal.getTimeInMillis() + cal.getTimeZone().getOffset(cal.getTimeInMillis());

      return orgTime / DAY == dateTime / DAY;
   }

   /**
    * DOCUMENT ME!
    * 
    * @param org
    *           DOCUMENT ME!
    * @param date
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static boolean dateEquals(Date org, Date date) {
      if (null == org || null == date) {
         return false;
      }

      // Fix dst date diff problem.
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(org);

      Calendar cal = Calendar.getInstance();
      cal.setTime(date);

      long orgTime = calendar.getTimeInMillis() + calendar.getTimeZone().getOffset(calendar.getTimeInMillis());
      long dateTime = cal.getTimeInMillis() + cal.getTimeZone().getOffset(cal.getTimeInMillis());

      return orgTime / DAY == dateTime / DAY;
   }

   public static boolean monthEquals(Date org, Date date) {
      if (null == org || null == date) {
         return false;
      }
      Calendar cal = Calendar.getInstance();
      cal.setTime(org);
      int orgYear = cal.get(Calendar.YEAR);
      int orgMonth = cal.get(Calendar.MONTH);

      cal.setTime(date);
      int year = cal.get(Calendar.YEAR);
      int month = cal.get(Calendar.MONTH);

      return orgYear == year && orgMonth == month;
   }

   /**
    * DOCUMENT ME!
    * 
    * @param date1
    *           DOCUMENT ME!
    * @param date2
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static boolean timeEquals(Date date1, Date date2) {
      if (null == date1 || null == date2) {
         return false;
      }

      return date1.getTime() == date2.getTime();
   }

   /**
    * 返回时间间隔的<资源Key, 参数>列表
    * @param duration 以毫秒为单位的时间间隔
    * @author macroliu
    * @return
    */
   public static Collection getDurationDisplayValues(Long duration) {
      if (duration != null && duration > 0) {
         long days = 0;
         long hours = 0;
         long minutes = 0;
         if (duration > CalendarUtil.DAY) {
            days = duration / CalendarUtil.DAY;
            duration %= CalendarUtil.DAY;
         }
         if (duration > CalendarUtil.HOUR) {
            hours = duration / CalendarUtil.HOUR;
            duration %= CalendarUtil.HOUR;
         }
         if (duration > CalendarUtil.MINUTE) {
            minutes = duration / CalendarUtil.MINUTE;
            if (duration % CalendarUtil.MINUTE > 0) {
               minutes++;
            }
         } else {
            minutes++;
         }
         ArrayList dispValues = new ArrayList();
         if (days > 0) {
            if (days > 1) {
               dispValues.add(new Object[]{"common.label.xDay.s", new String[]{String.valueOf(days)}});
            } else {
               dispValues.add(new Object[]{"common.label.xDay", new String[]{String.valueOf(days)}});
            }
         }
         if (hours > 0) {
            if (!dispValues.isEmpty()) {
               if (hours > 1) {
                  dispValues.add(new Object[]{"common.label.xHour2.s", new String[]{String.valueOf(hours)}});
               } else {
                  dispValues.add(new Object[]{"common.label.xHour2", new String[]{String.valueOf(hours)}});
               }
            } else {
               if (hours > 1) {
                  dispValues.add(new Object[]{"common.label.xHour.s", new String[]{String.valueOf(hours)}});
               } else {
                  dispValues.add(new Object[]{"common.label.xHour", new String[]{String.valueOf(hours)}});
               }
            }
         }
         if (!dispValues.isEmpty()) {
            if (minutes > 1) {
               dispValues.add(new Object[]{"common.label.xMinute2.s", new String[]{String.valueOf(minutes)}});
            } else {
               dispValues.add(new Object[]{"common.label.xMinute2", new String[]{String.valueOf(minutes)}});
            }
         } else {
            if (minutes > 1) {
               dispValues.add(new Object[]{"common.label.xMinute.s", new String[]{String.valueOf(minutes)}});
            } else {
               dispValues.add(new Object[]{"common.label.xMinute", new String[]{String.valueOf(minutes)}});
            }
         }
         return dispValues;
      }
      return null;
   }

   /**
    * DOCUMENT ME!
    * 
    * @param start
    *           DOCUMENT ME!
    * @param end
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static long offsetDate(Date start, Date end) {
      start = CalendarUtil.getDayFirstTime(start);
      end = CalendarUtil.getDayFirstTime(end);

      return offsetDate(start, end, Calendar.DATE);
   }

   /**
    * DOCUMENT ME!
    * 
    * @param start
    *           DOCUMENT ME!
    * @param end
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static long diffDate(Date start, Date end) {
      start = CalendarUtil.getDayFirstTime(start);
      end = CalendarUtil.getDayFirstTime(end);

      return diffDate(start, end, Calendar.DATE);
   }

   /**
    * DOCUMENT ME!
    * 
    * @param start
    *           DOCUMENT ME!
    * @param end
    *           DOCUMENT ME!
    * @param type
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static long diffDate(Date start, Date end, int type) {
      long offset = CalendarUtil.getTimeDelta(end, start);

      switch (type) {
      case Calendar.YEAR:
         return offset / YEAR;

      case Calendar.MONTH:
         return offset / MONTH;

      case Calendar.DATE:
         return offset / DAY;

      case Calendar.HOUR:
         return offset / HOUR;

      case Calendar.MINUTE:
         return offset / MINUTE;

      case Calendar.SECOND:
         return offset / SECOND;

      case Calendar.MILLISECOND:
         return offset;
      }

      return 0;
   }

   public static long getUTCTime(Date date1) {
      // Fix dst date diff problem.
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date1);
      long date1Time = calendar.getTimeInMillis() + calendar.getTimeZone().getOffset(calendar.getTimeInMillis());

      return date1Time;
   }

   public static long getTimeDelta(Date date1, Date date2) {
      // Fix dst date diff problem.
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date1);

      Calendar calendar2 = Calendar.getInstance();
      calendar2.setTime(date2);

      long date1Time = calendar.getTimeInMillis() + calendar.getTimeZone().getOffset(calendar.getTimeInMillis());
      long date2Time = calendar2.getTimeInMillis() + calendar2.getTimeZone().getOffset(calendar2.getTimeInMillis());

      long offset = date1Time - date2Time;
      return offset;
   }

   /**
    * DOCUMENT ME!
    * 
    * @param start
    *           DOCUMENT ME!
    * @param end
    *           DOCUMENT ME!
    * @param type
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static long offsetDate(Date start, Date end, int type) {
      // Fix dst date diff problem.
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(start);

      Calendar calendar2 = Calendar.getInstance();
      calendar2.setTime(end);

      long startTime = calendar.getTimeInMillis() + calendar.getTimeZone().getOffset(calendar.getTimeInMillis());
      long endTime = calendar2.getTimeInMillis() + calendar2.getTimeZone().getOffset(calendar2.getTimeInMillis());

      long offset = endTime - startTime;

      switch (type) {
      case Calendar.YEAR:
         return offset / YEAR + 1;

      case Calendar.MONTH:
         return offset / MONTH + 1;

      case Calendar.DATE:
         return offset / DAY + 1;

      case Calendar.HOUR:
         return offset / HOUR + 1;

      case Calendar.MINUTE:
         return offset / MINUTE + 1;

      case Calendar.SECOND:
         return offset / SECOND + 1;

      case Calendar.MILLISECOND:
         return offset;
      }

      return 0;
   }

   /**
    * DOCUMENT ME!
    * 
    * @param start
    *           DOCUMENT ME!
    * @param end
    *           DOCUMENT ME!
    * @param type
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static double offsetTime(Date start, Date end, int type) {
      double time = (double) CalendarUtil.getTimeDelta(end, start);

      switch (type) {
      case Calendar.DATE:
         return (CalendarUtil.getTimeDelta(getDayFirstTime(end), getDayFirstTime(start))) / DAY;
         // return time / DAY;

      case Calendar.HOUR:
         return time / HOUR;

      case Calendar.MINUTE:
         return time / MINUTE;

      case Calendar.SECOND:
         return time / SECOND;

      case Calendar.MILLISECOND:
         return time;
      }

      return time;
   }

   public static Date[] getPeriodDateOfWeek(Date tmp) {
      List<Date> set = new ArrayList();
      set.add(tmp);
      Calendar cal = Calendar.getInstance();
      cal.setTime(tmp);
      while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
         cal.add(Calendar.DATE, -1);
         set.add(cal.getTime());
      }

      cal.setTime(tmp);
      while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
         cal.add(Calendar.DATE, 1);
         set.add(cal.getTime());
      }
      Collections.sort(set);
      return set.toArray(new Date[]{});
   }

   public static Date getFirstDayOfWeek(Calendar cal) {
      while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
         cal.add(Calendar.DATE, -1);
      }

      return cal.getTime();
   }

   public static Date getLastDayOfWeek(Calendar cal) {
      while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
         cal.add(Calendar.DATE, 1);
      }

      return cal.getTime();
   }

   /**
    * @param month:
    *           start from 1, not 0.
    * @return
    * @author oliverzheng
    */
   public static int getDayCountOfMonth(Date date) {
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      int month = cal.get(Calendar.MONTH);
      if (month == 1) {
         int year = cal.get(Calendar.YEAR);
         if (year % 4 == 0) {
            return 29;
         } else {
            return 28;
         }
      }
      return MONTHDAYS[month];
   }

   public static Date getAfter(Date date1, Date date2) {
      if (date1.after(date2)) {
         return date1;
      }

      return date2;
   }

   public static Date getBefore(Date date1, Date date2) {
      if (date1.before(date2)) {
         return date1;
      }

      return date2;
   }

   public static Date getFirstDateOfMonth(int year, int month) {
      Calendar calendar = Calendar.getInstance();
      calendar.set(year, month, 1, 0, 0, 0);
      calendar.set(Calendar.MILLISECOND, 0);
      return calendar.getTime();
   }
}
