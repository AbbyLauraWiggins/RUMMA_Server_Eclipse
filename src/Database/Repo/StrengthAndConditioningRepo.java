package Database.Repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
                + StrengthAndConditioning.KEY_SessionId  + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + StrengthAndConditioning.KEY_SessionDate + " TEXT,"
                + StrengthAndConditioning.KEY_SessionTime + " TEXT)";
    }


    public int insert(StrengthAndConditioning strengthAndConditioning) {
   	 int retVal = 0; //if update happens correctly, retVal = 1, else = 0
   	 String insertStatement = "INSERT INTO " + StrengthAndConditioning.TABLE
   			 + "("
             //+ StrengthAndConditioning.KEY_SessionId  + ","
             + StrengthAndConditioning.KEY_SessionDate + ","
             + StrengthAndConditioning.KEY_SessionTime + ") "
	  			 + "VALUES(?,?)";
	  	  
	  	  try {
	  		  	PreparedStatement prepStatement = connection.prepareStatement(insertStatement);
	  		  	//prepStatement.setString(1, strengthAndConditioning.getSessionID());
	  		  	prepStatement.setString(1, strengthAndConditioning.getSessionDate());
		  		prepStatement.setString(2, strengthAndConditioning.getSessionTime());
		  		
		  		retVal = prepStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  
   	 
       return retVal;
    }


    public ArrayList<String> getSessionIDs(){
   	 String selectQuery = "Select " + StrengthAndConditioning.KEY_SessionId 
   			 + " FROM " + StrengthAndConditioning.TABLE;
   	
   	 ArrayList<String> sessionIDs = new ArrayList<>();
   	 
   	 try {
  			PreparedStatement prepStatement = connection.prepareStatement(selectQuery);
  			ResultSet rs = prepStatement.executeQuery();
  			if(!rs.next()){
  				System.out.println("THERE ARE NO SESSIONS");
  	   		sessionIDs.add("CODE:4701:NOSESSIONS");
  			}else{
  				do{
  					sessionIDs.add(rs.getString("SessionId"));
  				}while(rs.next());
  			}
  			
  			
     	 } catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
     	 }
   	 
   	 return sessionIDs;
    }
    
    public ArrayList<String> getSC(int tableSize){
   	 String selectQuery = "Select * FROM " + StrengthAndConditioning.TABLE;
   	
   	 ArrayList<String> sc = new ArrayList<>();
   	 
   	 try {
  			PreparedStatement prepStatement = connection.prepareStatement(selectQuery);
  			ResultSet rs = prepStatement.executeQuery();
  			if(!rs.next()){
  				System.out.println("THERE ARE NO SESSIONS");
  	   		sc.add("CODE:4701:NOSESSIONS");
  			}else{
  				int counter = 0;
  				do{
  					if(counter >= tableSize){
  	  					String str = 
  	  							String.valueOf(rs.getString("SessionId")) + "4h4f" 
  	  							+ rs.getString("SessionDate") + "4h4f"
  	  							+ rs.getString("SessionTime");
  	  					
  	  					System.out.println("getSC: " + str);
  	  					
  	  					sc.add(str);
   				}
  				}while(rs.next());
  			}
  			
  			
     	 } catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
     	 }
   	 
   	 return sc;
    }
    
    public int getLastID(){
   	 String selectQuery = "SELECT MAX(SessionId) FROM " + StrengthAndConditioning.TABLE;
   	 int sessID = 0;
   	 try {
   			PreparedStatement prepStatement = connection.prepareStatement(selectQuery);
   			ResultSet rs = prepStatement.executeQuery();
   			if(!rs.next()){
   				System.out.println("THERE ARE NO SESSIONS");
   			}else{
   				do{
   					  sessID = rs.getInt("SessionId");
   					  System.out.println(sessID);
   				}while(rs.next());
   			}
   			
   			
      	 } catch (SQLException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
      	 }
    	 
    	 return sessID;
    }

}
