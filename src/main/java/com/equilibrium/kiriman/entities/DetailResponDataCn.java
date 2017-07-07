package com.equilibrium.kiriman.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by faisalw on 4/16/17.
 */
@Entity
public class DetailResponDataCn {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer idDetailResponDataCn;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_response")
    ResponseDataCn parentRespon;
    @OneToMany(cascade = CascadeType.ALL)
    List<DetilLartasResponDataCn> detilLartasses;
    String kdKantor;
    Double ndpbm;
    String noPibk;
    Date tglPibk;
    String noSpbl;
    Date tglSpbl;
    String noSppbmcp;
    Date tglSppbmcp;
    String kodeBilling;
    Double totalBilling;
    String noSptnp;
    Date tglSptnp;
    String noSppb;
    Date tglSppb;
    String filePdf;
    Date tglJtTempo;
    String kdDokBilling;

    public Integer getIdDetailResponDataCn() {
        return idDetailResponDataCn;
    }

    public void setIdDetailResponDataCn(Integer idDetailResponDataCn) {
        this.idDetailResponDataCn = idDetailResponDataCn;
    }

    public ResponseDataCn getParentRespon() {
        return parentRespon;
    }

    public void setParentRespon(ResponseDataCn parentRespon) {
        this.parentRespon = parentRespon;
    }

    public List<DetilLartasResponDataCn> getDetilLartasses() {
        return detilLartasses;
    }

    public void setDetilLartasses(List<DetilLartasResponDataCn> detilLartasses) {
        this.detilLartasses = detilLartasses;
    }

    public String getKdKantor() {
        return kdKantor;
    }

    public void setKdKantor(String kdKantor) {
        this.kdKantor = kdKantor;
    }

    public Double getNdpbm() {
        return ndpbm;
    }

    public void setNdpbm(Double ndpbm) {
        this.ndpbm = ndpbm;
    }

    public String getNoPibk() {
        return noPibk;
    }

    public void setNoPibk(String noPibk) {
        this.noPibk = noPibk;
    }

    public Date getTglPibk() {
        return tglPibk;
    }

    public void setTglPibk(Date tglPibk) {
        this.tglPibk = tglPibk;
    }

    public String getNoSpbl() {
        return noSpbl;
    }

    public void setNoSpbl(String noSpbl) {
        this.noSpbl = noSpbl;
    }

    public Date getTglSpbl() {
        return tglSpbl;
    }

    public void setTglSpbl(Date tglSpbl) {
        this.tglSpbl = tglSpbl;
    }

    public String getNoSppbmcp() {
        return noSppbmcp;
    }

    public void setNoSppbmcp(String noSppbmcp) {
        this.noSppbmcp = noSppbmcp;
    }

    public Date getTglSppbmcp() {
        return tglSppbmcp;
    }

    public void setTglSppbmcp(Date tglSppbmcp) {
        this.tglSppbmcp = tglSppbmcp;
    }

    public String getKodeBilling() {
        return kodeBilling;
    }

    public void setKodeBilling(String kodeBilling) {
        this.kodeBilling = kodeBilling;
    }

    public Double getTotalBilling() {
        return totalBilling;
    }

    public void setTotalBilling(Double totalBilling) {
        this.totalBilling = totalBilling;
    }

    public String getNoSptnp() {
        return noSptnp;
    }

    public void setNoSptnp(String noSptnp) {
        this.noSptnp = noSptnp;
    }

    public Date getTglSptnp() {
        return tglSptnp;
    }

    public void setTglSptnp(Date tglSptnp) {
        this.tglSptnp = tglSptnp;
    }

    public String getNoSppb() {
        return noSppb;
    }

    public void setNoSppb(String noSppb) {
        this.noSppb = noSppb;
    }

    public Date getTglSppb() {
        return tglSppb;
    }

    public void setTglSppb(Date tglSppb) {
        this.tglSppb = tglSppb;
    }

    public String getFilePdf() {
        return filePdf;
    }

    public void setFilePdf(String filePdf) {
        this.filePdf = filePdf;
    }

    public Date getTglJtTempo() {
        return tglJtTempo;
    }

    public void setTglJtTempo(Date tglJtTempo) {
        this.tglJtTempo = tglJtTempo;
    }

    public String getKdDokBilling() {
        return kdDokBilling;
    }

    public void setKdDokBilling(String kdDokBilling) {
        this.kdDokBilling = kdDokBilling;
    }
}
