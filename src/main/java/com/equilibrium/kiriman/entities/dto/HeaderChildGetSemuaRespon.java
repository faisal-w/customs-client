package com.equilibrium.kiriman.entities.dto;

import com.equilibrium.kiriman.tools.DateAdapter;
import com.equilibrium.kiriman.tools.DateTimeAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;

/**
 * Created by faisalw on 4/15/17.
 */
@XmlRootElement(name = "HEADER")
public class HeaderChildGetSemuaRespon {

    String noBarang;
    Date tglHouseBlawb;
    String kdRespon;
    String ketRespon;
    Date wkRekam;
    String noPibk;
    Date tglPibk;
    String noSppb;
    Date tglSppb;
    String pdf;
    String kodeBilling;
    Date tglBilling;
    Date tglJtTempo;
    String kdDokBilling;
    Double totalBilling;
    Double ndpbm;
    Double nilPabRp;
    String noSpbl;
    Date tglSpbl;
    String noSppbmcp;
    Date tglSppbmcp;
    String noSptnp;
    Date tglSptnp;
    List<ChildDetilLartas> detilLartas;


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

    public String getKetRespon() {
        return ketRespon;
    }

    @XmlElement(name = "KET_RESPON")
    public void setKetRespon(String ketRespon) {
        this.ketRespon = ketRespon;
    }

    public Date getWkRekam() {
        return wkRekam;
    }

    @XmlElement(name = "WK_REKAM")
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    public void setWkRekam(Date wkRekam) {
        this.wkRekam = wkRekam;
    }

    public String getNoPibk() {
        return noPibk;
    }

    @XmlElement(name = "NO_PIBK")
    public void setNoPibk(String noPibk) {
        this.noPibk = noPibk;
    }

    public Date getTglPibk() {
        return tglPibk;
    }

    @XmlElement(name = "TGL_PIBK")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public void setTglPibk(Date tglPibk) {
        this.tglPibk = tglPibk;
    }

    public String getNoSppb() {
        return noSppb;
    }

    @XmlElement(name = "NO_SPPB")
    public void setNoSppb(String noSppb) {
        this.noSppb = noSppb;
    }

    public Date getTglSppb() {
        return tglSppb;
    }

    @XmlElement(name = "TGL_SPPB")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public void setTglSppb(Date tglSppb) {
        this.tglSppb = tglSppb;
    }

    public String getPdf() {
        return pdf;
    }

    @XmlElement(name = "PDF")
    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getKodeBilling() {
        return kodeBilling;
    }

    @XmlElement(name = "KODE_BILLING")
    public void setKodeBilling(String kodeBilling) {
        this.kodeBilling = kodeBilling;
    }

    public Date getTglBilling() {
        return tglBilling;
    }

    @XmlElement(name = "TGL_BILLING")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public void setTglBilling(Date tglBilling) {
        this.tglBilling = tglBilling;
    }

    public Date getTglJtTempo() {
        return tglJtTempo;
    }

    @XmlElement(name = "TGL_JT_TEMPO")
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    public void setTglJtTempo(Date tglJtTempo) {
        this.tglJtTempo = tglJtTempo;
    }

    public String getKdDokBilling() {
        return kdDokBilling;
    }

    @XmlElement(name = "KD_DOK_BILLING")
    public void setKdDokBilling(String kdDokBilling) {
        this.kdDokBilling = kdDokBilling;
    }

    public Double getTotalBilling() {
        return totalBilling;
    }

    @XmlElement(name = "TOTAL_BILLING")
    public void setTotalBilling(Double totalBilling) {
        this.totalBilling = totalBilling;
    }

    public Double getNdpbm() {
        return ndpbm;
    }

    @XmlElement(name = "NDPBM")
    public void setNdpbm(Double ndpbm) {
        this.ndpbm = ndpbm;
    }

    public Double getNilPabRp() {
        return nilPabRp;
    }

    @XmlElement(name = "NIL_PAB_RP")
    public void setNilPabRp(Double nilPabRp) {
        this.nilPabRp = nilPabRp;
    }

    public String getNoSpbl() {
        return noSpbl;
    }

    @XmlElement(name = "NO_SPBL")
    public void setNoSpbl(String noSpbl) {
        this.noSpbl = noSpbl;
    }

    public Date getTglSpbl() {
        return tglSpbl;
    }

    @XmlElement(name = "TGL_SPBL")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public void setTglSpbl(Date tglSpbl) {
        this.tglSpbl = tglSpbl;
    }

    public String getNoSppbmcp() {
        return noSppbmcp;
    }

    @XmlElement(name = "NO_SPPBMCP")
    public void setNoSppbmcp(String noSppbmcp) {
        this.noSppbmcp = noSppbmcp;
    }

    public Date getTglSppbmcp() {
        return tglSppbmcp;
    }

    @XmlElement(name = "TGL_SPPBMCP")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public void setTglSppbmcp(Date tglSppbmcp) {
        this.tglSppbmcp = tglSppbmcp;
    }

    public String getNoSptnp() {
        return noSptnp;
    }

    @XmlElement(name = "NO_SPTNP")
    public void setNoSptnp(String noSptnp) {
        this.noSptnp = noSptnp;
    }

    public Date getTglSptnp() {
        return tglSptnp;
    }

    @XmlElement(name = "TGL_SPTNP")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public void setTglSptnp(Date tglSptnp) {
        this.tglSptnp = tglSptnp;
    }

    public List<ChildDetilLartas> getDetilLartas() {
        return detilLartas;
    }

    @XmlElement(name = "DETIL_LARTAS")
    public void setDetilLartas(List<ChildDetilLartas> detilLartas) {
        this.detilLartas = detilLartas;
    }
}
