package com.example.memory_springboot.service.ticket.impl;

import com.example.memory_springboot.dao.ticket.jpa.JpaTicketDaoImpl;
import com.example.memory_springboot.dao.ticket.mybatis.MyBatisTicketDaoImpl;
import com.example.memory_springboot.model.dto.ticket.TktReqDto;
import com.example.memory_springboot.model.dto.ticket.TktResDto;
import com.example.memory_springboot.model.entity.ticket.TktEntity;
import com.example.memory_springboot.service.ticket.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
public class TicketServiceImpl implements TicketService {
    private final JpaTicketDaoImpl jpaTicketDao;
    private final MyBatisTicketDaoImpl myBatisTicketDao;

    @Autowired
    public TicketServiceImpl(JpaTicketDaoImpl jpaTicketDao, MyBatisTicketDaoImpl myBatisTicketDao) {
        this.jpaTicketDao = jpaTicketDao;
        this.myBatisTicketDao = myBatisTicketDao;
    }

    @Override
    public List<TktResDto> getTktList() {

        return jpaTicketDao.findAllByTktStatus(1)
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

        jpaTicketDao.save(tktEntity);
    }

    @Override
    public void updateTicket(Integer tktNo, TktReqDto tktReqDto) {
        // 先找看有沒有這張票券
        TktEntity existingTkt = jpaTicketDao.findByTktNo(tktNo)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
        // 有票券再進行更新
        existingTkt.setTktName(tktReqDto.tktName());
        existingTkt.setPrice(tktReqDto.price());
        existingTkt.setOriginalAmount(tktReqDto.originalAmount());
        existingTkt.setLocate(tktReqDto.locate());
        existingTkt.setTktStatus(tktReqDto.tktStatus());
        existingTkt.setKind(tktReqDto.kind());

        jpaTicketDao.save(existingTkt);
    }

    @Override
    public void deleteTicket(Integer tktNo) {
        jpaTicketDao.deleteById(tktNo);
    }

    @Override
    public TktResDto getTicketDetail(Integer tktNo) {
        TktEntity byTktNo = jpaTicketDao.findByTktNo(tktNo)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));;
        return convertToTktDto(byTktNo);
    }

    @Override
    public List<TktResDto> getAllTickets() {
        return jpaTicketDao.findAll().stream()
                .map(this::convertToTktDto)
                .toList();
    }

    @Override
    public List<TktResDto> getSoldTickets() {
        return myBatisTicketDao.getSoldTickets().stream()
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
