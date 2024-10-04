package com.example.memory_springboot.service.ticket.impl;

import com.example.memory_springboot.dao.ticket.TicketDao;
import com.example.memory_springboot.model.dto.ticket.TktDto;
import com.example.memory_springboot.model.entity.ticket.TktEntity;
import com.example.memory_springboot.service.ticket.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TicketServiceImpl implements TicketService {
    private final TicketDao ticketDao;

    @Autowired
    public TicketServiceImpl(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    public List<TktDto> getTktList() {

        return ticketDao.findAllByTktStatus(1)
                  .stream()
                  .map(this::convertToTktDto)
                  .toList();

    }

    private TktDto convertToTktDto(TktEntity tktEntity) {
        return new TktDto(
                tktEntity.getTktNo(),
                tktEntity.getTktName(),
                tktEntity.getOriginalAmount(),
                tktEntity.getPrice(),
                tktEntity.getSoldAmount()
        );
    }
}
