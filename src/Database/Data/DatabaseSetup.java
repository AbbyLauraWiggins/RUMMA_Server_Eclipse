package Database.Data;
import java.sql.*;
import Database.Repo.FixtureRepo;
import Database.Repo.KPIRepo;
import Database.Repo.MemberRepo;
import Database.Repo.NoticeRepo;
import Database.Repo.SessionRepo;
import Database.Repo.StrengthAndConditioningRepo;
import Database.Repo.TeamFixturesRepo;
import Database.Repo.TeamRepo;
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

	public DatabaseSetup() {
		String connectString = "jdbc:sqlite:rummaServerDB.db";
		connectToDB(connectString);

	}

	public void connectToDB(String connectString) {

		try {
			connection = DriverManager.getConnection(connectString);

			System.out.println("Connection established.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		if (connection != null) {
			System.out.println("Database accessed!");
		} else {
			System.out.println("Failed to make connection");
			System.exit(1);
		}

		deleteTables();
		createTables();
		insertTables();
		
		try {
			connection.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}
	
	private void createTables(){
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
			System.out.println("Resident table created in database");
		} catch (SQLException ex1) {
			ex1.printStackTrace();
		}
	}
	
	private void insertTables(){
			
	}
	
	private void deleteTables(){
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
