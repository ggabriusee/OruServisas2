package web.servisas;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import spark.Request;
import spark.Response;

public class KitasServisas {

	private static final int HTTP_NOT_FOUND = 404;
	private static final int POST_CREATED_RESPONSE = 201;
	KitasServisas() {}
	
	public static FootballTeam getJson(Request request, Response response, int id) throws MalformedURLException, ProtocolException, IOException {
		FootballTeam ft = null;
		URL url = new URL("http://kitas:5000/football_teams/" + id);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
                try{
                    conn.getResponseCode();
                }catch(Exception e) {
                        ft = new FootballTeam(id);
			return ft;
		}
		if (conn.getResponseCode() == 404) {
		    throw new RuntimeException("Tokio Id nera");
		}else if(conn.getResponseCode() != 200){
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
                ft = JsonTransformer.fromJson(output, FootballTeam.class);
                if(ft == null){
                    throw new RuntimeException("Can't find element with selected ID");
                }
                String embed = request.queryParams("embedded");
                if(embed != null && embed.equalsIgnoreCase("footballTeam")){
                }else{
                    ft = new FootballTeam(id);
                }
		
		if (conn != null)
		    conn.disconnect();

		return ft;

	}

	public static FootballTeam postJson(Request request, Response response) throws MalformedURLException, ProtocolException, IOException {
		byte[] postDataBytes = request.body().getBytes("UTF-8");
		FootballTeam ft = null;
		URL url = new URL("http://kitas:5000/football_teams"); 
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
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
		ft = JsonTransformer.fromJson(output, FootballTeam.class);

		if (conn != null)
		    conn.disconnect();
		
		return ft;

	}

	public static FootballTeam putJson(Request request, Response response, int id) throws MalformedURLException, ProtocolException, IOException {
		byte[] postDataBytes = request.body().getBytes("UTF-8");
		FootballTeam ft = null;
		URL url = new URL("http://kitas:5000/football_teams/" + id); 
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("PUT");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.getOutputStream().write(postDataBytes);

		if (conn.getResponseCode() == 404) {
		    throw new RuntimeException("Tokio Id nera");
		}else if(conn.getResponseCode() != 200){
			throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		}
	
		BufferedReader br = new BufferedReader(new InputStreamReader(
		            (conn.getInputStream())));
		String out;
		String output = "";
		while ((out = br.readLine()) != null) {
		    output = output + out;
		}
		ft = JsonTransformer.fromJson(output, FootballTeam.class);

		if (conn != null)
		    conn.disconnect();
		
		return ft;

	}

	public static String deleteJson(Request request, Response response, int id) throws MalformedURLException, ProtocolException, IOException {
		FootballTeam ft = null;
		URL url = new URL("http://kitas:5000/football_teams/" + id);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestMethod("DELETE");
                //int responseCode = conn.getResponseCode(); irgi veikia
                conn.connect();
                try{
                    conn.getInputStream();
                }catch(Exception e){
                    return "Tokio Id nera";
                }
		if (conn != null)
		    conn.disconnect();
                return "OK";

	}

        public static Object getTeam(Request request, Response response, ServiceContainer sc){
            	Object o = null;
	    	FootballTeam obj = null;
		//int aid = Integer.valueOf(request.params(":id"));
		//int aid = sc.getAll().get(0).getId();
            	try{
                        for(int aid = 1; aid <= sc.getAll().size();aid++){
                            if(!(sc.checkId(aid))){
                                continue;
                            }
                            obj = getJson(request, response, aid);
                            TeamContainer tc = new TeamContainer(sc.getUsingId(aid).getFootballTeams());
                            tc.addTeam(obj);
                            sc.getUsingId(aid).setFootballTeams(tc.getTeamArray());
                            
                        }
			
			//o = sc.getUsingId(aid);
		}catch(Exception e) {
		    	response.status(HTTP_NOT_FOUND);
			e.printStackTrace();
			return e.getMessage();
        
		}	
            	return sc.getAll();
        }
	public static Object getTeamWithId(Request request, Response response, ServiceContainer sc){
		Object o = null;
		FootballTeam obj = null;
		int aid = Integer.valueOf(request.params(":id"));
                //int faid = Integer.valueOf(request.params(":fid"));
		try{
			obj = getJson(request, response, aid);
                        TeamContainer tc = new TeamContainer(sc.getUsingId(aid).getFootballTeams());
                        tc.addTeam(obj);
                        sc.getUsingId(aid).setFootballTeams(tc.getTeamArray());
			o = sc.getUsingId(aid);
		}catch(Exception e) {
		    	response.status(HTTP_NOT_FOUND);
			e.printStackTrace();
			return e.getMessage();
        
		}	
 		return o;
	}

	public static Object postTeam(Request request, Response response, ServiceContainer sc){
		FootballTeam obj = null;
		int aid = Integer.valueOf(request.params(":id"));
		try{
                    obj = postJson(request, response);
                    TeamContainer tc = new TeamContainer(sc.getUsingId(aid).getFootballTeams());
                    tc.addTeam(obj);
                    sc.getUsingId(aid).setFootballTeams(tc.getTeamArray());
		}catch(Exception e) {
		    response.status(HTTP_NOT_FOUND);
			e.printStackTrace();
			return e.getMessage();
        
		}
		response.status(POST_CREATED_RESPONSE);
		response.header("Location", request.url());
		return "Created";
	}

	public static Object putTeam(Request request, Response response, ServiceContainer sc){
		FootballTeam obj = null;
		int aid = Integer.valueOf(request.params(":id"));
                int faid = Integer.valueOf(request.params(":fid"));
		try{
			obj = putJson(request, response, faid);
                        TeamContainer tc = new TeamContainer(sc.getUsingId(aid).getFootballTeams());
			tc.upTeam(faid, obj);
                        sc.getUsingId(aid).setFootballTeams(tc.getTeamArray());
		}catch(Exception e) {
		    response.status(HTTP_NOT_FOUND);
			e.printStackTrace();
			return e.getMessage();
        
		}
		
 		return "OK";

	}

	public static Object deleteTeam(Request request, Response response, ServiceContainer sc){
		int aid = Integer.valueOf(request.params(":id"));
                int faid = Integer.valueOf(request.params(":fid"));
                String message = "OK";
		try{
			message = deleteJson(request, response, faid);
                        TeamContainer tc = new TeamContainer(sc.getUsingId(aid).getFootballTeams());
			tc.delTeam(faid);
                        sc.getUsingId(aid).setFootballTeams(tc.getTeamArray());
		}catch(Exception e) {
		    response.status(HTTP_NOT_FOUND);
			e.printStackTrace();
			return e.getMessage();
        
		}
		
 		return message;
		
	}
}
