package Database.Repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import Database.Schema.Fixture;
import Database.Schema.Session;

/**
 * Created by abbylaura on 10/03/2018.
 */

public class SessionRepo {

    private Session session;
    Statement statement = null;
 	 Connection connection = null;

    public SessionRepo(){
        session = new Session();
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
        return "CREATE TABLE IF NOT EXISTS " + Session.TABLE + "("
                //+ Session.KEY_AUTO + " TEXT PRIMARY KEY,"
                + Session.KEY_MemberID + " TEXT,"
                + Session.KEY_SessionID + " TEXT,"
                + Session.KEY_Deadlifts + " TEXT,"
                + Session.KEY_DeadliftJumps + " TEXT,"
                + Session.KEY_BackSquat + " TEXT,"
                + Session.KEY_BackSquatJumps + " TEXT,"
                + Session.KEY_GobletSquat + " TEXT,"
                + Session.KEY_BenchPress + " TEXT,"
                + Session.KEY_MilitaryPress + " TEXT,"
                + Session.KEY_SupineRow + " TEXT,"
                + Session.KEY_ChinUps + " TEXT,"
                + Session.KEY_Trunk + " TEXT,"
                + Session.KEY_RDL + " TEXT,"
                + Session.KEY_SplitSquat + " TEXT,"
                + Session.KEY_FourWayArms + " TEXT)";

    }

    public int insert(Session session) {
   	 int retVal = 0;
   	 
   	 String insertStatement = "INSERT INTO " + Session.TABLE
  			  + "(" + Session.KEY_MemberID + ","
           + Session.KEY_SessionID + ","
           + Session.KEY_Deadlifts + ","
           + Session.KEY_DeadliftJumps + ","
           + Session.KEY_BackSquat + ","
           + Session.KEY_BackSquatJumps + ","
           + Session.KEY_GobletSquat + ","
           + Session.KEY_BenchPress + ","
           + Session.KEY_MilitaryPress + ","
           + Session.KEY_SupineRow + ","
           + Session.KEY_ChinUps + ","
           + Session.KEY_Trunk + ","
           + Session.KEY_RDL + ","
           + Session.KEY_SplitSquat + ","
           + Session.KEY_FourWayArms + ") "
  			  + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
  	  
  	  try {
  		  	PreparedStatement prepStatement = connection.prepareStatement(insertStatement);
  		  	prepStatement.setString(1, session.getMemberID());
	  		prepStatement.setString(2, session.getSessionID());
	  		prepStatement.setString(3, session.getDeadlifts());
	  		prepStatement.setString(4, session.getDeadliftJumps());
	  		prepStatement.setString(5, session.getBackSquat());
	  		prepStatement.setString(6, session.getBackSquatJumps());
	  		prepStatement.setString(7, session.getGobletSquat());
	  		prepStatement.setString(8, session.getBenchPress());
	  		prepStatement.setString(9, session.getMilitaryPress());
	  		prepStatement.setString(10, session.getSupineRow());
	  		prepStatement.setString(11, session.getChinUps());
	  		prepStatement.setString(12, session.getTrunk());
	  		prepStatement.setString(13, session.getRdl());
	  		prepStatement.setString(14, session.getSplitSquat());
	  		prepStatement.setString(15, session.getFourWayArms());
  		  	retVal = prepStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       return retVal;
    }

   
}
