package com.example.memory_springboot.dao.ticket.mybatis;

import com.example.memory_springboot.dao.ticket.TicketDao;
import com.example.memory_springboot.model.entity.ticket.TktEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MyBatisTicketDaoImpl implements TicketDao {
    private final MyBatisTicketMapper myBatisTicketMapper;
    @Autowired
    public MyBatisTicketDaoImpl(MyBatisTicketMapper myBatisTicketMapper) {
        this.myBatisTicketMapper = myBatisTicketMapper;
    }

    @Override
    public List<TktEntity> findAllByTktStatus(int tktStatus) {
        throw new UnsupportedOperationException("JPA 實作 findAllByTktStatus 方法");
    }

    @Override
    public Optional<TktEntity> findByTktNo(int tktNo) {
        throw new UnsupportedOperationException("JPA 實作 findByTktNo 方法");
    }

    @Override
    public TktEntity save(TktEntity tktEntity) {
        throw new UnsupportedOperationException("JPA 實作 save 方法");
    }

    @Override
    public void deleteById(int tktNo) {
        throw new UnsupportedOperationException("JPA 實作 deleteById 方法");
    }

    @Override
    public List<TktEntity> findAll() {
        throw new UnsupportedOperationException("JPA 實作 findAll 方法");
    }

    @Override
    public List<TktEntity> getSoldTickets() {
        return myBatisTicketMapper.getSoldTickets();
    }
}
