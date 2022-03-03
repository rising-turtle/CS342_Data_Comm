package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client_cal {

    public static final int PORT = 2400;
    public static final String HOST = "127.0.0.1";

    public static void main(String[] args){

        try {
            Socket socket = new Socket(HOST, PORT);
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            do {
                System.out.println("input operations: ");
                Scanner scan = new Scanner(System.in);
                String oper = scan.nextLine();
                String[] oper_items = oper.split(" ");
                String message;
                if (oper_items.length < 3) {
                    if(oper.equalsIgnoreCase("quit")){
                        System.out.println("bye-bye");
                        outputStream.writeUTF("bye-bye");
                        break;
                    }
                    else {
                        System.out.println("acceptable input: a + b, a - b, a * b, a / b");
                        continue;
                    }
                }

                // sends out operation via socket
                outputStream.writeUTF(oper);

                // wait for result
                String output = inputStream.readUTF();
                System.out.println(oper + " = "+output);

            }while(true);

        }catch(UnknownHostException exception){
            System.out.println("UnknownHostException!");
        }catch(IOException exception){
            System.out.println("IO Exception");
        }




    }

}
