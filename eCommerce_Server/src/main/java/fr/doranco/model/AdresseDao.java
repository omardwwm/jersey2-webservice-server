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
 
public class AdresseDao implements IAdresseDao {

	@Override
	public Adresse addAdresse(Adresse adresse, Integer utilisateur_id) throws SQLException {
		
		Connection connexion = null;
		PreparedStatement statement = null;
		String query = "INSERT INTO adresse(numero, rue, ville, codePostal, utilisateur_id) VALUES(?, ?, ?, ?, ?)";

		try {
			connexion = DataSourceConnexion.getInstance().getConn();
			statement = connexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, adresse.getNumero());
			statement.setString(2, adresse.getVille());
			statement.setString(3, adresse.getRue());
			statement.setString(4, adresse.getCodePostal());
			statement.setInt(5, utilisateur_id);
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				adresse.setId(rs.getInt(1));
			}
			return adresse;

		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if (statement != null && !statement.isClosed())
				statement.close();
			if (connexion != null && !connexion.isClosed())
				connexion.close();
		}
		return null;
	}

	@Override
	public Adresse getAdresse(Integer id) throws SQLException {
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultat = null;
		String query = "SELECT * FROM adresse" + " WHERE id=?";
		Adresse adresse = new Adresse();
		try {
			connexion = DataSourceConnexion.getInstance().getConn();
			statement = connexion.prepareStatement(query);
			statement.setInt(1, id);
			resultat = statement.executeQuery();

			if (resultat.next()) {
				adresse.setId(resultat.getInt("id"));
				adresse.setNumero(resultat.getString("numero"));
				adresse.setRue(resultat.getString("rue"));
				adresse.setVille(resultat.getString("ville"));
				adresse.setCodePostal(resultat.getString("codePostal"));

			} else {
				adresse = null;
			}

			resultat.close();
			statement.close();
			return adresse;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Adresse> getAdresses() {
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultat = null;
		String query = "SELECT * FROM adresse";
		List<Adresse> AllAdresses = new ArrayList<>();

		try {
			connexion = DataSourceConnexion.getInstance().getConn();
			statement = connexion.prepareStatement(query);
			resultat = statement.executeQuery();

			while (resultat.next()) {
				Adresse adresse = new Adresse();
				adresse.setId(resultat.getInt("id"));
				adresse.setNumero(resultat.getString("numero"));
				adresse.setRue(resultat.getString("rue"));
				adresse.setVille(resultat.getString("ville"));
				adresse.setCodePostal(resultat.getString("codePostal"));
				AllAdresses.add(adresse);

			}
			resultat.close();
			statement.close();
			return AllAdresses;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Adresse updateAdresse(Adresse adresse) throws SQLException {
		Connection connexion = null;
		PreparedStatement statement = null;
		String query = "UPDATE adresse" + " SET numero = ?, rue=?, ville=?, codePostal=?" + " WHERE id=?";
		try {
			connexion = DataSourceConnexion.getInstance().getConn();
			statement = connexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, adresse.getNumero());
			statement.setString(2, adresse.getRue());
			statement.setString(3, adresse.getVille());
			statement.setString(4, adresse.getCodePostal());
			statement.setInt(5, adresse.getId());
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println(" Votre adresse was updated successfully!");
			}

			statement.close();
			return adresse;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteAdresse(Integer id) throws SQLException {
		Connection connexion = null;
		PreparedStatement statement = null;
		Adresse adresse = this.getAdresse(id);
		String query = "DELETE FROM adresse" + " WHERE id=" + id;

		if (adresse != null) {

			try {
				connexion = DataSourceConnexion.getInstance().getConn();
				statement = connexion.prepareStatement(query);
				int rowsDeleted = statement.executeUpdate();
				if (rowsDeleted > 0) {
					System.out.println("Cette adresse was deleted successfully!");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
