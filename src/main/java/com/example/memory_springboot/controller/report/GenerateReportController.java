package com.example.memory_springboot.controller.report;

import com.example.memory_springboot.config.JasperFontConfig;
import com.example.memory_springboot.model.dto.ticket.TktDto;
import com.example.memory_springboot.service.ticket.TicketService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.fonts.FontFamily;
import net.sf.jasperreports.extensions.ExtensionsEnvironment;
import net.sf.jasperreports.extensions.ExtensionsRegistry;
import net.sf.jasperreports.extensions.ExtensionsRegistryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/report")
@Slf4j
public class GenerateReportController {
    private final TicketService ticketService;
    private final JasperFontConfig jasperFontConfig;

    @Autowired
    public GenerateReportController(TicketService ticketService, JasperFontConfig jasperFontConfig) {
        this.ticketService = ticketService;
        this.jasperFontConfig = jasperFontConfig;
    }



    @GetMapping("/tkt-order")
    public void generateOrderReport(HttpServletResponse response) throws Exception {
        InputStream reportStream = getClass().getResourceAsStream("/report/tkt_report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        List<TktDto> dataList = getTktList();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ReportTitle", "票券商品報表");

        FontFamily chineseFont = jasperFontConfig.chineseFont();
        JasperReportsContext jasperReportsContext = DefaultJasperReportsContext.getInstance();

//
        jasperReportsContext.setProperty("net.sf.jasperreports.default.pdf.font.name", "NotoSansTC");
        jasperReportsContext.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
        jasperReportsContext.setProperty("net.sf.jasperreports.default.pdf.embedded", "true");

        JasperPrint jasperPrint = JasperFillManager.getInstance(jasperReportsContext).fill(jasperReport, parameters, dataSource);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=tktList_report.pdf");

        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }

    private List<TktDto> getTktList() {
        return ticketService.getTktList();
    }

}
