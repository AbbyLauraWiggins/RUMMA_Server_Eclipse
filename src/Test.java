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
		insertKPIs();
	}
	
	private void insertMembers(){
		 Member member = new Member();
		 
		 TeamRepo teamRepo = new TeamRepo();		 
		 
		 //SIMPLY TEST TEAM ID
		 //IN PROGRAM, THIS WILL COME FROM A SPINNER SELECTION
		 teamRepo.connectToDB();
		 String teamName = teamRepo.getTeamId("UOBWRFC"); 
		 
		 
		 MemberRepo memberRepo = new MemberRepo();

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
		teamRepo.connectToDB();
		
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
		teamRepo.connectToDB();
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
		Fixture fixtures = new Fixture();
		TeamRepo teamRepo = new TeamRepo();
		TeamFixturesRepo teamFixturesRepo = new TeamFixturesRepo();
		
		teamRepo.connectToDB();
		String uID = teamRepo.getTeamId("UOBWRFC");
		String lID = teamRepo.getTeamId("LURFC");
		String aID = teamRepo.getTeamId("AURFC");
		String oID = teamRepo.getTeamId("OUWRFC");

		ArrayList<String> fixtureIDs = teamFixturesRepo.getFixtureIDs();
		
		fixtureRepo.connectToDB();
		
		fixtures.setFixtureId(fixtureIDs.get(0)); //Fixture 1
      fixtures.setTeamId(uID);    //Team UOBWRFC
      fixtures.setFixturePoints("22"); //Points scored by UOBWRFC in fixture 0001
      fixtures.setForward(getRandomMember()); //memberID of UOBWRFC player who got forward of the match
      fixtures.setBack(getRandomMember());
      fixtures.setPlayer(getRandomMember());
      fixtures.setTriesScored("4");
      fixtures.setTriesSucceeded("4");
      fixtures.setConversions("1");
      fixtures.setConversionsSucceeded("0");
      fixtures.setScrumsWon("4");
      fixtures.setScrumsLost("2");
      fixtures.setMaulsWon("1");
      fixtures.setMaulsLost("2");
      fixtures.setLineOutsWon("3");
      fixtures.setLineOutsLost("4");
      fixtures.setDropGoals("0");
      fixtures.setPenaltyKicks("0");
      fixtureRepo.insert(fixtures);

      fixtures.setFixtureId(fixtureIDs.get(0)); //Fixture 1
      fixtures.setTeamId(lID); //Team LURFC
      fixtures.setFixturePoints("20"); //Points scored by LURFC in fixture 0001
      fixtures.setForward(null); //memberID of UOBWRFC player who got forward of the match
      fixtures.setBack(null);
      fixtures.setPlayer(null);
      fixtures.setTriesScored("4");
      fixtures.setTriesSucceeded("4");
      fixtures.setConversions("0");
      fixtures.setConversionsSucceeded("1");
      fixtures.setScrumsWon("2");
      fixtures.setScrumsLost("4");
      fixtures.setMaulsWon("2");
      fixtures.setMaulsLost("1");
      fixtures.setLineOutsWon("4");
      fixtures.setLineOutsLost("3");
      fixtures.setDropGoals("0");
      fixtures.setPenaltyKicks("0");
      fixtureRepo.insert(fixtures);

      fixtures.setFixtureId(fixtureIDs.get(1));
      fixtures.setTeamId(lID);
      fixtures.setFixturePoints("12");
      fixtures.setForward(null); //memberID of UOBWRFC player who got forward of the match
      fixtures.setBack(null);
      fixtures.setPlayer(null);
      fixtures.setTriesScored("2");
      fixtures.setTriesSucceeded("7");
      fixtures.setConversions("1");
      fixtures.setConversionsSucceeded("1");
      fixtures.setScrumsWon("3");
      fixtures.setScrumsLost("5");
      fixtures.setMaulsWon("1");
      fixtures.setMaulsLost("1");
      fixtures.setLineOutsWon("2");
      fixtures.setLineOutsLost("2");
      fixtures.setDropGoals("0");
      fixtures.setPenaltyKicks("0");
      fixtureRepo.insert(fixtures);

      fixtures.setFixtureId(fixtureIDs.get(1)); //Fixture 1
      fixtures.setTeamId(uID);    //Team UOBWRFC
      fixtures.setFixturePoints("37"); //Points scored by UOBWRFC
      fixtures.setForward(getRandomMember()); //memberID of UOBWRFC player who got forward of the match
      fixtures.setBack(getRandomMember());
      fixtures.setPlayer(getRandomMember());
      fixtures.setTriesScored("7");
      fixtures.setTriesSucceeded("2");
      fixtures.setConversions("1");
      fixtures.setConversionsSucceeded("1");
      fixtures.setScrumsWon("5");
      fixtures.setScrumsLost("3");
      fixtures.setMaulsWon("1");
      fixtures.setMaulsLost("1");
      fixtures.setLineOutsWon("2");
      fixtures.setLineOutsLost("2");
      fixtures.setDropGoals("0");
      fixtures.setPenaltyKicks("0");
      fixtureRepo.insert(fixtures);

      fixtures.setFixtureId(fixtureIDs.get(2));
      fixtures.setTeamId(aID);
      fixtures.setFixturePoints("32");
      fixtures.setForward(getRandomMember()); //memberID of UOBWRFC player who got forward of the match
      fixtures.setBack(getRandomMember());
      fixtures.setPlayer(getRandomMember());
      fixtures.setTriesScored("5");
      fixtures.setTriesSucceeded("20");
      fixtures.setConversions("1");
      fixtures.setConversionsSucceeded("6");
      fixtures.setScrumsWon("5");
      fixtures.setScrumsLost("8");
      fixtures.setMaulsWon("1");
      fixtures.setMaulsLost("1");
      fixtures.setLineOutsWon("4");
      fixtures.setLineOutsLost("4");
      fixtures.setDropGoals("0");
      fixtures.setPenaltyKicks("0");
      fixtureRepo.insert(fixtures);

      fixtures.setFixtureId(fixtureIDs.get(2));
      fixtures.setTeamId(lID);
      fixtures.setFixturePoints("112");
      fixtures.setForward(null);
      fixtures.setBack(null);
      fixtures.setPlayer(null);
      fixtures.setTriesScored("20");
      fixtures.setTriesSucceeded("5");
      fixtures.setConversions("6");
      fixtures.setConversionsSucceeded("1");
      fixtures.setScrumsWon("8");
      fixtures.setScrumsLost("5");
      fixtures.setMaulsWon("1");
      fixtures.setMaulsLost("1");
      fixtures.setLineOutsWon("4");
      fixtures.setLineOutsLost("4");
      fixtures.setDropGoals("0");
      fixtures.setPenaltyKicks("0");
      fixtureRepo.insert(fixtures);

      fixtures.setFixtureId(fixtureIDs.get(3));
      fixtures.setTeamId(oID);
      fixtures.setFixturePoints("66");
      fixtures.setForward(null);
      fixtures.setBack(null);
      fixtures.setPlayer(null);
      fixtures.setTriesScored("12");
      fixtures.setTriesSucceeded("11");
      fixtures.setConversions("3");
      fixtures.setConversionsSucceeded("0");
      fixtures.setScrumsWon("3");
      fixtures.setScrumsLost("3");
      fixtures.setMaulsWon("1");
      fixtures.setMaulsLost("1");
      fixtures.setLineOutsWon("3");
      fixtures.setLineOutsLost("3");
      fixtures.setDropGoals("0");
      fixtures.setPenaltyKicks("0");
      fixtureRepo.insert(fixtures);

      fixtures.setFixtureId(fixtureIDs.get(3));
      fixtures.setTeamId(uID);
      fixtures.setFixturePoints("55");
      fixtures.setForward(getRandomMember()); //memberID of UOBWRFC player who got forward of the match
      fixtures.setBack(getRandomMember());
      fixtures.setPlayer(getRandomMember());
      fixtures.setTriesScored("11");
      fixtures.setTriesSucceeded("12");
      fixtures.setConversions("0");
      fixtures.setConversionsSucceeded("3");
      fixtures.setScrumsWon("3");
      fixtures.setScrumsLost("3");
      fixtures.setMaulsWon("1");
      fixtures.setMaulsLost("1");
      fixtures.setLineOutsWon("3");
      fixtures.setLineOutsLost("3");
      fixtures.setDropGoals("0");
      fixtures.setPenaltyKicks("0");
      fixtureRepo.insert(fixtures);

      fixtures.setFixtureId(fixtureIDs.get(4));
      fixtures.setTeamId(uID);
      fixtures.setFixturePoints("15");
      fixtures.setForward(getRandomMember()); //memberID of UOBWRFC player who got forward of the match
      fixtures.setBack(getRandomMember());
      fixtures.setPlayer(getRandomMember());
      fixtures.setTriesScored("3");
      fixtures.setTriesSucceeded("0");
      fixtures.setConversions("0");
      fixtures.setConversionsSucceeded("0");
      fixtures.setScrumsWon("6");
      fixtures.setScrumsLost("3");
      fixtures.setMaulsWon("2");
      fixtures.setMaulsLost("1");
      fixtures.setLineOutsWon("6");
      fixtures.setLineOutsLost("3");
      fixtures.setDropGoals("0");
      fixtures.setPenaltyKicks("0");
      fixtureRepo.insert(fixtures);

      fixtures.setFixtureId(fixtureIDs.get(4));
      fixtures.setTeamId(aID);
      fixtures.setFixturePoints("0");
      fixtures.setForward(null);
      fixtures.setBack(null);
      fixtures.setPlayer(null);
      fixtures.setTriesScored("0");
      fixtures.setTriesSucceeded("3");
      fixtures.setConversions("0");
      fixtures.setConversionsSucceeded("0");
      fixtures.setScrumsWon("3");
      fixtures.setScrumsLost("6");
      fixtures.setMaulsWon("1");
      fixtures.setMaulsLost("2");
      fixtures.setLineOutsWon("3");
      fixtures.setLineOutsLost("6");
      fixtures.setDropGoals("0");
      fixtures.setPenaltyKicks("0");
      fixtureRepo.insert(fixtures);

      fixtures.setFixtureId(fixtureIDs.get(5));
      fixtures.setTeamId(aID);
      fixtures.setFixturePoints("10");
      fixtures.setForward(null);
      fixtures.setBack(null);
      fixtures.setPlayer(null);
      fixtures.setTriesScored("2");
      fixtures.setTriesSucceeded("2");
      fixtures.setConversions("0");
      fixtures.setConversionsSucceeded("0");
      fixtures.setScrumsWon("3");
      fixtures.setScrumsLost("3");
      fixtures.setMaulsWon("2");
      fixtures.setMaulsLost("2");
      fixtures.setLineOutsWon("4");
      fixtures.setLineOutsLost("4");
      fixtures.setDropGoals("0");
      fixtures.setPenaltyKicks("0");
      fixtureRepo.insert(fixtures);

      fixtures.setFixtureId(fixtureIDs.get(5));
      fixtures.setTeamId(lID);
      fixtures.setFixturePoints("10");
      fixtures.setForward(null);
      fixtures.setBack(null);
      fixtures.setPlayer(null);
      fixtures.setTriesScored("2");
      fixtures.setTriesSucceeded("2");
      fixtures.setConversions("0");
      fixtures.setConversionsSucceeded("0");
      fixtures.setScrumsWon("3");
      fixtures.setScrumsLost("3");
      fixtures.setMaulsWon("2");
      fixtures.setMaulsLost("2");
      fixtures.setLineOutsWon("4");
      fixtures.setLineOutsLost("4");
      fixtures.setDropGoals("0");
      fixtures.setPenaltyKicks("0");
      fixtureRepo.insert(fixtures);

      fixtures.setFixtureId(fixtureIDs.get(6));
      fixtures.setTeamId(aID);
      fixtures.setFixturePoints("7");
      fixtures.setForward(null);
      fixtures.setBack(null);
      fixtures.setPlayer(null);
      fixtures.setTriesScored("1");
      fixtures.setTriesSucceeded("2");
      fixtures.setConversions("0");
      fixtures.setConversionsSucceeded("1");
      fixtures.setScrumsWon("5");
      fixtures.setScrumsLost("5");
      fixtures.setMaulsWon("1");
      fixtures.setMaulsLost("1");
      fixtures.setLineOutsWon("2");
      fixtures.setLineOutsLost("2");
      fixtures.setDropGoals("0");
      fixtures.setPenaltyKicks("0");
      fixtureRepo.insert(fixtures);

      fixtures.setFixtureId(fixtureIDs.get(6));
      fixtures.setTeamId(uID);
      fixtures.setFixturePoints("12");
      fixtures.setForward(getRandomMember()); //memberID of UOBWRFC player who got forward of the match
      fixtures.setBack(getRandomMember());
      fixtures.setPlayer(getRandomMember());
      fixtures.setTriesScored("2");
      fixtures.setTriesSucceeded("1");
      fixtures.setConversions("1");
      fixtures.setConversionsSucceeded("0");
      fixtures.setScrumsWon("5");
      fixtures.setScrumsLost("5");
      fixtures.setMaulsWon("1");
      fixtures.setMaulsLost("1");
      fixtures.setLineOutsWon("2");
      fixtures.setLineOutsLost("2");
      fixtures.setDropGoals("0");
      fixtures.setPenaltyKicks("0");
      fixtureRepo.insert(fixtures);

      fixtures.setFixtureId(fixtureIDs.get(7));
      fixtures.setTeamId(oID);
      fixtures.setFixturePoints("68");
      fixtures.setForward(null);
      fixtures.setBack(null);
      fixtures.setPlayer(null);
      fixtures.setTriesScored(null);
      fixtures.setTriesSucceeded(null);
      fixtures.setConversions(null);
      fixtures.setConversionsSucceeded(null);
      fixtures.setScrumsWon(null);
      fixtures.setScrumsLost(null);
      fixtures.setMaulsWon(null);
      fixtures.setMaulsLost(null);
      fixtures.setLineOutsWon(null);
      fixtures.setLineOutsLost(null);
      fixtures.setDropGoals(null);
      fixtures.setPenaltyKicks(null);
      fixtureRepo.insert(fixtures);

      fixtures.setFixtureId(fixtureIDs.get(7));
      fixtures.setTeamId(lID);
      fixtures.setFixturePoints("64");
      fixtures.setForward(null);
      fixtures.setBack(null);
      fixtures.setPlayer(null);
      fixtures.setTriesScored(null);
      fixtures.setTriesSucceeded(null);
      fixtures.setConversions(null);
      fixtures.setConversionsSucceeded(null);
      fixtures.setScrumsWon(null);
      fixtures.setScrumsLost(null);
      fixtures.setMaulsWon(null);
      fixtures.setMaulsLost(null);
      fixtures.setLineOutsWon(null);
      fixtures.setLineOutsLost(null);
      fixtures.setDropGoals(null);
      fixtures.setPenaltyKicks(null);
      fixtureRepo.insert(fixtures);

      fixtures.setFixtureId(fixtureIDs.get(8));
      fixtures.setTeamId(oID);
      fixtures.setFixturePoints("87");
      fixtures.setForward(null);
      fixtures.setBack(null);
      fixtures.setPlayer(null);
      fixtures.setTriesScored(null);
      fixtures.setTriesSucceeded(null);
      fixtures.setConversions(null);
      fixtures.setConversionsSucceeded(null);
      fixtures.setScrumsWon(null);
      fixtures.setScrumsLost(null);
      fixtures.setMaulsWon(null);
      fixtures.setMaulsLost(null);
      fixtures.setLineOutsWon(null);
      fixtures.setLineOutsLost(null);
      fixtures.setDropGoals(null);
      fixtures.setPenaltyKicks(null);
      fixtureRepo.insert(fixtures);

      fixtures.setFixtureId(fixtureIDs.get(8));
      fixtures.setTeamId(aID);
      fixtures.setFixturePoints("44");
      fixtures.setForward(null);
      fixtures.setBack(null);
      fixtures.setPlayer(null);
      fixtures.setTriesScored(null);
      fixtures.setTriesSucceeded(null);
      fixtures.setConversions(null);
      fixtures.setConversionsSucceeded(null);
      fixtures.setScrumsWon(null);
      fixtures.setScrumsLost(null);
      fixtures.setMaulsWon(null);
      fixtures.setMaulsLost(null);
      fixtures.setLineOutsWon(null);
      fixtures.setLineOutsLost(null);
      fixtures.setDropGoals(null);
      fixtures.setPenaltyKicks(null);
      fixtureRepo.insert(fixtures);

      fixtures.setFixtureId(fixtureIDs.get(9));
      fixtures.setTeamId(lID);
      fixtures.setFixturePoints("null");
      fixtures.setForward(null);
      fixtures.setBack(null);
      fixtures.setPlayer(null);
      fixtures.setTriesScored(null);
      fixtures.setTriesSucceeded(null);
      fixtures.setConversions(null);
      fixtures.setConversionsSucceeded(null);
      fixtures.setScrumsWon(null);
      fixtures.setScrumsLost(null);
      fixtures.setMaulsWon(null);
      fixtures.setMaulsLost(null);
      fixtures.setLineOutsWon(null);
      fixtures.setLineOutsLost(null);
      fixtures.setDropGoals(null);
      fixtures.setPenaltyKicks(null);
      fixtureRepo.insert(fixtures);

      fixtures.setFixtureId(fixtureIDs.get(9));
      fixtures.setTeamId(oID);
      fixtures.setFixturePoints("null");
      fixtures.setForward(null);
      fixtures.setBack(null);
      fixtures.setPlayer(null);
      fixtures.setTriesScored(null);
      fixtures.setTriesSucceeded(null);
      fixtures.setConversions(null);
      fixtures.setConversionsSucceeded(null);
      fixtures.setScrumsWon(null);
      fixtures.setScrumsLost(null);
      fixtures.setMaulsWon(null);
      fixtures.setMaulsLost(null);
      fixtures.setLineOutsWon(null);
      fixtures.setLineOutsLost(null);
      fixtures.setDropGoals(null);
      fixtures.setPenaltyKicks(null);
      fixtureRepo.insert(fixtures);

      fixtures.setFixtureId(fixtureIDs.get(10));
      fixtures.setTeamId(aID);
      fixtures.setFixturePoints(null); //null as hasn't occured yet
      fixtures.setForward(null);
      fixtures.setBack(null);
      fixtures.setPlayer(null);
      fixtures.setTriesScored(null);
      fixtures.setTriesSucceeded(null);
      fixtures.setConversions(null);
      fixtures.setConversionsSucceeded(null);
      fixtures.setScrumsWon(null);
      fixtures.setScrumsLost(null);
      fixtures.setMaulsWon(null);
      fixtures.setMaulsLost(null);
      fixtures.setLineOutsWon(null);
      fixtures.setLineOutsLost(null);
      fixtures.setDropGoals(null);
      fixtures.setPenaltyKicks(null);
      fixtureRepo.insert(fixtures);

      fixtures.setFixtureId(fixtureIDs.get(10));
      fixtures.setTeamId(oID);
      fixtures.setFixturePoints(null);
      fixtures.setForward(null);
      fixtures.setBack(null);
      fixtures.setPlayer(null);
      fixtures.setTriesScored(null);
      fixtures.setTriesSucceeded(null);
      fixtures.setConversions(null);
      fixtures.setConversionsSucceeded(null);
      fixtures.setScrumsWon(null);
      fixtures.setScrumsLost(null);
      fixtures.setMaulsWon(null);
      fixtures.setMaulsLost(null);
      fixtures.setLineOutsWon(null);
      fixtures.setLineOutsLost(null);
      fixtures.setDropGoals(null);
      fixtures.setPenaltyKicks(null);
      fixtureRepo.insert(fixtures);

      fixtures.setFixtureId(fixtureIDs.get(11));
      fixtures.setTeamId(uID);
      fixtures.setFixturePoints(null);
      fixtures.setForward(null);
      fixtures.setBack(null);
      fixtures.setPlayer(null);
      fixtures.setTriesScored(null);
      fixtures.setTriesSucceeded(null);
      fixtures.setConversions(null);
      fixtures.setConversionsSucceeded(null);
      fixtures.setScrumsWon(null);
      fixtures.setScrumsLost(null);
      fixtures.setMaulsWon(null);
      fixtures.setMaulsLost(null);
      fixtures.setLineOutsWon(null);
      fixtures.setLineOutsLost(null);
      fixtures.setDropGoals(null);
      fixtures.setPenaltyKicks(null);
      fixtureRepo.insert(fixtures);

      fixtures.setFixtureId(fixtureIDs.get(11));
      fixtures.setTeamId(oID);
      fixtures.setFixturePoints(null);
      fixtures.setForward(null);
      fixtures.setBack(null);
      fixtures.setPlayer(null);
      fixtures.setTriesScored(null);
      fixtures.setTriesSucceeded(null);
      fixtures.setConversions(null);
      fixtures.setConversionsSucceeded(null);
      fixtures.setScrumsWon(null);
      fixtures.setScrumsLost(null);
      fixtures.setMaulsWon(null);
      fixtures.setMaulsLost(null);
      fixtures.setLineOutsWon(null);
      fixtures.setLineOutsLost(null);
      fixtures.setDropGoals(null);
      fixtures.setPenaltyKicks(null);
      fixtureRepo.insert(fixtures);
      
      
		fixtureRepo.closeConnection();
		
	}

	private void insertKPIs(){
		KPIRepo kpiRepo = new KPIRepo();
		KPI kpi = new KPI();
		MemberRepo memberRepo = new MemberRepo();
		TeamFixturesRepo tfRepo = new TeamFixturesRepo();
		TeamRepo teamRepo = new TeamRepo();

		ArrayList<String> myFixturesPlayed = tfRepo.getMyFixtures(teamRepo.getTeamId("UOBWRFC"));
		ArrayList<String> memberIDs = memberRepo.getMemberIDs();

		Random rand = new Random();

		for(String memberID: memberIDs){
			for(String fixtureID: myFixturesPlayed){
				  kpi.setMemberID(memberID);
		        kpi.setFixtureID(fixtureID);
		        kpi.setsTackles(String.valueOf((int) (Math.random() * 20)));
		        kpi.setuTackles(String.valueOf((int) (Math.random() * 15)));
		        kpi.setsCarries(String.valueOf((int) (Math.random() * 10)));
		        kpi.setuCarries(String.valueOf((int) (Math.random() * 10)));
		        kpi.setsPasses(String.valueOf((int) (Math.random() * 25)));
		        kpi.setuPasses(String.valueOf((int) (Math.random() * 15)));
		        kpi.setHandlingErrors(String.valueOf((int) (Math.random() * 10)));
		        kpi.setPenalties(String.valueOf((int) (Math.random() * 3)));
		        kpi.setYellowCards("0");
		        kpi.setTriesScored(String.valueOf((int) (Math.random() * 2)));
		        kpi.setTurnoversWon(String.valueOf((int) (Math.random() * 10)));
		        kpi.setsThrowIns("0");
		        kpi.setuThrowIns("0");
		        kpi.setsLineOutTakes("0");
		        kpi.setuLineOutTakes("0");
		        kpi.setsKicks(String.valueOf((int) (Math.random() * 2)));
		        kpi.setuKicks(String.valueOf((int) (Math.random() * 2)));
		        kpiRepo.insert(kpi);
			}
		}
		kpiRepo.closeConnection();
	}

	private String getRandomMember(){
		MemberRepo memberRepo = new MemberRepo();
		ArrayList<String> members = memberRepo.getMemberIDs();
		
		Random rand = new Random();
		if(!(members.size() == 0)){
			System.out.println("members size: " + members.size());

			int i = rand.nextInt(members.size());
			System.out.println(members.size() + " >>>><<<< " + i);
			System.out.println(members.get(i));
			return members.get(i);

		}
		return null;
	}
}

