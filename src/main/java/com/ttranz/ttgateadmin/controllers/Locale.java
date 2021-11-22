package com.ttranz.ttgateadmin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

public class Locale {


    @Controller
    public class ViewController {
        @GetMapping("/")
        public String locale() {
            return "home";
        }
    }

}
