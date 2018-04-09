package Database.Repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Database.Schema.Feedback;
import Database.Schema.Fixture;

public class FeedbackRepo {

   private Feedback feedback;
   Statement statement = null;
	Connection connection = null;


   public FeedbackRepo(){

       feedback = new Feedback();

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
       return "CREATE TABLE " + Feedback.TABLE  + "("
               + Feedback.KEY_FeedbackPrimary + " INTEGER PRIMARY KEY AUTOINCREMENT,"
               + Feedback.KEY_MemberId + " TEXT,"
               + Feedback.KEY_FixtureId + " TEXT,"
               + Feedback.KEY_FeedbackText + " TEXT,"
               + Feedback.KEY_Attack + " TEXT,"
               + Feedback.KEY_Defence + " TEXT,"
               + Feedback.KEY_Effort + " TEXT,"
               + Feedback.KEY_Overall + " TEXT)";
   }


   public void insert(Feedback feedback) {

   	 String insertStatement = "INSERT INTO " + Feedback.TABLE
   			 + "(" + feedback.KEY_MemberId + ","
   			 + feedback.KEY_FixtureId + ","
   			 + feedback.KEY_FeedbackText + ","
   			 + feedback.KEY_Attack + ","
   			 + feedback.KEY_Defence + ","
   			 + feedback.KEY_Effort + ","
   			 + feedback.KEY_Overall + ") "
   			 + "VALUES(?,?,?,?,?,?,?)";

   	 try {
 		   connectToDB();
 		  	PreparedStatement prepStatement = connection.prepareStatement(insertStatement);
 		  	prepStatement.setString(1, feedback.getMemberId());
 		  	prepStatement.setString(2, feedback.getFixtureId());
 		  	prepStatement.setString(3, feedback.getFeedbackText());		  	
 		  	prepStatement.setString(4, feedback.getAttack());
 		  	prepStatement.setString(5, feedback.getDefence());
 		  	prepStatement.setString(6, feedback.getEffort());
 		  	prepStatement.setString(7, feedback.getOverall());

 		  	prepStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }

   public ArrayList<String> getAdvTable(int size){
   	ArrayList<String> feedbackLst = new ArrayList<String>();

  	 	String selectQuery = "SELECT * FROM " + Feedback.TABLE; 
  			 
  	 	try {
  	 		connectToDB();
			PreparedStatement prepStatement = connection.prepareStatement(selectQuery);
			ResultSet rs = prepStatement.executeQuery();
			if(!rs.next()){
				System.out.println("CODE:4900:NOFEEDBACK");
				feedbackLst.add("CODE:4900:NOFEEDBACK");
			}else{
				int counter = 0;
				do{
					System.out.println(String.valueOf(rs.getString("FeedbackPrimary")) + "4h4f" +
								rs.getString("MemberId") + " " +
								rs.getString("FixtureId") + " " +
								rs.getString("FeedbackText") + " " +
								rs.getString("Attack") + " " +
								rs.getString("Defence") + " " +
								rs.getString("Effort") + " " +
								rs.getString("Overall") + " ");
								
					if(counter >= size){
						String str = String.valueOf(rs.getString("FeedbackPrimary")) + "4h4f" +
								rs.getString("MemberId") + "4h4f" +
								rs.getString("FixtureId") + "4h4f" +
								rs.getString("FeedbackText") + "4h4f" +
								rs.getString("Attack") + "4h4f" +
								rs.getString("Defence") + "4h4f" +
								rs.getString("Effort") + "4h4f" +
								rs.getString("Overall") + "4h4f";
								
						
						System.out.println("feedback repo: " + str);
						feedbackLst.add(str);
					}
					counter++;
				}while(rs.next());
			}
			
			
  	 	} catch (SQLException e) {
			// TODO Auto-generated catch block
  	 		e.printStackTrace();
  	 	}
  	
  	 	closeConnection();
  	 	return feedbackLst;
   }


   public ArrayList<String> getBasicFeedback(String id, int size){
   	ArrayList<String> response = new ArrayList<String>();
   	
   	String selectQuery = "SELECT * FROM Feedback WHERE MemberId = '" + id + "'";
		
   	connectToDB();

   	
   	try {
			PreparedStatement prepStatement = connection.prepareStatement(selectQuery);
			ResultSet rs = prepStatement.executeQuery();
			if(!rs.next()){
				System.out.println("CODE:4900:NOFEEDBACK");
				response.add("CODE:4900:NOFEEDBACK");
			}else{
				int counter = 0;
				do{		
					if(counter >= size){
						String str = String.valueOf(rs.getString("FeedbackPrimary")) + "4h4f" +
								rs.getString("MemberId") + "4h4f" +
								rs.getString("FixtureId") + "4h4f" +
								rs.getString("FeedbackText") + "4h4f" +
								rs.getString("Attack") + "4h4f" +
								rs.getString("Defence") + "4h4f" +
								rs.getString("Effort") + "4h4f" +
								rs.getString("Overall") + "4h4f";
								
						
						System.out.println("feedback repo: " + str);
						response.add(str);
					}
					counter++;
				}while(rs.next());
			}
			
			
  	 	} catch (SQLException e) {
			// TODO Auto-generated catch block
  	 		e.printStackTrace();
  	 	}
  	
  	 	closeConnection();
  	 	return response;
   }
   
   
   
   
   
   
   
   
   
}
