package com.ttranz.ttgateadmin.controllers;



import com.ttranz.ttgateadmin.models.Operation;
import com.ttranz.ttgateadmin.models.Org;
import com.ttranz.ttgateadmin.repo.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class ChartController {


    @Autowired
    private OperationRepository operationRepository;

    @GetMapping("/chart-panel")
    public String getChartPanel(){
        return "blocks/charts_block";
    }



    @GetMapping("/get-chart")
    public ResponseEntity<List<Operation>> getChart(@RequestParam Long org_id, @RequestParam String date){
        String[] dateSplited = date.split(" - ");

        List<Operation> operations = operationRepository.selectForChart(org_id, Timestamp.valueOf(dateSplited[0] + " 00:00:00"), Timestamp.valueOf(dateSplited[1] + " 23:59:59"));

        return ResponseEntity.ok(operations);

    }


}
