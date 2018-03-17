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
import Database.Repo.NoticeRepo;
import Database.Schema.Notice;

public class ServerThreads extends Thread{

    private Socket clientSocket;

    public ServerThreads(Socket clientSocket){
        super();
        this.clientSocket = clientSocket;
    }

    public void run(){
        try {
           	//Will also receive from client a HashMap<String TYPE, Object CONTENT> 
      	  
      	   ObjectOutputStream outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream inFromClient = new ObjectInputStream(clientSocket.getInputStream());
            
            @SuppressWarnings("unchecked")
				HashMap<String, Object> mapIn = (HashMap<String, Object>) inFromClient.readObject();
            
            if(mapIn.get("TYPE").equals("NOTICE")){
            	System.out.println("TYPE = NOTICE");
            	outToClient.writeObject(serviceNotice(mapIn));
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
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private ArrayList<Notice> serviceNotice(HashMap mapIn){
   	 System.out.println("Service notice");
		 NoticeRepo noticeRepo = new NoticeRepo();

   	 
   	 if(!(mapIn.get("CONTENT") instanceof String)){
   		 //Add all new notices to the server, then return with servers copy of Notice Table
      	 //ArrayList<Notice> noticesToAdd = convertToNotices((ArrayList<String>) mapIn.get("CONTENT")); //INSERT THESE
      	 ArrayList<Notice> noticesToAdd = (ArrayList<Notice>) mapIn.get("CLASS");
      	 
      	 if(noticesToAdd != null){
      		 for(Notice n: noticesToAdd){
         		 noticeRepo.insert(n);
         	 } 
      	 }
       }
   	 
   	 //ArrayList<Notice> noticeTableObj = noticeRepo.getTableObject(); //gets ALL notices on server
   	 
   	 return noticeRepo.getTableObject();
    }
    
    private ArrayList<Notice> convertToNotices(ArrayList<ArrayList<String>> notices){
   	 System.out.println("convert to notices");
   	 ArrayList<Notice> noticeObj = new ArrayList<>();
   	 if(notices != null){
   		 for(ArrayList al: notices){
   			 System.out.println(al);
   			 Notice notice = new Notice();
   			 notice.setMemberId((String) al.get(0));
   			 notice.setContents((String) al.get(1));
   			 notice.setDate((String) al.get(2));
   			 
   			 noticeObj.add(notice);

   		 }
   	 }
   	 
   	 return noticeObj;
    }
}