package com.equilibrium.kiriman.entities.dto.parent;

import com.equilibrium.kiriman.tools.DateAdapter;
import com.equilibrium.kiriman.tools.DateTimeAdapter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by faisalw on 3/22/17.
 */
@MappedSuperclass
public class ParentCnPibk implements Serializable{

    String jnsAju;
    String kdJnsPibk;
    String noBarang;
    String kdKantor;
    int kdJnsAngkut;
    String nmPengangkut;
    String noFlight;
    String kdPelMuat;
    String kdPelBongkar;
    String kdGudang;
    String noInvoice;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    Date tglInvoice;
    String kdNegaraAsal;
    int jmlBrg;
    String noBc11;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    Date tglBc11;
    String noPosBc11;
    String noSubposBc11;
    String noSubsubposBc11;
    String noMasterBlawb;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    Date tglMasterBlawb;
    String noHouseBlawb;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    Date tglHouseBlawb;
    String kdNegPengirim;
    String nmPengirim;
    String alPengirim;
    String jnsIdPenerima;
    String noIdPenerima;
    String nmPenerima;
    String alPenerima;
    String telpPenerima;
    String jnsIdPemberitahu;
    String noIdPemberitahu;
    String nmPemberitahu;
    String alPemberitahu;
    String noIzinPemberitahu;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    Date tglIzinPemberitahu;
    String kdVal;
    BigDecimal ndpbm;
    BigDecimal fob;
    BigDecimal asuransi;
    BigDecimal freight;
    BigDecimal cif;
    BigDecimal netto;
    BigDecimal bruto;
    BigDecimal totDibayar;
    String npwpBilling;
    String namaBilling;

    public ParentCnPibk() {
    }

    public String getJnsAju() {
        return jnsAju;
    }

    @XmlElement(name = "JNS_AJU")
    public void setJnsAju(String jnsAju) {
        this.jnsAju = jnsAju;
    }

    public String getKdJnsPibk() {
        return kdJnsPibk;
    }

    @XmlElement(name = "KD_JNS_PIBK")
    public void setKdJnsPibk(String kdJnsPibk) {
        this.kdJnsPibk = kdJnsPibk;
    }

    public String getNoBarang() {
        return noBarang;
    }

    @XmlElement(name = "NO_BARANG")
    public void setNoBarang(String noBarang) {
        this.noBarang = noBarang;
    }

    public String getKdKantor() {
        return kdKantor;
    }

    @XmlElement(name = "KD_KANTOR")
    public void setKdKantor(String kdKantor) {
        this.kdKantor = kdKantor;
    }

    public int getKdJnsAngkut() {
        return kdJnsAngkut;
    }

    @XmlElement(name = "KD_JNS_ANGKUT")
    public void setKdJnsAngkut(int kdJnsAngkut) {
        this.kdJnsAngkut = kdJnsAngkut;
    }

    public String getNmPengangkut() {
        return nmPengangkut;
    }

    @XmlElement(name = "NM_PENGANGKUT")
    public void setNmPengangkut(String nmPengangkut) {
        this.nmPengangkut = nmPengangkut;
    }

    public String getNoFlight() {
        return noFlight;
    }

    @XmlElement(name = "NO_FLIGHT")
    public void setNoFlight(String noFlight) {
        this.noFlight = noFlight;
    }

    public String getKdPelMuat() {
        return kdPelMuat;
    }

    @XmlElement(name = "KD_PEL_MUAT")
    public void setKdPelMuat(String kdPelMuat) {
        this.kdPelMuat = kdPelMuat;
    }

    public String getKdPelBongkar() {
        return kdPelBongkar;
    }

    @XmlElement(name = "KD_PEL_BONGKAR")
    public void setKdPelBongkar(String kdPelBongkar) {
        this.kdPelBongkar = kdPelBongkar;
    }

    public String getKdGudang() {
        return kdGudang;
    }

    @XmlElement(name = "KD_GUDANG")
    public void setKdGudang(String kdGudang) {
        this.kdGudang = kdGudang;
    }

    public String getNoInvoice() {
        return noInvoice;
    }

    @XmlElement(name = "NO_INVOICE")
    public void setNoInvoice(String noInvoice) {
        this.noInvoice = noInvoice;
    }

    public Date getTglInvoice() {
        return tglInvoice;
    }

