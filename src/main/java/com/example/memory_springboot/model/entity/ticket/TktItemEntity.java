package com.example.memory_springboot.model.entity.ticket;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tkt_item", schema = "cga103g1", catalog = "")
@IdClass(com.example.memory_springboot.model.entity.ticket.TktItemEntityPK.class)
public class TktItemEntity {
    @Id
    @Column(name = "tkt_no")
    private int tktNo;

    @Id
    @Column(name = "tkt_order_no")
    private int tktOrderNo;
    @Basic
    @Column(name = "amount")
    private int amount;
    @Basic
    @Column(name = "qrcode")
    private byte[] qrcode;
    @Basic
    @Column(name = "tkt_ori_price")
    private int tktOriPrice;
}
