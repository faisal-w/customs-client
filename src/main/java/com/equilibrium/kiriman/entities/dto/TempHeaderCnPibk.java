package com.equilibrium.kiriman.entities.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by faisalw on 3/24/17.
 */
@XmlRootElement(name = "CN_PIBK")
public class TempHeaderCnPibk {

    CnPibk cnPibk;

    public CnPibk getCnPibk() {
        return cnPibk;
    }

    @XmlElement(name = "HEADER")
    public void setCnPibk(CnPibk cnPibk) {
        this.cnPibk = cnPibk;
    }
}
