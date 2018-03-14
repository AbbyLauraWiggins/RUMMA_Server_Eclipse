package Database.Repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import Database.Schema.Fixture;
import Database.Schema.Member;

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
        return "CREATE TABLE " + Member.TABLE + "("
                + Member.KEY_MemberId + " TEXT PRIMARY KEY,"
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
   	 String insertStatement = "INSERT INTO " + Member.TABLE
   			 + "("
             + Member.KEY_MemberId + ","
             + Member.KEY_Name + ","
             + Member.KEY_Email + ","
             + Member.KEY_Password + ","
             + Member.KEY_DOB + ","
             + Member.KEY_Positions + ","
             + Member.KEY_Responsibilities + ","
             + Member.KEY_TeamId + ","
             + Member.KEY_Permissions + ") "
	  			  + "VALUES(?,?,?,?,?,?,?,?,?)";
	  	  
	  	  try {
	  		  	PreparedStatement prepStatement = connection.prepareStatement(insertStatement);
	  		  	prepStatement.setString(1, member.getMemberId());
	  		  	prepStatement.setString(2, member.getName());
		  		prepStatement.setString(3, member.getEmail());
		  		prepStatement.setString(4, member.getPassword());
		  		prepStatement.setString(5, member.getDOB());
		  		prepStatement.setString(6, member.getPositions());
		  		prepStatement.setString(7, member.getResponsibilities());
		  		prepStatement.setString(8, member.getTeamId());
		  		prepStatement.setString(9, member.getPermissions());
		  		  	prepStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   	 
   	 
   	 
   	 
       return 1;
    }


    public void delete() {
       
    }
}
