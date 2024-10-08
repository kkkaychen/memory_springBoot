package com.example.memory_springboot.dao.ticket.jpa;

import com.example.memory_springboot.dao.ticket.TicketDao;
import com.example.memory_springboot.model.entity.ticket.TktEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaTicketDaoImpl implements TicketDao {
    private final JpaTicketRepository jpaTicketRepository;

    @Autowired
    public JpaTicketDaoImpl(JpaTicketRepository jpaTicketRepository) {
        this.jpaTicketRepository = jpaTicketRepository;
    }

    @Override
    public List<TktEntity> findAllByTktStatus(int tktStatus) {
        return jpaTicketRepository.findAllByTktStatus(tktStatus);
    }

    @Override
    public Optional<TktEntity> findByTktNo(int tktNo) {
        return jpaTicketRepository.findByTktNo(tktNo);
    }

    @Override
    public TktEntity save(TktEntity tktEntity) {
        return jpaTicketRepository.save(tktEntity);
    }

    @Override
    public void deleteById(int tktNo) {
        jpaTicketRepository.deleteById(tktNo);
    }

    @Override
    public List<TktEntity> findAll() {
        return jpaTicketRepository.findAll();
    }

    @Override
    public List<TktEntity> getSoldTickets() {
        throw new UnsupportedOperationException("MyBatis 實作 getSoldTickets 方法");
    }
}
