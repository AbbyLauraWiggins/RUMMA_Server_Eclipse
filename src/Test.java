import java.util.ArrayList;
import java.util.Random;
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
import Database.Schema.Session;
import Database.Schema.StrengthAndConditioning;
import Database.Schema.Team;
import Database.Schema.TeamFixtures;

public class Test {

	public Test(){
		super();
		
	}
	
	public void insertTestData(){
		insertTeams();
		insertMembers();
		insertTeamFixtures();
		insertSC();
		insertSession();
		insertFixtures();
	}
	
	private void insertMembers(){
		 MemberRepo memberRepo = new MemberRepo();
		 Member member = new Member();
		 
		 TeamRepo teamRepo = new TeamRepo();		 
		 
		 //SIMPLY TEST TEAM ID
		 //IN PROGRAM, THIS WILL COME FROM A SPINNER SELECTION
		 String teamName = teamRepo.getTeamId("UOBWRFC"); 
		 
       member.setName("Michelle Ezigbo");
       member.setEmail("m.ezigbo@outlook.com");
       member.setPassword("0001");  //for testing, set password to memberID
       member.setDOB("14/12/1997");
       member.setPositions("5, 6, 8");
       member.setResponsibilities("None");
       member.setTeamId(teamName);
       member.setPermissions("BASIC");
       memberRepo.insert(member);

       member.setName("Beth Stacey");
       member.setEmail("b.Stacey@outlook.com");
       member.setPassword("0002");  //for testing, set password to memberID
       member.setDOB("22/02/1997");
       member.setPositions("6, 7");
       member.setResponsibilities("None");
       member.setTeamId(teamName);
       member.setPermissions("BASIC");
       memberRepo.insert(member);

       member.setName("Lizzie Nuttall");
       member.setEmail("l.Nuttall@outlook.com");
       member.setPassword("0003");  //for testing, set password to memberID
       member.setDOB("10/08/1995");
       member.setPositions("2");
       member.setResponsibilities("Team Captain");
       member.setTeamId(teamName);
       member.setPermissions("LEADER");
       memberRepo.insert(member);

       member.setName("Abbygayle Wiggins");
       member.setEmail("a.Wiggins@outlook.com");
       member.setPassword("0004");  //for testing, set password to memberID
       member.setDOB("20/12/1996");
       member.setPositions("1");
       member.setResponsibilities("Development Officer");
       member.setTeamId(teamName);
       member.setPermissions("LEADER");
       memberRepo.insert(member);

       member.setName("Jessica Ball");
       member.setEmail("j.ball@outlook.com");
       member.setPassword("0005");  //for testing, set password to memberID
       member.setDOB("01/02/1997");
       member.setPositions("9, 10");
       member.setResponsibilities("None");
       member.setTeamId(teamName);
       member.setPermissions("BASIC");
       memberRepo.insert(member);

       member.setName("Autumn Rose");
       member.setEmail("a.rose@outlook.com");
       member.setPassword("0006");  //for testing, set password to memberID
       member.setDOB("01/01/1996");
       member.setPositions("4, 5");
       member.setResponsibilities("Social Media Sec");
       member.setTeamId(teamName);
       member.setPermissions("ADMIN");
       memberRepo.insert(member);

       member.setName("Lou Rooker");
       member.setEmail("l.Rooker@outlook.com");
       member.setPassword("0007");  //for testing, set password to memberID
       member.setDOB("18/05/1998");
       member.setPositions("1, 3");
       member.setResponsibilities("None");
       member.setTeamId(teamName);
       member.setPermissions("BASIC");
       memberRepo.insert(member);

       member.setName("Kathryn Hyland");
       member.setEmail("kathyh@outlook.com");
       member.setPassword("0008");  //for testing, set password to memberID
       member.setDOB("10/12/1997");
       member.setPositions("4, 5");
       member.setResponsibilities("None");
       member.setTeamId(teamName);
       member.setPermissions("BASIC");
       memberRepo.insert(member);

       member.setName("Louise Gavin");
       member.setEmail("lou.gavin@outlook.com");
       member.setPassword("0009");  //for testing, set password to memberID
       member.setDOB("19/07/1997");
       member.setPositions("2");
       member.setResponsibilities("None");
       member.setTeamId(teamName);
       member.setPermissions("BASIC");
       memberRepo.insert(member);

       member.setName("Katie Purcell");
       member.setEmail("kp@outlook.com");
       member.setPassword("0010");  //for testing, set password to memberID
       member.setDOB("17/09/1997");
       member.setPositions("6, 7");
       member.setResponsibilities("Club Captain");
       member.setTeamId(teamName);
       member.setPermissions("ADMIN");
       memberRepo.insert(member);
       memberRepo.closeConnection();
	}

