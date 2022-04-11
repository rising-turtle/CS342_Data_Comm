package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client_png {

    public static final String IP_ADDR = "127.0.0.1";
    public static final int PORT_NUM = 2400;

    public static void main(String[] args){

        try {
            Socket socket = new Socket(IP_ADDR, PORT_NUM);
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            // read and send png via socket
            BufferedImage image = ImageIO.read(new File("Thanks.png"));
            ImageIO.write(image, "png", outputStream);

        }catch(UnknownHostException e){
            System.out.println("UnknownHostException");
        }catch (IOException e){
            System.out.println("IOException");
            System.out.println(e);
        }

    }


}
