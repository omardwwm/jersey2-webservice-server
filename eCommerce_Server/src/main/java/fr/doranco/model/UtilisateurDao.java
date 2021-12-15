package fr.doranco.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.doranco.connexion.DataSourceConnexion;
import fr.doranco.entity.Adresse;
import fr.doranco.entity.Utilisateur;

public class UtilisateurDao implements IUtilisateurDao {

	@Override
	public Utilisateur addUtilisateur(Utilisateur utilisateur) throws SQLException {

		Connection connexion = null;
		PreparedStatement statement = null;
		PreparedStatement adresseStatement = null;
		String query = "INSERT INTO utilisateur(nom, prenom, dateNaissance,isActif, profil, email, password, telephone) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		String queryAdresse = "INSERT INTO adresse(numero, rue, ville, codePostal, utilisateur_id) VALUES(?, ?, ?, ?, ?)";
		try {
			connexion = DataSourceConnexion.getInstance().getConn();
			statement = connexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			adresseStatement = connexion.prepareStatement(queryAdresse);
			statement.setString(1, utilisateur.getNom());
			statement.setString(2, utilisateur.getPrenom());
			statement.setDate(3, new java.sql.Date(utilisateur.getDateNaissance().getTime()));
			statement.setBoolean(4, utilisateur.getIsActif());
			statement.setString(5, utilisateur.getProfil());
			statement.setString(6, utilisateur.getEmail());
			statement.setString(7, utilisateur.getPassword());
			statement.setString(8, utilisateur.getTelephone());
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("A new employe was inserted successfully!");
				ResultSet rs = statement.getGeneratedKeys();
				System.out.println(rs);
				if (rs.next()) {
					int recuperatedUtlisateurId = rs.getInt(1);
					utilisateur.setId(recuperatedUtlisateurId);
					System.out.println(recuperatedUtlisateurId);
					for (Adresse adresse : utilisateur.getAdresses()) {
						adresseStatement.setString(1, adresse.getNumero());
						adresseStatement.setString(2, adresse.getVille());
						adresseStatement.setString(3, adresse.getRue());
						adresseStatement.setString(4, adresse.getCodePostal());
						adresseStatement.setInt(5, recuperatedUtlisateurId);
						adresseStatement.addBatch();
					}
					int rowsInsertedAdresse = adresseStatement.executeUpdate();
					if(rowsInsertedAdresse > 0) {
						System.out.println("A new adresse was inserted successfully!");
					}
				}	
			}	
//			if (utilisateur.getAdresses() != null && !utilisateur.getAdresses().isEmpty()) {
//				for (Adresse adresse : utilisateur.getAdresses()) {
//					adresseStatement.setString(1, adresse.getNumero());
//					adresseStatement.setString(2, adresse.getVille());
//					adresseStatement.setString(3, adresse.getRue());
//					adresseStatement.setString(4, adresse.getCodePostal());
//					adresseStatement.setInt(5, utilisateur.getId());
//					adresseStatement.addBatch();
//				}
//				int rowsInsertedAdresse = adresseStatement.executeUpdate();
//				if(rowsInsertedAdresse > 0) {
//					System.out.println("Votre adresse was inserted successfully!");
//				}
//			}
			statement.close();		
			adresseStatement.close();

			return utilisateur;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Utilisateur getUtilisateur(Integer id) throws SQLException {
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultat = null;
//		String query = "SELECT id, nom, prenom, dateNaissance, isActif, profil, email, telephone FROM utilisateur" + " WHERE id=" + id;
		String query2 = "SELECT * FROM utilisateur LEFT JOIN adresse ON adresse.utilisateur_id = utilisateur.id WHERE utilisateur.id=?";
		Utilisateur utilisateur = new Utilisateur();
		try {
			connexion = DataSourceConnexion.getInstance().getConn();
			statement = connexion.prepareStatement(query2);
			statement.setInt(1, id);
			resultat = statement.executeQuery();
			if (resultat.next()) {
				utilisateur.setId(resultat.getInt("id"));
				utilisateur.setNom(resultat.getString("nom"));
				utilisateur.setPrenom(resultat.getString("prenom"));
				utilisateur.setDateNaissance(resultat.getDate("dateNaissance"));
				utilisateur.setIsActif(resultat.getBoolean("isActif"));
				utilisateur.setProfil(resultat.getString("profil"));
				utilisateur.setEmail(resultat.getString("email"));
				utilisateur.setTelephone(resultat.getString("telephone"));
				List<Adresse> listAdresses = new ArrayList<>();
//				List<Adresse> listAdresses = utilisateur.getAdresses();
				for(Adresse adresse : listAdresses) {
					adresse.setNumero(resultat.getString("numero"));
					adresse.setRue(resultat.getString("rue"));
					adresse.setVille(resultat.getString("ville"));
					adresse.setCodePostal(resultat.getString("codePostal"));
//					((PreparedStatement) adresse).addBatch();
					utilisateur.setAdresses(listAdresses);
				}
				utilisateur.setAdresses(listAdresses);
			} else {
				utilisateur = null;
			}

			resultat.close();
			statement.close();
			return utilisateur;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Utilisateur> getUtilisateurs() {
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultat = null;
		String query = "SELECT * FROM utilisateur";
		List<Utilisateur> AllUtilisateurs = new ArrayList<>();
		
		try {
			connexion = DataSourceConnexion.getInstance().getConn();
			statement = connexion.prepareStatement(query);
			resultat = statement.executeQuery();

			while (resultat.next()) {
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setId(resultat.getInt("id"));
				utilisateur.setNom(resultat.getString("nom"));
				utilisateur.setPrenom(resultat.getString("prenom"));
				utilisateur.setDateNaissance(resultat.getDate("dateNaissance"));
				utilisateur.setIsActif(resultat.getBoolean("isActif"));
				utilisateur.setProfil(resultat.getString("profil"));
				utilisateur.setEmail(resultat.getString("email"));
				utilisateur.setTelephone(resultat.getString("telephone"));
				AllUtilisateurs.add(utilisateur);

			}
			resultat.close();
			statement.close();
			return AllUtilisateurs;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Utilisateur> getUtilisateursAvecPanierNonVide() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur updateUtilisateur(Utilisateur utilisateur) throws SQLException {
		Connection connexion = null;
		PreparedStatement statement = null;
		String query ="UPDATE utilisateur" 
				+ " SET nom = ?, prenom=?, dateNaissance=?, isActif=?, profil=?, email=?, password=?, telephone=?"
				+ " WHERE id=?" ;
		
		try {
			connexion = DataSourceConnexion.getInstance().getConn();
			statement = connexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, utilisateur.getNom());
			statement.setString(2, utilisateur.getPrenom());
			statement.setDate(3, new java.sql.Date(utilisateur.getDateNaissance().getTime()));
			statement.setBoolean(4, utilisateur.getIsActif());
			statement.setString(5, utilisateur.getProfil());
			statement.setString(6, utilisateur.getEmail());
			statement.setString(7, utilisateur.getPassword());
			statement.setString(8, utilisateur.getTelephone());
			statement.setInt(9, utilisateur.getId());
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println(" Employe was updated successfully!");
			}

			statement.close();
			return utilisateur;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteUtilisateur(Integer id) throws SQLException {
		Connection connexion = null;
		PreparedStatement statement = null;
		Utilisateur utilisateur = this.getUtilisateur(id);
		String query = "DELETE FROM utilisateur WHERE id=?";
		
		if (utilisateur != null) {

			try {
				connexion = DataSourceConnexion.getInstance().getConn();
				statement = connexion.prepareStatement(query);
				statement.setInt(1, id);
				int rowsDeleted = statement.executeUpdate();
				if (rowsDeleted > 0) {
					System.out.println("employe was deleted successfully!");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
