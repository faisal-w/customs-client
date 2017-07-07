package com.equilibrium.kiriman.services;

import com.equilibrium.kiriman.entities.Perusahaan;
import com.equilibrium.kiriman.entities.User;

import java.util.List;

/**
 * Created by faisalw on 3/28/17.
 */
public interface AdminClientService{

    void addClientAndUser(Perusahaan perusahaan, User user);

    void editPerusahaanClient(Perusahaan perusahaan);

    Perusahaan getOnePerusahaan(Integer id);

    Perusahaan getOnePerusahaan(String npwp);

    User getOneUser(Integer id);

    User editUser(User user);

    Iterable<Perusahaan> getAllPerusahaan();

    Iterable<User> getAllUser();

}
