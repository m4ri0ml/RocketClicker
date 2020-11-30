package me.MarioML.RocketClicker.ui;

import me.MarioML.RocketClicker.Rocket;
import me.MarioML.RocketClicker.util.VersionChecker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class IntroTab extends JPanel {
    private static IntroTab instance;

    public static IntroTab getInstance() {
        if (instance == null) {
            instance = new IntroTab();
        }
        return instance;
    }

    private JPanel introPane;

    private IntroTab() {
        introPane = new JPanel();
        introPane.setBorder(BorderFactory.createEtchedBorder(2, new Color(227, 19, 48), new Color(227, 19, 48)));
        introPane.setLayout(null);

        ImageIcon closeIcon = new ImageIcon(getClass().getResource("/me/MarioML/RocketClicker/assets/closeicon.png"));
        JLabel closeButton = new JLabel(closeIcon);
        closeButton.setBounds(329, 6, 30, 30);
        introPane.add(closeButton);

        closeButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        JLabel picLabel = new JLabel(new ImageIcon(getClass().getResource("/me/MarioML/RocketClicker/assets/rocketlogo_small.png")));
        picLabel.setBounds(77, 15, 230, 215);
        introPane.add(picLabel);

        JButton purchaseButton = new JButton("Purchase");
        purchaseButton.setFocusable(false);
        purchaseButton.setBounds(70, 225, 130, 33);
        purchaseButton.setBackground(Color.BLACK);
        purchaseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openWeb("https://github.com/m4ri0ml/RocketClicker");
                JOptionPane.showMessageDialog(null, "  [UPDATE] This program is now free and open-source.  ", " Now Open-Source", JOptionPane.WARNING_MESSAGE);
            }

        });
        introPane.add(purchaseButton);


        JButton loginButton = new JButton("Login");
        loginButton.setFocusable(false);
        loginButton.setBounds(205, 225, 80, 33);
        loginButton.setBackground(Color.BLACK);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Rocket.getInstance().getVersion().equals(VersionChecker.getVersion())) {
                    introPane.setVisible(false);
                    Frame.getInstance().setContentPane(LoginTab.getInstance().getLoginPane());
                } else if (!VersionChecker.ic) {
                    JOptionPane.showMessageDialog(null, "  [CONNECTION] No reachable internet connection!   ", " Connection Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "  [UPDATE] A newer version of Rocket Clicker is out!  ", " Outdated Version", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        introPane.add(loginButton);
    }

    public void openWeb(String url) {
        try {
            String os = System.getProperty("os.name").toLowerCase();

            // Unix OS
            if (os.indexOf("nix") >=0 || os.indexOf("nux") >=0) {
                Runtime rt = Runtime.getRuntime();
                String[] browsers = { "epiphany", "firefox", "mozilla", "konqueror",
                        "netscape", "opera", "links", "lynx" };

                StringBuffer cmd = new StringBuffer();
                for (int i = 0; i < browsers.length; i++)
                    if(i == 0)
                        cmd.append(String.format(    "%s \"%s\"", browsers[i], url));
                    else
                        cmd.append(String.format(" || %s \"%s\"", browsers[i], url));

                // If the first didn't work, try the next browser and so on
                rt.exec(new String[] { "sh", "-c", cmd.toString() });

            // MAC OS
            }else if (os.indexOf("mac") >= 0) {
                Runtime rt = Runtime.getRuntime();
                rt.exec("open " + url);

            // Windows OS
            }else {
                Runtime rt = Runtime.getRuntime();
                rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
            }
        }catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    public JPanel getIntroPane() { return this.introPane; }
}
