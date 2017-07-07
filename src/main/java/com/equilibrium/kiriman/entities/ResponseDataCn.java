package com.equilibrium.kiriman.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by faisalw on 3/22/17.
 */
@Entity
public class ResponseDataCn implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer idResponse;
    @ManyToOne
    LogCnPibk cnPibkTerkait;
    Date waktuCekRespon;
    int kodeRespon;
    String keterangan;
    @OneToOne(mappedBy = "parentRespon",cascade = CascadeType.ALL)
    DetailResponDataCn detailRespon;

    public Integer getIdResponse() {
        return idResponse;
    }

    public void setIdResponse(Integer idResponse) {
        this.idResponse = idResponse;
    }

    public LogCnPibk getCnPibkTerkait() {
        return cnPibkTerkait;
    }

    public void setCnPibkTerkait(LogCnPibk cnPibkTerkait) {
        this.cnPibkTerkait = cnPibkTerkait;
    }

    public Date getWaktuCekRespon() {
        return waktuCekRespon;
    }

    public void setWaktuCekRespon(Date waktuCekRespon) {
        this.waktuCekRespon = waktuCekRespon;
    }

    public int getKodeRespon() {
        return kodeRespon;
    }

    public void setKodeRespon(int kodeRespon) {
        this.kodeRespon = kodeRespon;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public DetailResponDataCn getDetailRespon() {
        return detailRespon;
    }

    public void setDetailRespon(DetailResponDataCn detailRespon) {
        this.detailRespon = detailRespon;
    }
}
