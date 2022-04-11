package com.company;

import java.net.Socket;
import java.util.Scanner;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.UnknownHostException;
import java.io.IOException;

public class Client_chatting {

    public static final int PORT = 24001;
    public static final String HOST = "127.0.0.1";

    public static void main(String[] args) {

        // input your name
        System.out.println("Welcome to GCC chatting room");
        System.out.print("Please enter your name: ");
        Scanner scan = new Scanner(System.in);

        String user_name = scan.nextLine().trim();

        try {
            // build socket connection
            Socket socket = new Socket(HOST, PORT);
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            while (true) {

                // read input message
                System.out.print(user_name + ": ");
                String message = scan.nextLine();
                // String nowhitespace = message.replaceAll("\\s+", "");
                // System.out.println("message: "+message + " nowhitespace: "+nowhitespace);

                if (message.equalsIgnoreCase("quit")) {
                    System.out.println("bye-bye");
                    message = user_name + ": bye-bye";
                    outputStream.writeUTF(message);
                    break;
                }

                message = user_name + ": " + message;

                // send the message out to server
                outputStream.writeUTF(message);

                // get response
                String response = inputStream.readUTF();

                // print out
                // System.out.println(message);
                System.out.println(response);
            }

        }catch(UnknownHostException e){
            System.out.println("unknown exception happens");
        }catch (IOException exception){
            System.out.println("IO Exception happens");
        }
    }


}
