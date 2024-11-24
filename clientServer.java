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

            System.out.println("Server says: " + input.readUTF());
            System.out.println("Server says: " + input.readUTF());
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
