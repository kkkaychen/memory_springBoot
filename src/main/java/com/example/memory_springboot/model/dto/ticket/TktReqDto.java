package com.example.memory_springboot.model.dto.ticket;

public record TktReqDto(String tktName,
                        int originalAmount,
                        int price,
                        String tktStartdate,
                        String tktEnddate,
                        String locate,
                        String instruction,
                        String address,
                        String notice,
                        String howuse,
                        String canxpolicy,
                        int tktStatus,
                        int soldAmount,
                        int kind
                        ) {

}
