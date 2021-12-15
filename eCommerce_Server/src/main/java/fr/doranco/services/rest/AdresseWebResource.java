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

import fr.doranco.entity.Adresse;
import fr.doranco.model.AdresseDao;

@Path("/adresses")
@Produces(MediaType.TEXT_PLAIN)
public class AdresseWebResource implements IAdresseWebResource{
	private final static String CHARSET = ";charset=UTF-8";


	@GET
	public String getInfos() {
		
		return "Bonjour Omar, bienvenue aur le server Jersey, SERVICE DES ADRESSES!!s";
	}
	
	@GET
	@Path("liste")
	@Produces({MediaType.APPLICATION_JSON + CHARSET, MediaType.APPLICATION_XML + CHARSET})
	public Response getAdresses() throws URISyntaxException {
		AdresseDao adresseDao = new AdresseDao();
		
		GenericEntity<List<Adresse>> listeAdresses  = new GenericEntity<List<Adresse>>(adresseDao.getAdresses()) {};
		return Response.ok().entity(listeAdresses).build();
		
	}

	@GET
	@Path("adresse/{id}")
	@Produces({MediaType.APPLICATION_JSON + CHARSET, MediaType.APPLICATION_XML + CHARSET})
	public Response getAdresse(@PathParam("id") Integer id) throws SQLException {
		AdresseDao adresseDao = new AdresseDao();
		Adresse adresse = null;
		try {
			adresse = adresseDao.getAdresse(id);
			if(adresse == null) {
				return Response.noContent().build();
			}else {
				return Response.ok().entity(adresse).build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@POST
	@Path("add/{utilisateur_id}")
	@Consumes({MediaType.APPLICATION_JSON + CHARSET, MediaType.APPLICATION_XML + CHARSET})
	@Produces({MediaType.APPLICATION_JSON + CHARSET, MediaType.APPLICATION_XML + CHARSET})
	public Response addAdresse(Adresse adresse, @PathParam("utilisateur_id") Integer utilisateur_id) throws URISyntaxException {
		
		AdresseDao adresseDao = new AdresseDao();

		try {
			adresseDao.addAdresse(adresse, utilisateur_id);
			return Response.status(Status.CREATED).entity("L'adresse a ete ajoutee").build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erreur technique !").build();
		}
		
	}


	@PUT
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	public Response updateAdresse(Adresse adresse) throws URISyntaxException {
		AdresseDao adresseDao = new AdresseDao();

		try { 
			adresseDao.updateAdresse(adresse);;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Response.status(200).entity("Adresse a ete mise a jour").build();
	}

	@DELETE
	@Path("delete/{id}")
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	public Response removeAdresse(@PathParam("id") Integer id) throws URISyntaxException {
		AdresseDao adresseDao = new AdresseDao();
		try { 
			adresseDao.deleteAdresse(id);;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Response.status(200).entity("Adresse supprime").build();
	}

}
