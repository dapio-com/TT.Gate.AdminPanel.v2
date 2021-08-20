package com.ttranz.ttgateadmin.controllers;


import com.ttranz.ttgateadmin.models.Operation;
import com.ttranz.ttgateadmin.repo.OperationRepository;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
public class ReportController {

    @Autowired
    private OperationRepository operationRepository;

    @GetMapping("/get-report")
    public void downloadXls(HttpServletResponse response, String date_from, String date_to) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=report.xlsx");
        ByteArrayInputStream stream = ReportFileExporter.reportToExcelFile(operationRepository.selectForReport(Timestamp.valueOf(date_from + " 00:00:00"), Timestamp.valueOf(date_to + " 23:59:59")));
        IOUtils.copy(Objects.requireNonNull(stream), response.getOutputStream());
    }


}
