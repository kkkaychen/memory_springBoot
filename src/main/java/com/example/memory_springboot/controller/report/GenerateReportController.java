package com.example.memory_springboot.controller.report;


import com.example.memory_springboot.service.report.ReportService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
@Slf4j
public class GenerateReportController {
    private final ReportService reportService;

    @Autowired
    public GenerateReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    @GetMapping("/tkt-order")
    public void generateOrderReport(HttpServletResponse response) throws Exception {
        // 生成報表
        JasperPrint jasperPrint = reportService.generateTktOrderReport();

        // 設置輸出類型和標頭
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=tktList_report.pdf");

        // 將報表輸出為PDF格式
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }

}
