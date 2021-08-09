package com.ttranz.ttgateadmin.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {


    @GetMapping("/get-errors")
    private String getErrors(){



        return "results/errors";
    }





}
