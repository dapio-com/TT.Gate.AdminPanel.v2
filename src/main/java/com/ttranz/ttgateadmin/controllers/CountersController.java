package com.ttranz.ttgateadmin.controllers;

import com.ttranz.ttgateadmin.dto.DtoCounters;
import com.ttranz.ttgateadmin.dto.DtoOrgsAutocomplete;
import com.ttranz.ttgateadmin.models.Counters;
import com.ttranz.ttgateadmin.repo.CountersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.css.Counter;

import java.util.List;

@Controller
public class CountersController {

    @Autowired
    private CountersRepository countersRepository;

    @GetMapping("/get-counters")
    //public ResponseEntity<List<DtoCounters>> getCounters() {
    public ResponseEntity<List<Counters>> getCounters() {
        List<Counters> counters = countersRepository.selectCounters();
        return ResponseEntity.ok(counters);

    }

}
