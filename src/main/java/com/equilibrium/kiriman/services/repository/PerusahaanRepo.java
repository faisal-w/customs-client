package com.equilibrium.kiriman.services.repository;

import com.equilibrium.kiriman.entities.Perusahaan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by faisalw on 3/24/17.
 */
public interface PerusahaanRepo extends JpaRepository<Perusahaan,Integer>{

    Perusahaan findByNpwp(String npwp);

    Perusahaan findByPegawais_idUser(int idUser);

    Perusahaan findByPegawais_username(String username);

    List<Perusahaan> findByIsAktifTrue();

}
