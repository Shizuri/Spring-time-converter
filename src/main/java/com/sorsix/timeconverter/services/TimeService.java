package com.sorsix.timeconverter.services;

import com.sorsix.timeconverter.models.Time;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Service
public class TimeService {

    public Time convertTime(String date) {

        String dateResult = "";
        long epochResult = 0L;
        boolean isEpoch = date.matches("\\d{10,19}");

        if (isEpoch) {
            Instant instant = Instant.ofEpochSecond(fixEpoch(date));
            ZonedDateTime zonedDateTimeEpoch = ZonedDateTime.ofInstant(instant, ZoneId.of("Europe/Skopje"));
            dateResult = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).format(zonedDateTimeEpoch);
            epochResult = zonedDateTimeEpoch.toEpochSecond();
            return new Time(epochResult, dateResult);
        } else {
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(date + "T00:00:00+02:00[Europe/Skopje]");
            dateResult = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).format(zonedDateTime);
            epochResult = zonedDateTime.toEpochSecond();
            return new Time(epochResult, dateResult);
        }
    }

    private long fixEpoch(String input) {
        long result = Long.parseLong(input);
        long shortener = 9999999999L;

        while (result > shortener) {
            result /= 10;
        }

        return result;
    }
}
