package com.example.memory_springboot.dao.ticket;

import com.example.memory_springboot.model.entity.ticket.TktEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketDao extends JpaRepository<TktEntity, Integer> {
    List<TktEntity> findAllByTktStatus(int tktStatus);
    Optional<TktEntity> findByTktNo(int TktNo);
}
