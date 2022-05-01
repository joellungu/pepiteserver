package org.pepite.models.utilisateurs;

import javax.enterprise.context.ApplicationScoped;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;


@ApplicationScoped
public class UtilisateurRepository implements PanacheRepository<Utilisateur>{
	
	
	public void updateUtilisateur(Long id, Utilisateur u) {
		//this.upd
	}

	public String saveUtilisateur(Utilisateur utilisateur){

		AtomicReference<Boolean> bool = new AtomicReference<>(false);
		PanacheQuery<Utilisateur> liste = findAll();
		liste.list().forEach((u)->{
			if(u.numero.equals(utilisateur.numero)){
				bool.set(true);
			}
		});

		if(bool.get()){
			return "Ce numéro existe déjà";
		}else{
			persist(utilisateur);
			return "Enregistrement reussit";
		}
	}

	public HashMap authUtilisateur(String numero, String motDePasse) {

		final ObjectMapper mapper = new ObjectMapper();

		AtomicReference<Boolean> bool = new AtomicReference<>(false);
		HashMap<String, Object> map = new HashMap<>();
		map.put("exist", false);
		PanacheQuery<Utilisateur> liste = findAll();
		liste.list().forEach((u)->{
			if(u.numero.equals(numero) && u.motDePasse.equals(motDePasse)){
				//bool.set(true);
				map.put("exist",true);
				try {
					map.put("utilisateur", u);//,mapper.writeValueAsString(u)
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});
		//Sting rep = mapper.writeValueAsString()
		return  map;
	}

}
