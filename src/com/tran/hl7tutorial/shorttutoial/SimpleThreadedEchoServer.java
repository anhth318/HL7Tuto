package com.tran.hl7tutorial.shorttutoial;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleThreadedEchoServer {
    private int listenPort;

    public SimpleThreadedEchoServer(int aListenPort) {
        listenPort = aListenPort;
    }

    public static void main(String[] args) {
        SimpleThreadedEchoServer server = new SimpleThreadedEchoServer(1080);
        server.acceptIncomingConnections();
    }

    private void acceptIncomingConnections() {
        try {
            ServerSocket server = new ServerSocket(listenPort, 5); //Accept up to 5 clients in the queue
            System.out.println("Server has been started");
            Socket clientSocket = null;
            while (true) {
                clientSocket = server.accept();
                handleIncomingConnection(clientSocket);
            }
        } catch (BindException e) {
            System.out.println("Unable to bind to port " + listenPort);
        } catch (IOException e) {
            System.out.println("Unable to instantiate a ServerSocket on port: " + listenPort);
        }
    }

    protected void handleIncomingConnection(Socket aConnectionToHandle) {
        new Thread(new ConnectionHandler(aConnectionToHandle)).start();
    }

    private static class ConnectionHandler implements Runnable {
        private Socket connection;
        private int receivedMessageSize;
        private byte[] receivedByeBuffer = new byte[BUFFER_SIZE];
        private static final int BUFFER_SIZE = 32;

        public ConnectionHandler(Socket aClientSocket) {
            connection = aClientSocket;
        }

        public void run() {
            try {
                System.out.println("Handling client at " + connection.getInetAddress().getHostAddress()
                        + " on port " + connection.getPort());

                InputStream in = connection.getInputStream();
                OutputStream out = connection.getOutputStream();

                receivedMessageSize = in.read(receivedByeBuffer);
                out.write(receivedByeBuffer, 0, receivedMessageSize);

                connection.close();  // Close the socket.  We are done serving this client

            } catch (IOException e) {
                System.out.println("Error handling a client: " + e);
            }
        }
    }

}
