package org.pepite.models.admin;


import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Administrateur extends PanacheEntity {

    public String nom;
    public String postnom;
    public String prenom;
    public String email;
    public String motdepasse;
    public int privilege;
}
