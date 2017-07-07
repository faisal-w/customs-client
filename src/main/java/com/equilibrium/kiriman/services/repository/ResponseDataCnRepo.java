package com.equilibrium.kiriman.services.repository;

import com.equilibrium.kiriman.entities.LogCnPibk;
import com.equilibrium.kiriman.entities.Perusahaan;
import com.equilibrium.kiriman.entities.ResponseDataCn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by faisalw on 3/27/17.
 */
public interface ResponseDataCnRepo extends JpaRepository<ResponseDataCn,Integer>{

    @Query("SELECT r FROM ResponseDataCn r WHERE r.kodeRespon NOT IN :codes")
    List<ResponseDataCn> findDokumensByKodeNotIn(@Param("codes") List<Integer> codes);

    @Query("SELECT r FROM ResponseDataCn r WHERE r.kodeRespon  = :kode")
    List<ResponseDataCn> findDokumensNotTerkirim(@Param("kode") Integer kode);

    @Query("SELECT r FROM ResponseDataCn r WHERE r.cnPibkTerkait.perusahaan = :perusahaan AND r.kodeRespon = :kode")
    List<ResponseDataCn> findAllUnsentDokumensByPerusahaan(@Param("perusahaan") Perusahaan perusahaan,@Param("kode") Integer kode);

//    @Query(value = "select * from traintable where (train, time) " +
//                    "in (select train, max(time) " +
//                    "from traintable group by train)", nativeQuery = true)
//    List<ResponseDataCn> findAllLatestResponByNpwp(@Param("npwp") String npwp);

    List<ResponseDataCn> findByCnPibkTerkait_perusahaanOrderByWaktuCekResponDesc(Perusahaan perusahaan);

    List<ResponseDataCn> findByCnPibkTerkaitInOrderByWaktuCekResponDesc(List<LogCnPibk> cnPibks);

}
