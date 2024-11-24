import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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
            PrintWriter pw = new PrintWriter (client.getOutputStream(), true);

            DataInputStream input = new DataInputStream(client.getInputStream());
            DataOutputStream output = new DataOutputStream(client.getOutputStream());

            while (!client.isClosed()) {
                if (input.available() > 0){
                    int size = input.readInt();
                    int [] values = new int [size];



                    for (int i = 0; i < size; i++){
                        values[i] = input.readInt();
                    }

                    int[] chunk1 = Arrays.copyOfRange(values, 0, size / 2);
                    int[] chunk2 = Arrays.copyOfRange(values, size / 2, size);

                    System.out.println("Received numbers: " + java.util.Arrays.toString(values));
                    int sum1 = 0;
                    for (int num : chunk1) {
                        sum1 += num;
                    }   
                    int sum2 = 0;
                    for (int num : chunk2) {
                        sum2 += num;
                    }  


                    
                output.writeUTF("Server received for chunk 1 " + chunk1.length + " numbers. Sum: " + sum1);
                output.flush();
                output.writeUTF("Server received for chunk 2 " + chunk2.length + " numbers. Sum: " + sum2);
                output.flush();

                }

                

                pw.println("ping");
                System.out.println("Sent: ping");
                

                TimeUnit.SECONDS.sleep(5);
                
            }
                // input.close();
                // output.close();
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



