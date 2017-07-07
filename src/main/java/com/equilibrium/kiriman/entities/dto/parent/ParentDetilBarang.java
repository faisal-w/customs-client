package com.equilibrium.kiriman.entities.dto.parent;

import com.equilibrium.kiriman.tools.DateAdapter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by faisalw on 3/22/17.
 */
@MappedSuperclass
public class ParentDetilBarang {

    int seriBrg;
    String hsCode;
    String urBrg;
    String kdNegAsal;
    String jmlKms;
    String jnsKms;
    BigDecimal cif;
    String kdSatHrg;
    BigDecimal jmlSatHrg;
    String flBebas;
    String noSkep;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    Date tglSkep;

    public ParentDetilBarang() {}

    public int getSeriBrg() {
        return seriBrg;
    }

    @XmlElement(name = "SERI_BRG")
    public void setSeriBrg(int seriBrg) {
        this.seriBrg = seriBrg;
    }

    public String getHsCode() {
        return hsCode;
    }

    @XmlElement(name = "HS_CODE")
    public void setHsCode(String hsCode) {
        this.hsCode = hsCode;
    }

    public String getUrBrg() {
        return urBrg;
    }

    @XmlElement(name = "UR_BRG")
    public void setUrBrg(String urBrg) {
        this.urBrg = urBrg;
    }

    public String getKdNegAsal() {
        return kdNegAsal;
    }

    @XmlElement(name = "KD_NEG_ASAL")
    public void setKdNegAsal(String kdNegAsal) {
        this.kdNegAsal = kdNegAsal;
    }

    public String getJmlKms() {
        return jmlKms;
    }

    @XmlElement(name = "JML_KMS")
    public void setJmlKms(String jmlKms) {
        this.jmlKms = jmlKms;
    }

    public String getJnsKms() {
        return jnsKms;
    }

    @XmlElement(name = "JNS_KMS")
    public void setJnsKms(String jnsKms) {
        this.jnsKms = jnsKms;
    }

    public BigDecimal getCif() {
        return cif;
    }

    @XmlElement(name = "CIF")
    public void setCif(BigDecimal cif) {
        this.cif = cif;
    }

    public String getKdSatHrg() {
        return kdSatHrg;
    }

    @XmlElement(name = "KD_SAT_HRG")
    public void setKdSatHrg(String kdSatHrg) {
        this.kdSatHrg = kdSatHrg;
    }

    public BigDecimal getJmlSatHrg() {
        return jmlSatHrg;
    }

    @XmlElement(name = "JML_SAT_HRG")
    public void setJmlSatHrg(BigDecimal jmlSatHrg) {
        this.jmlSatHrg = jmlSatHrg;
    }

    public String getFlBebas() {
        return flBebas;
    }

    @XmlElement(name = "FL_BEBAS")
    public void setFlBebas(String flBebas) {
        this.flBebas = flBebas;
    }

    public String getNoSkep() {
        return noSkep;
    }

    @XmlElement(name = "NO_SKEP")
    public void setNoSkep(String noSkep) {
        this.noSkep = noSkep;
    }

    public Date getTglSkep() {
        return tglSkep;
    }

    @XmlElement(name = "TGL_SKEP")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public void setTglSkep(Date tglSkep) {
        this.tglSkep = tglSkep;
    }

}