    @XmlElement(name = "TGL_INVOICE")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public void setTglInvoice(Date tglInvoice) {
        this.tglInvoice = tglInvoice;
    }

    public String getKdNegaraAsal() {
        return kdNegaraAsal;
    }

    @XmlElement(name = "KD_NEGARA_ASAL")
    public void setKdNegaraAsal(String kdNegaraAsal) {
        this.kdNegaraAsal = kdNegaraAsal;
    }

    public int getJmlBrg() {
        return jmlBrg;
    }

    @XmlElement(name = "JML_BRG")
    public void setJmlBrg(int jmlBrg) {
        this.jmlBrg = jmlBrg;
    }

    public String getNoBc11() {
        return noBc11;
    }

    @XmlElement(name = "NO_BC11")
    public void setNoBc11(String noBc11) {
        this.noBc11 = noBc11;
    }

    public Date getTglBc11() {
        return tglBc11;
    }

    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    @XmlElement(name = "TGL_BC11")
    public void setTglBc11(Date tglBc11) {
        this.tglBc11 = tglBc11;
    }

    public String getNoPosBc11() {
        return noPosBc11;
    }

    @XmlElement(name = "NO_POS_BC11")
    public void setNoPosBc11(String noPosBc11) {
        this.noPosBc11 = noPosBc11;
    }

    public String getNoSubposBc11() {
        return noSubposBc11;
    }

    @XmlElement(name = "NO_SUBPOS_BC11")
    public void setNoSubposBc11(String noSubposBc11) {
        this.noSubposBc11 = noSubposBc11;
    }

    public String getNoSubsubposBc11() {
        return noSubsubposBc11;
    }

    @XmlElement(name = "NO_SUBSUBPOS_BC11")
    public void setNoSubsubposBc11(String noSubsubposBc11) {
        this.noSubsubposBc11 = noSubsubposBc11;
    }

    public String getNoMasterBlawb() {
        return noMasterBlawb;
    }

    @XmlElement(name = "NO_MASTER_BLAWB")
    public void setNoMasterBlawb(String noMasterBlawb) {
        this.noMasterBlawb = noMasterBlawb;
    }

    public Date getTglMasterBlawb() {
        return tglMasterBlawb;
    }

    @XmlElement(name = "TGL_MASTER_BLAWB")
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    public void setTglMasterBlawb(Date tglMasterBlawb) {
        this.tglMasterBlawb = tglMasterBlawb;
    }

    public String getNoHouseBlawb() {
        return noHouseBlawb;
    }

    @XmlElement(name = "NO_HOUSE_BLAWB")
    public void setNoHouseBlawb(String noHouseBlawb) {
        this.noHouseBlawb = noHouseBlawb;
    }

    public Date getTglHouseBlawb() {
        return tglHouseBlawb;
    }

    @XmlElement(name = "TGL_HOUSE_BLAWB")
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    public void setTglHouseBlawb(Date tglHouseBlawb) {
        this.tglHouseBlawb = tglHouseBlawb;
    }

    public String getKdNegPengirim() {
        return kdNegPengirim;
    }

    @XmlElement(name = "KD_NEG_PENGIRIM")
    public void setKdNegPengirim(String kdNegPengirim) {
        this.kdNegPengirim = kdNegPengirim;
    }

    public String getNmPengirim() {
        return nmPengirim;
    }

    @XmlElement(name = "NM_PENGIRIM")
    public void setNmPengirim(String nmPengirim) {
        this.nmPengirim = nmPengirim;
    }

    public String getAlPengirim() {
        return alPengirim;
    }

    @XmlElement(name = "AL_PENGIRIM")
    public void setAlPengirim(String alPengirim) {
        this.alPengirim = alPengirim;
    }

    public String getJnsIdPenerima() {
        return jnsIdPenerima;
    }

    @XmlElement(name = "JNS_ID_PENERIMA")
    public void setJnsIdPenerima(String jnsIdPenerima) {
        this.jnsIdPenerima = jnsIdPenerima;
    }

    public String getNoIdPenerima() {
        return noIdPenerima;
    }

    @XmlElement(name = "NO_ID_PENERIMA")
    public void setNoIdPenerima(String noIdPenerima) {
        this.noIdPenerima = noIdPenerima;
    }

    public String getNmPenerima() {
        return nmPenerima;
    }

    @XmlElement(name = "NM_PENERIMA")
    public void setNmPenerima(String nmPenerima) {
        this.nmPenerima = nmPenerima;
    }

