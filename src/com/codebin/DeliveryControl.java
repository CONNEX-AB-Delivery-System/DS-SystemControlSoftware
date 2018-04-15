package com.codebin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

import static java.lang.Thread.*;

public class DeliveryControl {

    private JPanel panelMain;
    private JFormattedTextField runCommandDTInput;
    private JFormattedTextField connectionStatusCSLT;
    private JFormattedTextField ipAddressCSLTInput;
    private JFormattedTextField formattedTextField4;
    private JFormattedTextField connectionStatusDT;
    private JFormattedTextField ipAddressDTInput;
    private JTextArea outputFromDT;
    private JTextArea textArea2;
    private JButton connectDTButton;
    private JButton connectCSLTButton;
    private JLabel deliveryTruck;
    private JLabel CSLTruck;
    private JLabel ipAddressDTLabel;
    private JFormattedTextField ipAddressFTInput;
    private JButton connectFTButton;
    private JTextField runCommandFTInput;
    private JTextArea textArea3;
    private JLabel ipAddressCSLTLabel;
    private JLabel ipAddressFTLabel;
    private JLabel forkliftTruck;
    private JFormattedTextField connectionStatusFT;
    private JLabel connectionStatusDTLabel;
    private JButton disconnectDTButton;
    private JLabel runCommandDTLabel;
    private JButton runCommandDTButton;
    private JButton disconnectCSLTButton;
    private JButton disconnectFTButton;
    private JButton runCommandCSLTButton;
    private JButton runCommandFTButton;
    private JLabel connectionStatusCSLTLabel;
    private JLabel connectionStatusFTLabel;
    private JLabel runCommandCSLTLabel;
    private JLabel runCommandFTLabel;
    private JFormattedTextField orderInput;
    private JLabel SCSLabel;
    private JLabel orderLabel;
    private JButton orderButton;

    ActionThread threadDT;

    //variables for DT connection
    public static boolean isConnectedDT = false;
    private Socket socketDT;
    public static BufferedWriter pwDT;
    public static BufferedReader prDT;
    public static String ipAddressDTValue;
    public static String connectionStatusDTValue;

    //variables for FT connection
    private Socket socketFT;
    private BufferedWriter pwFT;
    private BufferedReader prFT;

    private SocketListener socketListenerDT;

    public void setOutputFromDTValue (String value) {
        outputFromDT.append(value);
    }

