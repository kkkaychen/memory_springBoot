package com.example.memory_springboot.model.entity.ticket;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class TktItemEntityPK implements Serializable {
    @Column(name = "tkt_no")
    private int tktNo;

    @Column(name = "TKT_ORDER_NO")
    private int tktOrderNo;
}
