package com.example.prestigeportfoliocreators.controller;

import com.example.prestigeportfoliocreators.models.Stats;
import com.example.prestigeportfoliocreators.service.StatsService;
import com.example.prestigeportfoliocreators.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(path = "/stats")
public class StatisticsController {
    private final StatsService statsService;

    @Autowired
    public StatisticsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping(path = "/get")
    public ResponseEntity<HashMap<String, Object>> getStats(){
        Stats stats = statsService.getStats();
        return new ResponseEntity<>(Response.createBody("stats", stats), HttpStatus.OK);
    }

    @PatchMapping(path = "/update/views")
    public ResponseEntity<HashMap<String,Object>> updateViews(){
        Stats stats = statsService.updateViews();
        return new ResponseEntity<>(Response.createBody("stats",stats), HttpStatus.OK);
    }

    @PatchMapping(path = "/update/last_updated")
    public ResponseEntity<HashMap<String,Object>> updateLastUpdate(){
        Stats stats = statsService.updateLastUpdate();
        return new ResponseEntity<>(Response.createBody("stats",stats), HttpStatus.OK);
    }
}
