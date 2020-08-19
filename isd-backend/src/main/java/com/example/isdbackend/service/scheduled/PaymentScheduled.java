//package com.example.isdbackend.service.scheduled;
//
//
//import com.example.isdbackend.dto.PaymentDataDTO;
//import com.example.isdbackend.dto.UserPaymentData;
//import com.example.isdbackend.filter.OrderFilter;
//import com.example.isdbackend.model.Payment;
//import com.example.isdbackend.repository.PaymentRepository;
//import com.example.isdbackend.service.SupervisorService;
//import com.example.isdbackend.util.DateUtil;
//import lombok.AllArgsConstructor;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFFont;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//@AllArgsConstructor
//@Component
//public class PaymentScheduled {
//
//    private final SupervisorService supervisorService;
//
//    private final PaymentRepository paymentRepository;
//
//    private final List<String> userFields = new ArrayList<>(Arrays.asList("Full name", "Payment"));
//
//    //    cron = "0 6 1 * *"
//    @Scheduled(fixedDelay = 200000000000)
//    public void savePaymentData() throws ParseException, IOException {
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date currentDate = dateFormat.parse("2020-09-01");
//
//        Date dt = dateFormat.parse("2020-09-01");
//        Calendar c = Calendar.getInstance();
//        c.setTime(dt);
//        c.add(Calendar.MONTH, -1);
//        dt = c.getTime();
//
//        OrderFilter orderFilter = new OrderFilter();
//        orderFilter.setDateFrom(DateUtil.getDateFromDateTime(dt));
//
//        c.setTime(currentDate);
//        c.add(Calendar.DAY_OF_MONTH, -1);
//        currentDate = c.getTime();
//
//        orderFilter.setDateTo(DateUtil.getDateFromDateTime(currentDate));
//
//        PaymentDataDTO paymentData = supervisorService.getAllPaymentData(orderFilter);
//
//        for (UserPaymentData userPaymentData : paymentData.getUserPayments()) {
//            Payment payment = new Payment();
//            payment.setDate(dateFormat.parse(paymentData.getDateTo()));
//            payment.setFullName(userPaymentData.getFullName());
//            payment.setPayment(userPaymentData.getPayment());
//            payment.setUserId(userPaymentData.getUserId());
//
//            paymentRepository.save(payment);
//        }
//
//        Workbook workbook = new XSSFWorkbook();
//
//        Sheet sheet = workbook.createSheet("Payment data");
////        sheet.setColumnWidth(0, 6000);
////        sheet.setColumnWidth(1, 4000);
//
//        Row header = sheet.createRow(1);
//        Row userFieldsRow = sheet.createRow(2);
//        CellStyle headerStyle = workbook.createCellStyle();
////        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
////        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//
//        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
//        font.setFontName("Arial");
//        font.setFontHeightInPoints((short) 16);
//        font.setBold(true);
//        headerStyle.setFont(font);
//
//        Cell headerCell = header.createCell(0);
//        headerCell.setCellValue("Period");
//        headerCell.setCellStyle(headerStyle);
//
//        headerCell = header.createCell(1);
//        headerCell.setCellValue(paymentData.getDateFrom() + " / " + paymentData.getDateTo());
//        headerCell.setCellStyle(headerStyle);
//
//        CellStyle style = workbook.createCellStyle();
//        style.setWrapText(true);
//
//        for (int i = 0; i < userFields.size(); i++) {
//            Cell cell = userFieldsRow.createCell(i);
//            cell.setCellValue(userFields.get(i));
//        }
//
//        for (int i = 0; i < paymentData.getUserPayments().size(); i++) {
//            Row userPaymentRow = sheet.createRow(3 + i);
//            Cell cell = userPaymentRow.createCell(0);
//            cell.setCellValue(paymentData.getUserPayments().get(i).getFullName());
//            Cell cell2 = userPaymentRow.createCell(1);
//            cell2.setCellValue(paymentData.getUserPayments().get(i).getPayment());
//        }
//
//        File currDir = new File(".");
//        String path = currDir.getAbsolutePath();
//        String fileLocation = path.substring(0, path.length() - 1) + "temp.xlsx";
//
//        FileOutputStream outputStream = new FileOutputStream(fileLocation);
//        workbook.write(outputStream);
//        workbook.close();
//    }
//}
