package com.example.memory_springboot.dao.ticket.mybatis;

import com.example.memory_springboot.model.entity.ticket.TktEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MyBatisTicketMapper {
    @Select("SELECT * FROM cga103g1.tkt WHERE sold_amount > 0")
    @Results({
            @Result(property = "tktNo", column = "tkt_no"),
            @Result(property = "tktName", column = "tkt_name"),
            @Result(property = "originalAmount", column = "original_amount"),
            @Result(property = "tktStartdate", column = "tkt_startdate"),
            @Result(property = "tktEnddate", column = "tkt_enddate"),
            @Result(property = "tktStatus", column = "tkt_status"),
            @Result(property = "soldAmount", column = "sold_amount")
    })
    List<TktEntity> getSoldTickets();
}
