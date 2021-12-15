package fr.doranco.model;

import java.sql.SQLException;
import java.util.List;

import fr.doranco.entity.Utilisateur;

public interface IUtilisateurDao {

	public Utilisateur addUtilisateur(Utilisateur utilisateur) throws SQLException;

	public Utilisateur getUtilisateur(Integer id) throws SQLException;

	public List<Utilisateur> getUtilisateurs();

//	DEMANDEE EXPLICITEMENT EN PROJET
	public List<Utilisateur> getUtilisateursAvecPanierNonVide();

	public Utilisateur updateUtilisateur(Utilisateur utilisateur) throws SQLException;

	public void deleteUtilisateur(Integer id) throws SQLException;

}
