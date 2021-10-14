package com.ttranz.ttgateadmin.controllers;

import com.ttranz.ttgateadmin.models.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {


    @GetMapping("/")
    public String home(@AuthenticationPrincipal User user, Model model){
        //User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "home";
    }

    @GetMapping("/main-panel")
    public String getMainPanel(){

        return "blocks/main_panel_block";
    }



}
