package com.sorsix.timeconverter.services;

import com.sorsix.timeconverter.models.Time;
import org.junit.Test;

import static org.junit.Assert.*;

public class TimeServiceTest {

    TimeService ts = new TimeService();

    // ts.convertTime() tests start from here
    @Test
    public void convertTimeTestEpoch() {
        Time timeExpected = new Time(1450998000, "1");
        Time timeActual = ts.convertTime("2015-12-25");
        assertEquals(timeExpected.getEpoch(), timeActual.getEpoch());
    }

    @Test
    public void convertTimeTestDate() {
        Time timeExpected = new Time(1, "петок, 25, декември 2015 00:00:00 CET");
        Time timeActual = ts.convertTime("2015-12-25");
        assertEquals(timeExpected.getSkopje(), timeActual.getSkopje());
    }

    @Test
    public void convertTimeWrongInput1() {
        Time timeExpected = new Time(0, "ERROR; Invalid input");
        Time timeActual = ts.convertTime("2015-12-");
        assertEquals(timeExpected.getSkopje(), timeActual.getSkopje());
    }

    @Test
    public void convertTimeWrongInput2() {
        Time timeExpected = new Time(0, "ERROR; Invalid input");
        Time timeActual = ts.convertTime("2015-12-00000000000000000");
        assertEquals(timeExpected.getEpoch(), timeActual.getEpoch());
    }

    @Test
    public void convertTimeWrongDateFormat1() {
        Time timeExpected = new Time(0, "ERROR; Invalid input");
        Time timeActual = ts.convertTime("2015-01-33");
        assertEquals(timeExpected.getEpoch(), timeActual.getEpoch()); //is this a good or bad practice
        assertEquals(timeExpected.getSkopje(), timeActual.getSkopje()); //to have 2 assertions in one test?
    }

    @Test
    public void convertTimeWrongDateFormat2() {
        Time timeExpected = new Time(0, "ERROR; Invalid input");
        Time timeActual = ts.convertTime("2015-13-01");
        assertEquals(timeExpected.getEpoch(), timeActual.getEpoch());
        assertEquals(timeExpected.getSkopje(), timeActual.getSkopje());
    }

    @Test
    public void convertTimeWrongDateFormat3() {
        Time timeExpected = new Time(0, "Text '2015-02-30T00:00:00+02:00[Europe/Skopje]' could not be parsed: Invalid date 'FEBRUARY 30'");
        Time timeActual = ts.convertTime("2015-02-30");
        assertEquals(timeExpected.getEpoch(), timeActual.getEpoch());
        assertEquals(timeExpected.getSkopje(), timeActual.getSkopje());
    }

    @Test
    public void convertTimeRightTimeAndDate() {
        Time timeExpected = new Time(1430604000, "недела, 3, мај 2015 00:00:00 CEST");
        Time timeActual = ts.convertTime("2015-05-03T00:00:00");
        assertEquals(timeExpected.getEpoch(), timeActual.getEpoch());
        assertEquals(timeExpected.getSkopje(), timeActual.getSkopje());
    }

    @Test
    public void convertTimeWrongDateAndTimeFormatHour() {
        Time timeExpected = new Time(0, "ERROR; Invalid input");
        Time timeActual = ts.convertTime("2015-05-03T24:00:00");
        assertEquals(timeExpected.getEpoch(), timeActual.getEpoch());
        assertEquals(timeExpected.getSkopje(), timeActual.getSkopje());
    }

    @Test
    public void convertTimeWrongDateAndTimeFormatMinute() {
        Time timeExpected = new Time(0, "ERROR; Invalid input");
        Time timeActual = ts.convertTime("2015-05-03T00:60:00");
        assertEquals(timeExpected.getEpoch(), timeActual.getEpoch());
        assertEquals(timeExpected.getSkopje(), timeActual.getSkopje());
    }

    @Test
    public void convertTimeWrongDateAndTimeFormatSecond() {
        Time timeExpected = new Time(0, "ERROR; Invalid input");
        Time timeActual = ts.convertTime("2015-05-03T00:00:60");
        assertEquals(timeExpected.getEpoch(), timeActual.getEpoch());
        assertEquals(timeExpected.getSkopje(), timeActual.getSkopje());
    }

    @Test
    public void convertTimeWrongDateAndTimeFormat1() {
        Time timeExpected = new Time(0, "ERROR; Invalid input");
        Time timeActual = ts.convertTime("2015-02-30T00:00:00");
        assertEquals(timeExpected.getEpoch(), timeActual.getEpoch());
        assertEquals(timeExpected.getSkopje(), timeActual.getSkopje());
    }

    // ts.dateOrEpoch() tests start from here
    @Test
    public void rightDate() {
        int result = ts.dateOrEpoch("2015-05-03");
        assertEquals(1, result);
    }

    @Test
    public void wrongMonth() {
        int result = ts.dateOrEpoch("2015-99-03");
        assertEquals(-1, result);
    }

    @Test
    public void wrongDay() {
        int result = ts.dateOrEpoch("2015-01-99");
        assertEquals(-1, result);
    }

    @Test
    public void rightEpoch() {
        int result = ts.dateOrEpoch("1450998000");
        assertEquals(0, result);
    }

    @Test
    public void rightDateAndTime() {
        int result = ts.dateOrEpoch("2015-05-03T00:00:00");
        assertEquals(2, result);
    }

    // ts.fixEpoch() tests start from here
    @Test
    public void fixEpochShortNumber() {
        long result = ts.fixEpoch("10");
        assertEquals(10L, result);
    }

    @Test
    public void fixEpochRegularNumber() {
        long result = ts.fixEpoch("1234567890");
        assertEquals(1234567890L, result);
    }

    @Test
    public void fixEpochLongNumber() {
        long result = ts.fixEpoch("1234567890123456789");
        assertEquals(1234567890L, result);
    }
}