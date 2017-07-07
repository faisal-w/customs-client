package com.equilibrium.kiriman.services.impl;

import com.equilibrium.kiriman.entities.ArgoBilling;
import com.equilibrium.kiriman.entities.LogCnPibk;
import com.equilibrium.kiriman.entities.Perusahaan;
import com.equilibrium.kiriman.services.ArgoBillingService;
import com.equilibrium.kiriman.services.repository.ArgoBillingRepo;
import com.equilibrium.kiriman.services.repository.LogCnPibkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Created by faisalw on 3/26/17.
 */
@Service("argoBillingService")
public class ArgoBillingServiceImpl implements ArgoBillingService {

    @Autowired
    ArgoBillingRepo argoRepo;
    @Autowired
    LogCnPibkRepo cnPibkRepo;

    @Override
    public ArgoBilling depositBaru(ArgoBilling depositBaru) {
        argoRepo.save(depositBaru);
        return depositBaru;
    }

    @Override
    public ArgoBilling transaksi(ArgoBilling trans) {
        trans.getCnPibk().getPerusahaan().getJenisDeposit();
        if (trans.getNilaiTransaksi().compareTo(BigInteger.ZERO)==-1){
            BigInteger saldo = argoRepo.getSaldo(trans.getPerusahaan().getIdPerusahaan());
            if(saldo.compareTo(trans.getNilaiTransaksi())==-1){
                return null;
            }else{
                argoRepo.save(trans);
                return trans;
            }
        }
        return null;
    }

    @Override
    public ArgoBilling potongKuotaPerDokumen(Perusahaan perusahaan, String noBrg, Date tglHouseAwb) {
        ArgoBilling potong = new ArgoBilling();
        potong.setCnPibk(cnPibkRepo.findByNoBarangAndTglHouseBlawb(noBrg,tglHouseAwb));
        potong.setNilaiTransaksi(BigInteger.valueOf(-500));
        potong.setPerusahaan(perusahaan);
        potong.setDeposit(false);
        potong.setTglTrans(new Date());
        potong.setTarif(500);
        potong.setKeterangan("Argo per-dokumen");
        argoRepo.saveAndFlush(potong);
        return potong;
    }

    @Override
    public ArgoBilling potongKuotaPerKilo(Perusahaan perusahaan,String noBrg,Date tglHouseAwb) {
        ArgoBilling potong = new ArgoBilling();
        LogCnPibk tempCn = cnPibkRepo.findByNoBarangAndTglHouseBlawb(noBrg,tglHouseAwb);
        potong.setCnPibk(tempCn);
        //Bruto x 200 rupiahs
        BigInteger nilaiTrans = tempCn.getBruto().multiply(BigDecimal.valueOf(200)).toBigInteger();
        if(nilaiTrans.compareTo(BigInteger.valueOf(2000))==-1){
            nilaiTrans = BigInteger.valueOf(2000);
        }
        potong.setNilaiTransaksi(nilaiTrans);
        potong.setPerusahaan(perusahaan);
        potong.setDeposit(false);
        potong.setTglTrans(new Date());
        potong.setTarif(200);
        potong.setKeterangan("Argo per-kilo");
        argoRepo.saveAndFlush(potong);
        return potong;
    }

    @Override
    public List<ArgoBilling> getAllDeposit() {
        return argoRepo.findByIsDepositTrueOrderByTglTransDesc();
    }

    @Override
    public List<ArgoBilling> getDepositsByPerusahaan(int idPerusahaan) {
        return argoRepo.findByPerusahaan_idPerusahaan(idPerusahaan);
    }
}
