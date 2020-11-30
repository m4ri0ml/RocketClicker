package me.MarioML.RocketClicker.ui;

import javax.swing.*;

public class Frame extends JFrame {


    private static Frame instance;

    public static Frame getInstance() {
        if (instance == null) {
            instance = new Frame();
        }
        return instance;
    }

    public void createGui() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        setTitle("Rocket Clicker");
        setUndecorated(true);
        setContentPane(IntroTab.getInstance().getIntroPane());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/me/MarioML/RocketClicker/assets/rocketlogo.png")).getImage());
        setBounds(100, 100, 367, 315);
        setResizable(false);
        setVisible(true);


    }

    public void setLoginTab() {
        Frame.getInstance().setContentPane(LoginTab.getInstance().getLoginPane());
    }
}
