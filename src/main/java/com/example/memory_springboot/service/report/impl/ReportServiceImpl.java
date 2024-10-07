package com.example.memory_springboot.service.report.impl;

import com.example.memory_springboot.config.JasperFontConfig;
import com.example.memory_springboot.model.dto.ticket.TktDto;
import com.example.memory_springboot.service.report.ReportService;
import com.example.memory_springboot.service.ticket.TicketService;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.fonts.FontFamily;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    private final TicketService ticketService;
    private final JasperFontConfig jasperFontConfig;

    @Autowired
    public ReportServiceImpl(TicketService ticketService, JasperFontConfig jasperFontConfig) {
        this.ticketService = ticketService;
        this.jasperFontConfig = jasperFontConfig;
    }
    @Override
    public JasperPrint generateTktOrderReport() throws Exception {
        InputStream reportStream = getClass().getResourceAsStream("/report/tkt_report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        List<TktDto> dataList = ticketService.getTktList();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ReportTitle", "票券商品報表");

        FontFamily chineseFont = jasperFontConfig.chineseFont();
        JasperReportsContext jasperReportsContext = DefaultJasperReportsContext.getInstance();

        jasperReportsContext.setProperty("net.sf.jasperreports.default.pdf.font.name", "NotoSansTC");
        jasperReportsContext.setProperty("net.sf.jasperreports.default.pdf.encoding", "Identity-H");
        jasperReportsContext.setProperty("net.sf.jasperreports.default.pdf.embedded", "true");


        return JasperFillManager.getInstance(jasperReportsContext).fill(jasperReport, parameters, dataSource);
    }
}
