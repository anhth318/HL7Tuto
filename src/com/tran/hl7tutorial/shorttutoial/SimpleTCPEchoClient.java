package com.tran.hl7tutorial.shorttutoial;

import java.net.*;
import java.io.*;

public class SimpleTCPEchoClient {

    public static void main(String[] args) throws IOException {

        String testMessage = "This is a test message that the client will transmit";
        byte[] byteBuffer = testMessage.getBytes();

        // Create socket that is connected to a server running on the same machine on port 1080
        Socket socket = new Socket("localhost", 1080);
        System.out.println("Connected to Server");

        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        // Send the message to the server
        out.write(byteBuffer);

        in.read(byteBuffer);

        System.out.println("Message received from Server: " + new String(byteBuffer));

        // Close the socket and its streams
        socket.close();
    }
}
