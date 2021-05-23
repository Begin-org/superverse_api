package resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.BattleDao;
import dao.BattleDaoImplementation;
import model.Battle;

/*
 * ROTAS URL:
 * 
 * Create Battle (POST)
 * http://localhost:8080/superverse_api/battles
 * 
 * Exemplo de corpo:
 * {
    "idWinner": 1,
    "idLoser": 2,
    "idChosen": 2,
    "skill": "Skill teste",
    "googleUidUser": "UIDteste"
    }
 * 
 * -----------
 * 
 * List Battles (GET) - Retorna lista
 * http://localhost:8080/superverse_api/battles/{googleUidUser}
 * 
 * -----------
 * 
 * Get Battle (GET) - Retorna objeto único
 * http://localhost:8080/superverse_api/battles/{googleUidUser}/{idBattle}
 * 
 * -----------
 * 
 * Delete Battle (DELETE)
 * Retorna número 1 (e status 200) em caso de sucesso
 * Retorna uma mensagem (e status 500) em caso de erro
 * 
 * http://localhost:8080/superverse_api/battles/{idBattle}
 */


@Path("/battles")
public class BattleResource {

	private BattleDao battleDao = new BattleDaoImplementation();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createBattle(Battle battle) {
		try {
			battleDao.createBattle(battle);

			return Response.status(Response.Status.CREATED)
					.entity(battle).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("{ \"message\": "+ e.getMessage() +" }")
					.build();
		}
	}

	@GET
	@Path("{googleUidUser}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Battle> listBattles(@PathParam("googleUidUser") String uidUser) {
		try {
			return battleDao.listBattles(uidUser);
		} catch (Exception e) {
			return new ArrayList<Battle>();
		}
	}

	@GET
	@Path("{googleUidUser}/{idBattle}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBattle(@PathParam("googleUidUser") String uidUser,
							  @PathParam("idBattle") int idBattle) {
		try {
			return Response.status(Response.Status.OK)
					.entity(battleDao.getBattle(uidUser, idBattle)).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("{ \"message\": "+ e.getMessage() +" }")
					.build();
		}
	}

	@DELETE
	@Path("{idBattle}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteBattle(@PathParam("idBattle") int idBattle) {
		try {
			battleDao.deleteBattle(idBattle);
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("{ \"message\": "+ e.getMessage() +" }")
					.build();
		}
	}
}
