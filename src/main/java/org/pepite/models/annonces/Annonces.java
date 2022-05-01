package org.pepite.models.annonces;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Annonces extends PanacheEntity{

    String libelle;
    String description;
    byte[] idimage;
    Long idauteur;
    String auteur;
    LocalDate datedebut;
    LocalDate datefin;
    int maxporte;
    int total;
}
