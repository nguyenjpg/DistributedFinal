import java.util.Arrays;
import java.net.*;
import java.io.*;

public class bubblesort {

    public static void main (String [] args) throws IOException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(9000);
            System.out.println("Bubble Sort Server is running on port 9000...");

            while (true) {
                Socket socket = null;
                DataInputStream input = null;
                DataOutputStream output = null;

                try {
                    socket = serverSocket.accept();
                    System.out.println("Connected to master server.");

                    input = new DataInputStream(socket.getInputStream());
                    output = new DataOutputStream(socket.getOutputStream());

                    int size = input.readInt();
                    int[] numbers = new int[size];
                    for (int i = 0; i < size; i++) {
                        numbers[i] = input.readInt();
                    }

                    System.out.println("Received numbers to sort: " + Arrays.toString(numbers));

                    bubbleSort(numbers);

                    output.writeInt(numbers.length);
                    for (int num : numbers) {
                        output.writeInt(num);
                    }

                    System.out.println("Sorted numbers sent back: " + Arrays.toString(numbers));
                } catch (IOException ex) {
                    System.err.println("Error handling connection: " + ex.getMessage());
                } finally {
                    try {
                        if (input != null) 
                        {
                            input.close();
                        }
                        if (output != null){
                            output.close();
                        } 
                        if (socket != null){
                            socket.close();
                        } 
                    } catch (IOException ex) {
                        System.err.println("Error closing resources: " + ex.getMessage());
                    }
                }
            }
        } catch (IOException ex) {
            System.err.println("Error starting Bubble Sort Server: " + ex.getMessage());
        } finally {

            try {
                if (serverSocket != null) serverSocket.close();
            } catch (IOException ex) {
                System.err.println("Error closing server socket: " + ex.getMessage());
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

