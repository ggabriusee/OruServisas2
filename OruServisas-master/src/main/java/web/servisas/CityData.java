package web.servisas;

import java.util.ArrayList;
import lombok.Data;

@Data
public class CityData {
    private int id;
    private double temperature;
    private String city;
    private String date;
    private ArrayList<FootballTeam> footballTeams;
    
    public CityData(int id, double temperature, String city, String date, ArrayList<FootballTeam> farray) {
        this.id = id;
        this.temperature = temperature;
        this.city = city;
	this.date = date;
	this.footballTeams = farray;
//	if(farray == null){
//		this.footballTeams[]={};
//	}else{this.footballTeams = farray;}
    }
}