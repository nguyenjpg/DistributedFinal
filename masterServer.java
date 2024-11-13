import java.io.IOException;
import java.net.ServerSocket;
import java.net.*;

public class masterServer {
    public static void main(String[] args) throws IOException {
        try {
            ServerSocket socket = new ServerSocket(8000);
            System.out.println("Server is up!");

            Socket clientSocket = socket.accept();


        }
        catch (Exception ex){
            ex.printStackTrace();
        }


    }
}
