package com.ttranz.ttgateadmin.controllers;


import com.ttranz.ttgateadmin.dto.DtoChart;
import com.ttranz.ttgateadmin.repo.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    //public ResponseEntity<List<Operation>> getChart(@RequestParam Long org_id, @RequestParam String date){
    public ResponseEntity<List<DtoChart>> getChart(@RequestParam Long org_group_id, @RequestParam Long org_id, @RequestParam String date){
        System.out.println(org_id);

        String[] dateSplited = date.split(" - ");

        List<DtoChart> operations;
        if(org_group_id > 0){
            operations = operationRepository.selectForChartOrgGroup(org_group_id, Timestamp.valueOf(dateSplited[0] + " 00:00:00"), Timestamp.valueOf(dateSplited[1] + " 23:59:59"));
        } else if(org_id > 0) {
            operations = operationRepository.selectForChartOrg(org_id, Timestamp.valueOf(dateSplited[0] + " 00:00:00"), Timestamp.valueOf(dateSplited[1] + " 23:59:59"));
        } else {
            operations = operationRepository.selectForChartAll(Timestamp.valueOf(dateSplited[0] + " 00:00:00"), Timestamp.valueOf(dateSplited[1] + " 23:59:59"));
        }

        return ResponseEntity.ok(operations);

    }


}
