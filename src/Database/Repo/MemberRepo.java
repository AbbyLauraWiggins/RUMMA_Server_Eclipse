package Database.Repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Database.Schema.Fixture;
import Database.Schema.Member;
import Database.Schema.Notice;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class MemberRepo {

    private Member member;
    Statement statement = null;
 	 Connection connection = null;


    public MemberRepo() {
        member = new Member();
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

    public static String createTable() {
        return "CREATE TABLE IF NOT EXISTS " + Member.TABLE + "("
                + Member.KEY_MemberId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Member.KEY_Name + " TEXT,"
                + Member.KEY_Email + " TEXT,"
                + Member.KEY_Password + " TEXT,"
                + Member.KEY_DOB + " TEXT,"
                + Member.KEY_Positions + " TEXT,"
                + Member.KEY_Responsibilities + " TEXT,"
                + Member.KEY_TeamId + " TEXT,"
                + Member.KEY_Permissions + " TEXT)";
    }

    public int insert(Member member) {
   	 int retVal = 0; //if update happens correctly, retVal = 1, else = 0
   	 String insertStatement = "INSERT INTO " + Member.TABLE
   			 + "("
             //+ Member.KEY_MemberId + ","
             + Member.KEY_Name + ","
             + Member.KEY_Email + ","
             + Member.KEY_Password + ","
             + Member.KEY_DOB + ","
             + Member.KEY_Positions + ","
             + Member.KEY_Responsibilities + ","
             + Member.KEY_TeamId + ","
             + Member.KEY_Permissions + ") "
	  			  + "VALUES(?,?,?,?,?,?,?,?)";
	  	  
	  	  try {
	  		  	PreparedStatement prepStatement = connection.prepareStatement(insertStatement);
	  		  	//prepStatement.setString(1, member.getMemberId());
	  		  	prepStatement.setString(1, member.getName());
		  		prepStatement.setString(2, member.getEmail());
		  		prepStatement.setString(3, member.getPassword());
		  		prepStatement.setString(4, member.getDOB());
		  		prepStatement.setString(5, member.getPositions());
		  		prepStatement.setString(6, member.getResponsibilities());
		  		prepStatement.setString(7, member.getTeamId());
		  		prepStatement.setString(8, member.getPermissions());
		  		retVal = prepStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  
   	 
       return retVal;
    }

    public ArrayList<String> getMembers(){
   	 System.out.println("in get members");
  	  
   	 ArrayList<String> members = new ArrayList<>();
   	 
   	 String selectQuery = "SELECT * FROM Member";
   	 try {
 			PreparedStatement prepStatement = connection.prepareStatement(selectQuery);
 			ResultSet rs = prepStatement.executeQuery();
 			if(!rs.next()){
 				System.out.println("THERE ARE NO MEMBERS");
 	   		members.add("CODE:4699:NOMEMBERS");
 			}else{
 				do{
 					String str = 
 							String.valueOf(rs.getString("MemberId")) + "4h4f" +
 							rs.getString("Name") + "4h4f" +
 							rs.getString("Email") + "4h4f" +
 							rs.getString("Password") + "4h4f" +
 							rs.getString("DOB") + "4h4f" +
 							rs.getString("Positions") + "4h4f" +
 							rs.getString("Responsibilities") + "4h4f" +
 							rs.getString("TeamId") + "4h4f" +
 							rs.getString("Permissions");
 					
 					System.out.println(str);
 					
 					members.add(str);
 					
 				}while(rs.next());
 			}
 			
 			
    	 } catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
    	 }
    	
    	 
    	 return members;
    }

    public ArrayList<String> getMemberIDs(){
   	 String selectQuery = "Select " + Member.KEY_MemberId + " FROM " + Member.TABLE;
   	 ArrayList<String> memberIDs = new ArrayList<>();
   	 
   	 try {
  			PreparedStatement prepStatement = connection.prepareStatement(selectQuery);
  			ResultSet rs = prepStatement.executeQuery();
  			if(!rs.next()){
  				System.out.println("THERE ARE NO MEMBERS");
  	   		memberIDs.add("CODE:4699:NOMEMBERS");
  			}else{
  				do{
  					memberIDs.add(rs.getString("MemberId"));
  				}while(rs.next());
  			}
  			
  			
     	 } catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
     	 }
   	 
   	 return memberIDs;
    }
}
