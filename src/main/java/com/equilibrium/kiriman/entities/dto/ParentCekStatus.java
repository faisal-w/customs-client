package com.equilibrium.kiriman.entities.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by faisalw on 4/15/17.
 */
@XmlRootElement(name = "CEK_STATUS")
public class ParentCekStatus {

    List<HeaderChildGetSemuaRespon> header;

    public List<HeaderChildGetSemuaRespon> getHeader() {
        return header;
    }

    @XmlElement(name = "HEADER")
    public void setHeader(List<HeaderChildGetSemuaRespon> header) {
        this.header = header;
    }
}
