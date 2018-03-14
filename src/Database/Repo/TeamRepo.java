package Database.Repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import Database.Schema.Member;
import Database.Schema.Team;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class TeamRepo {
    private Team team;
    Statement statement = null;
 	 Connection connection = null;

    public TeamRepo(){

        team = new Team();

    }

    public void connectToDB() {
  		try {
  			String connectString = "jdbc:sqlite:rummaServerDB.db";
  			connection = DriverManager.getConnection(connectString);
  		} catch (SQLException e) {
  			System.out.println(e.getMessage());
  		}
  	 }
     
     public void closeConnection(){  //TODO add in fixtureRepo.closeConnection() where used elsewhere in code
    	 try {
   			connection.close();
   		} catch (SQLException ex) {
   			ex.printStackTrace();
   		}
     }

    public static String createTable(){
        return "CREATE TABLE IF NOT EXISTS " + Team.TABLE  + "("
                + Team.KEY_TeamId  + "   PRIMARY KEY,"
                + Team.KEY_TeamName + " TEXT,"
                + Team.KEY_TeamLocation + " TEXT,"
                + Team.KEY_TeamCurPoints + " TEXT)";
    }


    public int insert(Team team) {
   	 int retVal = 0; //if update happens correctly, retVal = 1, else = 0
   	 String insertStatement = "INSERT INTO " + Team.TABLE
   			 + "("
             + Team.KEY_TeamId  + ","
             + Team.KEY_TeamName + ","
             + Team.KEY_TeamLocation + ","
             + Team.KEY_TeamCurPoints + ") "
	  			  + "VALUES(?,?,?,?)";
	  	  
	  	  try {
	  		  	PreparedStatement prepStatement = connection.prepareStatement(insertStatement);
	  		  	prepStatement.setString(1, team.getTeamId());
	  		  	prepStatement.setString(2, team.getTeamName());
		  		prepStatement.setString(3, team.getTeamLocation());
		  		prepStatement.setString(4, team.getTeamCurPoints());
		  	
		  		retVal = prepStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  
   	 
       return retVal;
    }



    public void delete( ) {
        
    }

}