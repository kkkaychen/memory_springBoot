package com.example.memory_springboot.model.entity.ticket;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;

@Entity
@jakarta.persistence.Table(name = "tkt_img", schema = "cga103g1", catalog = "")
public class TktImgEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "TKT_img_no")
    private int tktImgNo;

    public int getTktImgNo() {
        return tktImgNo;
    }

    public void setTktImgNo(int tktImgNo) {
        this.tktImgNo = tktImgNo;
    }

    @Basic
    @Column(name = "tkt_no")
    private Integer tktNo;

    public Integer getTktNo() {
        return tktNo;
    }

    public void setTktNo(Integer tktNo) {
        this.tktNo = tktNo;
    }

    @Basic
    @Column(name = "tkt_img")
    private byte[] tktImg;

    public byte[] getTktImg() {
        return tktImg;
    }

    public void setTktImg(byte[] tktImg) {
        this.tktImg = tktImg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TktImgEntity that = (TktImgEntity) o;
        return tktImgNo == that.tktImgNo && Objects.equals(tktNo, that.tktNo) && Arrays.equals(tktImg, that.tktImg);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(tktImgNo, tktNo);
        result = 31 * result + Arrays.hashCode(tktImg);
        return result;
    }
}
