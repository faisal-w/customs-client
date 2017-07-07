package com.equilibrium.kiriman.services.impl;

import com.equilibrium.kiriman.entities.Perusahaan;
import com.equilibrium.kiriman.entities.User;
import com.equilibrium.kiriman.services.PerusahaanAndUserService;
import com.equilibrium.kiriman.services.repository.PerusahaanRepo;
import com.equilibrium.kiriman.services.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by faisalw on 3/24/17.
 */
@Service("perusahaanAndUserService")
public class PerusahaanAndUserServiceImpl implements PerusahaanAndUserService{

    @Autowired
    PerusahaanRepo perusRepo;

    @Autowired
    UserRepo userRepo;

    @Override
    public Perusahaan createNewPerusahaan(Perusahaan dataPerusahaan) {
        //Cek perusahaan
        //Validasi
        //Simpan
        perusRepo.save(dataPerusahaan);
        return null;
    }

    @Override
    public User createNewUser(User user) {
        userRepo.save(user);
        return user;
    }

    @Override
    public Perusahaan getLoggedInUserPerusahaan(String username) {
        Perusahaan perusahaan = perusRepo.findByPegawais_username(username);
        return perusahaan;
    }
}
