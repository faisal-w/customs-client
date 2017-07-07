package com.equilibrium.kiriman.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * Created by faisalw on 3/19/17.
 */
@Entity
public class Perusahaan implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int idPerusahaan;
    String namaPerusahaan;
    String npwp;
    String alamat;
    String userWSBeaCukai;
    String passWSBeaCukai;
    String tokenWSBeaCukai;
    @OneToMany(cascade = CascadeType.ALL)
    List<User> pegawais;
    String jenisDeposit;
    String nomorIzin;
    Date tglIzin;
    boolean isAktif;

    public int getIdPerusahaan() {
        return idPerusahaan;
    }

    public void setIdPerusahaan(int idPerusahaan) {
        this.idPerusahaan = idPerusahaan;
    }

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }

    public void setNamaPerusahaan(String namaPerusahaan) {
        this.namaPerusahaan = namaPerusahaan;
    }

    public List<User> getPegawais() {
        return pegawais;
    }

    public void setPegawais(List<User> pegawais) {
        this.pegawais = pegawais;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getUserWSBeaCukai() {
        return userWSBeaCukai;
    }

    public void setUserWSBeaCukai(String userWSBeaCukai) {
        this.userWSBeaCukai = userWSBeaCukai;
    }

    public String getPassWSBeaCukai() {
        return passWSBeaCukai;
    }

    public void setPassWSBeaCukai(String passWSBeaCukai) {
        this.passWSBeaCukai = passWSBeaCukai;
    }

    public String getTokenWSBeaCukai() {
        return tokenWSBeaCukai;
    }

    public void setTokenWSBeaCukai(String tokenWSBeaCukai) {
        this.tokenWSBeaCukai = tokenWSBeaCukai;
    }

    public String getJenisDeposit() {
        return jenisDeposit;
    }

    public void setJenisDeposit(String jenisDeposit) {
        this.jenisDeposit = jenisDeposit;
    }

    public String getNomorIzin() {
        return nomorIzin;
    }

    public void setNomorIzin(String nomorIzin) {
        this.nomorIzin = nomorIzin;
    }

    public Date getTglIzin() {
        return tglIzin;
    }

    public void setTglIzin(Date tglIzin) {
        this.tglIzin = tglIzin;
    }

    public boolean isAktif() {
        return isAktif;
    }

    public void setAktif(boolean aktif) {
        isAktif = aktif;
    }
}
