package com.codebin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

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
    private JTextField textField3;
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

    private BufferedWriter pw;
    private BufferedReader pr;

    public DeliveryControl() {
        //set default values
        ipAddressDTInput.setValue("172.16.42.25");
        ipAddressCSLTInput.setValue("172.16.42.26");
        ipAddressFTInput.setValue("172.16.42.24");




       /* try {
            pr.readLine();
            outputFromDT.setText(pr.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        connectDTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ipAddressDTValue = ipAddressDTInput.getText();
                String connectionStatusDTValue = "";


                try {
                    //https://docs.oracle.com/javase/7/docs/api/java/net/Socket.html
                    Socket socket = new Socket(ipAddressDTValue, 19231);
//			        Socket socket = new Socket("127.0.0.1", 19231);//for mocking
                    pw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    pr = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    connectionStatusDTValue = pr.readLine();
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
                    Socket socket = new Socket(ipAddressCSLTValue, 19231);
//			        Socket socket = new Socket("127.0.0.1", 19231);//for mocking
                    pw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    pr = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    connectionStatusCSLTValue = pr.readLine();
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
                    Socket socket = new Socket(ipAddressFTValue, 19231);
//			        Socket socket = new Socket("127.0.0.1", 19231);//for mocking
                    pw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    pr = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    connectionStatusFTValue = pr.readLine();
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
                    case "left":
                        sendCommand("LEFT-PRESS");
                        break;
                    case "right":
                        sendCommand("RIGHT-PRESS");
                        break;
                }
            }
        });
        disconnectDTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendCommand("STOP");
                connectionStatusDT.setValue("disconnected");
            }
        });
    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("DeliveryControl");
        frame.setContentPane(new DeliveryControl().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.pack();
        frame.setVisible(true);


    }

    private void sendCommand(String command) {
        try {
            pw.write(command+"\n");pw.flush();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


}
