package fr.doranco.services.rest;

import java.net.URISyntaxException;
import java.sql.SQLException;

import javax.ws.rs.core.Response;

import fr.doranco.entity.Utilisateur;

public interface IUtilisateurWebResource {

	String getInfos();

//	String getUtilisateur();
	Response getAllUtilisateurs() throws URISyntaxException;

	Response getUtilisateursAvecPanierNonVide() throws URISyntaxException;

	Response getUtilisateur(Integer id) throws SQLException;

	Response addUtilisateur(Utilisateur utilisateur) throws URISyntaxException;

	Response updateUtilisateur(Utilisateur utilisateur) throws URISyntaxException;

	Response removeUtilisateur(Integer id) throws URISyntaxException;

}
