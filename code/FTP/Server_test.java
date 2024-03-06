import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_test {

    public static final int PORT = 24001;

    public static void main(String[] args) {

        try {
			System.out.println("Listening to PORT: " + PORT);
            ServerSocket serverSocket = new ServerSocket(PORT);
            Socket socket = serverSocket.accept();
            System.out.println("Request received, socket is : "+socket);

            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            // 1. read Boolean
            boolean yesorNo = false;
            yesorNo = inputStream.readBoolean();
            System.out.println(yesorNo? "yes" : "no");

            // 3. read double
            Double price = inputStream.readDouble();
            System.out.println("The price is: " + price);

            // 4. read alphabets
            byte [] alphabet = new byte[26];
            inputStream.read(alphabet, 0, 26);
            for(int i=0; i<26; i++)
                System.out.print((char)alphabet[i]);
            System.out.println();

            // 4.1 What if the receiver do not know the size of the array
            int N = inputStream.readInt();
            byte [] bytes = new byte[N];
            // int NofBytesRead = inputStream.read(bytes, 0, N);

            // 4.2 What if the reader cannot obtain all the data at once?
            int NofBytesRead = 0;
            while(NofBytesRead != N){
                int readBytes = inputStream.read(bytes, NofBytesRead, N - NofBytesRead);
                NofBytesRead += readBytes;
                System.out.println("read Bytes this time: " + readBytes);
                System.out.println("read Bytes so far: " + NofBytesRead);
            }

            // 2. write a ACK
            String response = "ACK";
            outputStream.writeUTF(response);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
