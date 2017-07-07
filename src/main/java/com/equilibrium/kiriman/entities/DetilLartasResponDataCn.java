package com.equilibrium.kiriman.entities;

import javax.persistence.*;

/**
 * Created by faisalw on 4/16/17.
 */
@Entity
public class DetilLartasResponDataCn {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer id;
    @ManyToOne
    DetailResponDataCn detailRespon;
    int seriBrg;
    String urBrg;
    String ketLartas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DetailResponDataCn getDetailRespon() {
        return detailRespon;
    }

    public void setDetailRespon(DetailResponDataCn detailRespon) {
        this.detailRespon = detailRespon;
    }

    public int getSeriBrg() {
        return seriBrg;
    }

    public void setSeriBrg(int seriBrg) {
        this.seriBrg = seriBrg;
    }

    public String getUrBrg() {
        return urBrg;
    }

    public void setUrBrg(String urBrg) {
        this.urBrg = urBrg;
    }

    public String getKetLartas() {
        return ketLartas;
    }

    public void setKetLartas(String ketLartas) {
        this.ketLartas = ketLartas;
    }
}
