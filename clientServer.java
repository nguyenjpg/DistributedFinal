import java.io.*;
import java.net.*;
public class clientServer {
    public static void main(String[] args) throws IOException{
        Socket socket = null;
        DataInputStream input = null;
        DataOutputStream output = null;
       

        String file = "/home/user/DistributedFinalGit/DistributedFinal-1/testValues.txt";

        try {
            socket = new Socket("localhost", 8000);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
           
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line = " ";
            int count = 0;
            

            while ((line = br.readLine())!= null){
                if (!line.isEmpty()){
                    String [] tokens = line.split(" ");
                    for (int i = 0; i < tokens.length; i++){
                        count++;
                    }
                }
            }

            output.writeInt(count);
            output.flush();

            br.close();
            br = new BufferedReader(new FileReader(file));

            while ((line = br.readLine()) != null){
                if (!line.isEmpty()){
                    String [] tokens = line.split(" ");
                    for (int i = 0; i < tokens.length; i++){
                        output.writeInt(Integer.parseInt(tokens[i]));
                    }
                }
            }

            try {
                System.out.println("Server says: " + input.readUTF());
                System.out.println("Server says: " + input.readUTF()); 
            } catch (EOFException ex) {
                System.err.println("Unexpected end of stream. Ensure the server sends all responses.");
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
                if (input != null) {
                    input.close();
                }
                if (socket != null){
                    socket.close();
                } 
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        
        }
    }
}
