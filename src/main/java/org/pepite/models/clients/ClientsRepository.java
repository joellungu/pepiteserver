package org.pepite.models.clients;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClientsRepository implements PanacheRepository<Clients> {


}
