package com.equilibrium.kiriman.entities.dto;

import com.equilibrium.kiriman.entities.LogDetilBarang;
import com.equilibrium.kiriman.entities.dto.parent.ParentDetilBarang;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by faisal on 3/20/2017.
 */
public class DetilBarang extends ParentDetilBarang{

    @XmlTransient
    int id;
    List<DetilPungutan> detilPungutans;

    public DetilBarang(){}

    public DetilBarang(LogDetilBarang brg){
        this.setSeriBrg(brg.getSeriBrg());
        this.setHsCode(brg.getHsCode());
        this.setUrBrg(brg.getUrBrg());
        this.setKdNegAsal(brg.getKdNegAsal());
        this.setJmlKms(brg.getJmlKms());
        this.setJnsKms(brg.getJnsKms());
        this.setCif(brg.getCif());
        this.setKdSatHrg(brg.getKdSatHrg());
        this.setJmlSatHrg(brg.getJmlSatHrg());
        this.setFlBebas(brg.getFlBebas());
        this.setNoSkep(brg.getNoSkep());
        this.setTglSkep(brg.getTglSkep());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<DetilPungutan> getDetilPungutans() {
        if(null==detilPungutans){
            detilPungutans = new ArrayList<DetilPungutan>();
        }
        return detilPungutans;
    }

    @XmlElement(name = "DETIL_PUNGUTAN")
    public void setDetilPungutans(List<DetilPungutan> detilPungutans) {
        this.detilPungutans = detilPungutans;
    }
}
