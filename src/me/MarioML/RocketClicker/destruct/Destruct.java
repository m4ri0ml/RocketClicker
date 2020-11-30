package me.MarioML.RocketClicker.destruct;

import me.MarioML.RocketClicker.Rocket;
import me.MarioML.RocketClicker.Main;
import org.jnativehook.GlobalScreen;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Destruct {

    private String machine = "HKEY_CURRENT_USER";
    private int machinePortID = 8006;

    public void Destruct() {
        this.deletedFiles = 0;
        deleteFiles();
        JOptionPane.showMessageDialog(null, "  Self-Destruct module cleared " + this.deletedFiles + " files.     ");
        check();
        System.exit(1);

    }

    private int deletedFiles;
    private void deleteFiles() {
        JOptionPane.showMessageDialog(null, "   Self-Destruct module activated. Destroying last activity data.    ");
        GlobalScreen.removeNativeMouseListener(Rocket.getInstance().mouseHandler);
        try {
            GlobalScreen.unregisterNativeHook();
            File dir = (new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath())).getParentFile();
            for (File f : dir.listFiles()) {
                if (f.getName().contains("json")) {
                    f.delete();
                    this.deletedFiles++;
                }
            }

            File recent = new File("C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Roaming\\Microsoft\\Windows\\Recent");
            if (recent == null)
                recent = new File("D:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Roaming\\Microsoft\\Windows\\Recent");
            if (recent != null) {
                for (File f : recent.listFiles()) {
                    if (shouldRemove(f.getName())) {
                        System.out.println("File deleted:" + f.getName());
                        f.delete();
                        this.deletedFiles++;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Couldn't find recent folder, AppData\\Roaming\\Microsoft\\Windows\\Recent");
            }

            File temp = new File("C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Local\\Temp");
            if (temp == null) temp = new File("D:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Local\\Temp");
            for (File f : temp.listFiles()) {
                if (shouldRemove(f.getName())) {
                    f.delete();
                    this.deletedFiles++;
                }
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private boolean shouldRemove(String s) {
        String msg = s.toLowerCase();
        List<String> words = new ArrayList<String>();
        try {
            words.addAll(Arrays.asList(new String[] { "winrm", "native", "GetMBSerial", "rocket", "java", "jar", "json", "custom", "automatic", (new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath())).getParentFile().getName(), "clicker" }));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        for (String str : words) {
            if (msg.toLowerCase().contains(str.toLowerCase())) {
                return true;
            }
        }

        return false;
    }


    private void check() {
        File temp = new File("C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Local\\Temp");
        if (temp == null) temp = new File("D:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Local\\Temp");
        for (File file : temp.listFiles()) {
            if (file.getName().contains("JNativeHook") || file.getName().contains("mysql-connector") || file.getName().contains("clicker")) {
                JOptionPane.showMessageDialog(null, "  Dangerous file found in temp: " + file.getName() + ", please delete this file.     ");
            }
        }

        try {
            Desktop.getDesktop().open(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
