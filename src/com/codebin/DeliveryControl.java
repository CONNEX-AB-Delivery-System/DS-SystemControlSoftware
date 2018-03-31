package com.codebin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class DeliveryControl {

    private JPanel panelMain;
    private JFormattedTextField formattedTextField1;
    private JFormattedTextField connectionStatusCSLT;
    private JFormattedTextField ipAddressCSLTInput;
    private JFormattedTextField formattedTextField4;
    private JFormattedTextField connectionStatusDT;
    private JFormattedTextField ipAddressDTInput;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JButton connectDTButton;
    private JButton connectCSLTButton;
    private JLabel deliveryTruck;
    private JLabel CSLTruck;
    private JLabel ipAddressDTLabel;
    private JTextField ipAddressFTInput;
    private JButton connectFTButton;
    private JTextField connectionStatusFT;
    private JTextField textField3;
    private JTextArea textArea3;
    private JLabel ipAddressCSLTLabel;
    private JLabel ipAddressFTLabel;
    private JLabel forkliftTruck;

    private BufferedWriter pw;
    private BufferedReader pr;

    public DeliveryControl() {
        connectDTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ipAddressDTValue = ipAddressDTInput.getText();
                String connectionStatusDTValue = "";


                try {
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
                //TODO: Add connection to brick
            }
        });

        connectFTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Add connection to brick
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
