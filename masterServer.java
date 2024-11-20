import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

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

            // while ((line = br.readLine()) != null){
            //     System.out.println("Client says: " + line); 
            //     pw.println("Server received: " + line);   
            // }

            while (!client.isClosed()) {
                if (br.ready()) {
                    String incoming = br.readLine();
                    if (incoming != null) {
                        System.out.println("Client says: " + line);
                        pw.println("Server received: " + line);
                    }
                }

                pw.println("ping");
                System.out.println("Sent: ping");

                TimeUnit.SECONDS.sleep(5);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        } 
        finally {
            try {
                client.close();
            } catch (IOException ex) {
                System.out.println("Error closing client socket: " + ex.getMessage());
            }
        }
    }
}



