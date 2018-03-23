package Database.Repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Database.Schema.Fixture;
import Database.Schema.KPI;
import Database.Schema.Session;

/**
 * Created by abbylaura on 06/03/2018.
 */

public class KPIRepo {
	
    private KPI kpi;
    Statement statement = null;
 	 Connection connection = null;

    public KPIRepo(){
        kpi = new KPI();
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

    public static String createtable(){
        return "CREATE TABLE IF NOT EXISTS " + KPI.TABLE + "("
                + KPI.KEY_KPIPrimary + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KPI.KEY_MemberID + " TEXT, "
                + KPI.KEY_FixtureID + " TEXT,"
                + KPI.KEY_sTackles + " TEXT,"
                + KPI.KEY_uTackles + " TEXT,"
                + KPI.KEY_sCarries + " TEXT,"
                + KPI.KEY_uCarries + " TEXT,"
                + KPI.KEY_sPasses + " TEXT,"
                + KPI.KEY_uPasses + " TEXT,"
                + KPI.KEY_HandlingErrors + " TEXT,"
                + KPI.KEY_Penalties + " TEXT,"
                + KPI.KEY_YellowCards + " TEXT,"
                + KPI.KEY_TriesScored + " TEXT,"
                + KPI.KEY_TurnoversWon + " TEXT,"
                + KPI.KEY_sThrowIns + " TEXT,"
                + KPI.KEY_uThrowIns + " TEXT,"
                + KPI.KEY_sLineOutTakes + " TEXT,"
                + KPI.KEY_uLineOutTakes + " TEXT,"
                + KPI.KEY_sKicks + " TEXT,"
                + KPI.KEY_uKicks + " TEXT)";
    }

    public void insert(KPI kpi){
	     String insertStatement = "INSERT INTO " + KPI.TABLE
	   		  + " ("
              + KPI.KEY_MemberID + ","
              + KPI.KEY_FixtureID + ","
              + KPI.KEY_sTackles + ","
              + KPI.KEY_uTackles + ","
              + KPI.KEY_sCarries + ","
              + KPI.KEY_uCarries + ","
              + KPI.KEY_sPasses + ","
              + KPI.KEY_uPasses + ","
              + KPI.KEY_HandlingErrors + ","
              + KPI.KEY_Penalties + ","
              + KPI.KEY_YellowCards + ","
              + KPI.KEY_TriesScored + ","
              + KPI.KEY_TurnoversWon + ","
              + KPI.KEY_sThrowIns + ","
              + KPI.KEY_uThrowIns + ","
              + KPI.KEY_sLineOutTakes + ","
              + KPI.KEY_uLineOutTakes + ","
              + KPI.KEY_sKicks + ","
              + KPI.KEY_uKicks + ") " 
	  			  + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	  	  
	  	  try {
	  		  	PreparedStatement prepStatement = connection.prepareStatement(insertStatement);
		  		prepStatement.setString(1, kpi.getMemberID());
		  		prepStatement.setString(2, kpi.getFixtureID());
		  		prepStatement.setString(3, kpi.getsTackles());
		  		prepStatement.setString(4, kpi.getuTackles());
		  		prepStatement.setString(5, kpi.getsCarries());
		  		prepStatement.setString(6, kpi.getuCarries());
		  		prepStatement.setString(7, kpi.getsPasses());
		  		prepStatement.setString(8, kpi.getuPasses());
		  		prepStatement.setString(9, kpi.getHandlingErrors());
		  		prepStatement.setString(10, kpi.getPenalties());
		  		prepStatement.setString(11, kpi.getYellowCards());
		  		prepStatement.setString(12, kpi.getTriesScored());
		  		prepStatement.setString(13, kpi.getTurnoversWon());
		  		prepStatement.setString(14, kpi.getsThrowIns());
		  		prepStatement.setString(15, kpi.getuThrowIns());
		  		prepStatement.setString(16, kpi.getsLineOutTakes());
		  		prepStatement.setString(17, kpi.getuLineOutTakes());
		  		prepStatement.setString(18, kpi.getsKicks());
		  		prepStatement.setString(19, kpi.getuKicks());
	  		  	prepStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    
    public ArrayList<String> getMyKPIs(String myID){
   	 ArrayList<String> kpis = new ArrayList<String>();

   	 String selectQuery = "SELECT * FROM " + KPI.TABLE
   			 + " WHERE MemberId = '" + myID + "'"; 
   			 
   	 try {
			PreparedStatement prepStatement = connection.prepareStatement(selectQuery);
			ResultSet rs = prepStatement.executeQuery();
			if(!rs.next()){
				System.out.println("CODE:4809:NOKPIS");
	   		kpis.add("CODE:4809:NOKPIS");
			}else{
				do{
					String str = 
							String.valueOf(rs.getString("KPIPrimary")) + "4h4f"
					 		+ rs.getString("MemberId") + "4h4f"
					 		+ rs.getString("FixtureId") + "4h4f"
					 		+ rs.getString("sTackles") + "4h4f"
							+ rs.getString("uTackles") + "4h4f"
							+ rs.getString("sCarries") + "4h4f"
							+ rs.getString("uCarries") + "4h4f"
							+ rs.getString("sPasses") + "4h4f"
							+ rs.getString("uPasses") + "4h4f"
							+ rs.getString("HandlingErrors") + "4h4f"
							+ rs.getString("Penalties") + "4h4f"
							+ rs.getString("YellowCards") + "4h4f"
							+ rs.getString("TriesScored") + "4h4f"
							+ rs.getString("TurnoversWon") + "4h4f"		
							+ rs.getString("sThrowIns") + "4h4f"
							+ rs.getString("uThrowIns") + "4h4f"
							+ rs.getString("sLineOutTakes") + "4h4f"
							+ rs.getString("uLineOutTakes") + "4h4f"
							+ rs.getString("sKicks") + "4h4f"
							+ rs.getString("uKicks") + "4h4f";
					System.out.println("kpi repo: " + str);
					kpis.add(str);
					
				}while(rs.next());
			}
			
			
   	 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
   	 }
   	
   	 closeConnection();
   	 return kpis;
    }

    public ArrayList<String> getAllKPIs(int tableSize){
   	 ArrayList<String> kpis = new ArrayList<String>();

   	 String selectQuery = "SELECT * FROM " + KPI.TABLE; 
   			 
   	 try {
			PreparedStatement prepStatement = connection.prepareStatement(selectQuery);
			ResultSet rs = prepStatement.executeQuery();
			if(!rs.next()){
				System.out.println("CODE:4809:NOKPIS");
	   		kpis.add("CODE:4809:NOKPIS");
			}else{
				int counter = 0;
				do{
					if(counter >= tableSize){
						String str = 
								String.valueOf(rs.getString("KPIPrimary")) + "4h4f"
						 		+ rs.getString("MemberId") + "4h4f"
						 		+ rs.getString("FixtureId") + "4h4f"
						 		+ rs.getString("sTackles") + "4h4f"
								+ rs.getString("uTackles") + "4h4f"
								+ rs.getString("sCarries") + "4h4f"
								+ rs.getString("uCarries") + "4h4f"
								+ rs.getString("sPasses") + "4h4f"
								+ rs.getString("uPasses") + "4h4f"
								+ rs.getString("HandlingErrors") + "4h4f"
								+ rs.getString("Penalties") + "4h4f"
								+ rs.getString("YellowCards") + "4h4f"
								+ rs.getString("TriesScored") + "4h4f"
								+ rs.getString("TurnoversWon") + "4h4f"		
								+ rs.getString("sThrowIns") + "4h4f"
								+ rs.getString("uThrowIns") + "4h4f"
								+ rs.getString("sLineOutTakes") + "4h4f"
								+ rs.getString("uLineOutTakes") + "4h4f"
								+ rs.getString("sKicks") + "4h4f"
								+ rs.getString("uKicks") + "4h4f";
						//System.out.println("kpi repo: " + str);
						kpis.add(str);
					}
					counter ++;
					
				}while(rs.next());
			}
			
			
   	 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
   	 }
   	
   	 closeConnection();
   	 return kpis;
    }

}
