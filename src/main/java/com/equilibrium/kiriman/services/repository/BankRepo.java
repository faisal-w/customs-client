package com.equilibrium.kiriman.services.repository;

import com.equilibrium.kiriman.entities.RefBank;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by faisalw on 4/2/17.
 */
public interface BankRepo extends JpaRepository<RefBank,Integer>{
}
