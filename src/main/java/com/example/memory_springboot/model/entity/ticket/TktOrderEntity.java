package com.example.memory_springboot.model.entity.ticket;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tkt_order", schema = "cga103g1", catalog = "")
public class TktOrderEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "tkt_order_no")
    private int tktOrderNo;
    @Basic
    @Column(name = "mem_no")
    private int memberNo;
    @Basic
    @Column(name = "mem_coup_no")
    private Integer memberCouponNo;
    @Basic
    @Column(name = "tkt_no")
    private Integer tktNo;
    @Basic
    @Column(name = "original_price")
    private int originalPrice;
    @Basic
    @Column(name = "orderdate")
    private Timestamp orderdate;
    @Basic
    @Column(name = "TTL_PRICE")
    private int ttlPrice;
}
