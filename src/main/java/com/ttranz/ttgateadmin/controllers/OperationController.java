package com.ttranz.ttgateadmin.controllers;

import com.ttranz.ttgateadmin.models.Operation;
import com.ttranz.ttgateadmin.models.Org;
import com.ttranz.ttgateadmin.repo.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OperationController {

    @Autowired
    private OperationRepository operationRepository;



    @GetMapping("/operation-panel")
    public String getOperationPanel(Model model){
        Iterable<Operation> operations = operationRepository.selectLastN();
        model.addAttribute("operations", operations);
        return "blocks/operation_block";
    }


}
