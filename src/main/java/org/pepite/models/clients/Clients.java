package org.pepite.models.clients;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Clients extends PanacheEntity {
    public String nom;
    public String adresse;
    public String email;
    public String motdepasse;
}
