package web.servisas;

import static spark.Spark.*;

import java.io.IOException;
import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
//web servias /curencies <- postas per body paduoti, jis nueina i kolegos paima ta curency ir ideda i tavo oru seviso curecy masyva

public class Main {
   
	public static void main(String[] args) throws IOException {
		//ServiceContainer sc = new ServiceContainer();
		OruServisas service = new OruServisas();
		KitasServisas other = new KitasServisas();
		port(5000);

		path("/locations", () -> {

			get("", (request, response) -> {
				return service.getAllData(request, response);
			}, new JsonTransformer());

			get("/byCity/:city", (request, response) -> {
				return service.getCities(request, response);
			}, new JsonTransformer());

			get("/:id", (request, response) -> {
				return service.getWithId(request, response);
			}, new JsonTransformer());

			post("", (request, response) -> {
				return service.postData(request, response);
			} , new JsonTransformer());

			put("/:id", (request, response) -> {
				return service.putData(request, response);
			}, new JsonTransformer());

			delete("/:id", (request, response) -> {
				return service.deleteData(request, response);
			}, new JsonTransformer());

			get("/:id/football_teams", (request, response) -> {
				return other.getTeamWithId(request, response, service.getCont());
			}, new JsonTransformer());
		
			post("/:id/football_teams", (request, response) -> {
				return other.postTeam(request, response, service.getCont());
			}, new JsonTransformer());

			put("/:id/football_teams", (request, response) -> {
				return other.putTeam(request, response, service.getCont());
			}, new JsonTransformer());

			delete("/:id/football_teams", (request, response) -> {
				return other.deleteTeam(request, response, service.getCont());
			}, new JsonTransformer());

        	});

//		path("/football_teams", () -> {

////			get("/:id", (request, response) -> {
////				response.header("PATH", request.pathInfo());
////               			response.header("Method", request.requestMethod());
////				return other.gugu(request, response); 
////			}, new JsonTransformer());

//			post("", (request, response) -> {
//				return service.postData(request, response);
//			} , new JsonTransformer());


//        	});
	
		exception(Exception.class, (e, request, response) -> {
			response.status(HTTP_BAD_REQUEST);
			e.printStackTrace();
		});
		
		after((request, response) -> response.type("application/json"));

	}
    
}
