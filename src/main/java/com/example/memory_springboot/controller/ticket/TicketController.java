package com.example.memory_springboot.controller.ticket;

import com.example.memory_springboot.model.dto.ticket.TktReqDto;
import com.example.memory_springboot.model.dto.ticket.TktResDto;
import com.example.memory_springboot.service.ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PatchMapping("/{tktNo}/update")
    public ResponseEntity<String> updateTicket(@PathVariable("tktNo") Integer tktNo,
                                               @RequestBody TktReqDto tktReqDto){
        ticketService.updateTicket(tktNo, tktReqDto);
        return ResponseEntity.ok("票券修改成功");
    }

    @DeleteMapping("/{tktNo}/delete")
    public ResponseEntity<String> deleteTicket(@PathVariable("tktNo") Integer tktNo) {
        ticketService.deleteTicket(tktNo);
        return ResponseEntity.ok("票券移除成功");
    }

    @GetMapping("/{tktNo}/detail")
    public ResponseEntity<TktResDto> showTicketDetail(@PathVariable("tktNo") Integer tktNo) {
        TktResDto ticketDetail = ticketService.getTicketDetail(tktNo);
        return ResponseEntity.ok(ticketDetail);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TktResDto>> showAllTickets() {
        List<TktResDto> allTickets = ticketService.getTktList();
        return ResponseEntity.ok(allTickets);
    }
}
