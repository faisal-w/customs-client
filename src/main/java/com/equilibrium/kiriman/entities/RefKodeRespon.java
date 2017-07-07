package com.equilibrium.kiriman.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by faisalw on 3/23/17.
 */
@Entity
public class RefKodeRespon {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int idKodeRespon;
    int kodeRespon;
    String uraian;
    String keterangan;

    public int getIdKodeRespon() {
        return idKodeRespon;
    }

    public void setIdKodeRespon(int idKodeRespon) {
        this.idKodeRespon = idKodeRespon;
    }

    public int getKodeRespon() {
        return kodeRespon;
    }

    public void setKodeRespon(int kodeRespon) {
        this.kodeRespon = kodeRespon;
    }

    public String getUraian() {
        return uraian;
    }

    public void setUraian(String uraian) {
        this.uraian = uraian;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
