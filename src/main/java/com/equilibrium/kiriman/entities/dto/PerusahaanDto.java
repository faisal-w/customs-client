package com.equilibrium.kiriman.entities.dto;

/**
 * Created by faisalw on 3/28/17.
 */
public class PerusahaanDto{

    int id;
    String namaPerusahaan;
    String npwp;
    String alamat;
    String userWSBeaCukai;
    String passWSBeaCukai;
    String tokenWSBeaCukai;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }

    public void setNamaPerusahaan(String namaPerusahaan) {
        this.namaPerusahaan = namaPerusahaan;
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
}