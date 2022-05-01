package org.pepite.models.annonces;

public class TacheAnnonce implements Runnable{

    Long id ;
    public TacheAnnonce(Long j){
        this.id = id;
    }

    @Override
    public void run() {
        ajouterUneTacheMethode(this.id);
    }

    public static void ajouterUneTacheMethode(Long id) {

    }
}
