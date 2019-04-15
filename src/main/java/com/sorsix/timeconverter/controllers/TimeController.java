package com.sorsix.timeconverter.controllers;

import com.sorsix.timeconverter.models.Time;
import com.sorsix.timeconverter.services.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/timestamp/")
public class TimeController {

    @Autowired
    private TimeService timeService;

    @GetMapping(value = "/{date}", produces = "application/json")
    public ResponseEntity getDate(@PathVariable String date) {
        Time res = timeService.convertTime(date);
        if (res.getEpoch() == 0) {
            return ResponseEntity.badRequest().body(new Time(0, res.getSkopje()));
        }
        return ResponseEntity.ok(timeService.convertTime(date));
    }
}
