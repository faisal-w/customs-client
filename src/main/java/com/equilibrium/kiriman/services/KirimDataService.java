package com.equilibrium.kiriman.services;

import com.equilibrium.kiriman.entities.LogCnPibk;
import com.equilibrium.kiriman.entities.User;
import com.equilibrium.kiriman.entities.dto.CnPibk;
import com.equilibrium.kiriman.entities.dto.DetilBarang;
import com.equilibrium.kiriman.entities.dto.HeaderPungutan;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Created by faisalw on 3/18/17.
 */
public interface KirimDataService {

    Callable<Void> kirimDataSatuan(LogCnPibk cnPibk);

    void kirimDataUlang(CnPibk cnPibk, LogCnPibk log, int idRespon);

    void kirimDataBulk();

    Callable<Void> catatRespon(int kodeRespon,String keteranganRespon,int idCnPibk);

}
