import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import Database.Data.DatabaseSetup;


public class Server{
    public static void main(String[] args){
        //super();
   	  DatabaseSetup db = new DatabaseSetup();
   	  db.connectToDB();
   	  db.deleteTables();
   	  db.createTables(); //create tables if they dont already exist
   	  
   	  Test tester = new Test();
   	  tester.insertTestData();
   	  
   	  db.closeDatabase();

        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        int portNumber = 9002;

        try{
            serverSocket = new ServerSocket(portNumber);
            while(true) {
                try {
               	 System.out.println("before client socket");
                    clientSocket = serverSocket.accept();
                    System.out.println("new client accepted");
                    new ServerThreads(clientSocket).start();
                    System.out.println("after new server thread");

                } catch (IOException e) {
                    System.err.println("IO error " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try{
                serverSocket.close();
            } catch (IOException e){
                System.out.println(e);
            }

        }
    }

}