import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
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
import Database.Schema.Notice;
import Database.Schema.Session;
import Database.Schema.StrengthAndConditioning;
import Database.Schema.Team;
import Database.Schema.TeamFixtures;

/**
 * @author abbylaura
 */
public class ServerThreads extends Thread{
	 
    private Socket clientSocket;

    /**
    * @param clientSocket
    */
   public ServerThreads(Socket clientSocket){
        super();
        this.clientSocket = clientSocket;
        String hostName = clientSocket.getInetAddress().getHostName();
        System.out.println("hostname " + hostName);
    }

    /*
     * (non-Javadoc)
     * Reads in type, tableSize and in from server
     * String type: type of request to be serviced (indicates which method to call)
     * int tableSize: size of client-version table that is to be updated, so don't send data they already have
     * ArrayList<String> in: hold the database table contents to be added to server database
     * 
     * This method reads in objects from the client, services the request and responds with an ArrayList
     * containing any data the user has requested.
     * 
     * @see java.lang.Thread#run()
     */
    public void run(){
        try {
           	//Will also receive from client a HashMap<String TYPE, Object CONTENT> 
      	  
      	   ObjectOutputStream outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream inFromClient = new ObjectInputStream(clientSocket.getInputStream());
            

            String type = (String) inFromClient.readObject(); //DATABASE CLASS TO ADD TO
            
            int tableSize = (int) inFromClient.readObject(); //SIZE OF CLIENT DB TABLE
            System.out.println("table size: " + String.valueOf(tableSize));
            
            @SuppressWarnings("unchecked")
				ArrayList<String> in = (ArrayList<String>) inFromClient.readObject();
            
            if(type.equals("NOTICE")){ //mapIn.get("TYPE").equals("NOTICE")){
	            	System.out.println("TYPE = NOTICE");
		            ArrayList<String> outList = serviceNotice(in, tableSize);
		            System.out.println("Out To Client: " + outList.toString());
		            outToClient.writeObject(outList);
            }
            else if(type.equals("LOGIN")){
	            	System.out.println("TYPE = LOGIN");
	            	ArrayList<String> outList = serviceMembers();
	            	System.out.println("outList: " + outList);
	            	outToClient.writeObject(outList);
            }else if(type.equals("FIXTURES")){
	            	System.out.println("TPYE = FIXTURES");
	            	ArrayList<String> outList = serviceFixtures(in, tableSize);	
	            	System.out.println("outList: " + outList);
	            	outToClient.writeObject(outList);
            }else if(type.equals("TEAMFIXTURES")){
	            	System.out.println("TYPE = TEAM FIXTURES");
		         	ArrayList<String> outList = serviceTeamFixtures(in, tableSize);
		         	System.out.println("outList: " + outList);
		         	outToClient.writeObject(outList);
	         }else if(type.equals("TEAMS")){
	            	System.out.println("TYPE = TEAMS");
		         	ArrayList<String> outList = serviceTeams(in, tableSize);
		         	System.out.println("outList: " + outList);
		         	outToClient.writeObject(outList);
	         }else if(type.equals("SC")){
	            	System.out.println("TYPE = SC");
		         	ArrayList<String> outList = serviceSC(in, tableSize);
		         	System.out.println("outList: " + outList);
		         	outToClient.writeObject(outList);
	         }else if(type.equals("SESSION")){
	         		System.out.println("TYPE = SESSION");
	         		ArrayList<String> outList = new ArrayList<>();
	         		if(Integer.parseInt(in.get(1)) < 2){
			         	outList = serviceBasicSession(in);
	         		}else{
			         	outList = serviceAdvancedSession(in, tableSize);
	         		}
	         		//System.out.println("outList: " + outList);
		         	outToClient.writeObject(outList);
	         }else if(type.equals("KPI")){
	         		System.out.println("TYPE = KPI");
	         		ArrayList<String> outList = new ArrayList<>();
	         		if(Integer.parseInt(in.get(1)) < 2){
			         	outList = serviceBasicKPI(in);
	         		}else{
			         	outList = serviceAdvancedKPI(in, tableSize);
	         		}
	         		//System.out.println("outList: " + outList);
		         	outToClient.writeObject(outList);
	         }else if(type.equals("SCADD")){
	         		System.out.println("TYPE = SCADD");
	         		ArrayList<String> outList = serviceSCADD(in);
	         		System.out.println("outlist: " + outList);
	         		outToClient.writeObject(outList);
	         }else if(type.equals("ADDFEEDBACK")){
	         		System.out.println("TYPE = ADDFEEDBACK");
	         		ArrayList<String> outList = serviceAddFeedback(in, tableSize);
	         		outToClient.writeObject(outList);
	         }else if(type.equals("updateBasicFEEDBACK")){
	         		System.out.println("TYPE = updateBasicFEEDBACK");
	         		ArrayList<String> outList = serviceBasicFeedback(in, tableSize);
	         		outToClient.writeObject(outList);
	         }else if(type.equals("ADDTEAM")){
	         		System.out.println("TYPE = ADDTEAM");
	         		ArrayList<String> outList = addTeam(in);
	         		outToClient.writeObject(outList);
	         }else if(type.equals("PERMISSIONS")){
         		System.out.println("TYPE = PERMISSIONS");
         		ArrayList<String> outList = updatePermissions(in);
         		outToClient.writeObject(outList);
         }
	            
            clientSocket.close();
            return;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            try{
                clientSocket.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        
        
    }  
    
    /**
     * @param in: the arraylist containing the id and permission of users whose permission is to be updated
     * @return memberRepo.getMembers() an arraylist of the updated member table
     */
   private ArrayList<String> updatePermissions(ArrayList<String> in) {
		 MemberRepo memberRepo = new MemberRepo();

   	 if(!(in.isEmpty())){
   		 for(String mem: in){
   			 System.out.println(in + " ||| " + mem);
   			 String[] splitter = mem.split("4h4f");
   			 String id = splitter[0];
   			 String permission = splitter[1];
   			 
   			 String permNum = "";
   			 if(permission.equals("Basic")){
   				 permNum = "0";
   			 } else if(permission.equals("Admin")){
   				 permNum = "1";
   			 } else if(permission.equals("Leader")){
   				 permNum = "2";
   			 } else if(permission.equals("CAL")){
   				 permNum = "3";
   			 } else if(permission.equals("PAL")){
   				 permNum = "4";
   			 } 
   			    			 
   			 memberRepo.updateMember(id, permNum);
   			 
   		 }
   	 }
   	 
   	 return memberRepo.getMembers();
 		
	}

   /**
    * @param in: the arraylist containing the details of the team to be added to the Team table
    * @return an empty arraylist as this is already added client side
    */
	private ArrayList<String> addTeam(ArrayList<String> in){
   	 Team team = new Team();
   	 
   	 team.setTeamId(in.get(0));
   	 team.setTeamName(in.get(1));
   	 team.setTeamLocation(in.get(2));
   	 team.setTeamCurPoints(in.get(3));
   	 
   	 TeamRepo teamRepo = new TeamRepo();
   	 teamRepo.insert(team);
   	 
   	 return new ArrayList<String>();
    }
    
	/**
    * @param in: the arraylist containing the details to update a member's feedback
    * @return an arraylist of the feedback table, the return from either getAdvTable() or getBasicFeedback()
    */
    private ArrayList<String> serviceBasicFeedback(ArrayList<String> in, int size){
   	 String id = in.get(0);
   	 FeedbackRepo feedbackRepo = new FeedbackRepo();

   	 if(id.equals("ADVANCED")){
   		 return feedbackRepo.getAdvTable(size);
   	 }else{
   		 return feedbackRepo.getBasicFeedback(id, size);
   	 }
   	 
   	
    }
   
    
    /**
     * @param in: an arraylist containing the one feedback entry to add to table
     * @param: size: the size of the client's feedback table
     * @return response: an arraylist of any entries added to the feedback table that client does not have
     */
    private ArrayList<String> serviceAddFeedback(ArrayList<String> in, int size){
   	 Feedback feedback = new Feedback();
   	 feedback.setMemberId(in.get(0));
   	 feedback.setFixtureId(in.get(1));
   	 feedback.setFeedbackText(in.get(2));
   	 feedback.setAttack(in.get(3));
   	 feedback.setDefence(in.get(4));
   	 feedback.setEffort(in.get(5));
   	 feedback.setOverall(in.get(6));
   	 
   	 FeedbackRepo feedbackRepo = new FeedbackRepo();
   	 feedbackRepo.insert(feedback);
   	 
   	 ArrayList<String> response = feedbackRepo.getAdvTable(size);
   	 
   	 return response;
    }
    
    /**
     * @param in: an arraylist containing the S&C data to be added
     * @return response: an arraylist of any entries added to the feedback table that client does not have
     */
    private ArrayList<String> serviceSCADD(ArrayList<String> in){
   	  
   	  StrengthAndConditioningRepo scRepo = new StrengthAndConditioningRepo();
   	  StrengthAndConditioning sc = new StrengthAndConditioning();
   	  
   	  sc.setSessionDate(in.get(0));
   	  sc.setSessionTime(in.get(1));
   	  scRepo.insert(sc);
   	  
   	  int sessionID = scRepo.getLastID() + 1;
   	  
   	  Session scs = new Session();

   	  scs.setMemberID(null);
   	  scs.setSessionID(String.valueOf(sessionID));
   	  
        for(int i = 2; i < in.size(); i++){
      	  String[] splitter = in.get(i).split("4h4f");
      	  if(splitter[0].equals("Deadlifts")){
      		  scs.setDeadlifts(splitter[1]);
      	  }
      	  else if(splitter[0].equals("DeadliftJumps")){
      		  scs.setDeadliftJumps(splitter[1]);
      	  }
      	  else if(splitter[0].equals("BackSquat")){
      		  scs.setBackSquat(splitter[1]);
      	  }
      	  else if(splitter[0].equals("BackSquatJumps")){
      		  scs.setBackSquatJumps(splitter[1]);
      	  }
      	  else if(splitter[0].equals("GobletSquat")){
      		  scs.setGobletSquat(splitter[1]);
      	  }
      	  else if(splitter[0].equals("BenchPress")){
      		  scs.setBenchPress(splitter[1]);
      	  }
      	  else if(splitter[0].equals("MilitaryPress")){
      		  scs.setMilitaryPress(splitter[1]);
      	  }
      	  else if(splitter[0].equals("SupineRow")){
      		  scs.setSupineRow(splitter[1]);
      	  }
      	  else if(splitter[0].equals("ChinUps")){
      		  scs.setBackSquat(splitter[1]);
      	  }
      	  else if(splitter[0].equals("RDL")){
      		  scs.setRdl(splitter[1]);
      	  }
      	  else if(splitter[0].equals("SplitSquat")){
      		  scs.setSplitSquat(splitter[1]);
      	  }
      	  else if(splitter[0].equals("FourWayArms")){
      		  scs.setFourWayArms(splitter[1]);
      	  }
      	  
        }
        
        ArrayList<String> scSess = scRepo.getSC(0); 
        
        SessionRepo sessionRepo = new SessionRepo();
        sessionRepo.insert(scs);
        
        ArrayList<String> sessions = sessionRepo.getAllSessions(0); 
        
        scSess.add("session");
        ArrayList<String> toReturn = new ArrayList<>();
        toReturn.addAll(scSess);
        toReturn.addAll(sessions);
       
        return toReturn;
        
    }
   
   private ArrayList<String> serviceTeamFixtures(ArrayList<String> in, int tableSize){
   	 System.out.println("Service team fixtures" + in.toString());
 		 TeamFixturesRepo tfRepo = new TeamFixturesRepo();

 		 
   	 //add to Notices:
 		 for(String al: in){
 			 System.out.println(al);
 			 
 			 if(!(al.equals("CODE:4801:UPDATETEAMFIXTURES"))){ //UNIQUE EMPTY BUFFER STRING = empty buffer

	 	          TeamFixtures tf = new TeamFixtures();
	 	          String[] splitter = al.split("4h4f");

		 	       tf.setFixtureDate(splitter[0]);
		          tf.setFixtureLocation(splitter[1]);
		          tf.setHomeTeam(splitter[2]);
		          tf.setAwayTeam(splitter[3]);
	             tfRepo.insert(tf);
	 	                
 	             tfRepo.insert(tf);
 			 }
 			 
 		 }
 		 return tfRepo.getFixtures(tableSize);		 
   	    

 	}
    
	private ArrayList<String> serviceNotice(ArrayList<String> in, int tableSize){ 
   	 System.out.println("Service notice" + in.toString());
		 NoticeRepo noticeRepo = new NoticeRepo();

		 
   	 //add to Notices:
		 for(String al: in){
			 System.out.println(al);
			 
			 if(!(al.equals("CODE:4698:EMPTYBUFFER"))){ //UNIQUE EMPTY BUFFER STRING = empty buffer

	          Notice notice = new Notice();
	          String[] splitter = al.split("4h4f");
	          System.out.println(splitter[0] + " " + splitter[1] + " " + splitter[2]);
	          notice.setMemberId((String) splitter[0]);
	          notice.setContents((String) splitter[1]);
	          notice.setDate((String) splitter[2]);
	          
	                   
	          noticeRepo.insert(notice);
			 }
			 
      }
   	 
   	 //ArrayList<Notice> noticeTableObj = noticeRepo.getTableObject(); //gets ALL notices on server
   	 
   	 return noticeRepo.getNotices(tableSize);
    }
   
	private ArrayList<String> serviceMembers(){
		MemberRepo memberRepo = new MemberRepo();
		
		ArrayList<String> members = memberRepo.getMembers();
		System.out.println(members);
		return members;
	}
   
	private ArrayList<String> serviceFixtures(ArrayList<String> in, int tableSize){
		System.out.println("Service fixtures" + in.toString());
		FixtureRepo fixtureRepo = new FixtureRepo();

		 
  	 //add to Notices:
		 for(String al: in){
			 System.out.println(al);
			 
			 if(!(al.equals("CODE:4800:UPDATEFIXTURES"))){ //UNIQUE EMPTY BUFFER STRING = empty buffer

	          Fixture fixture = new Fixture();
	          String[] splitter = al.split("4h4f");

	          fixture.setTeamId(splitter[1]);
             fixture.setFixtureId(splitter[2]);
             fixture.setFixturePoints(splitter[3]);
             fixture.setForward(splitter[4]);
             fixture.setBack(splitter[5]);
             fixture.setPlayer(splitter[6]);
             fixture.setTriesScored(splitter[7]);
             fixture.setTriesSucceeded(splitter[8]);
             fixture.setConversions(splitter[9]);
             fixture.setConversionsSucceeded(splitter[10]);
             fixture.setScrumsWon(splitter[11]);
             fixture.setScrumsLost(splitter[12]);
             fixture.setMaulsWon(splitter[13]);
             fixture.setMaulsLost(splitter[14]);
             fixture.setLineOutsWon(splitter[15]);
             fixture.setLineOutsLost(splitter[16]);
             fixture.setDropGoals(splitter[17]);
             fixture.setPenaltyKicks(splitter[18]);
	                
	          fixtureRepo.insert(fixture);
			 }
		 }
	
  	    return fixtureRepo.getFixtures(tableSize);		 
  	    

	}
	
	private ArrayList<String> serviceTeams(ArrayList<String> in, int tableSize){
		System.out.println("Service teams" + in.toString());
		TeamRepo teamRepo = new TeamRepo();

		 
  	 //add to Notices:
		 for(String al: in){
			 System.out.println(al);
			 
			 if(!(al.equals("CODE:4802:UPDATETEAMS"))){ 

				 String[] splitter = al.split("4h4f");

             Team team = new Team();
             team.setTeamName(splitter[1]);
             team.setTeamLocation(splitter[2]);
             team.setTeamCurPoints(splitter[3]);
             teamRepo.insert(team);
			 }
			 
			 			 
		 }
			return teamRepo.getTeams(tableSize); 

	}
	
	private ArrayList<String> serviceSC(ArrayList<String> in, int tableSize){
		System.out.println("Service teams" + in.toString());
		StrengthAndConditioningRepo scRepo = new StrengthAndConditioningRepo();

		 
  	 //add to Notices:
		 for(String al: in){
			 System.out.println(al);
			 
			 if(!(al.equals("CODE:4804:UPDATESC"))){ 

				 String[] splitter = al.split("4h4f");

				 StrengthAndConditioning sc = new StrengthAndConditioning();
             sc.setSessionDate(splitter[1]);
             sc.setSessionTime(splitter[2]);
            
             scRepo.insert(sc);
			 }
			 
			 			 
		 }
			return scRepo.getSC(tableSize); 

	}
	
	private ArrayList<String> serviceBasicSession(ArrayList<String> in){
		System.out.println("Service basic session" + in.toString());
		SessionRepo sessionRepo = new SessionRepo();
		
		String myID = in.get(2);
		
		return sessionRepo.getMySession(myID);

	}

	private ArrayList<String> serviceAdvancedSession(ArrayList<String> in, int tableSize){
		System.out.println("Service adv session" + in.toString());
		SessionRepo sessionRepo = new SessionRepo();
		 
  	 //add to Notices:
		 
		System.out.println(in.get(0));
		 if(!(in.get(0).equals("CODE:4805:UPDATESESSION"))){ 
			 for(String al: in){
				 System.out.println(al);
				 String[] splitter = al.split("4h4f");
				 Session scs = new Session();
             scs.setMemberID(splitter[1]);
             scs.setDeadlifts(splitter[1]);
             scs.setDeadliftJumps(splitter[3]);
             scs.setBackSquat(splitter[4]);
             scs.setBackSquatJumps(splitter[5]);
             scs.setGobletSquat(splitter[6]);
             scs.setBenchPress(splitter[7]);
             scs.setMilitaryPress(splitter[8]);
             scs.setSupineRow(splitter[9]);
             scs.setChinUps(splitter[10]);
             scs.setTrunk(splitter[11]);
             scs.setRdl(splitter[12]);
             scs.setSplitSquat(splitter[13]);
             scs.setFourWayArms(splitter[14]);
            
             sessionRepo.insert(scs);
			 } 		 
		 }
		 return sessionRepo.getAllSessions(tableSize); 
	}

	private ArrayList<String> serviceBasicKPI(ArrayList<String> in){
		System.out.println("Service basic session" + in.toString());
		KPIRepo kpiRepo = new KPIRepo();
		
		String myID = in.get(2);
		
		return kpiRepo.getMyKPIs(myID);

	}
	
	private ArrayList<String> serviceAdvancedKPI(ArrayList<String> in, int tableSize){
		System.out.println("Service adv session" + in.toString());
		KPIRepo kpiRepo = new KPIRepo();
		 
  	 //add to Notices:
		if(!(in.get(0).equals("CODE:4808:UPDATEKPI"))){ 
			for(String al: in){
				 System.out.println(al);

				 String[] splitter = al.split("4h4f");
             KPI kpi = new KPI();
             kpi.setMemberID(splitter[1]);
             kpi.setFixtureID(splitter[2]);
             kpi.setsTackles(splitter[3]);
             kpi.setuTackles(splitter[4]);
             kpi.setsCarries(splitter[5]);
             kpi.setuCarries(splitter[6]);
             kpi.setsPasses(splitter[7]);
             kpi.setuPasses(splitter[8]);
             kpi.setHandlingErrors(splitter[9]);
             kpi.setPenalties(splitter[10]);
             kpi.setYellowCards(splitter[11]);
             kpi.setTriesScored(splitter[12]);
             kpi.setTurnoversWon(splitter[13]);
             kpi.setsThrowIns(splitter[14]);
             kpi.setuThrowIns(splitter[15]);
             kpi.setsLineOutTakes(splitter[16]);
             kpi.setuLineOutTakes(splitter[17]);
             kpi.setsKicks(splitter[18]);
             kpi.setuKicks(splitter[19]);
             kpiRepo.insert(kpi);
			 }
			 
			 			 
		 }
		 return kpiRepo.getAllKPIs(tableSize); 
	}
}