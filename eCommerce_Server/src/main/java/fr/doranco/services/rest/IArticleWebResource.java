package fr.doranco.services.rest;

import java.net.URISyntaxException;
import java.sql.SQLException;

import javax.ws.rs.core.Response;

import fr.doranco.entity.Article;

public interface IArticleWebResource {

	String getInfos();

	Response getArticles() throws URISyntaxException;

	Response getArticle(Integer id) throws SQLException;

	Response addArticle(Article article) throws URISyntaxException;

	Response updateArticle(Article article) throws URISyntaxException;

	Response removeArticle(Integer id) throws URISyntaxException;

}
