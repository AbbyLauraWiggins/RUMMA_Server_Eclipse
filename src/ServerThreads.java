import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import Database.Repo.MemberRepo;
import Database.Repo.NoticeRepo;
import Database.Schema.Notice;

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
            	//String valid = checkLogInValidation(in, outList);
            	outToClient.writeObject(outList);
            	//outToClient.writeObject(valid);
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
    
   private String checkLogInValidation(ArrayList<String> logInDetails, ArrayList<String> outList){
   	String email = logInDetails.get(0);
   	String password = logInDetails.get(1);
   	
   	for(String ol: outList){
			 System.out.println(ol);
			 
	       Notice notice = new Notice();
	       String[] splitter = ol.split("4h4f");
	       
	       if(splitter[2].equals(email) && splitter[3].equals(password)){
	      	 return "true";
	       }	       
			 
      }
      return "false";

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
		
		return members;
	}
   
}