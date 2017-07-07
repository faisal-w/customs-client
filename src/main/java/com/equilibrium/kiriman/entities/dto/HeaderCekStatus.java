package com.equilibrium.kiriman.entities.dto;

import com.equilibrium.kiriman.tools.DateAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

/**
 * Created by faisalw on 3/28/17.
 */
@XmlRootElement(name = "HEADER")
public class HeaderCekStatus {

    private String npwp;
    private String noBarang;
    private Date tglHouseBlawb;

    public String getNpwp() {
        return npwp;
    }

    @XmlElement(name = "NPWP")
    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public String getNoBarang() {
        return noBarang;
    }

    @XmlElement(name = "NO_BARANG")
    public void setNoBarang(String noBarang) {
        this.noBarang = noBarang;
    }

    public Date getTglHouseBlawb() {
        return tglHouseBlawb;
    }

    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlElement(name = "TGL_HOUSE_BLAWB")
    public void setTglHouseBlawb(Date tglHouseBlawb) {
        this.tglHouseBlawb = tglHouseBlawb;
    }
}
