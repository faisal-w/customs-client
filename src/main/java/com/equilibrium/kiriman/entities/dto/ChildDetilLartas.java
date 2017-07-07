package com.equilibrium.kiriman.entities.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by faisalw on 4/16/17.
 */
@XmlRootElement(name = "DETIL_LARTAS")
public class ChildDetilLartas {

    int seriBrg;
    String urBrg;
    String ketLartas;

    public int getSeriBrg() {
        return seriBrg;
    }

    @XmlElement(name = "SERI_BRG")
    public void setSeriBrg(int seriBrg) {
        this.seriBrg = seriBrg;
    }

    public String getUrBrg() {
        return urBrg;
    }

    @XmlElement(name = "UR_BRG")
    public void setUrBrg(String urBrg) {
        this.urBrg = urBrg;
    }

    public String getKetLartas() {
        return ketLartas;
    }

    @XmlElement(name = "KET_LARTAS")
    public void setKetLartas(String ketLartas) {
        this.ketLartas = ketLartas;
    }
}
