package web.servisas;

import lombok.Data;

@Data
public class FootballTeam {
	private String Attendance;
	private String Captain;
	private String Country;
	private int ID;
	private String Name;    
	private String Stadium;
    
    public FootballTeam(){}
	
    public FootballTeam(int id) {
	this.ID = id;
    }
    public FootballTeam(String attendance, String captain, String country, int id, String name, String stadium) {
        this.Attendance = attendance;
        this.Captain = captain;
        this.Country = country;
	this.ID = id;
	this.Name = name;
	this.Stadium = stadium;
    }
}

