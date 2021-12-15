package fr.doranco.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.doranco.connexion.DataSourceConnexion;
import fr.doranco.entity.Article;

public class ArticleDao implements IArticleDao{

	@Override
	public Article getArticle(Integer id) throws SQLException {
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultat = null;
		String query = "SELECT * FROM article WHERE id=?";
		Article article = new Article();
		
		try {
			connexion = DataSourceConnexion.getInstance().getConn();
			statement = connexion.prepareStatement(query);
			statement.setInt(1, id);
			resultat = statement.executeQuery();

			if (resultat.next()) {
				article.setId(resultat.getInt("id"));
				article.setNom(resultat.getString("nom"));
				article.setDescription(resultat.getString("description"));
				article.setPrix(resultat.getDouble("prix"));
				article.setRemise(resultat.getDouble("remise"));
				article.setStock(resultat.getInt("stock"));
				article.setIsVendable(resultat.getBoolean("isVendable"));
				List<String> photos = Stream.of(resultat.getString("photos").split(";", -1))
						  .collect(Collectors.toList());
				article.setPhotos(photos);
				List<String> videos = Stream.of(resultat.getString("videos").split(";", -1))
						  .collect(Collectors.toList());
				article.setVideos(videos);

			} else {
				article = null;
			}

			resultat.close();
			statement.close();
			return article;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Article> getArticles() {
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultat = null;
		String query = "SELECT * FROM article";
		List<Article> allArticles = new ArrayList<Article>();
		try {
			connexion = DataSourceConnexion.getInstance().getConn();
			statement = connexion.prepareStatement(query);
			resultat = statement.executeQuery();
			while (resultat.next()) {
				Article article = new Article();
				article.setId(resultat.getInt("id"));
				article.setNom(resultat.getString("nom"));
				article.setDescription(resultat.getString("description"));
				article.setPrix(resultat.getDouble("prix"));
				article.setRemise(resultat.getDouble("remise"));
				article.setStock(resultat.getInt("stock"));
				article.setIsVendable(resultat.getBoolean("isVendable"));
				List<String> photos = Arrays.asList(resultat.getString("photos").split(";"));
//				List<String> photos = Stream.of(resultat.getString("photos").split(";", -1))
//						  .collect(Collectors.toList());
				article.setPhotos(photos);
//				List<String> videos = Stream.of(resultat.getString("videos").split(";", -1))
//						  .collect(Collectors.toList());
				List<String> videos = Arrays.asList(resultat.getString("videos").split(";"));
				article.setVideos(videos);
		
				allArticles.add(article);

			}
			resultat.close();
			statement.close();
			return allArticles;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Article updateArticle(Article article) throws SQLException {
		Connection connexion = null;
		PreparedStatement statement = null;
		String query ="UPDATE article" 
				+ " SET nom = ?, description=?, prix=?, remise=?, stock=?, isVendable=?, photos=?, videos=?"
				+ " WHERE id=?" ;
		try {
			connexion = DataSourceConnexion.getInstance().getConn();
			statement = connexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, article.getNom());
			statement.setString(2, article.getDescription());
			statement.setDouble(3, article.getPrix());
			statement.setDouble(4, article.getRemise());
			statement.setInt(5, article.getStock());
			statement.setBoolean(6, article.getIsVendable());
			
			String photosStr = new String();
			article.getPhotos().forEach(s -> photosStr.concat(s).concat(";"));
			statement.setString(7, photosStr);
			String videosStr = new String();
			article.getVideos().forEach(s -> videosStr.concat(s).concat(";"));
			statement.setString(8, videosStr);
			statement.setInt(9, article.getId());
			int rowsUpdated = statement.executeUpdate();
//			ResultSet rs = statement.getGeneratedKeys();
			if (rowsUpdated > 0) {
				System.out.println(" Votre article was updated successfully!");
			}
			statement.close();
			return article;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteArticle(Integer id) throws SQLException {
		Connection connexion = null;
		PreparedStatement statement = null;
		Article article = this.getArticle(id);
		String query = "DELETE FROM article WHERE id=?";
		if (article != null) {

			try {
				connexion = DataSourceConnexion.getInstance().getConn();
				statement = connexion.prepareStatement(query);
				statement.setInt(1, id);
				int rowsDeleted = statement.executeUpdate();
				if (rowsDeleted > 0) {
					System.out.println("article was deleted successfully!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}

	@Override
	public Article addArticle(Article article) throws SQLException {
		Connection connexion = null;
		PreparedStatement statement = null;
		String query = "INSERT INTO article(nom, description, prix, remise, stock, isVendable, photos, videos) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	
		try {
			connexion = DataSourceConnexion.getInstance().getConn();
			statement = connexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, article.getNom());
			statement.setString(2, article.getDescription());
			statement.setDouble(3, article.getPrix());
			statement.setDouble(4, article.getRemise());
			statement.setInt(5, article.getStock());
			statement.setBoolean(6, article.getIsVendable());
			statement.setString(7, String.join(";", article.getPhotos()));
			statement.setString(8, String.join(";", article.getVideos()));
//			String photosStr = new String();
//			article.getPhotos().forEach(s -> photosStr.concat(s).concat(";"));
//			statement.setString(7, photosStr);
//			String videosStr = new String();
//			article.getVideos().forEach(s -> videosStr.concat(s).concat(";"));
//			statement.setString(8, videosStr);
//			statement.setArray(7, statement.getConnection().createArrayOf("VARCHAR", new Object[] {article.getPhotos()}));
//			statement.setArray(8, statement.getConnection().createArrayOf("VARCHAR", new Object[] {article.getVideos()}));
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				article.setId(rs.getInt(1));
			}
			return article;

		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if (statement != null && !statement.isClosed())
				statement.close();
//			if (connexion != null && !connexion.isClosed())
//				connexion.close();
		}
		return null;
	}

}
