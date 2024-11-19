import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.*;
import java.util.*;

public class masterServer {
    public static void main(String[] args) throws IOException {
        try {
            ServerSocket socket = new ServerSocket(8000);
            boolean listening = true;
        
            while (listening){
                new Thread (new Handler (socket.accept())).start();
            }

            socket.close();

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

}

class Handler implements Runnable {
    private Socket client; 

    public Handler (Socket c){
        this.client = c;
    }

    public void run (){

        String line = " ";

        try {
            BufferedReader br = new BufferedReader (new InputStreamReader (client.getInputStream()));
            PrintWriter pw = new PrintWriter (client.getOutputStream(), true);

            while ((line = br.readLine()) != null){
                System.out.println("Client says: " + line); 
                pw.println("Server received: " + line);   
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        } 
        finally {
            try {
                client.close(); 
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }
}



