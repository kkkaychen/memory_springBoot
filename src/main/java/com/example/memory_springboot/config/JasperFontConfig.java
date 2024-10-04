package com.example.memory_springboot.config;

import net.sf.jasperreports.engine.fonts.FontFamily;
import net.sf.jasperreports.engine.fonts.SimpleFontFamily;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.awt.*;
import java.io.BufferedInputStream;
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
}
