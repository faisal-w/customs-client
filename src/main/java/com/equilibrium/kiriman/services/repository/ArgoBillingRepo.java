package com.equilibrium.kiriman.services.repository;

import com.equilibrium.kiriman.entities.ArgoBilling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by faisalw on 3/26/17.
 */
public interface ArgoBillingRepo extends JpaRepository<ArgoBilling,Integer>{

    @Query(value = "SELECT sum(t.nilaiTransaksi) FROM ArgoBilling t WHERE t.perusahaan.idPerusahaan = :idPerusahaan")
    BigInteger getSaldo(@Param("idPerusahaan") Integer idPerusahaan);

    //@Query(value = "")
    List<ArgoBilling> findByIsDepositTrueOrderByTglTransDesc();

    List<ArgoBilling> findByPerusahaan_idPerusahaan(int idPerusahaan);

}
