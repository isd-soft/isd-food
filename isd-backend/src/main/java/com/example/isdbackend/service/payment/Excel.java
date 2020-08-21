package com.example.isdbackend.service.payment;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Excel {

    private final XSSFWorkbook workbook = new XSSFWorkbook();
    private final Sheet sheet;
    private Row row;
    private XSSFCellStyle style;

    public Excel(String sheetName) {
        this.sheet = workbook.createSheet(sheetName);

        this.style = workbook.createCellStyle();

        XSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
    }

    public void createRow(int n) {
        row = this.sheet.createRow(n);
    }

    public void createCell(int r, String value) {
        Cell cell = row.createCell(r);
        cell.setCellValue(value);
    }

    public void createCell(int r, String value, boolean bold) {
        Cell cell = row.createCell(r);
        cell.setCellValue(value);

        if (bold)
            cell.setCellStyle(style);
    }

    public byte[] save() {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            workbook.write(outputStream);
            workbook.close();

            outputStream.close();
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
