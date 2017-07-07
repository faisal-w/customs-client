package com.equilibrium.kiriman.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by faisalw on 4/1/17.
 */
@Entity
public class RefJenisIdentitas {

    @Id
    int id;
    String jenisId;
    String keterangan;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJenisId() {
        return jenisId;
    }

    public void setJenisId(String jenisId) {
        this.jenisId = jenisId;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
