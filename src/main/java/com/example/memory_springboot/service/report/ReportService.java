package com.example.memory_springboot.service.report;

import net.sf.jasperreports.engine.JasperPrint;

public interface ReportService {

    JasperPrint generateTktOrderReport() throws Exception;
}
