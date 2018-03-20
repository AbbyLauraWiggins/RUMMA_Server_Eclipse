import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import Database.Repo.FixtureRepo;
import Database.Repo.MemberRepo;
import Database.Repo.NoticeRepo;
import Database.Repo.SessionRepo;
import Database.Repo.StrengthAndConditioningRepo;
import Database.Repo.TeamFixturesRepo;
import Database.Repo.TeamRepo;
import Database.Schema.Fixture;
import Database.Schema.Notice;
import Database.Schema.StrengthAndConditioning;
import Database.Schema.Team;
import Database.Schema.TeamFixtures;

public class ServerThreads extends Thread{

    private Socket clientSocket;

    public ServerThreads(Socket clientSocket){
        super();
        this.clientSocket = clientSocket;
        String hostName = clientSocket.getInetAddress().getHostName();
        System.out.println("hostname " + hostName);
    }

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
	         		ArrayList<String> outList
	         		if(Integer.parseInt(in.get(1)) < 2){
			         	outList = serviceBasicSession(in);
	         		}else{
			         	outList = serviceAdvancedSession(in, tableSize);
	         		}
	         		System.out.println("outList: " + outList);
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
	          noticeRepo.closeConnection();
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

}