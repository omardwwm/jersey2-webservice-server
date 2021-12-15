package fr.doranco.services.rest;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import fr.doranco.entity.Article;
import fr.doranco.model.ArticleDao;

@Path("/articles")
@Produces(MediaType.TEXT_PLAIN)
public class ArticleWebResource implements IArticleWebResource{
	
	private final static String CHARSET = ";charset=UTF-8";
	
	@GET
	public String getInfos() {
		return "Bonjour Omar, SERVICE DES ADRICLES!!";
	}

	@GET
	@Path("liste")
	@Produces({MediaType.APPLICATION_JSON + CHARSET, MediaType.APPLICATION_XML + CHARSET})
	public Response getArticles() throws URISyntaxException {
		ArticleDao articleDao = new ArticleDao();
		GenericEntity<List<Article>> listeArticles  = new GenericEntity<List<Article>>(articleDao.getArticles()) {};
		return Response.ok().entity(listeArticles).build();
	}

	@GET
	@Path("article/{id}")
	@Produces({MediaType.APPLICATION_JSON + CHARSET, MediaType.APPLICATION_XML + CHARSET})
	public Response getArticle(@PathParam("id") Integer id) throws SQLException {
		
		ArticleDao articleDao = new ArticleDao();
		Article article = null;
		try {
			article = articleDao.getArticle(id);
			if(article == null) {
				return Response.noContent().build();
			}else {
				return Response.ok().entity(article).build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	@POST
	@Path("add")
	@Consumes({MediaType.APPLICATION_JSON + CHARSET, MediaType.APPLICATION_XML + CHARSET})
	@Produces({MediaType.APPLICATION_JSON + CHARSET, MediaType.APPLICATION_XML + CHARSET})
	public Response addArticle(Article article) throws URISyntaxException {
		ArticleDao articleDao = new ArticleDao();
		
		try {
			articleDao.addArticle(article);
			return Response.status(Status.CREATED).entity("L'article a ete ajoute").build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erreur technique !").build();
		}
	}

	@PUT
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	public Response updateArticle(Article article) throws URISyntaxException {
		ArticleDao articleDao = new ArticleDao();
		try {
			articleDao.updateArticle(article);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(200).entity("Article met a jour").build();
	}

	@DELETE
	@Path("delete/{id}")
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	public Response removeArticle(@PathParam("id") Integer id) throws URISyntaxException {
		ArticleDao articleDao = new ArticleDao();
		try {
			articleDao.deleteArticle(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(200).entity("Article supprime avec succes").build();
	}

}
