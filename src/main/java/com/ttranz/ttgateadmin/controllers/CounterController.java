package com.ttranz.ttgateadmin.controllers;

import com.ttranz.ttgateadmin.models.Counter;
import com.ttranz.ttgateadmin.repo.CounterRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CounterController {

    private final CounterRepository counterRepository;

    public CounterController(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @GetMapping("/get-counters")
    //public ResponseEntity<List<DtoCounters>> getCounters() {
    public ResponseEntity<List<Counter>> getCounters() {
        List<Counter> counters = counterRepository.selectCounters();
        return ResponseEntity.ok(counters);

    }

}