	private void insertTeams(){
		TeamRepo teamRepo = new TeamRepo();
		Team team = new Team();
		
      team.setTeamName("UOBWRFC");
      team.setTeamLocation("B29 2TT");
      team.setTeamCurPoints("149");
      teamRepo.insert(team);

      team.setTeamName("LURFC");
      team.setTeamLocation("LB1 56T");
      team.setTeamCurPoints("131");
      teamRepo.insert(team);

      team.setTeamName("AURFC");
      team.setTeamLocation("B1 8LM");
      team.setTeamCurPoints("109");
      teamRepo.insert(team);

      team.setTeamName("OUWRFC");
      team.setTeamLocation("B1 8LM");
      team.setTeamCurPoints("109");
      teamRepo.insert(team);
      
      teamRepo.closeConnection();
	}

	private void insertTeamFixtures(){
		TeamFixturesRepo teamFixturesRepo = new TeamFixturesRepo();
		TeamFixtures tf = new TeamFixtures();
		
      //League calendar is (4 teams, each play each other home and away, so 12 fixtures, as follows
      //1: UOBWRFC vs LURFC   10/01/2018
      //2: LURFC vs UOBWRFC   17/01/2018
      //3: AURFC vs LURFC     24/01/2018
      //4: OUWRFC vs UOBWRFC  31/01/2018
      //5: UOBWRFC vs AURFC   7/02/2018
      //6: LURFC vs AURFC     14/02/2018
      //7: AURFC vs UOBWRFC   21/02/2018
      //8: OUWRFC vs LURFC    28/02/2018
      //9: OUWRFC vs AURFC  07/03/2018
      //10: LURFC vs OUWRFC   14/03/2018
      //11: AURFC vs OUWRFC   21/03/2017
      //12: UOBWRFC vs OUWRFC   28/03/2018
		TeamRepo teamRepo = new TeamRepo();
		String uID = teamRepo.getTeamId("UOBWRFC");
		String lID = teamRepo.getTeamId("LURFC");
		String aID = teamRepo.getTeamId("AURFC");
		String oID = teamRepo.getTeamId("OUWRFC");

		
      tf.setFixtureDate("10/01/2018");
      tf.setFixtureLocation("B29 2TT");
      tf.setHomeTeam(uID);
      tf.setAwayTeam(lID);
      teamFixturesRepo.insert(tf);

      tf.setFixtureDate("17/01/2018");
      tf.setFixtureLocation("LE12 8WD");
      tf.setHomeTeam(lID);
      tf.setAwayTeam(uID);
      teamFixturesRepo.insert(tf);

      tf.setFixtureDate("14/01/2018");
      tf.setFixtureLocation("B1 7ST");
      tf.setHomeTeam(aID);
      tf.setAwayTeam(lID);
      teamFixturesRepo.insert(tf);

      tf.setFixtureDate("31/01/2018");
      tf.setFixtureLocation("OX 9LN");
      tf.setHomeTeam(oID);
      tf.setAwayTeam(uID);
      teamFixturesRepo.insert(tf);

      tf.setFixtureDate("07/02/2018");
      tf.setFixtureLocation("B29 2TT");
      tf.setHomeTeam(uID);
      tf.setAwayTeam(aID);
      teamFixturesRepo.insert(tf);

      tf.setFixtureDate("14/02/2018");
      tf.setFixtureLocation("LE11 6NE");
      tf.setHomeTeam(lID);
      tf.setAwayTeam(aID);
      teamFixturesRepo.insert(tf);

      tf.setFixtureDate("21/02/2018");
      tf.setFixtureLocation("B1 7ST");
      tf.setHomeTeam(aID);
      tf.setAwayTeam(uID);
      teamFixturesRepo.insert(tf);

      tf.setFixtureDate("28/02/2018");
      tf.setFixtureLocation("OX 6ET");
      tf.setHomeTeam(oID);
      tf.setAwayTeam(lID);
      teamFixturesRepo.insert(tf);

      tf.setFixtureDate("07/03/2018");
      tf.setFixtureLocation("OX 9LN");
      tf.setHomeTeam(oID);
      tf.setAwayTeam(aID);
      teamFixturesRepo.insert(tf);

      tf.setFixtureDate("14/03/2018");
      tf.setFixtureLocation("LE12 8WD");
      tf.setHomeTeam(lID);
      tf.setAwayTeam(oID);
      teamFixturesRepo.insert(tf);

      tf.setFixtureDate("21/02/2018");
      tf.setFixtureLocation("B2 6DV");
      tf.setHomeTeam(aID);
      tf.setAwayTeam(oID);
      teamFixturesRepo.insert(tf);

      tf.setFixtureDate("28/03/2018");
      tf.setFixtureLocation("B15 2QW");
      tf.setHomeTeam(uID);
      tf.setAwayTeam(oID);
      teamFixturesRepo.insert(tf);
      
      teamFixturesRepo.closeConnection();
	}
	
