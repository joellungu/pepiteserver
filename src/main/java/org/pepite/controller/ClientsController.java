package org.pepite.controller;

import org.pepite.models.clients.Clients;
import org.pepite.models.clients.ClientsRepository;
import org.pepite.models.utilisateurs.Utilisateur;
import org.pepite.models.utilisateurs.UtilisateurRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.websocket.server.PathParam;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/api/client")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientsController {

    @Inject
    ClientsRepository clientsRepository;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Clients get(@PathParam("id") Long id){
        Clients client = clientsRepository.findById(id); //resource.get(id);
        if (client == null) {
            throw new WebApplicationException(404);
        }
        return client;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Clients> list() {
        //System.out.println("LocalDate c'est: "+LocalDate.now());
    	/*
    	@QueryParam("sort") List<String> sortQuery,
            @QueryParam("page") @DefaultValue("0") int pageIndex,
            @QueryParam("size") @DefaultValue("20") int pageSize
    	*/
        //Page page = Page.of(pageIndex, pageSize);
        //Sort sort = getSortFromQuery(sortQuery);
        //List<Utilisateur> people = utilisateurRepository.list(page, sort);
        List<Clients> clients = clientsRepository.listAll();
        // ... build a response with page links and return a 200 response with a list
        return clients;
    }

    @Transactional
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Clients personToSave) {

        clientsRepository.persist(personToSave);
        // ... build a new location URL and return 201 response with an entity
        //return Response.status(201).build();
        return Response.created(URI.create("/persons/" + personToSave.id)).build();
    }

    @Transactional
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Clients personToSave) {
        //
        Clients clients = clientsRepository.findById(id);
        //
        if (clients == null) {
            throw new WebApplicationException("Pas d'utilisateur avec cette id: "+id,404);
        }else {

            //utilisateur.id = personToSave.id;
            clients.nom = personToSave.nom;
            clients.adresse = personToSave.adresse;
            clients.email = personToSave.email;
            clients.motdepasse = personToSave.motdepasse;
            //
            //utilisateurRepository.persist(utilisateur);
            return Response.status(201).build();
        }
        //Utilisateur person = utilisateurRepository.update(id, personToSave);
        // ... build a new location URL and return 201 response with an entity
        //return Response.status(201).build();
    }

    @Transactional
    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {

        Clients clients = clientsRepository.findById(id);
        clients.delete();

        return Response.status(201).build();

    }

}
