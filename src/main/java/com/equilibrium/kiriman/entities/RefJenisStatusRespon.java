package com.equilibrium.kiriman.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by faisalw on 3/23/17.
 */
@Entity
public class RefJenisStatusRespon {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int idJnsRespon;
    int kodeJenisStatus;
    String keterangan;

    public RefJenisStatusRespon() {}

    public int getIdJnsRespon() {
        return idJnsRespon;
    }

    public void setIdJnsRespon(int idJnsRespon) {
        this.idJnsRespon = idJnsRespon;
    }

    public int getKodeJenisRespon() {
        return kodeJenisStatus;
    }

    public void setKodeJenisRespon(int kodeJenisRespon) {
        this.kodeJenisStatus = kodeJenisRespon;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
