package com.equilibrium.kiriman.services;

import com.equilibrium.kiriman.entities.Perusahaan;
import com.equilibrium.kiriman.entities.User;

/**
 * Created by faisalw on 3/24/17.
 */
public interface PerusahaanAndUserService {

    Perusahaan createNewPerusahaan(Perusahaan dataPerusahaan);

    User createNewUser(User user);

    Perusahaan getLoggedInUserPerusahaan(String username);

}
