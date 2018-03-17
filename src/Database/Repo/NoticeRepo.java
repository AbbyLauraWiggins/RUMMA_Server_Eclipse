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
     
     public void closeConnection(){  //TODO add in fixtureRepo.closeConnection() where used elsewhere in code
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
   	  String noticeID = generateNewID();
   	  
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

   
    public ArrayList<String> getNotices(){
   	 
   	 ArrayList<String> notices = new ArrayList<String>();
   	 
   	 
   	 String getLast = "SELECT * FROM " + Notice.TABLE; 
   			 //" INNER JOIN (" +
   			 //"SELECT " + Notice.KEY_MemberId + ", " + Notice.KEY_Date + ", MAX(" + Notice.KEY_Date + ") " +
   			 //"AS maxDate FROM " + Notice.TABLE + " GROUP BY " + Notice.KEY_MemberId + ") " + " maxSelect " +
   			 //"ON " + Notice.KEY_MemberId + " = " + "maxSelect.MemberId";
 
   	 
   	 try {
			PreparedStatement prepStatement = connection.prepareStatement(getLast);
			ResultSet rs = prepStatement.executeQuery();
			if(!rs.next()){
				System.out.println("NoticeRepo: getLastNotice prepStatement returned null");
				return null;
			}else{
				do{
					String str = (String.valueOf(rs.getString("NoticeId")) + "4h4f" + rs.getString("MemberId") + "4h4f" + rs.getString("Contents") + "4h4f" + rs.getString("Date"));
					System.out.println("notice repo: " + str);
					notices.add(str);
				}while(rs.next());
			}
			
			
   	 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
   	 }
   	 
   	 return notices;
    }

    /*
     * @returns result - an ArrayList of type Notice which represents all notices
     * 
     */
    public ArrayList<Notice> getTableObject(){
       ArrayList<Notice> result = new ArrayList<>();

       String selectQuery = " SELECT * FROM " + Notice.TABLE;

       try {
 			PreparedStatement prepStatement = connection.prepareStatement(selectQuery);
 			ResultSet rs = prepStatement.executeQuery();
 			if(!rs.next()){
 				System.out.println("NoticeRepo: getLastNotice prepStatement returned null");
 				return null;
 			}else{
 				while(rs.next()){
               Notice notice = new Notice();
 					notice.setMemberId(rs.getString("MemberId"));
 					notice.setContents(rs.getString("Contents"));
 					notice.setDate(rs.getString("Date"));
 					notice.setNoticeId(rs.getString("NoticeId"));
 					result.add(notice);
 				}
 			}
 			
 			
    	 } catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
    	 }
    	 
    	
       return result;
   }

    private String generateNewID(){
   	 String selectQuery = "SELECT NoticeId FROM Notice WHERE NoticeId=(SELECT max(NoticeId) FROM Notice)";
   	 String lastID = "0";
   	 try {
  			PreparedStatement prepStatement = connection.prepareStatement(selectQuery);
  			ResultSet rs = prepStatement.executeQuery();
  			if(!rs.next()){
  				System.out.println("NoticeRepo: prepStatement returned null");
  				return "1";
  			}else{
  				while(rs.next()){
               lastID = rs.getString("NoticeID");
  				}
  			}
  		
     	 } catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
     	 }
   	 
   	 return String.valueOf(Integer.parseInt(lastID) + 1);
    }
    

}
