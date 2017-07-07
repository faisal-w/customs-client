package com.equilibrium.kiriman.services.repository;

import com.equilibrium.kiriman.entities.LogCnPibk;
import com.equilibrium.kiriman.entities.Perusahaan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by faisalw on 3/22/17.
 */
public interface LogCnPibkRepo extends JpaRepository<LogCnPibk,Integer>{

    LogCnPibk findByNoBarangAndTglHouseBlawb(String noBarang, Date tglHouseBlawb);

    List<LogCnPibk> findByIsDraftTrueAndPerusahaanOrderByTglSimpanDesc(Perusahaan perusahaan);

}
