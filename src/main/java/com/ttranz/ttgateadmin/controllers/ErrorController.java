package com.ttranz.ttgateadmin.controllers;


import com.ttranz.ttgateadmin.models.Error;
import com.ttranz.ttgateadmin.models.Org;
import com.ttranz.ttgateadmin.repo.ErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {


    @Autowired
    private ErrorRepository errorRepository;

    @GetMapping("/get-errors")
    public String getErrors(Model model){

        Iterable<Error> errors = errorRepository.selectLastN();
        model.addAttribute("errors", errors);

        return "results/error_panel";
    }





}
