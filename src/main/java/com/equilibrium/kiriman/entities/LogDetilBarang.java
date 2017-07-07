package com.equilibrium.kiriman.entities;

import com.equilibrium.kiriman.entities.dto.DetilBarang;
import com.equilibrium.kiriman.entities.dto.DetilPungutan;
import com.equilibrium.kiriman.entities.dto.parent.ParentDetilBarang;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by faisalw on 3/22/17.
 */
@Entity
public class LogDetilBarang extends ParentDetilBarang{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int idLogDetilBarang;
    @ManyToOne
    LogCnPibk cnPibk;
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    List<LogDetilPungutan> detilPungutans;

    public LogDetilBarang() {}

    public LogDetilBarang(DetilBarang brg){
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

    public int getIdLogDetilBarang() {
        return idLogDetilBarang;
    }

    public void setIdLogDetilBarang(int idLogDetilBarang) {
        this.idLogDetilBarang = idLogDetilBarang;
    }

    public List<LogDetilPungutan> getDetilPungutans() {
        return detilPungutans;
    }

    public void setDetilPungutans(List<LogDetilPungutan> detilPungutans) {
        this.detilPungutans = detilPungutans;
    }

    public LogCnPibk getCnPibk() {
        return cnPibk;
    }

    public void setCnPibk(LogCnPibk cnPibk) {
        this.cnPibk = cnPibk;
    }
}
