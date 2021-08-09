package com.ttranz.ttgateadmin.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {


    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/main-panel")
    public String getMainPanel(){
        return "blocks/main_panel_block";
    }



}
