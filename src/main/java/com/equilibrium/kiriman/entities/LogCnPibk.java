package com.equilibrium.kiriman.entities;

import com.equilibrium.kiriman.entities.dto.CnPibk;
import com.equilibrium.kiriman.entities.dto.parent.ParentCnPibk;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by faisal on 3/20/2017.
 */
@Entity
public class LogCnPibk extends ParentCnPibk{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Integer idLogCnPibk;
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    List<LogHeaderPungutan> headerPungutans;
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    List<LogDetilBarang> detilBarangs;
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    List<ResponseDataCn> respons;
    @OneToOne
    ArgoBilling argoBilling;
    @ManyToOne
    Perusahaan perusahaan;
    @Column(name = "is_draft",nullable = true)
    Boolean isDraft;
    @Temporal(TemporalType.DATE)
    Date tglSimpan;
    @Column(nullable = true)
    String validasi;


    public LogCnPibk() {}

    public LogCnPibk(CnPibk cnPibk){
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

    public Integer getIdLogCnPibk() {
        return idLogCnPibk;
    }

    public void setIdLogCnPibk(Integer idLogCnPibk) {
        this.idLogCnPibk = idLogCnPibk;
    }

    public List<LogHeaderPungutan> getHeaderPungutans() {
        return headerPungutans;
    }

    public void setHeaderPungutans(List<LogHeaderPungutan> headerPungutans) {
        this.headerPungutans = headerPungutans;
    }

    public List<LogDetilBarang> getDetilBarangs() {
        return detilBarangs;
    }

    public void setDetilBarangs(List<LogDetilBarang> detilBarangs) {
        this.detilBarangs = detilBarangs;
    }

    public List<ResponseDataCn> getRespons() {
        return respons;
    }

    public void setRespons(List<ResponseDataCn> respons) {
        this.respons = respons;
    }

    public ArgoBilling getArgoBilling() {
        return argoBilling;
    }

    public void setArgoBilling(ArgoBilling argoBilling) {
        this.argoBilling = argoBilling;
    }

    public Perusahaan getPerusahaan() {
        return perusahaan;
    }

    public void setPerusahaan(Perusahaan perusahaan) {
        this.perusahaan = perusahaan;
    }

    public boolean isDraft() {
        return isDraft;
    }

    public void setDraft(boolean draft) {
        isDraft = draft;
    }

    public Date getTglSimpan() {
        return tglSimpan;
    }

    @PrePersist
    void isiTglSimpan(){
        this.tglSimpan = new Date();
    }

    public String getValidasi() {
        return validasi;
    }

    public void setValidasi(String validasi) {
        this.validasi = validasi;
    }
}
