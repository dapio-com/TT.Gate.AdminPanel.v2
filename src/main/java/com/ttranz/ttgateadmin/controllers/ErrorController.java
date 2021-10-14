package com.ttranz.ttgateadmin.controllers;


import com.ttranz.ttgateadmin.models.Error;
import com.ttranz.ttgateadmin.repo.ErrorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ErrorController {


    private final ErrorRepository errorRepository;

    public ErrorController(ErrorRepository errorRepository) {
        this.errorRepository = errorRepository;
    }

    @GetMapping("/get-errors")
    public String getErrors(Model model){

        List<Error> errors = errorRepository.selectLastN();
        model.addAttribute("errors", errors);

        return "results/error_panel";
    }





}
