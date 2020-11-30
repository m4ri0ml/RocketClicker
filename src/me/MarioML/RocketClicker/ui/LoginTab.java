	package me.MarioML.RocketClicker.ui;

import me.MarioML.RocketClicker.Rocket;
import me.MarioML.RocketClicker.util.HWID;
import me.MarioML.RocketClicker.util.JTLimit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class LoginTab extends JPanel {
    private static LoginTab instance;

    public static LoginTab getInstance() {
        if (instance == null) {
            instance = new LoginTab();
        }
        return instance;
    }

    private JPanel loginPane;
    private int result = 2;

    public LoginTab() {

        loginPane = new JPanel();
        loginPane.setBorder(BorderFactory.createEtchedBorder(2, new Color(227, 19, 48), new Color(227, 19, 48)));
        loginPane.setLayout(null);

        ImageIcon closeIcon = new ImageIcon(getClass().getResource("/me/MarioML/RocketClicker/assets/closeicon.png"));
        JLabel closeButton = new JLabel(closeIcon);
        closeButton.setBounds(329, 6, 30, 30);
        loginPane.add(closeButton);

        closeButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        JLabel loginImage = new JLabel("");
        loginImage.setFocusable(false);
        loginImage.setBounds(120, 15, 200, 160);
        loginImage.setIcon(new ImageIcon(getClass().getResource("/me/MarioML/RocketClicker/assets/loginicon.png")));
        loginPane.add(loginImage);

        JLabel usernameLabel = new JLabel("U:");
        usernameLabel.setFocusable(false);
        usernameLabel.setBounds(82, 187, 20, 20);
        usernameLabel.setFont(new Font("Sherif", Font.BOLD, 16));
        loginPane.add(usernameLabel);

        final JTextField usernameLogin = new JTextField(" Username");
        usernameLogin.setEditable(true);
        usernameLogin.setDocument(new JTLimit(15));
        usernameLogin.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        usernameLogin.setBounds(105, 183, 150, 30);
        loginPane.add(usernameLogin);

        usernameLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                usernameLogin.setText("");
            }
        });

        JLabel passwordLabel = new JLabel("P:");
        passwordLabel.setFocusable(false);
        passwordLabel.setBounds(82, 229, 20, 20);
        passwordLabel.setFont(new Font("Sherif", Font.BOLD, 16));
        loginPane.add(passwordLabel);

        final JPasswordField passwordLogin = new JPasswordField(" Password");
        passwordLogin.setEditable(true);
        passwordLogin.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        passwordLogin.setBounds(105, 225, 150, 30);
        passwordLogin.setDocument(new JTLimit(15));
        loginPane.add(passwordLogin);

        passwordLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                passwordLogin.setText("");
            }
        });


        JLabel logInButton = new JLabel();
        logInButton.setIcon(new ImageIcon(getClass().getResource("/me/MarioML/RocketClicker/assets/loginbutton.png")));
        logInButton.setBounds(265, 209, 60, 60);
        loginPane.add(logInButton);


        logInButton.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		String name = usernameLogin.getText();
        		char charPass[] = passwordLogin.getPassword();
        		String pass = String.valueOf(charPass);

        		if (name.equals(Rocket.getInstance().getUsername()) && pass.equals(Rocket.getInstance().getPassword())) {

        				try {
        					Thread.sleep(1000);
        					loginPane.setVisible(false);
        					Frame.getInstance().setContentPane(MainTab.getInstance().getModulesPane());

        				} catch (InterruptedException exc) {
        					exc.printStackTrace();
        				}
        		} else {
        		    JOptionPane.showMessageDialog(null, "   [ERROR] Incorrect username (RocketClicker) or password (root).    ");
        		}
        	}
        });
    }

    public JPanel getLoginPane() {
        return this.loginPane;
    }
}
