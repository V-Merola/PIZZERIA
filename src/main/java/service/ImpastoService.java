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
import model.Impasto;

@Path("/impasto")
public class ImpastoService {

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Impasto> getImpasto_JSON() {
		List<Impasto> listaUtente = RESTDao.readAllImpasto();
		return listaUtente;
	}

	@GET
	@Path("/{idImpasto}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Impasto getImpasto(@PathParam("idImpasto") Long idImpasto) {
		return RESTDao.readImpasto(idImpasto);
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Impasto addImpasto(Impasto impasto) {
		return RESTDao.createImpasto(impasto);
	}

	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Impasto updateImpasto(Impasto impasto) {
		return RESTDao.updateImpasto(impasto);
	}

	@DELETE
	@Path("/{idImpasto}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String deleteImpasto(@PathParam("idImpasto") Long idImpasto) {
		return RESTDao.deleteImpasto(idImpasto);
	}
	
}