    public String getAlPenerima() {
        return alPenerima;
    }

    @XmlElement(name = "AL_PENERIMA")
    public void setAlPenerima(String alPenerima) {
        this.alPenerima = alPenerima;
    }

    public String getTelpPenerima() {
        return telpPenerima;
    }

    @XmlElement(name = "TELP_PENERIMA")
    public void setTelpPenerima(String telpPenerima) {
        this.telpPenerima = telpPenerima;
    }

    public String getJnsIdPemberitahu() {
        return jnsIdPemberitahu;
    }

    @XmlElement(name = "JNS_ID_PEMBERITAHU")
    public void setJnsIdPemberitahu(String jnsIdPemberitahu) {
        this.jnsIdPemberitahu = jnsIdPemberitahu;
    }

    public String getNoIdPemberitahu() {
        return noIdPemberitahu;
    }

    @XmlElement(name = "NO_ID_PEMBERITAHU")
    public void setNoIdPemberitahu(String noIdPemberitahu) {
        this.noIdPemberitahu = noIdPemberitahu;
    }

    public String getNmPemberitahu() {
        return nmPemberitahu;
    }

    @XmlElement(name = "NM_PEMBERITAHU")
    public void setNmPemberitahu(String nmPemberitahu) {
        this.nmPemberitahu = nmPemberitahu;
    }

    public String getAlPemberitahu() {
        return alPemberitahu;
    }

    @XmlElement(name = "AL_PEMBERITAHU")
    public void setAlPemberitahu(String alPemberitahu) {
        this.alPemberitahu = alPemberitahu;
    }

    public String getNoIzinPemberitahu() {
        return noIzinPemberitahu;
    }

    @XmlElement(name = "NO_IZIN_PEMBERITAHU")
    public void setNoIzinPemberitahu(String noIzinPemberitahu) {
        this.noIzinPemberitahu = noIzinPemberitahu;
    }

    public Date getTglIzinPemberitahu() {
        return tglIzinPemberitahu;
    }

    @XmlElement(name = "TGL_IZIN_PEMBERITAHU")
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    public void setTglIzinPemberitahu(Date tglIzinPemberitahu) {
        this.tglIzinPemberitahu = tglIzinPemberitahu;
    }

    public String getKdVal() {
        return kdVal;
    }

    @XmlElement(name = "KD_VAL")
    public void setKdVal(String kdVal) {
        this.kdVal = kdVal;
    }

    public BigDecimal getNdpbm() {
        return ndpbm;
    }

    @XmlElement(name = "NDPBM")
    public void setNdpbm(BigDecimal ndpbm) {
        this.ndpbm = ndpbm;
    }

    public BigDecimal getFob() {
        return fob;
    }

    @XmlElement(name = "FOB")
    public void setFob(BigDecimal fob) {
        this.fob = fob;
    }

    public BigDecimal getAsuransi() {
        return asuransi;
    }

    @XmlElement(name = "ASURANSI")
    public void setAsuransi(BigDecimal asuransi) {
        this.asuransi = asuransi;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    @XmlElement(name = "FREIGHT")
    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public BigDecimal getCif() {
        return cif;
    }

    @XmlElement(name = "CIF")
    public void setCif(BigDecimal cif) {
        this.cif = cif;
    }

    public BigDecimal getNetto() {
        return netto;
    }

    @XmlElement(name = "NETTO")
    public void setNetto(BigDecimal netto) {
        this.netto = netto;
    }

    public BigDecimal getBruto() {
        return bruto;
    }

    @XmlElement(name = "BRUTO")
    public void setBruto(BigDecimal bruto) {
        this.bruto = bruto;
    }

    public BigDecimal getTotDibayar() {
        return totDibayar;
    }

    @XmlElement(name = "TOT_DIBAYAR")
    public void setTotDibayar(BigDecimal totDibayar) {
        this.totDibayar = totDibayar;
    }

    public String getNpwpBilling() {
        return npwpBilling;
    }

    @XmlElement(name = "NPWP_BILLING")
    public void setNpwpBilling(String npwpBilling) {
        this.npwpBilling = npwpBilling;
    }

    public String getNamaBilling() {
        return namaBilling;
    }

    @XmlElement(name = "NAMA_BILLING")
    public void setNamaBilling(String namaBilling) {
        this.namaBilling = namaBilling;
    }
}