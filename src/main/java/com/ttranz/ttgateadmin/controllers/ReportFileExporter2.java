package com.ttranz.ttgateadmin.controllers;

import com.ttranz.ttgateadmin.models.Operation;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class ReportFileExporter2 {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Operation> listUsers;

    public ReportFileExporter2(List<Operation> listOperations) {
        this.listUsers = listOperations;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Users");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Дата / Время", style);
        createCell(row, 1, "Статус", style);
        createCell(row, 2, "Терминал", style);
        createCell(row, 3, "ТСП", style);
        createCell(row, 4, "Карта", style);
        createCell(row, 5, "RRN", style);
        createCell(row, 6, "Авторизация", style);
        createCell(row, 7, "Сумма", style);
        createCell(row, 8, "Доп. инфо.", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Timestamp) {
            cell.setCellValue((Timestamp) value);
        } else if (value instanceof BigDecimal) {
            cell.setCellValue(String.valueOf(value));
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Operation operation : listUsers) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, operation.getOp_date_time(), style);
            createCell(row, columnCount++, operation.getOp_status(), style);
            createCell(row, columnCount++, operation.getOp_tid(), style);
            createCell(row, columnCount++, operation.getOp_tsp(), style);
            createCell(row, columnCount++, operation.getOp_card_num(), style);
            createCell(row, columnCount++, operation.getOp_rrn(), style);
            createCell(row, columnCount++, operation.getOp_auth_code(), style);
            createCell(row, columnCount++, operation.getOp_amount(), style);
            createCell(row, columnCount++, operation.getOp_amount(), style);

        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }


}
