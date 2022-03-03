package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Server_chatting {

    public static final int PORT = 24001;

    public static void main(String[] args){

        System.out.println("Welcome to GCC chatting room");
        System.out.print("Input your name: ");
        Scanner scan = new Scanner(System.in);
        String usr_name = scan.nextLine().trim();

        try {
            // create server socket
            ServerSocket socket_listen = new ServerSocket(PORT);

            // waiting for socket connection
            Socket socket = socket_listen.accept();

            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            do{
                String message_in = inputStream.readUTF();
                System.out.println(message_in);

                String[] message_split = message_in.split(" ");
                if(message_split.length > 1 && message_split[1].equalsIgnoreCase("bye-bye")){
                    System.out.println("client has quitted, I'm leaving now");
                    break;
                }

                System.out.print(usr_name + ": ");
                String message_out = scan.nextLine().trim();

                message_out = usr_name + ": " + message_out;
                outputStream.writeUTF(message_out);



            }while (true);

        }catch(UnknownHostException exception){
            System.out.println("UnknownHost EXCEPTION");
        }catch(IOException exception){
            System.out.println("IO EXCEPTION");
        }




    }


}
