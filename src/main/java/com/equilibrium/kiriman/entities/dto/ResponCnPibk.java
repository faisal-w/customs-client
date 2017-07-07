package com.equilibrium.kiriman.entities.dto;

import com.equilibrium.kiriman.tools.DateAdapter;
import com.equilibrium.kiriman.tools.DateTimeAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

/**
 * Created by faisalw on 3/23/17.
 */
@XmlRootElement(name = "CN_PIBK")
public class ResponCnPibk {


    public static class TempHeader{
        String noBarang;
        Date tglHouseBlawb;
        String kdRespon;
        Date wkRespon;
        String ketRespon;

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

        @XmlElement(name = "TGL_HOUSE_BLAWB")
        @XmlJavaTypeAdapter(DateAdapter.class)
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

        public Date getWkRespon() {
            return wkRespon;
        }

        @XmlElement(name = "WK_RESPON")
        @XmlJavaTypeAdapter(DateTimeAdapter.class)
        public void setWkRespon(Date wkRespon) {
            this.wkRespon = wkRespon;
        }

        public String getKetRespon() {
            return ketRespon;
        }

        @XmlElement(name = "KET_RESPON")
        public void setKetRespon(String ketRespon) {
            this.ketRespon = ketRespon;
        }
    }

    public static TempHeader tempHeader;

    public TempHeader getTempHeader() {
        return tempHeader;
    }

    @XmlElement(name = "HEADER")
    public void setTempHeader(TempHeader tempHeader) {
        this.tempHeader = tempHeader;
    }
}
