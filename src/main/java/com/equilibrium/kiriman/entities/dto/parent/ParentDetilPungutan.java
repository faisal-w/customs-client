package com.equilibrium.kiriman.entities.dto.parent;

import com.equilibrium.kiriman.entities.LogDetilPungutan;

import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by faisalw on 3/22/17.
 */
@MappedSuperclass
@XmlRootElement(name = "DETIL_PUNGUTAN")
public class ParentDetilPungutan implements Serializable{

    int kdPungutan;
    BigDecimal nilai;
    String kdTarif;
    String kdSatTarif;
    Integer jmlSat;
    String jnsTarif;
    double tarif;

    public ParentDetilPungutan() {}

    public int getKdPungutan() {
        return kdPungutan;
    }

    @XmlElement(name = "KD_PUNGUTAN")
    public void setKdPungutan(int kdPungutan) {
        this.kdPungutan = kdPungutan;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    @XmlElement(name = "NILAI")
    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public String getKdTarif() {
        return kdTarif;
    }

    @XmlElement(name = "KD_TARIF")
    public void setKdTarif(String kdTarif) {
        this.kdTarif = kdTarif;
    }

    public String getKdSatTarif() {
        return kdSatTarif;
    }

    @XmlElement(name = "KD_SAT_TARIF")
    public void setKdSatTarif(String kdSatTarif) {
        this.kdSatTarif = kdSatTarif;
    }

    public Integer getJmlSat() {
        return jmlSat;
    }

    @XmlElement(name = "JML_SAT")
    public void setJmlSat(Integer jmlSat) {
        this.jmlSat = jmlSat;
    }

    public double getTarif() {
        return tarif;
    }

    @XmlElement(name = "TARIF")
    public void setTarif(double tarif) {
        this.tarif = tarif;
    }

    public String getJnsTarif() {
        return jnsTarif;
    }

    public void setJnsTarif(String jnsTarif) {
        this.jnsTarif = jnsTarif;
    }
}
