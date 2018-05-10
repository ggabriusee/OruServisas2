package web.servisas;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;

//public class TeamContainer<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable{
public class TeamContainer{
	
	private ArrayList<FootballTeam> teamArray ;
        //private ArrayList<FootballTeam> teamArray = new ArrayList<FootballTeam>();
	TeamContainer(ArrayList<FootballTeam> al){teamArray = al;}
        
        public ArrayList<FootballTeam> getTeamArray(){
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
		if(check==false){throw new Exception("Tokio Id nėra");}
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
		if(check==false){throw new Exception("Tokio Id nėra");}
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
		if(check==false){throw new Exception("Tokio Id nėra");}
		return teamArray.get(index);
	}

	public void addTeam(FootballTeam teamObj)  {
		//int id = teamArray.size()==0 ? 1 : teamArray.get(teamArray.size()-1).getID()+1;
		//teamObj.setID(id);
		//teamArray.add(teamObj);
		//return id;
                int id = teamObj.getID();
                boolean check=false;
		for (int i = 0; i < teamArray.size(); i++) {
			if(teamArray.get(i).getID() == id){
                                teamArray.set(i, teamObj);
				check=true;
				break;
			}
		}
		if(check==false){teamArray.add(teamObj);}
	}	

}

/*
public class TeamContainer <E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable{

    private FootballTeam[] teamArray = new FootballTeam[10];
    private int size = 0;

    public E get(int i){
        if(i >= size) throw new IndexOutOfBoundsException("IndexOutOfBounds in teamArray!");
        return (E)teamArray[i];
    }
    
    public void addTeam(FootballTeam teamObj){
        if(size >= teamArray.length){
            FootballTeam[] newList = new FootballTeam[teamArray.length + 10];
            System.arraycopy(teamArray,0, newList, 0, teamArray.length);
            teamArray = newList;
        }
        int id = teamObj.getID();
        boolean check=false;
        for (int i = 0; i < size; i++) {
            if(teamArray[i].getID() == id){
                teamArray[i] = teamObj;
                check=true;
                break;
            }
        }
	if(check==false){teamArray[size] = teamObj;size++;}
    }
    
    public Object getTeamUsingId(int id) throws Exception{
            boolean check=false;
            int index=-1;
            for (int i = 0; i < size; i++) {
                    if(teamArray[i].getID() == id){
                            check=true;
                            index = i;
                            break;
                    }
            }
            if(check==false){throw new Exception("Tokios reikšmės nėra");}
            return teamArray[index];
    }

    public void upTeam(int id, FootballTeam teamObj) throws Exception {
		boolean check=false;
		for (int i = 0; i < size; i++) {
			if(teamArray[i].getID() == id){
				check=true;
				teamObj.setID(id);
				teamArray[i]= teamObj;
				break;
			}
		}
		if(check==false){throw new Exception("Tokio Id nėra");}
    }
    
    public FootballTeam[] getAllTeams(){
		return teamArray;
	}

    public void delTeam(int id) throws Exception{
        boolean check=false;
        for (int i = 0; i < size; i++) {
                if(teamArray[i].getID() == id){
                        check=true;
                        System.arraycopy(teamArray, i + 1, teamArray, i, size - i-1);
                        size--;
                        //FootballTeam[] copy = new FootballTeam[size-1];
            //System.arraycopy(teamArray, 0, copy, 0, i);
            //System.arraycopy(teamArray, i+1, copy, i, size-i-1);
                        //teamArray.remove(i);
                        break;
                }
        }
       
        if(check==false){throw new Exception("Tokio Id nėra");}
    }

    public int size(){
        return size;
    }

}
*/

