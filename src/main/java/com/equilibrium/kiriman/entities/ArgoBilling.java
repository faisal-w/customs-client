package com.equilibrium.kiriman.entities;

import com.equilibrium.kiriman.entities.LogCnPibk;
import com.equilibrium.kiriman.entities.Perusahaan;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by faisalw on 3/24/17.
 */
@Entity
public class ArgoBilling {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int idArgo;
    @OneToOne
    LogCnPibk cnPibk;
    @ManyToOne
    Perusahaan perusahaan;
    BigInteger nilaiTransaksi;
    Integer tarif;
    boolean isDeposit;
    String keterangan;
    Date tglTrans;

    public Date getTglTrans() {
        return tglTrans;
    }

    public void setTglTrans(Date tglTrans) {
        this.tglTrans = tglTrans;
    }

    public void setDeposit(boolean deposit) {
        isDeposit = deposit;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public int getIdArgo() {
        return idArgo;
    }

    public void setIdArgo(int idArgo) {
        this.idArgo = idArgo;
    }

    public LogCnPibk getCnPibk() {
        return cnPibk;
    }

    public void setCnPibk(LogCnPibk cnPibk) {
        this.cnPibk = cnPibk;
    }

    public Perusahaan getPerusahaan() {
        return perusahaan;
    }

    public void setPerusahaan(Perusahaan perusahaan) {
        this.perusahaan = perusahaan;
    }

    public BigInteger getNilaiTransaksi() {
        return nilaiTransaksi;
    }

    public void setNilaiTransaksi(BigInteger nilaiTransaksi) {
        this.nilaiTransaksi = nilaiTransaksi;
    }

    public Integer getTarif() {
        return tarif;
    }

    public void setTarif(Integer tarif) {
        this.tarif = tarif;
    }

    public boolean isDeposit() {
        return isDeposit;
    }

}
