package com.codebin;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 /**
 * This was written on jCrete 2015 on a LeJOS session by Steven Chin.
 *
 * We were given the task to build a lego and do something cool with it.
 * We built a pretty large tank and remote-controlled it via wi-fi with
 * simple socket commands.
 *
 * For more info, go to https://mihail.stoynov.com/?p=2909
 *
 * This is a mock server for testing if you don't have a lego at hand.
 *
 * @author Nayden Gochev, jug.bg
 * @author Mihail Stoynov, jug.bg
 *
 */
public class App extends JFrame {

    private static final long serialVersionUID = -8402983606638099877L;

    private final JButton left;
    private final JButton right;
    private final JButton up;
    private final JButton down;
    private final JButton stop;

    private BufferedWriter pw;

/*
    public class App {
        private JButton buttonSubmit;
        private JPanel panelMain;
        private JTextArea systemInfo;
        private JTextField systemCommand;

        public App() {
            buttonSubmit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
        }

        public static void main(String[] args) {
            JFrame frame = new JFrame("App");
            frame.setContentPane(new App().panelMain);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }
    }
*/

    public static void main(String[] args) {
        App remote = new App();
        remote.setSize(500, 500);
        remote.setVisible(true);
    }

    public App() {
        try {
            Socket socket = new Socket("172.16.42.26", 19231);
//			Socket socket = new Socket("127.0.0.1", 19231);//for mocking
            pw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        setLayout(new BorderLayout());



        left = new JButton("LEFT");
        this.getContentPane().add(left, BorderLayout.WEST);
        left.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                leftRelease();
//				System.out.println("LEFT-RELEASE\n");pw.flush();
            }


            @Override
            public void mousePressed(MouseEvent e) {
                leftPress();
//				System.out.println("LEFT-PRESS\n");pw.flush();
            }


        });
        right = new JButton("RIGHT");
        this.getContentPane().add(right, BorderLayout.EAST);
        right.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                rightRelease();
//				System.out.println("RIGHT-RELEASE\n");pw.flush();
            }



            @Override
            public void mousePressed(MouseEvent e) {
                rightPress();
//				System.out.println("RIGHT-PRESS\n");pw.flush();
            }


        });
        up = new JButton("UP");
        this.getContentPane().add(up, BorderLayout.NORTH);
        up.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                upRelease();
//				System.out.println("UP-RELEASE\n");pw.flush();
            }



            @Override
            public void mousePressed(MouseEvent e) {
                upPress();
//				System.out.println("UP-PRESS\n");pw.flush();
            }


        });
        down = new JButton("DOWN");
        this.getContentPane().add(down, BorderLayout.SOUTH);
        down.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                downRelease();
//				System.out.println("DOWN-RELEASE\n");
            }



            @Override
            public void mousePressed(MouseEvent e) {
                downPress();
//				System.out.println("DOWN-PRESS\n");
            }


        });
        stop = new JButton("STOP\n");
        this.getContentPane().add(stop, BorderLayout.CENTER);
        stop.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                sendCommand("STOP");
            }
        });

        //keypad arrows work only if the focus is on the stop button
        //lame but that's life
        stop.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent event) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyReleased(KeyEvent event) {
                if(KeyEvent.VK_UP == event.getKeyCode()){
                    upRelease();
                } else if(KeyEvent.VK_DOWN == event.getKeyCode()){
                    downRelease();
                } else if(KeyEvent.VK_LEFT == event.getKeyCode()){
                    leftRelease();
                } else if(KeyEvent.VK_RIGHT == event.getKeyCode()){
                    rightRelease();
                }
            }

            @Override
            public void keyPressed(KeyEvent event) {
                if(KeyEvent.VK_UP == event.getKeyCode()){
                    upPress();
                } else if(KeyEvent.VK_DOWN == event.getKeyCode()){
                    downPress();
                } else if(KeyEvent.VK_LEFT == event.getKeyCode()){
                    leftPress();
                } else if(KeyEvent.VK_RIGHT == event.getKeyCode()){
                    rightPress();
                }
            }
        });


        pack();
    }

    private void downPress() {
        sendCommand("DOWN-PRESS");
    }

    private void sendCommand(String command) {
        try {
            pw.write(command+"\n");pw.flush();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void downRelease() {
        sendCommand("DOWN-RELEASE");
    }

    private void leftPress() {
        sendCommand("LEFT-PRESS");
    }

    private void upRelease() {
        sendCommand("UP-RELEASE");
    }

    private void leftRelease() {
        sendCommand("LEFT-RELEASE");
    }
    private void rightRelease() {
        sendCommand("RIGHT-RELEASE");
    }

    private void upPress() {
        sendCommand("UP-PRESS");
    }

    private void rightPress() {
        sendCommand("RIGHT-PRESS");
    }
}