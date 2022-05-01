package org.pepite.controller;


import java.net.URI;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.pepite.models.utilisateurs.Utilisateur;
import org.pepite.models.utilisateurs.UtilisateurRepository;

@Path("/api/utilisateur")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UtilisateurController {


    private static final ObjectMapper mapper = new ObjectMapper();
	@Inject
	UtilisateurRepository utilisateurRepository;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Utilisateur get(@PathParam("id") Long id){
    	Utilisateur utilisateur = utilisateurRepository.findById(id); //resource.get(id);
        if (utilisateur == null) {
            throw new WebApplicationException(404);
        }
        return utilisateur;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Utilisateur> list() {
    	//System.out.println("LocalDate c'est: "+LocalDate.now());
    	/*
    	@QueryParam("sort") List<String> sortQuery,
            @QueryParam("page") @DefaultValue("0") int pageIndex,
            @QueryParam("size") @DefaultValue("20") int pageSize
    	*/
        //Page page = Page.of(pageIndex, pageSize);
        //Sort sort = getSortFromQuery(sortQuery);
        //List<Utilisateur> people = utilisateurRepository.list(page, sort);
        List<Utilisateur> utilisateurs = utilisateurRepository.listAll();
        // ... build a response with page links and return a 200 response with a list
        return utilisateurs;
    }

    @Transactional
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Utilisateur personToSave) {
    	
    	String rep = utilisateurRepository.saveUtilisateur(personToSave);
        // ... build a new location URL and return 201 response with an entity
    	//return Response.status(201).build();

        ObjectNode json = mapper.createObjectNode();
        json.put("reponse", rep);
        return Response.status(Response.Status.OK).entity(json).build();
    }

    @Transactional
    @GET
    @Path("auth/{numero}/{motDePasse}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response log(@PathParam("numero") String numero, @PathParam("motDePasse") String motDePasse) {

        HashMap rep = utilisateurRepository.authUtilisateur(numero,motDePasse);
        // ... build a new location URL and return 201 response with an entity
        //return Response.status(201).build();
        return Response.status(Response.Status.OK).entity(rep).build();
    }

    @Transactional
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Utilisateur personToSave) {
    	//
    	Utilisateur utilisateur = utilisateurRepository.findById(id);
    	//
        if (utilisateur == null) {
            throw new WebApplicationException("Pas d'utilisateur avec cette id: "+id,404);
        }else {
        	
            //utilisateur.id = personToSave.id;
            utilisateur.nom = personToSave.nom;
            utilisateur.postNom = personToSave.postNom;
            utilisateur.prenom = personToSave.prenom;
            utilisateur.dateNaissance = personToSave.dateNaissance;
            utilisateur.numero = personToSave.numero;
            utilisateur.motDePasse = personToSave.motDePasse;
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
    	
    	Utilisateur utilisateur = utilisateurRepository.findById(id);
    	utilisateur.delete();
    	
    	return Response.status(201).build();
    
    }

}
