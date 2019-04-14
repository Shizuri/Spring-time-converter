package com.sorsix.timeconverter.services;

import com.sorsix.timeconverter.models.Time;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Service
public class TimeService {

    public Time convertTime(String date){

        int dateOrEpoch = dateOrEpoch(date);
        String dateResult = "";
        long epochResult = 0L;

        if(dateOrEpoch == 0){ //it's an epoch
            Instant instant = Instant.ofEpochSecond(fixEpoch(date));
            ZonedDateTime zonedDateTimeEpoch = ZonedDateTime.ofInstant(instant, ZoneId.of("Europe/Skopje"));
            dateResult = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).format(zonedDateTimeEpoch);
            epochResult = zonedDateTimeEpoch.toEpochSecond();
            return new Time(epochResult, dateResult);
        }

        if(dateOrEpoch == 1){ //it's a date
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(date + "T00:00:00+02:00[Europe/Skopje]");
            dateResult = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).format(zonedDateTime);
            epochResult = zonedDateTime.toEpochSecond();
            return new Time(epochResult, dateResult);
        }

        if(dateOrEpoch == 2){ //it's a date and time
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(date + "02:00[Europe/Skopje]");
            dateResult = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).format(zonedDateTime);
            epochResult = zonedDateTime.toEpochSecond();
            return new Time(epochResult, dateResult);
        }


        return new Time(0, "ERROR; Invalid input");
    }

    public int dateOrEpoch(String value){
        if(value.matches("\\d{10,19}")){ // long can't be longer than 19 digits
            return 0; //it's an epoch
        }

        if(value.matches("\\d\\d\\d\\d-[0-1]\\d-[0-3]\\d")){
            return 1; //it's a date
        }

        if(value.matches("\\d\\d\\d\\d-[0-1]\\d-[0-3]\\dT[0-2]\\d:[0-5]\\d:[0-5]\\d")){
            return 2; //it's a date with time
        }

        return -1;
    }

    public long fixEpoch(String input){
        long result = Long.parseLong(input);
        long shortener = 9999999999L;

        while(result > shortener){
            result /= 10;
        }

        return result;
    }
}
