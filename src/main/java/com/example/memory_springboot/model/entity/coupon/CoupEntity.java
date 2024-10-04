package com.example.memory_springboot.model.entity.coupon;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "coup", schema = "cga103g1", catalog = "")
public class CoupEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "coup_no")
    private int coupNo;
    @Basic
    @Column(name = "coup_name")
    private String coupName;
    @Basic
    @Column(name = "introduce")
    private String introduce;
    @Basic
    @Column(name = "discount")
    private int discount;
    @Basic
    @Column(name = "startdate")
    private Timestamp startdate;
    @Basic
    @Column(name = "enddate")
    private Timestamp enddate;
    @Basic
    @Column(name = "status")
    private Byte status;

    public int getCoupNo() {
        return coupNo;
    }

    public void setCoupNo(int coupNo) {
        this.coupNo = coupNo;
    }

    public String getCoupName() {
        return coupName;
    }

    public void setCoupName(String coupName) {
        this.coupName = coupName;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Timestamp getStartdate() {
        return startdate;
    }

    public void setStartdate(Timestamp startdate) {
        this.startdate = startdate;
    }

    public Timestamp getEnddate() {
        return enddate;
    }

    public void setEnddate(Timestamp enddate) {
        this.enddate = enddate;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoupEntity that = (CoupEntity) o;
        return coupNo == that.coupNo && discount == that.discount && Objects.equals(coupName, that.coupName) && Objects.equals(introduce, that.introduce) && Objects.equals(startdate, that.startdate) && Objects.equals(enddate, that.enddate) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coupNo, coupName, introduce, discount, startdate, enddate, status);
    }
}
