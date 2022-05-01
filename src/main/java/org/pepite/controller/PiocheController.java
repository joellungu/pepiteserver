package org.pepite.controller;


import org.pepite.models.annonces.Pioche;
import org.pepite.models.annonces.PiocheRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/utilisateur")
public class PiocheController {

    @Inject
    PiocheRepository piocheRepository;
    /*

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Pioche> getAllPioches(@PathParam("if") Long idannonce){
        return piocheRepository.list("");
    }
    */
}
