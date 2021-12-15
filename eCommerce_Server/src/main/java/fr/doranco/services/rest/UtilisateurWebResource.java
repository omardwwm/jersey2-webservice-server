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

import fr.doranco.entity.Utilisateur;
import fr.doranco.model.UtilisateurDao;

@Path("/utilisateurs")
@Produces(MediaType.TEXT_PLAIN)
public class UtilisateurWebResource implements IUtilisateurWebResource{
	
	private final static String CHARSET = ";charset=UTF-8";
	
	// URI = http://localhost:9991/ecommServer/utilisateurs
	@GET
	public String getInfos() {
		
		return "Bonjour Omar, bienvenue aur le server Jersey, ton fournissuer de web servic rest";
	}
	
	
	@GET
	@Path("liste")
	@Produces({MediaType.APPLICATION_JSON + CHARSET, MediaType.APPLICATION_XML + CHARSET})
	public Response getAllUtilisateurs() throws URISyntaxException {
		
		UtilisateurDao utilisateurDao = new UtilisateurDao();
		
		GenericEntity<List<Utilisateur>> listeUtilisateurs  = new GenericEntity<List<Utilisateur>>(utilisateurDao.getUtilisateurs()) {};
		return Response.ok().entity(listeUtilisateurs).build();
	}
	
	
	@Override
	public Response getUtilisateursAvecPanierNonVide() {
		// TODO Auto-generated method stub
		return null;
	}
 
	@GET
	@Path("utilisateur/{id}")
	@Produces({MediaType.APPLICATION_JSON + CHARSET, MediaType.APPLICATION_XML + CHARSET})
	public Response getUtilisateur(@PathParam("id") Integer id) throws SQLException {
		
		UtilisateurDao utilisateurDao = new UtilisateurDao();
		Utilisateur utilsateur = null;
		try {
			utilsateur = utilisateurDao.getUtilisateur(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Response.ok().entity(utilsateur).build();
	}

	@POST
	@Path("add")
	@Consumes({MediaType.APPLICATION_JSON + CHARSET, MediaType.APPLICATION_XML + CHARSET})
	@Produces({MediaType.APPLICATION_JSON + CHARSET, MediaType.APPLICATION_XML + CHARSET})
	public Response addUtilisateur(Utilisateur utilisateur) throws URISyntaxException {
		
		UtilisateurDao utilisateurDao = new UtilisateurDao();
		
		try {
			utilisateurDao.addUtilisateur(utilisateur);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Response.status(Status.CREATED).entity("L'employe : "+ utilisateur.getNom()+" a ete ajoute").build();
	}

	@PUT
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	public Response updateUtilisateur(Utilisateur utilisateur) throws URISyntaxException {
		UtilisateurDao utilisateurDao = new UtilisateurDao();
		try { 
			utilisateurDao.updateUtilisateur(utilisateur);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Response.status(200).entity("Utilisateur met a jour").build();
	}

	@DELETE
	@Path("delete/{id}")
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	public Response removeUtilisateur(@PathParam("id") Integer id) throws URISyntaxException {
		UtilisateurDao utilisateurDao = new UtilisateurDao();
		try { 
			utilisateurDao.deleteUtilisateur(id);;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Response.status(200).entity("Utilisateur supprime").build();
	}

}
