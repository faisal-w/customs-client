package com.equilibrium.kiriman.services.repository;

import com.equilibrium.kiriman.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by faisalw on 3/24/17.
 */
public interface UserRepo extends JpaRepository<User,Integer>{
}
