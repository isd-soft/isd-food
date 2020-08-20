package com.example.isdbackend.service.payment;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Excel {

    private final XSSFWorkbook workbook = new XSSFWorkbook();
    private final Sheet sheet;
    private Row row;

    public Excel(String sheetName) {
        this.sheet = workbook.createSheet(sheetName);
    }

    public void createRow(int n) {
        row = this.sheet.createRow(n);
    }

    public void createCell(int r, String value) {
        Cell cell = row.createCell(r);
        cell.setCellValue(value);
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
