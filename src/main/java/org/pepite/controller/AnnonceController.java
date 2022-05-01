package org.pepite.controller;


import io.quarkus.arc.impl.Identified;
import org.pepite.models.annonces.Annonces;
import org.pepite.models.annonces.AnnoncesRepository;
import org.pepite.models.annonces.Pioche;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/annonce")
public class AnnonceController {

    @Inject
    AnnoncesRepository annoncesRepository;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Annonces getAnnonce(@PathParam("id") Long id){
        return annoncesRepository.findById(id);
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void supprimerAnnonce(@PathParam("id") Long id){
        annoncesRepository.supprimerAnnonce(id);
        Response.status(201);
    }


    @Transactional
    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void malAnnonce(@PathParam("id") Long id, Annonces annoncesN){
        Annonces annonces = annoncesRepository.findById(id);
        if(annonces == null){
            throw new WebApplicationException(404);
        }
        /*
        annonces.libelle;
    annonces.description;
    annonces.idimage;
    annonces.idauteur;
    annonces.auteur;
    annonces.datedebut;
    annonces.datefin;
    annonces.maxporte;
    annonces.total;
         */
    }

}
