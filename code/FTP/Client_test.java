import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client_test {

    public static final int PORT = 24001;

    public static final String HOST = "127.0.0.1";

    public static void main(String[] args) {

        System.out.println("Try to connect to " + HOST + ": "+ PORT);
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Succeed: socket: " + socket);

            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            // 1. write Boolean
            boolean marryme = false;
            outputStream.writeBoolean(marryme);

            // 3. write a double
            Double price = 3.1415926;
            outputStream.writeDouble(price);

            // 4. send 26 alphabets
            byte [] alphabet = new byte[26];
            for(int i=0; i<26; i++)
                alphabet[i] = (byte)('a' + i);
            outputStream.write(alphabet, 0, 26);

            // 4.1
            int N = 655350; // x10
            byte [] bytes = new byte[N];
            outputStream.writeInt(N);
            outputStream.write(bytes, 0, N);

            // 2. read a ACK
            String reply;
            reply = inputStream.readUTF();
            System.out.println(reply);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
