package com.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.DAO;
import com.dto.VilleFrance;

@RestController
//@RequestMapping("/path")
public class Controller {

	DAO dao = new DAO();

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public String get(@RequestParam(required = false, value = "codePostal") String codePostal) {
		List<VilleFrance> listeVilles;
		String villes = "";
		if(codePostal == null) {
			listeVilles = dao.lister();
		} else {
			listeVilles = dao.trouver(codePostal);
		}
		for(VilleFrance v : listeVilles) {
			villes += v.toString() + "<br>";
		}
		return villes;
	}

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	@ResponseBody
	public void post(@RequestParam(required = true, value = "codeCommuneINSEE") String codeCommuneINSEE,
					  @RequestParam(required = false, value = "nomCommune") String nomCommune,
					  @RequestParam(required = false, value = "codePostal") String codePostal,
					  @RequestParam(required = false, value = "libelleAcheminement") String libelleAcheminement,
					  @RequestParam(required = false, value = "ligne5") String ligne5,
					  @RequestParam(required = false, value = "latitude") String latitude,
					  @RequestParam(required = false, value = "longitude") String longitude) {
		if(nomCommune==null) {
			nomCommune = "";
		}
		if(codePostal==null) {
			codePostal = "00000";
		}
		if(libelleAcheminement==null) {
			libelleAcheminement = "";
		}
		if(ligne5==null) {
			ligne5 = "";
		}
		if(latitude==null) {
			latitude = "0";
		}
		if(longitude==null) {
			longitude = "0";
		}
		
		VilleFrance ville = new VilleFrance(codeCommuneINSEE, nomCommune, codePostal, libelleAcheminement,
											ligne5, latitude, longitude);
		dao.creer(ville);
	}
	
	@RequestMapping(value = "/put", method = RequestMethod.PUT)
	@ResponseBody
	public void put(@RequestParam(required = true, value = "codeCommuneINSEE") String codeCommuneINSEE,
					  @RequestParam(required = true, value = "modif") String modif,
					  @RequestParam(required = true, value = "indiceModif") int indiceModif) {
		
		dao.modifier(codeCommuneINSEE, modif, indiceModif);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@RequestParam(required = true, value = "codePostal") String codePostal) {
		dao.supprimer(codePostal);
	}
}