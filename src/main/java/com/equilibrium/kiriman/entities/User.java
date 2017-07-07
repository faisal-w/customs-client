package com.equilibrium.kiriman.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by faisalw on 3/19/17.
 */
@Entity
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int idUser;
    String nama;
    String username;
    String password;
    String role; //SUPERADMIN or USER
    boolean enabled;
    @ManyToOne
    Perusahaan client;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Perusahaan getClient() {
        return client;
    }

    public void setClient(Perusahaan client) {
        this.client = client;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
