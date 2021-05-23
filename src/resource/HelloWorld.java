package resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/*
 * ROTAS URL:
 * 
 * Get Message (GET) - Retorna objeto JSON com mensagem
 * http://localhost:8080/superverse_api/
 */

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorld {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getMessage() {
		return "{\"Message\": \"Hello World\"}";
	}

}
