import java.io.*;
import java.net.*;
public class clientServer {
    public static void main(String[] args) throws IOException{
        Socket socket = new Socket("localhost", 8000);

        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        String file = "/home/user/DistributedFinalGit/DistributedFinal-1/small_random_numbers.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = " ";
            int count = 0;
            
            // while ((line = br.readLine()) != null){
            //     count++;
            // }
            
            // br.close();

            // output.writeInt(count);

            // br = new BufferedReader(new FileReader(file));

            // while ((line = br.readLine()) != null){
            //     String [] tokens = line.split(" ");
            //     output.writeInt(Integer.parseInt(tokens[0]));
            // }

            // System.out.println("Server says: " + input.readUTF());
            // //System.out.println("Server says: " + input.readUTF());

            while ((line = br.readLine())!= null){
                if (!line.isEmpty()){
                    String [] tokens = line.split(" ");
                    for (int i = 0; i < tokens.length; i++){
                        output.writeInt(Integer.parseInt(tokens[0]));
                        count++;
                    }
                }
            }

            output.writeInt(count);

            String response = input.readUTF();

            System.out.println("Server says: " + response);
            System.out.println("Server says: " + input.readUTF());
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        
        
        //String response = input.readUTF();
        
        
        
        input.close();
        output.close();
        socket.close();
    }
}
