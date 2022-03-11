package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client_byte {

    public static final String IP_ADDR = "127.0.0.1";
    public static final int PORT_NUM = 2400;

    public static void main(String[] args){

        try {
            Socket socket = new Socket(IP_ADDR, PORT_NUM);
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            // read image
            BufferedImage image = ImageIO.read(new File("gcc.jpg"));

            // convert it to byte stream
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", bos );
            byte [] data = bos.toByteArray();

            // first send the length, how many Bytes to be sent
            outputStream.writeInt(data.length);
            System.out.println("after write length : " + data.length);

            // then, sends the data
            outputStream.write(data);
            System.out.println("write data");

        }catch(UnknownHostException e){
            System.out.println("UnknownHostException");
        }catch (IOException e){
            System.out.println("IOException");
            System.out.println(e);
        }

    }


}
