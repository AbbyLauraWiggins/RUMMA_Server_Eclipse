package Database.Data;
import java.sql.*;
import Database.Repo.FeedbackRepo;
import Database.Repo.FixtureRepo;
import Database.Repo.KPIRepo;
import Database.Repo.MemberRepo;
import Database.Repo.NoticeRepo;
import Database.Repo.SessionRepo;
import Database.Repo.StrengthAndConditioningRepo;
import Database.Repo.TeamFixturesRepo;
import Database.Repo.TeamRepo;
import Database.Schema.Feedback;
import Database.Schema.Fixture;
import Database.Schema.KPI;
import Database.Schema.Member;
import Database.Schema.Notice;
import Database.Schema.Session;
import Database.Schema.StrengthAndConditioning;
import Database.Schema.Team;
import Database.Schema.TeamFixtures;

public class DatabaseSetup {
	Statement statement = null;
	Connection connection = null;
	String connectString;

	public DatabaseSetup() {
		connectString = "jdbc:sqlite:rummaServerDB.db";
	}

	public void connectToDB() {

		try {
			connection = DriverManager.getConnection(connectString);

			System.out.println("Connection established.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		if (connection != null) {
			System.out.println("Database accessed");
		} else {
			System.out.println("Failed to make connection");
			System.exit(1);
		}

	}
	
	public void closeDatabase(){
		try {
			connection.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void createTables(){
		try {
			statement = connection.createStatement();
			statement.executeUpdate(FixtureRepo.createTable());
			statement.executeUpdate(MemberRepo.createTable());
			statement.executeUpdate(SessionRepo.createTable());
			statement.executeUpdate(StrengthAndConditioningRepo.createTable());
			statement.executeUpdate(TeamRepo.createTable());
			statement.executeUpdate(TeamFixturesRepo.createTable());
			statement.executeUpdate(NoticeRepo.createTable());
			statement.executeUpdate(KPIRepo.createtable());
			statement.executeUpdate(FeedbackRepo.createTable());
		} catch (SQLException ex1) {
			ex1.printStackTrace();
		}
	}
	
	
	
	public void deleteTables(){
		try {
			statement = connection.createStatement();
			statement.executeUpdate("DROP TABLE IF EXISTS " + Fixture.TABLE);
			statement.executeUpdate("DROP TABLE IF EXISTS " + Member.TABLE);
			statement.executeUpdate("DROP TABLE IF EXISTS " + Session.TABLE);
			statement.executeUpdate("DROP TABLE IF EXISTS " + StrengthAndConditioning.TABLE);
			statement.executeUpdate("DROP TABLE IF EXISTS " + Team.TABLE);
			statement.executeUpdate("DROP TABLE IF EXISTS " + TeamFixtures.TABLE);
			statement.executeUpdate("DROP TABLE IF EXISTS " + Notice.TABLE);
			statement.executeUpdate("DROP TABLE IF EXISTS " + KPI.TABLE);
			statement.executeUpdate("DROP TABLE IF EXISTS " + Feedback.TABLE);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
