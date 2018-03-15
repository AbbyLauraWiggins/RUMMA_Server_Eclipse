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
	private Object serviceNotice(HashMap mapIn){
   	 
   	 if(mapIn.get("CONTENT") instanceof String){
   		 if(mapIn.get("CONTENT").equals("show")){
   			 NoticeRepo noticeRepo = new NoticeRepo();
         	 ArrayList<ArrayList<String>> notices = noticeRepo.getNotices();
         	 Object out = (ArrayList<ArrayList<String>>) notices;
         	 return out;
   		 }
      	 
   	 }else{ 
      	//TODO change this for being sent an Arraylist<Arraylist>
   		 ArrayList<ArrayList<String>> noticesToAdd = (ArrayList<ArrayList<String>>) mapIn.get("CONTENT");
      	 NoticeRepo noticeRepo = new NoticeRepo();
   		 for(ArrayList al : noticesToAdd){
   			 Notice notice = new Notice();
             notice.setMemberId((String) al.get(0)); //for testing
             notice.setDate((String) al.get(2));
             notice.setContents((String) al.get(1));
             noticeRepo.insert(notice);
   		 }
          noticeRepo.closeConnection();
          
          Object out = (String) "Notices added";
          return out;
      }
   	 
   	 return null;
    }
}