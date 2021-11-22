package com.ttranz.ttgateadmin.controllers;

import com.ttranz.ttgateadmin.models.Operation;
import com.ttranz.ttgateadmin.models.User;
import com.ttranz.ttgateadmin.repo.OperationRepository;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Controller
public class OperationController {


    private final OperationRepository operationRepository;

    public OperationController(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }


    @GetMapping("/operation-panel")
    public String getOperationPanel(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        return "blocks/operation_block";
    }

    @GetMapping("/get-last-operations")
    public String getLastOperations(@AuthenticationPrincipal User user, @RequestParam Long org_id, Long org_group_id, Model model){

        switch (user.getRoles().size()){
            case 1 : {
                org_id = user.getUserOrgId();
                org_group_id = user.getUserOrgGroupId();
                break;
            }
            case 2 : {
                org_group_id = user.getUserOrgGroupId();
                break;
            }
        }


        List<Operation> operations;
        if(org_id > 0){
            operations = operationRepository.selectLastNOrgId(org_id);
        } else if (org_group_id > 0) {
            operations = operationRepository.selectLastNOrgGroupId(org_group_id);
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
    public void downloadXls(@AuthenticationPrincipal User user, HttpServletResponse response, @RequestParam Long org_group_id, @RequestParam Long org_id, @RequestParam String date) throws IOException {

        switch (user.getRoles().size()){
            case 1 : {
                org_id = user.getUserOrgId();
                org_group_id = user.getUserOrgGroupId();
                break;
            }
            case 2 : {
                org_group_id = user.getUserOrgGroupId();
                break;
            }
        }

        String[] dateSplited = date.split(" - ");

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=report_"+date+".xlsx");

        InputStream stream;
        if (org_group_id > 0){
            stream = ReportFileExporter.reportToExcelFile(date, operationRepository.selectForReportOrgGroupId(org_group_id, Timestamp.valueOf(dateSplited[0] + " 00:00:00"), Timestamp.valueOf(dateSplited[1] + " 23:59:59")));
        } else if (org_id > 0) {
            stream = ReportFileExporter.reportToExcelFile(date, operationRepository.selectForReportOrgId(org_id, Timestamp.valueOf(dateSplited[0] + " 00:00:00"), Timestamp.valueOf(dateSplited[1] + " 23:59:59")));
        } else {
            stream = ReportFileExporter.reportToExcelFile(date, operationRepository.selectForReportAll(Timestamp.valueOf(dateSplited[0] + " 00:00:00"), Timestamp.valueOf(dateSplited[1] + " 23:59:59")));
        }

        //InputStream stream = ReportFileExporter.reportToExcelFile(date, operationRepository.selectForReportOrgId(org_id, Timestamp.valueOf(dateSplited[0] + " 00:00:00"), Timestamp.valueOf(dateSplited[1] + " 23:59:59")));
        IOUtils.copy(Objects.requireNonNull(stream), response.getOutputStream());
        response.flushBuffer();
        stream.close();
    }


    @GetMapping("view-report")
    public String viewReport(@RequestParam Long org_id, @RequestParam String date, @RequestParam String tid, @RequestParam String time, Model model){
        String[] dateSplited = date.split(" - ");
        List<Operation> operations;

        if(time.length() > 0){
            if(tid.length() > 0){
                operations = operationRepository.viewReportOrgIdTimeTid(org_id, tid, Timestamp.valueOf(dateSplited[1] + " " + time + ":00:00"), Timestamp.valueOf(dateSplited[1] + " " + time + ":59:59"));
            } else {
                operations = operationRepository.viewReportOrgIdTime(org_id, Timestamp.valueOf(dateSplited[1] + " " + time + ":00:00"), Timestamp.valueOf(dateSplited[1] + " " + time + ":59:59"));
            }

        } else {
            if(tid.length() > 0){
                operations = operationRepository.viewReportOrgIdTid(org_id, tid, Timestamp.valueOf(dateSplited[0] + " 00:00:00"), Timestamp.valueOf(dateSplited[1] + " 23:59:59"));
            } else {
                operations = operationRepository.viewReportOrgId(org_id, Timestamp.valueOf(dateSplited[0] + " 00:00:00"), Timestamp.valueOf(dateSplited[1] + " 23:59:59"));
            }

        }


        model.addAttribute("operations", operations);
        return "results/operations";

    }




}
