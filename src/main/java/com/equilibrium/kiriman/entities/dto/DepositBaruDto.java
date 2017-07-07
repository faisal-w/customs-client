package com.equilibrium.kiriman.entities.dto;

import java.io.Serializable;

/**
 * Created by faisalw on 4/1/17.
 */
public class DepositBaruDto implements Serializable {

    int id;
    int idPerusahaan;
    Integer nilaiDeposit;
    String bankTujuan;
    String bankPengirim;
    String noReferensi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPerusahaan() {
        return idPerusahaan;
    }

    public void setIdPerusahaan(int idPerusahaan) {
        this.idPerusahaan = idPerusahaan;
    }

    public Integer getNilaiDeposit() {
        return nilaiDeposit;
    }

    public void setNilaiDeposit(Integer nilaiDeposit) {
        this.nilaiDeposit = nilaiDeposit;
    }

    public String getBankTujuan() {
        return bankTujuan;
    }

    public void setBankTujuan(String bankTujuan) {
        this.bankTujuan = bankTujuan;
    }

    public String getBankPengirim() {
        return bankPengirim;
    }

    public void setBankPengirim(String bankPengirim) {
        this.bankPengirim = bankPengirim;
    }

    public String getNoReferensi() {
        return noReferensi;
    }

    public void setNoReferensi(String noReferensi) {
        this.noReferensi = noReferensi;
    }
}
