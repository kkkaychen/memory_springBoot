package com.example.memory_springboot.service.ticket.impl;

import com.example.memory_springboot.dao.ticket.TicketDao;
import com.example.memory_springboot.model.entity.ticket.TktEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TicketServiceImplTest {
    private final TicketDao ticketDao;

    @Autowired
    public TicketServiceImplTest(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Test
    void test_tktName_by_tktNo1() {
        Optional<TktEntity> byTktNo = ticketDao.findByTktNo(1);
        // 這張票券是否存在
        assertTrue(byTktNo.isPresent(), "找不到這張票券");
        // 當 tktNo 為 1 時，票券名稱應為「六福村主題樂園門票」
        assertEquals("六福村主題樂園門票", byTktNo.get().getTktName());
    }

    @Test
    void test_create_tkt() {
        TktEntity tkt = new TktEntity("德義一日遊",
                999, 1000, LocalDateTime.now(), LocalDateTime.now(),
                "台北市", "", "", "", "",
                "", 1, 0, 1);

        TktEntity saveTkt = ticketDao.save(tkt);

        assertEquals("德義一日遊", saveTkt.getTktName());
        assertEquals(1000, saveTkt.getPrice());
    }
}