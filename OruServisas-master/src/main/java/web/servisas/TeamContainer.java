package web.servisas;

import java.util.ArrayList;

public class TeamContainer{
	

	private ArrayList<FootballTeam> teamArray = new ArrayList<FootballTeam>();
	TeamContainer(){}

	public ArrayList<FootballTeam> getAllTeams(){
		return teamArray;
	}

	public void delTeam(int id) throws Exception{
		boolean check=false;
		for (int i = 0; i < teamArray.size(); i++) {
			if(teamArray.get(i).getID() == id){
				check=true;
				teamArray.remove(i);
				break;
			}
		}
		if(check==false){throw new Exception("Tokios reikšmės nėra");}
	}
	
	public void upTeam(int id, FootballTeam teamObj) throws Exception {
		boolean check=false;
		for (int i = 0; i < teamArray.size(); i++) {
			if(teamArray.get(i).getID() == id){
				check=true;
				teamObj.setID(id);
				teamArray.set(i, teamObj);
				break;
			}
		}
		if(check==false){throw new Exception("Tokios reikšmės nėra");}
	}
	public Object getTeamUsingId(int id) throws Exception{
		boolean check=false;
		int index=-1;
		for (int i = 0; i < teamArray.size(); i++) {
			if(teamArray.get(i).getID() == id){
				check=true;
				index = i;
				break;
			}
		}
		if(check==false){throw new Exception("Tokios reikšmės nėra");}
		return teamArray.get(index);
	}

	public int addTeam(FootballTeam teamObj)  {
		int id = teamArray.size()==0 ? 1 : teamArray.get(teamArray.size()-1).getID()+1;
		teamObj.setID(id);
		teamArray.add(teamObj);
		return id;
	}	

}
