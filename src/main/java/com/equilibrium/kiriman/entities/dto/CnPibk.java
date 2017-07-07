package com.equilibrium.kiriman.entities.dto;

import com.equilibrium.kiriman.entities.LogCnPibk;
import com.equilibrium.kiriman.entities.dto.parent.ParentCnPibk;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by faisal on 3/20/2017.
 */
@XmlRootElement(name = "CN_PIBK")
public class CnPibk extends ParentCnPibk {

    private List<HeaderPungutan> headerPungutans;
    private List<DetilBarang> detilBarangs;

    public CnPibk() {
        List<HeaderPungutan> tempHeaderPungs = new ArrayList<HeaderPungutan>();
    }

    public CnPibk(LogCnPibk cnPibk){
        this.setJnsAju(cnPibk.getJnsAju());
        this.setKdJnsPibk(cnPibk.getKdJnsPibk());
        this.setNoBarang(cnPibk.getNoBarang());
        this.setKdKantor(cnPibk.getKdKantor());
        this.setKdJnsAngkut(cnPibk.getKdJnsAngkut());
        this.setNmPengangkut(cnPibk.getNmPengangkut());
        this.setNoFlight(cnPibk.getNoFlight());
        this.setKdPelMuat(cnPibk.getKdPelMuat());
        this.setKdPelBongkar(cnPibk.getKdPelBongkar());
        this.setKdGudang(cnPibk.getKdGudang());
        this.setNoInvoice(cnPibk.getNoInvoice());
        this.setTglInvoice(cnPibk.getTglInvoice());
        this.setKdNegaraAsal(cnPibk.getKdNegaraAsal());
        this.setJmlBrg(cnPibk.getJmlBrg());
        this.setNoBc11(cnPibk.getNoBc11());
        this.setTglBc11(cnPibk.getTglBc11());
        this.setNoPosBc11(cnPibk.getNoPosBc11());
        this.setNoSubposBc11(cnPibk.getNoSubposBc11());
        this.setNoSubsubposBc11(cnPibk.getNoSubsubposBc11());
        this.setNoMasterBlawb(cnPibk.getNoMasterBlawb());
        this.setTglMasterBlawb(cnPibk.getTglMasterBlawb());
        this.setNoHouseBlawb(cnPibk.getNoHouseBlawb());
        this.setTglHouseBlawb(cnPibk.getTglHouseBlawb());
        this.setKdNegPengirim(cnPibk.getKdNegPengirim());
        this.setNmPengirim(cnPibk.getNmPengirim());
        this.setAlPengirim(cnPibk.getAlPengirim());
        this.setJnsIdPenerima(cnPibk.getJnsIdPenerima());
        this.setNoIdPenerima(cnPibk.getNoIdPenerima());
        this.setNmPenerima(cnPibk.getNmPenerima());
        this.setAlPenerima(cnPibk.getAlPenerima());
        this.setTelpPenerima(cnPibk.getAlPenerima());
        this.setJnsIdPemberitahu(cnPibk.getJnsIdPemberitahu());
        this.setNoIdPemberitahu(cnPibk.getNoIdPemberitahu());
        this.setNmPemberitahu(cnPibk.getNmPemberitahu());
        this.setAlPemberitahu(cnPibk.getAlPemberitahu());
        this.setNoIzinPemberitahu(cnPibk.getNoIzinPemberitahu());
        this.setTglIzinPemberitahu(cnPibk.getTglIzinPemberitahu());
        this.setKdVal(cnPibk.getKdVal());
        this.setNdpbm(cnPibk.getNdpbm());
        this.setFob(cnPibk.getFob());
        this.setAsuransi(cnPibk.getAsuransi());
        this.setFreight(cnPibk.getFreight());
        this.setCif(cnPibk.getCif());
        this.setNetto(cnPibk.getNetto());
        this.setBruto(cnPibk.getBruto());
        this.setTotDibayar(cnPibk.getTotDibayar());
        this.setNpwpBilling(cnPibk.getNpwpBilling());
        this.setNamaBilling(cnPibk.getNamaBilling());
    }

    public List<HeaderPungutan> getHeaderPungutans() {
        //if(headerPungutans == null) {
        //    headerPungutans = new ArrayList<HeaderPungutan>();
        //}
        return headerPungutans;
    }

    @XmlElementWrapper(name = "HEADER_PUNGUTAN")
    @XmlElement(name = "PUNGUTAN_TOTAL")
    public void setHeaderPungutans(List<HeaderPungutan> headerPungutans) {
        this.headerPungutans = headerPungutans;
    }

    public List<DetilBarang> getDetilBarangs() {
        if(null==detilBarangs){
            detilBarangs = new ArrayList<DetilBarang>();
            detilBarangs.add(new DetilBarang());
        }
        return detilBarangs;
    }

    @XmlElementWrapper(name = "DETIL")
    @XmlElement(name = "BARANG")
    public void setDetilBarangs(List<DetilBarang> detilBarangs) {
        this.detilBarangs = detilBarangs;
    }
}
