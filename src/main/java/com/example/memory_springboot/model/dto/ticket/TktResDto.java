package com.example.memory_springboot.model.dto.ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TktResDto {
    private int tktNo;
    private String tktName;
    private int originalAmount;
    private int price;
    private int soldAmount;
}
