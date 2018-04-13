package web.servisas;

import static spark.Spark.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.IOException;
import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import spark.Request;
import spark.Response;

public class KitasServisas {

	private static final int HTTP_NOT_FOUND = 404;
	private static final int POST_CREATED_RESPONSE = 201;
	KitasServisas() {}
	//private static UserContainer uc = new UserContainer();
	public static FootballTeam getJson(Request request, Response response, int id) throws MalformedURLException, ProtocolException, IOException {
		FootballTeam ft = null;
		URL url = new URL("http://localhost:81/football_teams/" + id); //
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		if (conn.getResponseCode() != 200) {
		    throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
		            (conn.getInputStream())));
		String out;
		String output = "";
		while ((out = br.readLine()) != null) {
		    output = output + out;
		}
		output = output.replaceAll("[\\[\\]]", "");
		//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		//System.out.println(output);
		//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		ft = JsonTransformer.fromJson(output, FootballTeam.class);

		if (conn != null)
		    conn.disconnect();

		return ft;

	}

	public static FootballTeam postJson(Request request, Response response) throws MalformedURLException, ProtocolException, IOException {
		byte[] postDataBytes = request.body().getBytes("UTF-8");
		FootballTeam ft = null;
		URL url = new URL("http://localhost:81/football_teams"); //
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//conn.setRequestMethod("POST");
		//conn.setRequestProperty("Content-Type", "application/json");
		//if (conn.getResponseCode() != 200) {
		  //  throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		//}
		//byte[] postData = urlParameters.getBytes( StandardCharsets.UTF_8 );
		
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.getOutputStream().write(postDataBytes);
	
		BufferedReader br = new BufferedReader(new InputStreamReader(
		            (conn.getInputStream())));
		String out;
		String output = "";
		while ((out = br.readLine()) != null) {
		    output = output + out;
		}
		//output = output.replaceAll("[\\[\\]]", "");
		ft = JsonTransformer.fromJson(output, FootballTeam.class);

		if (conn != null)
		    conn.disconnect();
		
		return ft;

	}

	public static FootballTeam putJson(Request request, Response response, int id) throws MalformedURLException, ProtocolException, IOException {
		byte[] postDataBytes = request.body().getBytes("UTF-8");
		FootballTeam ft = null;
		URL url = new URL("http://localhost:81/football_teams/" + id); //
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//conn.setRequestMethod("POST");
		//conn.setRequestProperty("Content-Type", "application/json");
		//if (conn.getResponseCode() != 200) {
		  //  throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		//}
		//byte[] postData = urlParameters.getBytes( StandardCharsets.UTF_8 );
		
		conn.setRequestMethod("PUT");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.getOutputStream().write(postDataBytes);
	
		BufferedReader br = new BufferedReader(new InputStreamReader(
		            (conn.getInputStream())));
		String out;
		String output = "";
		while ((out = br.readLine()) != null) {
		    output = output + out;
		}
		//output = output.replaceAll("[\\[\\]]", "");
		ft = JsonTransformer.fromJson(output, FootballTeam.class);

		if (conn != null)
		    conn.disconnect();
		
		return ft;

	}

	public static void deleteJson(Request request, Response response, int id) throws MalformedURLException, ProtocolException, IOException {
		//byte[] postDataBytes = request.body().getBytes("UTF-8");
		FootballTeam ft = null;
		URL url = new URL("http://localhost:81/football_teams/" + id); //
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//conn.setRequestMethod("POST");
		//conn.setRequestProperty("Content-Type", "application/json");
		//if (conn.getResponseCode() != 200) {
		  //  throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		//}
		//byte[] postData = urlParameters.getBytes( StandardCharsets.UTF_8 );
		
		conn.setRequestMethod("DELETE");

		if (conn != null)
		    conn.disconnect();

	}


	public static Object getTeamWithId(Request request, Response response, ServiceContainer sc){
		//Object o = null;
		FootballTeam obj = null;
		int ident = Integer.valueOf(request.params(":id"));
		try{
			obj = getJson(request, response, ident);
			sc.getUsingId(ident).getFootballTeams().add(obj);
			//o = sc.getUsingId(ident);
		}catch(Exception e) {
		    response.status(HTTP_NOT_FOUND);
			e.printStackTrace();
		    //return new Exception("GUGUGU");
			return e.getMessage();
        
		}
		
 		return "OK";



	}

	public static Object postTeam(Request request, Response response, ServiceContainer sc){
		FootballTeam obj = null;
		int ident = Integer.valueOf(request.params(":id"));
		try{
			obj = postJson(request, response);
			sc.getUsingId(ident).getFootballTeams().add(obj);
			//o = sc.getUsingId(ident);
		}catch(Exception e) {
		    response.status(HTTP_NOT_FOUND);
			e.printStackTrace();
		    //return new Exception("GUGUGU");
			return e.getMessage();
        
		}
		response.status(POST_CREATED_RESPONSE);
		response.header("Location", request.url());
		return "Created";



	}

	public static Object putTeam(Request request, Response response, ServiceContainer sc){
		FootballTeam obj = null;
		int ident = Integer.valueOf(request.params(":id"));
		try{
			obj = putJson(request, response, ident);
			upTeam(ident, obj, sc.getUsingId(ident).getFootballTeams());
			//o = sc.getUsingId(ident);
		}catch(Exception e) {
		    response.status(HTTP_NOT_FOUND);
			e.printStackTrace();
		    //return new Exception("GUGUGU");
			return e.getMessage();
        
		}
		
 		return "OK";

	}

	public static void upTeam(int id, FootballTeam teamObj, ArrayList<FootballTeam> teamArray) throws Exception {
		boolean check=false;
		for (int i = 0; i < teamArray.size(); i++) {
			if(teamArray.get(i).getID() == id){
				check=true;
				teamObj.setID(id);
				teamArray.set(i, teamObj);
				break;
			}
		}
		if(check==false){throw new Exception("Tokio Id nėra");}
	}

	public static Object deleteTeam(Request request, Response response, ServiceContainer sc){
		int ident = Integer.valueOf(request.params(":id"));
		try{
			deleteJson(request, response, ident);
			delTeam(ident, sc.getUsingId(ident).getFootballTeams());
			//o = sc.getUsingId(ident);
		}catch(Exception e) {
		    response.status(HTTP_NOT_FOUND);
			e.printStackTrace();
		    //return new Exception("GUGUGU");
			return e.getMessage();
        
		}
		
 		return "OK";
		
	}

	public static void delTeam(int id, ArrayList<FootballTeam> teamArray) throws Exception{
		boolean check=false;
		for (int i = 0; i < teamArray.size(); i++) {
			if(teamArray.get(i).getID() == id){
				check=true;
				teamArray.remove(i);
				break;
			}
		}
		if(check==false){throw new Exception("Tokio Id nėra");}
	}

}
