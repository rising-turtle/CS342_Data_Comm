package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class Server_byte {

    public static final int PORT_NUM = 2400;

    public static void show_image(BufferedImage image){
        JFrame frame = new JFrame();
        frame.getContentPane().add(new JLabel(new ImageIcon(image)));
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args){

        try {
            ServerSocket serverSocket = new ServerSocket(PORT_NUM);
            Socket socket = serverSocket.accept();
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());

            // receive the length, how many Bytes to be received
            int length = inputStream.readInt();
            System.out.println("receive length: " + length);
            if(length > 0){
                // set up a byte array to receive the data
                byte[] data = new byte[length];

                // receive the data
                inputStream.readFully(data, 0, data.length);

                // put it to an array stream
                ByteArrayInputStream bis = new ByteArrayInputStream(data);

                // convert the array stream to an image file
                BufferedImage image = ImageIO.read(bis);
                ImageIO.write(image, "jpg", new File("received.jpg"));
                System.out.println("Image received!");

                // you can show the image
                show_image(image);
            }



        }catch(UnknownHostException e){
            System.out.println("UnknownHostException");
        }catch (IOException e){
            System.out.println("IOException");
        }



    }


}
