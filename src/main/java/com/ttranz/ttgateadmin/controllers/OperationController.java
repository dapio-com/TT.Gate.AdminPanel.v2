package com.ttranz.ttgateadmin.controllers;

import com.ttranz.ttgateadmin.models.Operation;
import com.ttranz.ttgateadmin.repo.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
public class OperationController {

    @Autowired
    private OperationRepository operationRepository;



    @GetMapping("/operation-panel")
    public String getOperationPanel(Model model){
        List<Operation> operations = operationRepository.selectLastN();
        model.addAttribute("operations", operations);
        return "blocks/operation_block";
    }

    @GetMapping("/ops-per-minute")
    public ResponseEntity<List<String>> getOperationsPerMinute() {
        List<String> opsPerMinute = operationRepository.getOperationsPerMinute();
        return ResponseEntity.ok(opsPerMinute);

    }

    @GetMapping("/op_show-info")
    public String getOperationInfo(@RequestParam Long id, Model model) {
        List<Operation> operationInfo = operationRepository.findAllById(Collections.singleton(id));
        //return ResponseEntity.ok(operationInfo);
        model.addAttribute("operation", operationInfo);
        return "results/operation_info";

    }


}
