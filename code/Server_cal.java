package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server_cal {

    public static final int PORT = 2400;

    public static void main(String[] args){

        System.out.println("Welcome to GCC calculate server");
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            Socket socket = serverSocket.accept();

            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());

            do{
                String message = inputStream.readUTF();
                String[] msg_items = message.split(" ");
                if(msg_items.length < 3){
                    if(message.equalsIgnoreCase("bye-bye")){
                        System.out.println("bye-bye");
                        break;
                    }

                    System.out.println("input message: " + message + " is invalid");
                    continue;
                }

                float a = Float.valueOf(msg_items[0]);
                float b = Float.valueOf(msg_items[2]);
                float result;

                switch(msg_items[1]){

                    case "+":
                        System.out.println("ADD");
                        result = a + b;
                        break;
                    case "-":
                        System.out.println("SUB");
                        result = a - b;
                        break;
                    case "*":
                        System.out.println("MUL");
                        result = a * b;
                        break;
                    case "/":
                        System.out.println("DIV");
                        result = a / b;
                        break;
                    default:
                        System.out.println("Unknown");
                        result = -9999.F;
                        break;
                }

                String msg_result = Float.toString(result);
                outputStream.writeUTF(msg_result);

            }while(true);

        }catch (UnknownHostException e){

        }catch (IOException e){

        }





    }
}
