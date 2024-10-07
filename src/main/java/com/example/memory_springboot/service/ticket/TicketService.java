package com.example.memory_springboot.service.ticket;

import com.example.memory_springboot.model.dto.ticket.TktReqDto;
import com.example.memory_springboot.model.dto.ticket.TktResDto;

import java.util.List;

public interface TicketService {
    List<TktResDto> getTktList();
    void generateTicket(TktReqDto tktReqDto);
}
