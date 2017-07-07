package com.equilibrium.kiriman.entities.dto;

import com.equilibrium.kiriman.entities.LogDetilPungutan;
import com.equilibrium.kiriman.entities.dto.parent.ParentDetilPungutan;

import java.math.BigDecimal;

/**
 * Created by faisal on 3/20/2017.
 */
public class DetilPungutan extends ParentDetilPungutan{

    public DetilPungutan(){}

    public DetilPungutan(LogDetilPungutan detilPungutan){
        this.setNilai(detilPungutan.getNilai());
        this.setKdPungutan(detilPungutan.getKdPungutan());
        this.setJmlSat(detilPungutan.getJmlSat());
        this.setJnsTarif(detilPungutan.getJnsTarif());
        this.setKdSatTarif(detilPungutan.getKdSatTarif());
        this.setKdTarif(detilPungutan.getKdTarif());
        this.setTarif(detilPungutan.getTarif());
    }


}
