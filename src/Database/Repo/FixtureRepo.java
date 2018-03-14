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
                + Fixture.KEY_FixturePoints + " TEXT)";
    }


    public void insert(Fixture fixture) {
   	  String insertStatement = "INSERT INTO " + Fixture.TABLE
   			  + "(" + Fixture.KEY_TeamId
   			  + "," + Fixture.KEY_FixtureId
   			  + "," + Fixture.KEY_FixturePoints + ") "
   			  + "VALUES(?,?,?)";
   	  
   	  try {
   		  	PreparedStatement prepStatement = connection.prepareStatement(insertStatement);
   		  	prepStatement.setString(1, fixture.getTeamId());;
   		  	prepStatement.setString(2, fixture.getFixtureId());;
   		  	prepStatement.setString(3, fixture.getFixturePoints());;
   		  	prepStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	 
    }


}
