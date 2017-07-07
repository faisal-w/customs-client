package com.equilibrium.kiriman.entities.dto;

import com.equilibrium.kiriman.tools.DateTimeAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

/**
 * Created by faisalw on 3/18/17.
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "CEK_STATUS")
public class CekStatus {

    private HeaderCekStatus header;
    private String noBarang;
    private Date tglHouseBlawb;
    private String kdRespon;
    private String ketRespon;
    private Date wkRespon;

    public CekStatus() {
    }

    public HeaderCekStatus getHeader() {
        return header;
    }

    @XmlElement(name = "HEADER")
    public void setHeader(HeaderCekStatus header) {
        this.header = header;
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

    @XmlElement(name = "TGL_HOUSE_BLAWB",type = Date.class)
    public void setTglHouseBlawb(Date tglHouseBlawb) {
        this.tglHouseBlawb = tglHouseBlawb;
    }

    public String getKdRespon() {
        return kdRespon;
    }

    @XmlElement(name = "KD_RESPON")
    public void setKdRespon(String kdRespon) {
        this.kdRespon = kdRespon;
    }

    public String getKetRespon() {
        return ketRespon;
    }

    @XmlElement(name = "KET_RESPON")
    public void setKetRespon(String ketRespon) {
        this.ketRespon = ketRespon;
    }

    public Date getWkRespon() {
        return wkRespon;
    }

    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    @XmlElement(name = "WK_RESPON")
    public void setWkRespon(Date wkRespon) {
        this.wkRespon = wkRespon;
    }
}
