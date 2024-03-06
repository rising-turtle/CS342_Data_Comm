import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Chat_Client {

    public static final int PORT = 24002;
    public static final String HOST = "127.0.0.1";
    public static void main(String[] args) {

        // System.out.println("Try to connect to " + HOST + ": " + PORT);
        System.out.println("Welcome to GCC Chatting room");
        System.out.print("Please enter your name: ");
        Scanner scanner = new Scanner(System.in);
        String user_name = scanner.nextLine().trim();

        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Succeed: socket: " + socket);

            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            while (true){

                System.out.print(user_name + ": ");
                String message = scanner.nextLine();
                message = user_name + ": " + message;

                outputStream.writeUTF(message);
                String response = inputStream.readUTF();
                System.out.println(response);
            }


        } catch (IOException e) {


        }
    }
}
