package com.sorsix.timeconverter.controllers;

import com.sorsix.timeconverter.models.Time;
import com.sorsix.timeconverter.services.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/timestamp/")
public class TimeController {

    @Autowired
    private TimeService timeService;

    @GetMapping("/{date}")
    public Time getDate(@PathVariable String date){
        return timeService.convertTime(date);
    }
}
