import java.util.Arrays;
import java.net.*;
import java.io.*;

public class bubblesort {

    public static void main (String [] args) throws IOException {
        Socket socket = new Socket ("localhost", 8000);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());


        try {
            while (true){
                int size = input.readInt();
        
        
                int [] numbers = new int[size];
        
                for (int i = 0; i < size; i++){
                    numbers[i] = input.readInt();
                }
        
                bubbleSort(numbers);
                output.writeInt(numbers.length);
        
                //System.out.println("Sorted numbers: " + Arrays.toString(numbers));
                for (int num : numbers) {
                    output.writeInt(num);
                }
        
            }
           
        }
        catch (Exception ex){
            ex.printStackTrace();
        }  finally {
            try {
                if (input != null){
                    input.close();
                } 
                if (output != null){
                    output.close();
                }
                if (socket != null){
                    socket.close();
                } 
            } catch (IOException ex) {
                System.err.println("Error closing client resources: " + ex.getMessage());
            }
        }
       

    }

    public static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }


}

