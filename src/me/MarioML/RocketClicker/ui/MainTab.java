package me.MarioML.RocketClicker.ui;

import me.MarioML.RocketClicker.Rocket;
import me.MarioML.RocketClicker.destruct.Destruct;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainTab extends JPanel {

    private static MainTab instance;

    public static MainTab getInstance() {
        if (instance == null) {
            instance = new MainTab();
        }
        return instance;
    }

    private JLabel enableButton;
    private JLabel jitterEnableButton;
    private JPanel modulesPane;
    private JCheckBox clickSounds;
    private JCheckBox mcOnly;
    private JCheckBox onlyVer;
    private JCheckBox onlyHor;
    private JRadioButton sound1;
    private JRadioButton sound2;
    private JRadioButton sound3;

    private MainTab() {

        modulesPane = new JPanel();
        modulesPane.setBorder(BorderFactory.createEtchedBorder(2, new Color(227, 19, 48), new Color(227, 19, 48)));
        modulesPane.setLayout(null);


        JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
        tabbedPane.setFocusable(false);
        tabbedPane.setBounds(10, 49, 350, 260);
        modulesPane.add(tabbedPane);

        JLabel header = new JLabel("Rocket Clicker");
        header.setBounds(15, 6, 300, 38);
        header.setFont(new Font("Arial", Font.ROMAN_BASELINE, 18));
        header.setIcon(new ImageIcon(getClass().getResource("/me/MarioML/RocketClicker/assets/rocket.png")));
        modulesPane.add(header);


        ImageIcon closeIcon = new ImageIcon(getClass().getResource("/me/MarioML/RocketClicker/assets/closeicon.png"));
        JLabel closeButton = new JLabel(closeIcon);
        closeButton.setBounds(329, 6, 30, 30);
        modulesPane.add(closeButton);

        closeButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        /*
            Clicker module Tab
        */

        JPanel clicker = new JPanel();
        tabbedPane.addTab("   Clicker  ", null, clicker, null);
        clicker.setLayout(null);

        JLabel cpsLabel = new JLabel("Clicks Per Second:");
        cpsLabel.setFocusable(false);
        cpsLabel.setBounds(15, 11, 180, 30);
        clicker.add(cpsLabel);

        final JTextField cpsTextField = new JTextField();
        cpsTextField.setEditable(false);
        cpsTextField.setBounds(268, 13, 50, 35);
        cpsTextField.setColumns(10);
        clicker.add(cpsTextField);

        final JSlider sliderCps = new JSlider();
        sliderCps.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                if (!Rocket.getInstance().isSkipNext()) {
                    cpsTextField.setText("" + sliderCps.getValue());
                    Rocket.getInstance().setClickerCps(sliderCps.getValue());
                    System.out.println(sliderCps.getValue());
                }
            }
        });

        sliderCps.setValue(5);
        sliderCps.setFocusable(false);
        sliderCps.setBounds(160, 0, 120, 47);
        sliderCps.setMinimum(1);
        sliderCps.setMaximum(20);
        clicker.add(sliderCps);

        final ImageIcon buttonOnIcon = new ImageIcon(getClass().getResource("/me/MarioML/RocketClicker/assets/power-button-on.png"));
        final ImageIcon buttonOffIcon = new ImageIcon(getClass().getResource("/me/MarioML/RocketClicker/assets/power-button-off.png"));
        enableButton = new JLabel(buttonOffIcon);

        enableButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (!Rocket.getInstance().isSkipNext()) {
                    Rocket.getInstance().setToggled(!Rocket.getInstance().isToggled());
                    Rocket.getInstance().setEnabled(!Rocket.getInstance().isEnabled());
                    enableButton.setIcon(Rocket.getInstance().isToggled() ? buttonOnIcon : buttonOffIcon);

                }
            }
        });

        enableButton.setBounds(125, 137, 80, 50);
        enableButton.setFocusable(false);
        clicker.add(enableButton);

        mcOnly = new JCheckBox("Minecraft Only");
        mcOnly.setFocusable(false);
        mcOnly.setBounds(8, 44, 165, 33);
        clicker.add(mcOnly);

        clickSounds = new JCheckBox("Click Sounds (Experimental)");
        clickSounds.setFocusable(false);
        clickSounds.setBounds(8, 65, 270, 33);
        clicker.add(clickSounds);

        sound1 = new JRadioButton("Sound 1");
        sound1.setBounds(8, 83, 123, 55);
        sound1.setFocusable(false);
        sound1.setSelected(true);
        clicker.add(sound1);

        sound2 = new JRadioButton("Sound 2");
        sound2.setBounds(120, 83, 123, 55);
        sound2.setFocusable(false);
        sound2.setSelected(false);
        clicker.add(sound2);

        ButtonGroup soundButtons = new ButtonGroup();
        soundButtons.add(sound1);
        soundButtons.add(sound2);
        soundButtons.add(sound3);

        JLabel currentVersion = new JLabel("v1.2");
        currentVersion.setBounds(300, 164, 75, 25);
        currentVersion.setFocusable(false);
        clicker.add(currentVersion);

        /*
            Jitter Module
        */

        JPanel jitter = new JPanel();
        tabbedPane.addTab("    Jitter    ", null, jitter, null);
        jitter.setLayout(null);

        //jitterButton = new JCheckBox("Enable Jitter Click");
        //jitterButton.setBounds(15, 15, 150, 25);
        //jitterButton.setFocusable(false);
        //jitter.add(jitterButton);

        ImageIcon jitterButtonOnIcon = new ImageIcon(getClass().getResource("/me/MarioML/RocketClicker/assets/green-jitter.png"));
        ImageIcon jitterButtonOffIcon = new ImageIcon(getClass().getResource("/me/MarioML/RocketClicker/assets/red-jitter.png"));
        jitterEnableButton = new JLabel(jitterButtonOffIcon);

        jitterEnableButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Rocket.getInstance().setJitterEnabled(!Rocket.getInstance().isJitterEnabled());
                jitterEnableButton.setIcon(Rocket.getInstance().isJitterEnabled() ? jitterButtonOnIcon : jitterButtonOffIcon);

            }
        });

        jitterEnableButton.setBounds(10, 10, 80, 80);
        jitterEnableButton.setFocusable(false);
        jitter.add(jitterEnableButton);

        JLabel vjLabel = new JLabel("Vertical Speed: ");
        vjLabel.setFocusable(false);
        vjLabel.setBounds(15, 95, 140, 25);
        jitter.add(vjLabel);

        JTextField vjTF = new JTextField();
        vjTF.setEditable(false);
        vjTF.setBounds(290, 95, 39, 30);
        jitter.add(vjTF);

        JSlider verticalJitterSlider = new JSlider();
        verticalJitterSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                vjTF.setText("" + verticalJitterSlider.getValue());
            }
        });

        verticalJitterSlider.setBounds(145, 80, 150, 40);
        verticalJitterSlider.setFocusable(false);
        verticalJitterSlider.setMinimum(1);
        verticalJitterSlider.setMaximum(5);
        verticalJitterSlider.setValue(3);
        jitter.add(verticalJitterSlider);

        JLabel hjLabel = new JLabel("Horizontal Speed: ");
        hjLabel.setFocusable(false);
        hjLabel.setBounds(15, 145, 155, 25);
        jitter.add(hjLabel);

        JTextField hjTF = new JTextField();
        hjTF.setEditable(false);
        hjTF.setBounds(290, 148, 39, 30);
        jitter.add(hjTF);

        JSlider horizontalJitterSlider = new JSlider();
        horizontalJitterSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                hjTF.setText("" + horizontalJitterSlider.getValue());
            }
        });
        horizontalJitterSlider.setBounds(145, 135, 150, 40);
        horizontalJitterSlider.setFocusable(false);
        horizontalJitterSlider.setMinimum(1);
        horizontalJitterSlider.setMaximum(5);
        horizontalJitterSlider.setValue(3);
        jitter.add(horizontalJitterSlider);

        onlyVer = new JCheckBox("Only Vertical Jitter");
        onlyVer.setFocusable(false);
        onlyVer.setBounds(105, 15, 210, 20);
        jitter.add(onlyVer);

        onlyHor = new JCheckBox("Only Horizontal Jitter");
        onlyHor.setFocusable(false);
        onlyHor.setBounds(105, 35, 240, 20);
        jitter.add(onlyHor);

        ButtonGroup jitterCBG = new ButtonGroup();
        jitterCBG.add(onlyVer);
        jitterCBG.add(onlyHor);

        JCheckBox randomize = new JCheckBox("Randomize");
        randomize.setFocusable(false);
        randomize.setBounds(105, 55, 180, 20);
        jitter.add(randomize);

        /*
            Self Destruct module Tab
        */

        JPanel sf = new JPanel();
        tabbedPane.addTab("  Self Detruct  ", null, sf, "Cleans all clicker traces");
        sf.setLayout(null);

        JButton selfDestructButton = new JButton("Self-Destruct");
        selfDestructButton.setBackground(Color.BLACK);
        selfDestructButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Destruct des = new Destruct();
                des.Destruct();

            }

        });

        JCheckBox warnings = new JCheckBox("Enable Warnings");
        warnings.setFocusable(false);
        warnings.setBounds(20, 135, 165, 25);
        sf.add(warnings);

        JCheckBox openTemp = new JCheckBox("Open Temp");
        openTemp.setFocusable(false);
        openTemp.setBounds(185, 135, 135, 25);
        sf.add(openTemp);

        final ImageIcon sfIcon = new ImageIcon(getClass().getResource("/me/MarioML/RocketClicker/assets/bombicon.png"));
        JLabel sfLabel = new JLabel();
        sfLabel.setIcon(sfIcon);
        sfLabel.setBounds(135, -4, 100, 95);
        sf.add(sfLabel);

        selfDestructButton.setBounds(87, 90, 155, 33);
        selfDestructButton.setFocusable(false);
        sf.add(selfDestructButton);

        //
        // ACCOUNT INFO
        //

        JPanel info = new JPanel();
        tabbedPane.addTab("  Account Info  ", null, info, "Account information");
        info.setLayout(null);

        JLabel infoHeader = new JLabel("Account Details");
        infoHeader.setFont(new Font("Arial", Font.BOLD, 14));
        infoHeader.setBounds(15, 5, 220, 40);
        info.add(infoHeader);

        JLabel user = new JLabel("Username:");
        user.setBounds(15, 30, 150, 40);
        info.add(user);

        JTextField userTF = new JTextField(" " + Rocket.getInstance().getUsername());
        userTF.setBounds(155, 38, 150, 25);
        userTF.setEditable(false);
        info.add(userTF);

        JLabel email = new JLabel("Email: ");
        email.setBounds(15, 62, 150, 40);
        info.add(email);

        JTextField emailTF = new JTextField(" " + Rocket.getInstance().getEmail());
        emailTF.setBounds(155, 70, 150, 25);
        emailTF.setEditable(false);
        info.add(emailTF);

        JLabel version = new JLabel("Version:");
        version.setBounds(15, 92, 150, 40);
        info.add(version);

        JTextField versionTF = new JTextField(" " + Rocket.getInstance().getVersion());
        versionTF.setBounds(155, 100, 150, 25);
        versionTF.setEditable(false);
        info.add(versionTF);

        JLabel status = new JLabel("Account Status: ");
        status.setBounds(15, 122, 150, 40);
        info.add(status);

        JTextField statusTF = new JTextField(" Unrestricted");
        statusTF.setBounds(155, 130 , 150, 25);
        statusTF.setEditable(false);
        info.add(statusTF);

        JLabel TOS = new JLabel("Terms of Service");
    }

    public JPanel getModulesPane() {
        return this.modulesPane;
    }

    public boolean isClickSoundsChecked() {
        return clickSounds.isSelected();
    }

    public boolean isMCOnlyChecked() {
        return mcOnly.isSelected();
    }

    public boolean isOnlyVerChecked() { return onlyVer.isSelected(); }

    public boolean isOnlyHorChecked() { return onlyHor.isSelected(); }

    public JRadioButton getSound1() {
        return sound1;
    }

    public JRadioButton getSound2() {
        return sound2;
    }


}