	private void insertSC(){
		StrengthAndConditioningRepo scRepo = new StrengthAndConditioningRepo();
		StrengthAndConditioning sc = new StrengthAndConditioning();
		
      sc.setSessionDate("25/09/2017");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("29/09/2017");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("02/10/2017");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("06/10/2017");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("09/10/2017");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("13/10/2017");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("16/10/2017");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("20/10/2017");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("23/10/2017");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("27/10/2017");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("30/10/2017");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("03/11/2017");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("06/11/2017");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("10/11/2017");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("13/11/2017");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("17/11/2017");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("20/11/2017");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("24/11/2017");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("27/11/2017");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("01/12/2017");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("04/12/2017");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("08/12/2017");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("11/12/2017");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("15/12/2017");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("18/12/2017");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("22/12/2017");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("25/12/2017");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("29/12/2017");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("01/01/2018");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("05/01/2018");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("08/01/2018");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("12/01/2018");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("15/01/2018");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("19/01/2018");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("22/01/2018");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("26/01/2018");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("29/01/2018");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("02/02/2018");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("05/02/2018");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("09/02/2018");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("12/02/2018");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("16/02/2018");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("19/02/2018");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("23/02/2018");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("26/02/2018");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("02/03/2018");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("05/03/2018");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionID("47");
      sc.setSessionDate("09/03/2018");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("12/03/2018");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("16/03/2018");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("19/03/2018");
      sc.setSessionTime("1615");
      scRepo.insert(sc);

      sc.setSessionDate("23/03/2018");
      sc.setSessionTime("1615");
      scRepo.insert(sc);


      sc.setSessionDate("26/03/2018");
      sc.setSessionTime("1615");
      scRepo.insert(sc);
      
      scRepo.closeConnection();
	}

