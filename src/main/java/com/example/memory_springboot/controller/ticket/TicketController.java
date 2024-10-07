package com.example.memory_springboot.controller.ticket;

import com.example.memory_springboot.model.dto.ticket.TktReqDto;
import com.example.memory_springboot.service.ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;
    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generateTicket(@RequestBody TktReqDto tktReqDto) {
        ticketService.generateTicket(tktReqDto);
        return ResponseEntity.ok("票券新增成功");
    }






}
