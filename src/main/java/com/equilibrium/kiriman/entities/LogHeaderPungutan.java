package com.equilibrium.kiriman.entities;

import com.equilibrium.kiriman.entities.dto.HeaderPungutan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by faisalw on 3/22/17.
 */
@Entity
public class LogHeaderPungutan {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int idLogHeaderPungutan;
    int kdPungutan;
    BigDecimal nilai;

    public LogHeaderPungutan() {}

    public LogHeaderPungutan(HeaderPungutan headerPungutan){
        this.setKdPungutan(headerPungutan.getKdPungutan());
        this.setNilai(headerPungutan.getNilai());
    }

    public LogHeaderPungutan(int kdPungutan,BigDecimal nilai){
        this.setKdPungutan(kdPungutan);
        this.setNilai(nilai);
    }

    public int getIdLogHeaderPungutan() {
        return idLogHeaderPungutan;
    }

    public void setIdLogHeaderPungutan(int idLogHeaderPungutan) {
        this.idLogHeaderPungutan = idLogHeaderPungutan;
    }

    public int getKdPungutan() {
        return kdPungutan;
    }

    public void setKdPungutan(int kdPungutan) {
        this.kdPungutan = kdPungutan;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }
}