	private void insertSession(){
		MemberRepo memberRepo = new MemberRepo();
		StrengthAndConditioningRepo scRepo = new StrengthAndConditioningRepo();
		SessionRepo sessionRepo = new SessionRepo();
		
		ArrayList<String> memberIDs = memberRepo.getMemberIDs();
		ArrayList<String> sessionIDs = scRepo.getSessionIDs();
		
		int i = 0;
		for(String memberID: memberIDs){
			for(String sessionID: sessionIDs){
				
				//* CREATE RANDOM DATA TO TEST GRAPHS ETC
				i++;
				Random rand = new Random(); 
				double multiplier;
				if(i < 10){
					multiplier = 2;
				}else if(i < 20){
					multiplier = 3;
				}else if(i < 30){
					multiplier = 6;
				}else if(i < 40){
					multiplier = 7;
				}else{
					multiplier = 7.5;
				}
				//int value = rand.nextInt(10) + 10; //random number between 10 and 20 -> weights between 50 and 100
				String deadlifts = "";
				for(int d = 0; d < 5; d++){
					deadlifts = deadlifts + "5:" + String.valueOf((rand.nextInt(20) + 10) * multiplier) + ", "; //between (20*2.5 = 50) and 50 + (10+i)*2.5
				}
				deadlifts = deadlifts + "5:" + String.valueOf((rand.nextInt(10) + 10)*multiplier);

 
				String squats = "";
				for(int d = 0; d < 5; d++){
					squats = squats + "8:" + String.valueOf((rand.nextInt(8) + 6)*multiplier) + ", ";
				}
				squats = squats + "8:" + String.valueOf((rand.nextInt(8) + 6)*multiplier);

				String benchpress = "";
				for(int d = 0; d < 5; d++){
					benchpress = benchpress + "6:" + String.valueOf((rand.nextInt(4) + 4)*multiplier) + ", ";
				}
				benchpress = benchpress + "6:" + String.valueOf((rand.nextInt(4) + 4)*multiplier);

				String splitSquat = "";
				for(int d = 0; d < 5; d++){
					splitSquat = splitSquat + "6:" + String.valueOf((rand.nextInt(4) + 4)*multiplier) + ", ";
				}
				splitSquat = splitSquat + "6:" + String.valueOf((rand.nextInt(4) + 4)*multiplier);
				
				
				String squatJumps = "";
				for(int d = 0; d < 5; d++){
					squatJumps = squatJumps + "4:" + String.valueOf((rand.nextInt(5) + 6)*multiplier) + ", ";
				}
				squatJumps = squatJumps + "4:" + String.valueOf((rand.nextInt(5) + 6)*multiplier);

				String deadliftJumps = "";
				for(int d = 0; d < 5; d++){
					deadliftJumps = deadliftJumps + "4:" + String.valueOf((rand.nextInt(6) + 7)*multiplier) + ", ";
				}
				deadliftJumps = deadliftJumps + "4:" + String.valueOf((rand.nextInt(6) + 7)*multiplier);

				String militaryPress = "";
				for(int d = 0; d < 5; d++){
					militaryPress = militaryPress + "6:" + String.valueOf((rand.nextInt(5) + 5)* (multiplier/2)) + ", ";
				}
				militaryPress = militaryPress + "6:" + String.valueOf((rand.nextInt(5) + 5)*(multiplier)/2);

				if(i % 2 == 0){
					Session session = new Session();
					session.setSessionID(sessionID);
					session.setMemberID(memberID);
					session.setDeadlifts(deadlifts); //5:60 means 5 reps of 60kg
					session.setDeadliftJumps(null);
					session.setBackSquat(squats);
					session.setBackSquatJumps(null);
					session.setGobletSquat(null);
					session.setBenchPress(benchpress);
					session.setMilitaryPress(null);
					session.setSupineRow(null);
					session.setChinUps(null);
					session.setTrunk(null);
					session.setRdl(null);
					session.setSplitSquat(splitSquat);
					session.setFourWayArms(null);
			      sessionRepo.insert(session);
				}
				else{
					Session session = new Session();
					session.setSessionID(sessionID);
					session.setMemberID(memberID);
					session.setDeadlifts(null); 
					session.setDeadliftJumps(deadliftJumps);
					session.setBackSquat(squats);
					session.setBackSquatJumps(squatJumps);
					session.setGobletSquat(null);
					session.setBenchPress(benchpress);
					session.setMilitaryPress(militaryPress);
					session.setSupineRow(null);
					session.setChinUps(null);
					session.setTrunk(null);
					session.setRdl(null);
					session.setSplitSquat(splitSquat);
					session.setFourWayArms(null);
			      sessionRepo.insert(session);
				}
			}
		}
		sessionRepo.closeConnection();
	}

	private void insertFixtures(){
		FixtureRepo fixtureRepo = new FixtureRepo();
		Fixture fixture = new Fixture();
		
		fixtureRepo.closeConnection();
		
	}
}

