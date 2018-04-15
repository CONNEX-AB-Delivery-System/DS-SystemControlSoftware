package com.codebin;

import java.io.*;
import java.net.Socket;

public class SocketListener implements Runnable {

    protected Socket clientSocket = null;
    protected String serverText = null;
    protected BufferedWriter writer;
    protected BufferedReader reader;

    public SocketListener(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public SocketListener(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText = serverText;
    }

    /*public SocketListener(Socket clientSocket, String serverText, BufferedReader reader, BufferedWriter writer) {
        this.clientSocket = clientSocket;
        this.serverText = serverText;
        this.reader = reader;
        this.writer = writer;
    }*/

    public void run() {
        try {
            DeliveryControl.prDT = new BufferedReader(new InputStreamReader(
                    clientSocket.getInputStream()));
            DeliveryControl.pwDT = new BufferedWriter(new OutputStreamWriter(
                    clientSocket.getOutputStream()));


            String line;
            while (DeliveryControl.isConnectedDT) {
                line = DeliveryControl.prDT.readLine();
                System.out.println("RECEIVED " + line);
                //DeliveryControl.setOutputFromDTValue();

            }
            //reader.close();
            //writer.close();
            //System.out.println("Request processed: " + time);

        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }
}
