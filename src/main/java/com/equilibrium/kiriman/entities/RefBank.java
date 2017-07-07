package com.equilibrium.kiriman.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by faisalw on 3/31/17.
 */
@Entity
public class RefBank implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int idRefBank;
    String nama;
    String alamat;
    //String swiftCode;
    String nomorRekening;

    public int getIdRefBank() {
        return idRefBank;
    }

    public void setIdRefBank(int idRefBank) {
        this.idRefBank = idRefBank;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

//    public String getSwiftCode() {
//        return swiftCode;
//    }

//    public void setSwiftCode(String swiftCode) {
//        this.swiftCode = swiftCode;
//    }

    public String getNomorRekening() {
        return nomorRekening;
    }

    public void setNomorRekening(String nomorRekening) {
        this.nomorRekening = nomorRekening;
    }
}
