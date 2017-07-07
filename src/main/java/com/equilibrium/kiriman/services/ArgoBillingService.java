package com.equilibrium.kiriman.services;

import com.equilibrium.kiriman.entities.ArgoBilling;
import com.equilibrium.kiriman.entities.Perusahaan;

import java.util.Date;
import java.util.List;

/**
 * Created by faisalw on 3/24/17.
 */
public interface ArgoBillingService {

    ArgoBilling depositBaru(ArgoBilling argoBaru);

    ArgoBilling transaksi(ArgoBilling trans);

    List<ArgoBilling> getAllDeposit();

    List<ArgoBilling> getDepositsByPerusahaan(int idPerusahaan);

    ArgoBilling potongKuotaPerDokumen(Perusahaan perusahaan,String noBrg,Date tglHouseAwb);

    ArgoBilling potongKuotaPerKilo(Perusahaan perusahaan,String noBrg,Date tglHouseAwb);

}
