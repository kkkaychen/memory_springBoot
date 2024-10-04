package com.example.memory_springboot.model.entity.member;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mem", schema = "cga103g1", catalog = "")
public class MemEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "mem_no")
    private int memNo;
    @Basic
    @Column(name = "mem_acc")
    private String memAcc;
    @Basic
    @Column(name = "mem_pwd")
    private String memPwd;
    @Basic
    @Column(name = "acc_status")
    private byte accStatus;
    @Basic
    @Column(name = "mem_name")
    private String memName;
    @Basic
    @Column(name = "mem_gender")
    private String memGender;
    @Basic
    @Column(name = "mem_email")
    private String memEmail;
    @Basic
    @Column(name = "mem_mobile")
    private String memMobile;
    @Basic
    @Column(name = "mem_city")
    private String memCity;
    @Basic
    @Column(name = "mem_dist")
    private String memDist;
    @Basic
    @Column(name = "mem_addr")
    private String memAddr;
    @Basic
    @Column(name = "mem_reg_date")
    private Timestamp memRegDate;
    @Basic
    @Column(name = "mem_pic")
    private byte[] memPic;
    @Basic
    @Column(name = "mem_report_count")
    private Byte memReportCount;
    @Basic
    @Column(name = "mem_card")
    private String memCard;
}
