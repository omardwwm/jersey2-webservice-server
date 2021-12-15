package fr.doranco.model;

import java.sql.SQLException;
import java.util.List;

import fr.doranco.entity.Article;

public interface IArticleDao {
	
	public Article getArticle(Integer id) throws SQLException;

	public List<Article> getArticles();

	public Article updateArticle(Article article) throws SQLException;

	public void deleteArticle(Integer id) throws SQLException;

	public Article addArticle(Article article) throws SQLException;

}
