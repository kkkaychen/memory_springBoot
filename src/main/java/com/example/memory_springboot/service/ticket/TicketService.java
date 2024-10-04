package com.example.memory_springboot.service.ticket;

import com.example.memory_springboot.model.dto.ticket.TktDto;

import java.util.List;

public interface TicketService {
    List<TktDto> getTktList();
}
