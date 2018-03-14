import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server{
    public static void main(String[] args){
        //super();

        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        int portNumber = 9002;

        try{
            serverSocket = new ServerSocket(portNumber);
            while(true) {
                try {
                    clientSocket = serverSocket.accept();
                    new ServerThreads(clientSocket).start();

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