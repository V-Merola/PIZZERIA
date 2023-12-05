package service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import DAO.RESTDao;
import model.Utente;

@Path("/utente")
public class UtenteService {

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Utente> getUtente_JSON() {
		List<Utente> listaUtente = RESTDao.readAllUtente();
		return listaUtente;
	}

	@GET
	@Path("/{idUtente}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Utente getUtente(@PathParam("idUtente") Long idUtente) {
		return RESTDao.readUtente(idUtente);
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Utente addUtente(Utente utente) {
		return RESTDao.createUtente(utente);
	}

	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Utente updateUtente(Utente utente) {
		return RESTDao.updateUtente(utente);
	}

	@DELETE
	@Path("/{idUtente}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String deleteUtente(@PathParam("idUtente") Long idUtente) {
		return RESTDao.deleteUtente(idUtente);
	}

}
