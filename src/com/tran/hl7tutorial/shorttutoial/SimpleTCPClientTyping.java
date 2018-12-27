package com.tran.hl7tutorial.shorttutoial;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class SimpleTCPClientTyping {
	public static void main(String[] args) throws IOException {
		
		String inputText;
		Scanner reader = null;
		byte[] byteBuffer = null;
		
		// Create socket that is connected to a server running on the same machine on port 1080
        Socket socket = new Socket("localhost", 1080);
        System.out.println("Connected to Server");

        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        
        reader = new Scanner(System.in);
        
        while (true) {

            inputText = reader.nextLine();

            // If you type 'EXIT', the application quits
            if (inputText.equals("EXIT")) {
                break;
            }
            
            byteBuffer = inputText.getBytes();

            // Send the message to the server
            out.write(byteBuffer);

            in.read(byteBuffer);

            System.out.println("Message received from Server: " + new String(byteBuffer));

        }
        //Close the socket and its streams
        socket.close();
        //Close the reader
        reader.close();
				
	}
	

}
