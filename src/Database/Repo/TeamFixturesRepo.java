package Database.Repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
                + TeamFixtures.KEY_FixtureId  + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TeamFixtures.KEY_TeamFixtureDate + " TEXT,"
                + TeamFixtures.KEY_TeamFixtureLocation + " TEXT,"
                + TeamFixtures.KEY_HomeTeam + " TEXT,"
                + TeamFixtures.KEY_AwayTeam + " TEXT)"
                ;
    }


    public int insert(TeamFixtures teamFixtures) {
   	 int retVal = 0; //if update happens correctly, retVal = 1, else = 0
   	 String insertStatement = "INSERT INTO " + TeamFixtures.TABLE
   			 + "("
             //+ TeamFixtures.KEY_FixtureId  + ","
             + TeamFixtures.KEY_TeamFixtureDate + ","
             + TeamFixtures.KEY_TeamFixtureLocation + ","
             + TeamFixtures.KEY_HomeTeam + ","
              + TeamFixtures.KEY_AwayTeam + ") "
	  			  + "VALUES(?,?,?,?)";
	  	  
	  	  try {
	  		  	PreparedStatement prepStatement = connection.prepareStatement(insertStatement);
	  		  	//prepStatement.setString(1, teamFixtures.getFixtureId());
	  		  	prepStatement.setString(1, teamFixtures.getFixtureDate());
		  		prepStatement.setString(2, teamFixtures.getFixtureLocation());
		  		prepStatement.setString(3, teamFixtures.getHomeTeam());
		  		prepStatement.setString(4, teamFixtures.getAwayTeam());


		  		retVal = prepStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  
   	 
       return retVal;
    }

    public ArrayList<String> getFixtureIDs(){
   	 String selectQuery = "Select " + TeamFixtures.KEY_FixtureId + " FROM " + TeamFixtures.TABLE;
   	 ArrayList<String> fixtureIDs = new ArrayList<>();
   	 
   	 try {
  			PreparedStatement prepStatement = connection.prepareStatement(selectQuery);
  			ResultSet rs = prepStatement.executeQuery();
  			if(!rs.next()){
  				System.out.println("THERE ARE NO FIXTURES");
  	   		fixtureIDs.add("CODE:4702:NOFIXTURES");
  			}else{
  				do{
  					fixtureIDs.add(rs.getString("FixtureId"));
  				}while(rs.next());
  			}
  			
  			
     	 } catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
     	 }
   	 
   	 return fixtureIDs;
    }
    
    public ArrayList<String> getMyFixtures(String teamID){
   	 String selectQuery = "Select " + TeamFixtures.KEY_FixtureId + " FROM " + TeamFixtures.TABLE
   			 + " WHERE HomeTeam = '" + teamID + "' OR AwayTeam = '" + teamID + "'";
   	 ArrayList<String> fixtureIDs = new ArrayList<>();
   	 
   	 try {
  			PreparedStatement prepStatement = connection.prepareStatement(selectQuery);
  			ResultSet rs = prepStatement.executeQuery();
  			if(!rs.next()){
  				System.out.println("THERE ARE NO FIXTURES");
  	   		fixtureIDs.add("CODE:4702:NOFIXTURES");
  			}else{
  				do{
  					fixtureIDs.add(rs.getString("FixtureId"));
  				}while(rs.next());
  			}
  			
  			
     	 } catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
     	 }
   	 
   	 return fixtureIDs;
    }
}
