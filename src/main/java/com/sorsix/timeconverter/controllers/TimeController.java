package com.sorsix.timeconverter.controllers;

import com.sorsix.timeconverter.services.TimeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/timestamp/")
public class TimeController {

    private final TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping(value = "/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getDate(@PathVariable String date) {
        try {
            return ResponseEntity.ok().body(timeService.convertTime(date));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("{\"Error\":\"" + e.getMessage() + "\"}");
        }
    }
}
