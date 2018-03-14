package Database.Repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import Database.Schema.Member;
import Database.Schema.TeamFixtures;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class TeamFixturesRepo {
    private TeamFixtures teamFixtures;
    Statement statement = null;
 	 Connection connection = null;


    public TeamFixturesRepo(){
        teamFixtures = new TeamFixtures();
        connectToDB();
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
        return "CREATE TABLE IF NOT EXISTS " + TeamFixtures.TABLE  + "("
                + TeamFixtures.KEY_FixtureId  + "   PRIMARY KEY,"
                + TeamFixtures.KEY_TeamFixtureDate + " TEXT,"
                + TeamFixtures.KEY_TeamFixtureLocation + " TEXT)";
    }


    public int insert(TeamFixtures teamFixtures) {
   	 int retVal = 0; //if update happens correctly, retVal = 1, else = 0
   	 String insertStatement = "INSERT INTO " + TeamFixtures.TABLE
   			 + "("
             + TeamFixtures.KEY_FixtureId  + ","
             + TeamFixtures.KEY_TeamFixtureDate + ","
             + TeamFixtures.KEY_TeamFixtureLocation + ") "
	  			  + "VALUES(?,?,?)";
	  	  
	  	  try {
	  		  	PreparedStatement prepStatement = connection.prepareStatement(insertStatement);
	  		  	prepStatement.setString(1, teamFixtures.getFixtureId());
	  		  	prepStatement.setString(2, teamFixtures.getFixtureDate());
		  		prepStatement.setString(3, teamFixtures.getFixtureLocation());
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
