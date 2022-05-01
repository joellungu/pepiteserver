package org.pepite.models.admin;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AdministrateurRepository implements PanacheRepository<Administrateur> {
}
