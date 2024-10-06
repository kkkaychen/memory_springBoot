package com.example.memory_springboot.config;

import jakarta.annotation.PostConstruct;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.fonts.FontFamily;
import net.sf.jasperreports.engine.fonts.SimpleFontFamily;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.awt.*;
import java.io.IOException;

@Configuration
public class JasperFontConfig {
    @Bean
    public FontFamily chineseFont() throws IOException, FontFormatException {
        SimpleFontFamily family = new SimpleFontFamily();
        family.setName("NotoSansTC");

        ClassPathResource fontResource = new ClassPathResource("fonts/NotoSansTC-VariableFont_wght.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontResource.getInputStream());

        family.setNormal(fontResource.getURL().toString());
        family.setPdfEncoding("Identity-H");
        family.setPdfEmbedded(true);

        return family;
    }

    @PostConstruct
    public void registerFont() {
        // 設置 JasperReports 的屬性以加載字體文件
        JRPropertiesUtil.getInstance(DefaultJasperReportsContext.getInstance())
                .setProperty("net.sf.jasperreports.default.font.name", "NotoSansTC");

        // 設置字體目錄，這樣 JasperReports 可以找到配置和字體
        JRPropertiesUtil.getInstance(DefaultJasperReportsContext.getInstance())
                .setProperty("net.sf.jasperreports.extension.registry.fonts",
                        "fonts/fonts.xml");
    }
}
