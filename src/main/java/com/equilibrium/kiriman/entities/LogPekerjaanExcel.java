package com.equilibrium.kiriman.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by faisalw on 4/29/17.
 */
@Entity
public class LogPekerjaanExcel {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int id;
    String npwp;
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Column(nullable = true)
    List<LogCnPibk> logCnPibks;
    @Column(nullable = true)
    Integer totalHeader;
    @Column(nullable = true)
    Integer totalBarang;
    @Temporal(TemporalType.TIMESTAMP)
    Date dateUpload;
    Date dateKirim;
    boolean isChecked;
    boolean isSent;
    String keterangan;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<LogCnPibk> getLogCnPibks() {
        return logCnPibks;
    }

    public void setLogCnPibks(List<LogCnPibk> logCnPibks) {
        this.logCnPibks = logCnPibks;
    }

    public Date getDateUpload() {
        return dateUpload;
    }

    @PrePersist
    void isiTglSimpan(){
        this.dateUpload = new Date();
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Integer getTotalHeader() {
        return totalHeader;
    }

    public void setTotalHeader(Integer totalHeader) {
        this.totalHeader = totalHeader;
    }

    public Integer getTotalBarang() {
        return totalBarang;
    }

    public void setTotalBarang(Integer totalBarang) {
        this.totalBarang = totalBarang;
    }

    public Date getDateKirim() {
        return dateKirim;
    }

    public void setDateKirim(Date dateKirim) {
        this.dateKirim = dateKirim;
    }
}