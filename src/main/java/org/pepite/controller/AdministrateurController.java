package org.pepite.controller;

import org.pepite.models.admin.Administrateur;
import org.pepite.models.admin.AdministrateurRepository;
import org.pepite.models.utilisateurs.Utilisateur;
import org.pepite.models.utilisateurs.UtilisateurRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.websocket.server.PathParam;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;


@Path("/api/admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdministrateurController {

    @Inject
    AdministrateurRepository administrateurRepository;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Administrateur get(@PathParam("id") Long id){
        Administrateur utilisateur = administrateurRepository.findById(id); //resource.get(id);
        if (utilisateur == null) {
            throw new WebApplicationException(404);
        }
        return utilisateur;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Administrateur> list() {
        //System.out.println("LocalDate c'est: "+LocalDate.now());
    	/*
    	@QueryParam("sort") List<String> sortQuery,
            @QueryParam("page") @DefaultValue("0") int pageIndex,
            @QueryParam("size") @DefaultValue("20") int pageSize
    	*/
        //Page page = Page.of(pageIndex, pageSize);
        //Sort sort = getSortFromQuery(sortQuery);
        //List<Utilisateur> people = utilisateurRepository.list(page, sort);
        List<Administrateur> utilisateurs = administrateurRepository.listAll();
        // ... build a response with page links and return a 200 response with a list
        return utilisateurs;
    }

    @Transactional
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Administrateur personToSave) {

        administrateurRepository.persist(personToSave);
        // ... build a new location URL and return 201 response with an entity
        //return Response.status(201).build();
        return Response.created(URI.create("/persons/" + personToSave.id)).build();
    }

    @Transactional
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Administrateur personToSave) {
        //
        Administrateur utilisateur = administrateurRepository.findById(id);
        //
        if (utilisateur == null) {
            throw new WebApplicationException("Pas d'utilisateur avec cette id: "+id,404);
        }else {

            //utilisateur.id = personToSave.id;
            utilisateur.nom = personToSave.nom;
            utilisateur.postnom = personToSave.postnom;
            utilisateur.prenom = personToSave.prenom;
            utilisateur.email = personToSave.email;
            utilisateur.motdepasse = personToSave.motdepasse;
            utilisateur.privilege = personToSave.privilege;
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

        Administrateur utilisateur = administrateurRepository.findById(id);
        utilisateur.delete();

        return Response.status(201).build();

    }

}
