package fr.doranco.services.rest;

import java.net.URISyntaxException;
import java.sql.SQLException;

import javax.ws.rs.core.Response;

import fr.doranco.entity.Adresse;


public interface IAdresseWebResource {
	String getInfos();
	
	Response getAdresses() throws URISyntaxException;

	Response getAdresse(Integer id) throws SQLException;

	Response addAdresse(Adresse adresse, Integer utilisateur_id) throws URISyntaxException;

	Response updateAdresse(Adresse adresse) throws URISyntaxException;

	Response removeAdresse(Integer id) throws URISyntaxException;

}
