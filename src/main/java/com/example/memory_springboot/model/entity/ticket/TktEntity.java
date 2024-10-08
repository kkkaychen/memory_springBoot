package com.example.memory_springboot.model.entity.ticket;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tkt", schema = "cga103g1", catalog = "")
public class TktEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "tkt_no")
    private int tktNo;
    @Basic
    @Column(name = "tkt_name")
    private String tktName;
    @Basic
    @Column(name = "original_amount")
    private int originalAmount;
    @Basic
    @Column(name = "price")
    private int price;
    @Basic
    @Column(name = "tkt_startdate")
    private LocalDateTime tktStartdate;
    @Basic
    @Column(name = "tkt_enddate")
    private LocalDateTime tktEnddate;
    @Basic
    @Column(name = "locate")
    private String locate;
    @Basic
    @Column(name = "instruction")
    private String instruction;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "notice")
    private String notice;
    @Basic
    @Column(name = "howuse")
    private String howuse;
    @Basic
    @Column(name = "canxpolicy")
    private String canxpolicy;
    @Basic
    @Column(name = "tkt_status")
    private int tktStatus;
    @Basic
    @Column(name = "sold_amount")
    private int soldAmount;
    @Basic
    @Column(name = "kind")
    private int kind;

    public TktEntity(String tktName, int originalAmount, int price,
                     LocalDateTime tktStartdate, LocalDateTime tktEnddate, String locate,
                     String instruction, String address, String notice, String howuse,
                     String canxpolicy, int tktStatus, int soldAmount, int kind) {
        this.tktName = tktName;
        this.originalAmount = originalAmount;
        this.price = price;
        this.tktStartdate = tktStartdate;
        this.tktEnddate = tktEnddate;
        this.locate = locate;
        this.instruction = instruction;
        this.address = address;
        this.notice = notice;
        this.howuse = howuse;
        this.canxpolicy = canxpolicy;
        this.tktStatus = tktStatus;
        this.soldAmount = soldAmount;
        this.kind = kind;
    }

}
