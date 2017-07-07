package com.equilibrium.kiriman.entities;

import com.equilibrium.kiriman.entities.dto.DetilPungutan;
import com.equilibrium.kiriman.entities.dto.parent.ParentDetilPungutan;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by faisalw on 3/22/17.
 */
@Entity
public class LogDetilPungutan extends ParentDetilPungutan{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int idLogDetilPungutan;
    @ManyToOne
    LogDetilBarang barang;

    public LogDetilPungutan() {
    }

    public LogDetilPungutan(DetilPungutan pung){
        this.setKdPungutan(pung.getKdPungutan());
        this.setNilai(pung.getNilai());
        this.setKdTarif(pung.getKdTarif());
        this.setKdSatTarif(pung.getKdSatTarif());
        this.setJmlSat(pung.getJmlSat());
        this.setTarif(pung.getTarif());
    }

    public int getIdLogDetilPungutan() {
        return idLogDetilPungutan;
    }

    public void setIdLogDetilPungutan(int idLogDetilPungutan) {
        this.idLogDetilPungutan = idLogDetilPungutan;
    }

    public LogDetilBarang getBarang() {
        return barang;
    }

    public void setBarang(LogDetilBarang barang) {
        this.barang = barang;
    }
}
