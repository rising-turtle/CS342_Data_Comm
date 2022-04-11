package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class Server_png {

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

            // receive and save png
            BufferedImage image = ImageIO.read(ImageIO.createImageInputStream(inputStream));
            ImageIO.write(image, "png", new File("received.png"));

            System.out.println("Image received!");

            // show image
            show_image(image);

        }catch(UnknownHostException e){
            System.out.println("UnknownHostException");
        }catch (IOException e){
            System.out.println("IOException");
        }



    }


}
