package com.ttranz.ttgateadmin.controllers;

import com.ttranz.ttgateadmin.models.Operation;
import com.ttranz.ttgateadmin.repo.OperationRepository;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class OperationController {

    @Autowired
    private OperationRepository operationRepository;



    @GetMapping("/operation-panel")
    public String getOperationPanel(Model model){
//        List<Operation> operations = operationRepository.selectLastN();
//        model.addAttribute("operations", operations);
        return "blocks/operation_block";
    }

    @GetMapping("/get-last-operations")
    public String getLastOperations(@RequestParam Long org_id, Model model){
        System.out.println("ORG_ID : " + org_id);
        List<Operation> operations;
        if(org_id != 0){
            operations = operationRepository.selectLastNOrgId(org_id);
        } else {
            operations = operationRepository.selectLastN();
        }

        model.addAttribute("operations", operations);
        return "results/operations";
    }



    @GetMapping("/ops-per-minute")
    public ResponseEntity<List<String>> getOperationsPerMinute() {
        List<String> opsPerMinute = operationRepository.getOperationsPerMinute();
        return ResponseEntity.ok(opsPerMinute);

    }

    @GetMapping("/op_show-info")
    public String getOperationInfo(@RequestParam Long id, Model model) {
        Iterable<Operation> operationInfo = operationRepository.findAllById(Collections.singleton(id));
        //Optional<Operation> operationInfo = operationRepository.findById(id);
        //return ResponseEntity.ok(operationInfo);
        model.addAttribute("operation", operationInfo);
        return "results/operation_info";

    }


    @GetMapping("/get-report")
    public void downloadXls(HttpServletResponse response, @RequestParam Long org_id, @RequestParam String date) throws IOException {
        String[] dateSplited = date.split(" - ");
        //response.setContentType("application/octet-stream");

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=report_"+date+".xlsx");

        //ByteArrayInputStream stream = ReportFileExporter.reportToExcelFile(operationRepository.selectForReport(org_id, Timestamp.valueOf(dateSplited[0] + " 00:00:00"), Timestamp.valueOf(dateSplited[1] + " 23:59:59")));
        InputStream stream = ReportFileExporter.reportToExcelFile(date, operationRepository.selectForReport(org_id, Timestamp.valueOf(dateSplited[0] + " 00:00:00"), Timestamp.valueOf(dateSplited[1] + " 23:59:59")));
        IOUtils.copy(Objects.requireNonNull(stream), response.getOutputStream());
        response.flushBuffer();
        stream.close();
    }




}
