package com.ttranz.ttgateadmin.controllers;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.ttranz.ttgateadmin.models.Operation;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




public class ReportFileExporter {


    private static void CreateHeader(Workbook workbook, Sheet sheet){

        Row row = sheet.createRow(0);


        // Style for header font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        XSSFFont font = (XSSFFont) workbook.createFont();
        font.setBold(true);
        headerCellStyle.setFont(font);

        // Creating header
        Cell cell = row.createCell(0);
        cell.setCellValue("Дата / Время");
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(1);
        cell.setCellValue("Статус");
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(2);
        cell.setCellValue("Терминал");
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(3);
        cell.setCellValue("ТСП");
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(4);
        cell.setCellValue("Карта");
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(5);
        cell.setCellValue("RRN");
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(6);
        cell.setCellValue("Авторизация");
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(7);
        cell.setCellValue("Сумма");
        cell.setCellStyle(headerCellStyle);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 8,17));
        cell = row.createCell(8);
        cell.setCellValue("Доп. инфо.");
        cell.setCellStyle(headerCellStyle);

        System.out.println("COLUMNS CREATED");

    }

    private static void CreateDataRows(Workbook workbook, Sheet sheet, List<Operation> operations){

        CellStyle cellStyleForAmount = workbook.createCellStyle();
        cellStyleForAmount.setAlignment(HorizontalAlignment.RIGHT);

        int k = 0;
        double summ = 0;
        for(int i = 0; i < operations.size(); i++) {
            Row dataRow = sheet.createRow(i + 1);

            dataRow.createCell(0).setCellValue(String.valueOf(operations.get(i).getOp_date_time()));
            //status = ((operations.get(i).getOp_status() == 0)? "X" : "OK");
            if ((operations.get(i).getOp_status() == 0)) {
                dataRow.createCell(1).setCellValue("X");
            } else {
                dataRow.createCell(1).setCellValue("OK");
                summ = summ + Double.parseDouble(String.valueOf(operations.get(i).getOp_amount()));
            }
            //dataRow.createCell(1).setCellValue((operations.get(i).getOp_status() == 0)? "X" : "OK");
            dataRow.createCell(2).setCellValue(operations.get(i).getOp_tid());
            dataRow.createCell(3).setCellValue(operations.get(i).getOp_tsp());
            dataRow.createCell(4).setCellValue(operations.get(i).getOp_card_num());
            dataRow.createCell(5).setCellValue(operations.get(i).getOp_rrn());
            dataRow.createCell(6).setCellValue(operations.get(i).getOp_auth_code());
            dataRow.createCell(7).setCellValue(String.valueOf(operations.get(i).getOp_amount()));
            dataRow.getCell(7).setCellStyle(cellStyleForAmount);

            dataRow.createCell(8).setCellValue((operations.get(i).getOp_xadd01() != null)? operations.get(i).getOp_xadd01() : "");
            dataRow.createCell(9).setCellValue((operations.get(i).getOp_xadd02() != null)? operations.get(i).getOp_xadd02() : "");
            dataRow.createCell(10).setCellValue((operations.get(i).getOp_xadd03() != null)? operations.get(i).getOp_xadd03() : "");
            dataRow.createCell(11).setCellValue((operations.get(i).getOp_xadd04() != null)? operations.get(i).getOp_xadd04() : "");
            dataRow.createCell(12).setCellValue((operations.get(i).getOp_xadd05() != null)? operations.get(i).getOp_xadd05() : "");
            dataRow.createCell(13).setCellValue((operations.get(i).getOp_xadd06() != null)? operations.get(i).getOp_xadd06() : "");
            dataRow.createCell(14).setCellValue((operations.get(i).getOp_xadd07() != null)? operations.get(i).getOp_xadd07() : "");
            dataRow.createCell(15).setCellValue((operations.get(i).getOp_xadd08() != null)? operations.get(i).getOp_xadd08() : "");
            dataRow.createCell(16).setCellValue((operations.get(i).getOp_xadd09() != null)? operations.get(i).getOp_xadd09() : "");
            dataRow.createCell(17).setCellValue((operations.get(i).getOp_xadd10() != null)? operations.get(i).getOp_xadd10() : "");

            k++;

        }

        Row totalAmountRow = sheet.createRow(k + 3);
        totalAmountRow.createCell(0).setCellValue(summ);
        totalAmountRow.getCell(0).setCellStyle(cellStyleForAmount);

        System.out.println("DATA IMPORTED");

    }

    private static void SizingColumns(Sheet sheet) {

        for (int i = 0; i < 18 ; i++) {
            sheet.autoSizeColumn(i);
        }

        System.out.println("SIZED");

    }


    public static ByteArrayInputStream reportToExcelFile(String date, List<Operation> operations) {

        try(Workbook workbook = new XSSFWorkbook()){

            Sheet sheet = workbook.createSheet("REPORT " + date);
            sheet.setDisplayGridlines(true);


            CreateHeader(workbook, sheet);

            CreateDataRows(workbook, sheet, operations);

            SizingColumns(sheet);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }




}
