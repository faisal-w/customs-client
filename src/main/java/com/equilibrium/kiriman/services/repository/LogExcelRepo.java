package com.equilibrium.kiriman.services.repository;

import com.equilibrium.kiriman.entities.LogCnPibk;
import com.equilibrium.kiriman.entities.LogPekerjaanExcel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by faisalw on 5/1/17.
 */
public interface LogExcelRepo extends JpaRepository<LogPekerjaanExcel,Integer> {

    List<LogPekerjaanExcel> findByNpwpOrderByDateUploadDesc(String string);

}
