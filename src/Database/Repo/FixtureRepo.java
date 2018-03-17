package Database.Repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import Database.Schema.Fixture;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class FixtureRepo {

    private Fixture fixture;
    Statement statement = null;
 	 Connection connection = null;

    public FixtureRepo(){
        fixture = new Fixture();
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
        return "CREATE TABLE IF NOT EXISTS " + Fixture.TABLE  + "("
                + Fixture.KEY_FixturePrimary + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Fixture.KEY_TeamId + " TEXT,"  //MAY NEED TO DECLARE P/F KEYS HERE?
                + Fixture.KEY_FixtureId + " TEXT,"
                + Fixture.KEY_FixturePoints + " TEXT"
                + Fixture.KEY_Forward + " TEXT,"
                + Fixture.KEY_Back + " TEXT,"
                + Fixture.KEY_Player + " TEXT,"
                + Fixture.KEY_TriesScored + " TEXT,"
                + Fixture.KEY_TriesSucceeded + " TEXT,"
                + Fixture.KEY_Conversions + " TEXT,"
                + Fixture.KEY_ConversionsSucceeded + " TEXT,"
                + Fixture.KEY_ScrumsWon + " TEXT,"
                + Fixture.KEY_ScrumsLost + " TEXT,"
                + Fixture.KEY_MaulsWon + " TEXT,"
                + Fixture.KEY_MaulsLost + " TEXT,"
                + Fixture.KEY_LineOutsWon + " TEXT,"
                + Fixture.KEY_LineOutsLost + " TEXT,"
                + Fixture.KEY_DropGoals + " TEXT,"
                + Fixture.KEY_PenaltyKicks + " TEXT)";
    }


    public void insert(Fixture fixture) {
   	  String insertStatement = "INSERT INTO " + Fixture.TABLE
   			  + "(" + Fixture.KEY_TeamId
   			  + "," + Fixture.KEY_FixtureId
   			  + "," + Fixture.KEY_FixturePoints + ","
   			  + Fixture.KEY_Forward + ","
   			  + Fixture.KEY_Back + ","
   	        + Fixture.KEY_Player + ","
   	        + Fixture.KEY_TriesScored + ","
   	        + Fixture.KEY_TriesSucceeded + ","
   	        + Fixture.KEY_Conversions + ","
   	        + Fixture.KEY_ConversionsSucceeded + ","
   	        + Fixture.KEY_ScrumsWon + ","
              + Fixture.KEY_ScrumsLost + ","
              + Fixture.KEY_MaulsWon + ","
              + Fixture.KEY_MaulsLost + ","
              + Fixture.KEY_LineOutsWon + ","
              + Fixture.KEY_LineOutsLost + ","
              + Fixture.KEY_DropGoals + ","
              + Fixture.KEY_PenaltyKicks + ") "
   			  + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   	  
   	  try {
   		  	PreparedStatement prepStatement = connection.prepareStatement(insertStatement);
   		  	prepStatement.setString(1, fixture.getTeamId());;
   		  	prepStatement.setString(2, fixture.getFixtureId());;
   		  	prepStatement.setString(3, fixture.getFixturePoints());;   		  	
   		  	prepStatement.setString(4, fixture.getForward());;
   		  	prepStatement.setString(5, fixture.getBack());;
   		  	prepStatement.setString(6, fixture.getPlayer());;
   		  	prepStatement.setString(7, fixture.getTriesScored());;
   		  	prepStatement.setString(8, fixture.getTriesSucceeded());;
   		  	prepStatement.setString(9, fixture.getConversions());;
   		  	prepStatement.setString(10, fixture.getConversionsSucceeded());;
   		  	prepStatement.setString(11, fixture.getScrumsWon());;
   		  	prepStatement.setString(12, fixture.getScrumsLost());;
   		  	prepStatement.setString(13, fixture.getLineOutsWon());;
   		  	prepStatement.setString(14, fixture.getLineOutsLost());;
   		  	prepStatement.setString(15, fixture.getMaulsWon());;
   		  	prepStatement.setString(16, fixture.getMaulsLost());;
   		  	prepStatement.setString(17, fixture.getDropGoals());;
   		  	prepStatement.setString(18, fixture.getPenaltyKicks());;


   		  	prepStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	 
    }


}
