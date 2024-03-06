import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Chat_Server {
    public static final int PORT = 24002;

    public static void main(String[] args) {

        System.out.println("Welcome to GCC Chatting room");
        System.out.print("Please enter your name: ");
        Scanner scanner = new Scanner(System.in);
        String user_name = scanner.nextLine().trim();

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            Socket socket = serverSocket.accept();
            System.out.println("Request received, socket is : " + socket);

            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            do{
                String message_in = inputStream.readUTF();
                System.out.println(message_in);

                System.out.print(user_name + ": ");
                String message_out = scanner.nextLine().trim();
                message_out = user_name + ": " + message_out;
                outputStream.writeUTF(message_out);

            }while(true);

        }catch (IOException e){
        }
    }

}
