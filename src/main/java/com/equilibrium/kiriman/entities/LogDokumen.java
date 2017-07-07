package com.equilibrium.kiriman.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by faisalw on 3/19/17.
 */
@Entity
public class LogDokumen {

    @Id
    Integer idLog;
    @Column
    String noDok;

    public LogDokumen() {
    }

    public Integer getIdLog() {
        return idLog;
    }

    public void setIdLog(Integer idLog) {
        this.idLog = idLog;
    }

    public String getNoDok() {
        return noDok;
    }

    public void setNoDok(String noDok) {
        this.noDok = noDok;
    }
}
