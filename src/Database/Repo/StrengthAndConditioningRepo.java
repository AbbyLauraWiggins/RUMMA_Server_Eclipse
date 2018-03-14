package Database.Repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import Database.Schema.Member;
import Database.Schema.StrengthAndConditioning;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class StrengthAndConditioningRepo {
    private StrengthAndConditioning strengthAndConditioning;
    Statement statement = null;
 	 Connection connection = null;

    public StrengthAndConditioningRepo(){
        strengthAndConditioning = new StrengthAndConditioning();
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
        return "CREATE TABLE IF NOT EXISTS " + StrengthAndConditioning.TABLE  + "("
                + StrengthAndConditioning.KEY_SessionId  + "   PRIMARY KEY,"
                + StrengthAndConditioning.KEY_SessionDate + " TEXT,"
                + StrengthAndConditioning.KEY_SessionTime + " TEXT)";
    }


    public int insert(StrengthAndConditioning strengthAndConditioning) {
   	 int retVal = 0; //if update happens correctly, retVal = 1, else = 0
   	 String insertStatement = "INSERT INTO " + StrengthAndConditioning.TABLE
   			 + "("
             + StrengthAndConditioning.KEY_SessionId  + ","
             + StrengthAndConditioning.KEY_SessionDate + ","
             + StrengthAndConditioning.KEY_SessionTime + ") "
	  			 + "VALUES(?,?,?)";
	  	  
	  	  try {
	  		  	PreparedStatement prepStatement = connection.prepareStatement(insertStatement);
	  		  	prepStatement.setString(1, strengthAndConditioning.getSessionID());
	  		  	prepStatement.setString(2, strengthAndConditioning.getSessionDate());
		  		prepStatement.setString(3, strengthAndConditioning.getSessionTime());
		  		
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
