package org.pepite.models.annonces;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.pepite.models.admin.Administrateur;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class AnnoncesRepository  implements PanacheRepository<Annonces> {

    @Inject
    PiocheRepository piocheRepository;

    public void ajouterUneTache(int j, Annonces annonces){
        this.persist(annonces);
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        //
        executorService.schedule(new TacheAnnonce(annonces.id), j, TimeUnit.DAYS);
        //Classname::someTask
    }

    public void supprimerAnnonce(Long id){
        deleteById(id);
        List<Pioche> listePioche = piocheRepository.listAll();
        listePioche.forEach(t -> {
            if(t.idannonce == id){
                t.delete();
            }
        });
    }

}
