package com.equilibrium.kiriman.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by faisalw on 3/31/17.
 */
@Entity
public class RefPengirim implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int idPengirim;
    @ManyToOne
    Perusahaan perusahaan;

}
