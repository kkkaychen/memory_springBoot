package com.example.memory_springboot.service.ticket.impl;

import com.example.memory_springboot.dao.ticket.TicketDao;
import com.example.memory_springboot.model.dto.ticket.TktReqDto;
import com.example.memory_springboot.model.dto.ticket.TktResDto;
import com.example.memory_springboot.model.entity.ticket.TktEntity;
import com.example.memory_springboot.service.ticket.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

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

        if (tktEntity.getTktStartdate().isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "票券開始日期不得小於現在時間");
        }

        if (tktEntity.getTktName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "票券名稱不得為空值或空字串");
        }

        ticketDao.save(tktEntity);
    }

    @Override
    public void updateTicket(Integer tktNo, TktReqDto tktReqDto) {
        // 先找看有沒有這張票券
        TktEntity existingTkt = ticketDao.findByTktNo(tktNo)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
        // 有票券再進行更新
        existingTkt.setTktName(tktReqDto.tktName());
        existingTkt.setPrice(tktReqDto.price());
        existingTkt.setOriginalAmount(tktReqDto.originalAmount());
        existingTkt.setLocate(tktReqDto.locate());
        existingTkt.setTktStatus(tktReqDto.tktStatus());
        existingTkt.setKind(tktReqDto.kind());

        ticketDao.save(existingTkt);
    }

    @Override
    public void deleteTicket(Integer tktNo) {
        ticketDao.deleteById(tktNo);
    }

    @Override
    public TktResDto getTicketDetail(Integer tktNo) {
        TktEntity byTktNo = ticketDao.findByTktNo(tktNo)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));;
        return convertToTktDto(byTktNo);
    }

    @Override
    public List<TktResDto> getAllTickets() {
        return ticketDao.findAll().stream()
                .map(this::convertToTktDto)
                .toList();
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
