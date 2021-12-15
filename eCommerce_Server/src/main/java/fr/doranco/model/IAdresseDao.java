package fr.doranco.model;

import java.sql.SQLException;
import java.util.List;

import fr.doranco.entity.Adresse;
//import fr.doranco.entity.Utilisateur;


public interface IAdresseDao {
	
	
//	public Adresse addAdresse(Adresse adresse) throws SQLException;

	public Adresse getAdresse(Integer id) throws SQLException;

	public List<Adresse> getAdresses();

	public Adresse updateAdresse(Adresse adresse) throws SQLException;

	public void deleteAdresse(Integer id) throws SQLException;

	public Adresse addAdresse(Adresse adresse, Integer utilisateur_id) throws SQLException;

}
