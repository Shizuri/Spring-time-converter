package com.sorsix.timeconverter.services;

import com.sorsix.timeconverter.models.Time;
import org.junit.Test;

import java.time.format.DateTimeParseException;

import static org.junit.Assert.*;

public class TimeServiceTest {

    TimeService ts = new TimeService();

    @Test
    public void convertTimeCorrectEpoch() {
        Time timeExpected = new Time(1450998000, "1");
        Time timeActual = ts.convertTime("2015-12-25");
        assertEquals(timeExpected.getUnixEpoch(), timeActual.getUnixEpoch());
    }

    @Test
    public void convertTimeCorrectDate() {
        Time timeExpected = new Time(1, "петок, 25, декември 2015 00:00:00 CET");
        Time timeActual = ts.convertTime("2015-12-25");
        assertEquals(timeExpected.getSkopjeDate(), timeActual.getSkopjeDate());
    }

    @Test(expected = DateTimeParseException.class)
    public void convertTimeWrongInput1() {
        Time timeActual = ts.convertTime("2015-12-");
    }

    @Test(expected = DateTimeParseException.class)
    public void convertTimeWrongInput2() {
        Time timeActual = ts.convertTime("2015-12-00000000000000000");
    }

    @Test(expected = DateTimeParseException.class)
    public void convertTimeWrongDateFormat1() {
        Time timeActual = ts.convertTime("2015-13-01");
    }

    @Test(expected = DateTimeParseException.class)
    public void convertTimeWrongDateFormat2() {
        Time timeActual = ts.convertTime("2015-02-30");
    }

}