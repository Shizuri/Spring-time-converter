package com.sorsix.timeconverter.services;

import com.sorsix.timeconverter.models.Time;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

@Service
public class TimeService {

    public Time convertTime(String date) {

        int dateOrEpoch = dateOrEpoch(date);
        String dateResult = "";
        long epochResult = 0L;

        if (dateOrEpoch == 0) { //it's an epoch
            Instant instant = Instant.ofEpochSecond(fixEpoch(date));
            ZonedDateTime zonedDateTimeEpoch = ZonedDateTime.ofInstant(instant, ZoneId.of("Europe/Skopje"));
            dateResult = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).format(zonedDateTimeEpoch);
            epochResult = zonedDateTimeEpoch.toEpochSecond();
            return new Time(epochResult, dateResult);
        }

        if (dateOrEpoch == 1) { //it's a date
            try {
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(date + "T00:00:00+02:00[Europe/Skopje]");
                dateResult = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).format(zonedDateTime);
                epochResult = zonedDateTime.toEpochSecond();
                return new Time(epochResult, dateResult);
            } catch (DateTimeParseException e) {
                e.printStackTrace();
                return new Time(0, "ERROR; Invalid input");
            }

        }

        if (dateOrEpoch == 2) { //it's a date and time
            try {
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(date + "+02:00[Europe/Skopje]");
                dateResult = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).format(zonedDateTime);
                epochResult = zonedDateTime.toEpochSecond();
                return new Time(epochResult, dateResult);
            } catch (DateTimeParseException e) {
                e.printStackTrace();
                return new Time(0, "ERROR; Invalid input");
            }

        }


        return new Time(0, "ERROR; Invalid input");
    }

    public int dateOrEpoch(String value) {
        if (value.matches("\\d{10,19}")) { // long can't be longer than 19 digits
            return 0; //it's an epoch
        }

        if (value.matches("[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])")) {
            return 1; //it's a date
        }

        if (value.matches("[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])T([0-1][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]")) {
            return 2; //it's a date with time
        }

        return -1;
    }

    public long fixEpoch(String input) {
        long result = Long.parseLong(input);
        long shortener = 9999999999L;

        while (result > shortener) {
            result /= 10;
        }

        return result;
    }
}
