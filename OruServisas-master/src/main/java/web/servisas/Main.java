package web.servisas;

import static spark.Spark.*;

import java.io.IOException;
import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;

public class Main {
   
	public static void main(String[] args) throws IOException {
                ServiceContainer myService = new ServiceContainer();
		port(5000);

		path("/locations", () -> {

			get("", (request, response) -> {
				return OruServisas.getAllData(request, response, myService);
			}, new JsonTransformer());

			get("/byCity/:city", (request, response) -> {
				return OruServisas.getCities(request, response, myService);
			}, new JsonTransformer());

			get("/:id", (request, response) -> {
				return OruServisas.getWithId(request, response, myService);
			}, new JsonTransformer());

			post("", (request, response) -> {
				return OruServisas.postData(request, response, myService);
			} , new JsonTransformer());

			put("/:id", (request, response) -> {
				return OruServisas.putData(request, response, myService);
			}, new JsonTransformer());

			delete("/:id", (request, response) -> {
				return OruServisas.deleteData(request, response, myService);
			}, new JsonTransformer());

                        /*KITAS SERVISAS*/
                        
                        get("/:id/football_teams", (request, response) -> {
				return KitasServisas.getTeam(request, response, myService);
			}, new JsonTransformer());
                        
			get("/:id/football_teams/:fid", (request, response) -> {
				return KitasServisas.getTeamWithId(request, response, myService);
			}, new JsonTransformer());
		
			post("/:id/football_teams", (request, response) -> {
				return KitasServisas.postTeam(request, response, myService);
			}, new JsonTransformer());

			put("/:id/football_teams/:fid", (request, response) -> {
				return KitasServisas.putTeam(request, response, myService);
			}, new JsonTransformer());

			delete("/:id/football_teams/:fid", (request, response) -> {
				return KitasServisas.deleteTeam(request, response, myService);
			}, new JsonTransformer());

        	});
                
		exception(Exception.class, (e, request, response) -> {
			response.status(HTTP_BAD_REQUEST);
			e.printStackTrace();
		});
		
		after((request, response) -> response.type("application/json"));

	}
}
