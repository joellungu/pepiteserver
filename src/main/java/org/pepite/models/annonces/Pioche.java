package org.pepite.models.annonces;


import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Pioche extends PanacheEntity {

    public Long idannonce;
    public Long idutilisateur;
    public LocalDateTime datedepioche;
}
