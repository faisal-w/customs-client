package com.equilibrium.kiriman.services.impl;

import com.equilibrium.kiriman.entities.Perusahaan;
import com.equilibrium.kiriman.entities.User;
import com.equilibrium.kiriman.services.AdminClientService;
import com.equilibrium.kiriman.services.repository.PerusahaanRepo;
import com.equilibrium.kiriman.services.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by faisalw on 3/29/17.
 */
@Service("adminClientService")
public class AdminClientServiceImpl implements AdminClientService{

    @Autowired
    PerusahaanRepo perusahaanRepo;
    @Autowired
    UserRepo userRepo;

    @Override
    public void addClientAndUser(Perusahaan perusahaan, User user) {
        List<User> users = new ArrayList<>();
        users.add(user);
        perusahaan.setPegawais(users);
        perusahaanRepo.save(perusahaan);
    }

    @Override
    public void editPerusahaanClient(Perusahaan perusahaan) {
        perusahaanRepo.save(perusahaan);
    }

    @Override
    public Perusahaan getOnePerusahaan(Integer id) {
        Perusahaan perusahaan;
        perusahaan = perusahaanRepo.getOne(id);
        return perusahaan;
    }

    @Override
    public Perusahaan getOnePerusahaan(String npwp) {
        Perusahaan perusahaan;
        perusahaan = perusahaanRepo.findByNpwp(npwp);
        return perusahaan;
    }

    @Override
    public User getOneUser(Integer id){
        User user;
        user = userRepo.findOne(id);
        return user;
    }

    @Override
    public User editUser(User user){
        userRepo.save(user);
        user = userRepo.findOne(user.getIdUser());
        return user;
    }

    @Override
    public Iterable<Perusahaan> getAllPerusahaan() {
        return perusahaanRepo.findAll();
    }

    @Override
    public Iterable<User> getAllUser() {
        return userRepo.findAll();
    }
}