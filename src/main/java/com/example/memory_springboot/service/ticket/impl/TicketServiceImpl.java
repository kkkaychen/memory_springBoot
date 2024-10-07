package com.example.memory_springboot.service.ticket.impl;

import com.example.memory_springboot.dao.ticket.TicketDao;
import com.example.memory_springboot.model.dto.ticket.TktReqDto;
import com.example.memory_springboot.model.dto.ticket.TktResDto;
import com.example.memory_springboot.model.entity.ticket.TktEntity;
import com.example.memory_springboot.service.ticket.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public List<TktResDto> getTktList() {

        return ticketDao.findAllByTktStatus(1)
                  .stream()
                  .map(this::convertToTktDto)
                  .toList();

    }

    @Override
    public void generateTicket(TktReqDto tktReqDto) {
        TktEntity tktEntity = tktReqDtoConvertToEntity(tktReqDto);
        ticketDao.save(tktEntity);
    }

    private TktEntity tktReqDtoConvertToEntity(TktReqDto tktReqDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return new TktEntity(
                tktReqDto.tktName(),
                tktReqDto.originalAmount(),
                tktReqDto.price(),
                LocalDateTime.parse(tktReqDto.tktStartdate(), formatter),
                LocalDateTime.parse(tktReqDto.tktEnddate(), formatter),
                tktReqDto.locate(),
                tktReqDto.instruction(),
                tktReqDto.address(),
                tktReqDto.notice(),
                tktReqDto.howuse(),
                tktReqDto.canxpolicy(),
                tktReqDto.tktStatus(),
                tktReqDto.soldAmount(),
                tktReqDto.kind()
        );
    }

    private TktResDto convertToTktDto(TktEntity tktEntity) {
        return new TktResDto(
                tktEntity.getTktNo(),
                tktEntity.getTktName(),
                tktEntity.getOriginalAmount(),
                tktEntity.getPrice(),
                tktEntity.getSoldAmount()
        );
    }
}
