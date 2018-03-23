package Database.Repo;

import java.sql.Connection;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Database.Schema.Fixture;
import Database.Schema.Notice;


/**
 * Created by abbylaura on 02/03/2018.
 */

public class NoticeRepo {

    private Notice notice;
    Statement statement = null;
 	 Connection connection = null;

    public NoticeRepo(){
        notice = new Notice();
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
     
     public void closeConnection(){  
    	 try {
   			connection.close();
   		} catch (SQLException ex) {
   			ex.printStackTrace();
   		}
     }
    

    public static String createTable() {
        return "CREATE TABLE IF NOT EXISTS " + Notice.TABLE + "("
                + Notice.KEY_NoticeId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Notice.KEY_MemberId + " TEXT," //FOREIGN KEY FROM MEMBERID
                + Notice.KEY_Contents + " TEXT,"
                + Notice.KEY_Date + " TEXT)";
    }

    public void insert(Notice notice) {
   	  System.out.println("in insert notice");
   	  
   	  String insertStatement = "INSERT INTO " + Notice.TABLE
   			  + "("
   			  //+ Notice.KEY_NoticeId + ","
              + Notice.KEY_MemberId + "," 
              + Notice.KEY_Contents + ","
              + Notice.KEY_Date + ") "
	  			  + "VALUES(?,?,?)";
   	  	  	  
	  	  try {
	  		  	PreparedStatement prepStatement = connection.prepareStatement(insertStatement);
	  		  	//prepStatement.setString(1, noticeID);;
	  		  	prepStatement.setString(1, notice.getMemberId());;
	  		  	prepStatement.setString(2, notice.getContents());;
	  		  	prepStatement.setString(3, notice.getDate());;
	  		  	prepStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }

   
    public ArrayList<String> getNotices(int tableSize){
   	 
   	 ArrayList<String> notices = new ArrayList<String>();
   	 
   	 
   	 String getLast = "SELECT * FROM " + Notice.TABLE; 
   			 
   	 try {
   		connectToDB();
			PreparedStatement prepStatement = connection.prepareStatement(getLast);
			ResultSet rs = prepStatement.executeQuery();
			if(!rs.next()){
				System.out.println("CODE:4698:EMPTYBUFFER");
	   		notices.add("CODE:4698:EMPTYBUFFER");
			}else{
				int counter = 0;
				do{
					if(counter >= tableSize){
						String str = (String.valueOf(rs.getString("NoticeId")) + "4h4f" + rs.getString("MemberId") + "4h4f" + rs.getString("Contents") + "4h4f" + rs.getString("Date"));
						System.out.println("notice repo: " + str);
						notices.add(str);
					}
					counter++;
				}while(rs.next());
			}
			
			
   	 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
   	 }
   	
   	 closeConnection();
   	 
   	 return notices;
    }

    

}
