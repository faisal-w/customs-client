package com.equilibrium.kiriman.entities.dto.parent;

import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

/**
 * Created by faisalw on 3/24/17.
 */
@MappedSuperclass
public class ParentHeaderPungutan {

    @XmlTransient
    int id;
    int kdPungutan;
    BigDecimal nilai;

    public ParentHeaderPungutan() {}

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


}