    public DeliveryControl() {
        //set default values
        ipAddressDTInput.setValue("ev3dev.local");
        ipAddressCSLTInput.setValue("ev3dev.local");
        ipAddressFTInput.setValue("ev3dev.local");

        connectDTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ipAddressDTValue = ipAddressDTInput.getText();

                connectionStatusDTValue = "";


                try {
                    //https://docs.oracle.com/javase/7/docs/api/java/net/Socket.html
                    //TODO: add port field
                    socketDT = new Socket(ipAddressDTValue, 8000);
                    isConnectedDT = true;

                    //socketListenerDT = new SocketListener( socketDT );
                    //socketListenerDT.run();

                    ActionThread threadDT = new ActionThread( socketDT, "ActionThread" );
                    Thread t = new Thread(threadDT);
                    t.start();

//			        Socket socket = new Socket("127.0.0.1", 19231);//for mocking
                    //pwDT = new BufferedWriter(new OutputStreamWriter(socketDT.getOutputStream()));
                    //prDT = new BufferedReader(new InputStreamReader(socketDT.getInputStream()));



                    //sendCommandDT("connected");

                    //connectionStatusDTValue = prDT.readLine();

                } catch (UnknownHostException exception) {
                    // TODO Auto-generated catch block
                     exception.printStackTrace();
                } catch (IOException exception) {
                    // TODO Auto-generated catch block
                    exception.printStackTrace();
                }


                connectionStatusDT.setValue("connected to:" + connectionStatusDTValue);
            }
        });

        connectCSLTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ipAddressCSLTValue = ipAddressCSLTInput.getText();
                String connectionStatusCSLTValue = "";


                try {
                    //https://docs.oracle.com/javase/7/docs/api/java/net/Socket.html
                    Socket socket = new Socket(ipAddressCSLTValue, 8000);
//			        Socket socket = new Socket("127.0.0.1", 19231);//for mocking
                    //pw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    //pr = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    //connectionStatusCSLTValue = pr.readLine();
                } catch (UnknownHostException exception) {
                    // TODO Auto-generated catch block
                    exception.printStackTrace();
                } catch (IOException exception) {
                    // TODO Auto-generated catch block
                    exception.printStackTrace();
                }


                connectionStatusCSLT.setValue("connected to:" + connectionStatusCSLTValue);
            }
        });

        connectFTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ipAddressFTValue = ipAddressFTInput.getText();
                String connectionStatusFTValue = "";


                try {
                    //https://docs.oracle.com/javase/7/docs/api/java/net/Socket.html
                    socketFT = new Socket(ipAddressFTValue, 8000);
//			        Socket socket = new Socket("127.0.0.1", 19231);//for mocking
                    pwFT = new BufferedWriter(new OutputStreamWriter(socketFT.getOutputStream()));
                    prFT = new BufferedReader(new InputStreamReader(socketFT.getInputStream()));

                    connectionStatusFTValue = prFT.readLine();
                } catch (UnknownHostException exception) {
                    // TODO Auto-generated catch block
                    exception.printStackTrace();
                } catch (IOException exception) {
                    // TODO Auto-generated catch block
                    exception.printStackTrace();
                }


                connectionStatusFT.setValue("connected to:" + connectionStatusFTValue);
            }
        });

        runCommandDTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String runCommandDTValue = runCommandDTInput.getText();

                switch (runCommandDTValue) {
                    case "kill":
                        sendCommandDT("KILL");
                        break;
                    case "run":
                        sendCommandDT("RUN");
                        break;
                    case "left":
                        sendCommandDT("LEFT-PRESS");
                        break;
                    case "right":
                        sendCommandDT("RIGHT-PRESS");
                        break;
                    case "exit":
                        sendCommandDT("STOP");
                        break;
                    case "deliver":
                        sendCommandDT("DELIVER");
                        break;
                }
            }
        });
        disconnectDTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendCommandDT("STOP");
                connectionStatusDT.setValue("disconnected");
                //socketListenerDT.
            }
        });


        runCommandFTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String runCommandFTValue = runCommandFTInput.getText();

                switch (runCommandFTValue) {
                    case "run":
                        sendCommandFT("RUN");
                        break;
                    case "left":
                        sendCommandFT("LEFT-PRESS");
                        break;
                    case "right":
                        sendCommandFT("RIGHT-PRESS");
                        break;
                    case "exit":
                        sendCommandFT("STOP");
                        break;
                    case "deliver":
                        sendCommandFT("DELIVER");
                        break;
                }
            }
        });

        /*String lineDT;

        while (true) {

            if (socketDT.isConnected())
                try {
                    lineDT = prDT.readLine();
                    outputFromDT.append(lineDT);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        } */

        disconnectFTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendCommandDT("KILL");
                connectionStatusDT.setValue("disconnected");
                //socketListenerDT.
            }
        });

        //ActionThread threadDT = new ActionThread( "socketDT" );
        //threadDT.start();
    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("DeliveryControl");
        frame.setContentPane(new DeliveryControl().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.pack();
        frame.setVisible(true);
    }

    private void sendCommandDT(String command) {
        try {
            pwDT.write(command+"\n");pwDT.flush();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void sendCommandFT(String command) {
        try {
            pwFT.write(command+"\n");pwFT.flush();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void sendCommand(String truck, String command) {
        String commandS = "";

        switch (command) {
            case "kill":
                commandS = "KILL";
                break;
            case "run":
                commandS = "RUN";
                break;
            case "left":
                commandS = "LEFT-PRESS";
                break;
            case "right":
                commandS = "RIGHT-PRESS";
                break;
            case "exit":
                commandS = "STOP";
                break;
            case "deliver":
                commandS = "DELIVER";
                break;
        }

        if (truck.equals("DT")) {
            try {
                pwDT.write(commandS+"\n");pwDT.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        if (truck.equals("CSLT")) {
            //TODO: implement this
        }

        if (truck.equals("FT")) {
            try {
                pwFT.write(commandS+"\n");pwFT.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }



    private class ActionThread extends Thread {
        private Thread t;
        private String threadName;
        protected Socket clientSocket = null;

        public ActionThread ( Socket clientSocket, String threadName) {
            this.clientSocket = clientSocket;
            this.threadName = threadName;
        }

        public void run() {
            DeliveryControl.connectionStatusDTValue = "test";

            //outputFromDT.append("command sended\n");

            try {
                DeliveryControl.prDT = new BufferedReader(new InputStreamReader(
                        clientSocket.getInputStream()));
                DeliveryControl.pwDT = new BufferedWriter(new OutputStreamWriter(
                        clientSocket.getOutputStream()));

                connectionStatusDTValue = prDT.readLine();
                connectionStatusDT.setValue("connected to:" + connectionStatusDTValue);

                String line;
                while (DeliveryControl.isConnectedDT) {
                    line = DeliveryControl.prDT.readLine();

                    if (line.equals("FINISHED")) {
                        outputFromDT.append("CMD " + line + "\n");
                        System.out.println("RECEIVED " + line);
                    }

                    //DeliveryControl.setOutputFromDTValue();

                    try {
                        //put some sleep
                        Thread.sleep(10 * 100);
                        //DeliveryTruck.isRunning = false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                //reader.close();
                //writer.close();
                //System.out.println("Request processed: " + time);

            } catch (IOException e) {
                //report exception somewhere.
                e.printStackTrace();
            }


            //connectionStatusDT.setValue("connected to:" + connectionStatusDTValue);

        }

        public void start() {
            if (t == null) {
                t = new Thread (this, this.threadName);
                t.start ();
            }
        }

    }


}
