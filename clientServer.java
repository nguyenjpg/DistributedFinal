import java.io.*;
import java.net.*;
public class clientServer {
    public static void main(String[] args) throws IOException{
        Socket socket = new Socket("localhost", 8000);

        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        String file = "/home/user/DistributedFinalGit/DistributedFinal-1/testValues.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = " ";
            int count = 0;
            
            while ((line = br.readLine()) != null){
                count++;
            }
            
            br.close();

            output.writeInt(count);

            br = new BufferedReader(new FileReader(file));

            while ((line = br.readLine()) != null){
                String [] tokens = line.split(" ");
                output.writeInt(Integer.parseInt(tokens[0]));
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        
        
        String response = input.readUTF();
        System.out.println("Server says: " + response);
        
        socket.close();
    }
}
