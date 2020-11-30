package me.MarioML.RocketClicker;


import com.sun.jna.Native;
import com.sun.jna.PointerType;
import com.sun.jna.win32.StdCallLibrary;
import me.MarioML.RocketClicker.handler.MouseHandler;
import me.MarioML.RocketClicker.modules.JitterModule;
import me.MarioML.RocketClicker.ui.MainTab;
import me.MarioML.RocketClicker.util.Timer;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.awt.*;
import java.util.Random;

public class Rocket implements Runnable {

    private static Rocket instance;
    public MouseHandler mouseHandler = new MouseHandler();
    private int clickerCps;
    private int clickerRand;
    private boolean enabled;
    private boolean jitterEnabled;
    private boolean toggled;
    private boolean skipNext;

    private Robot robot;
    private Thread thread;
    private Random random;
    private Timer timer;
    private AudioInputStream audioInputStream;

    private final String version = "RC-1.2";
    private final String email = "test@email.com";
    private final String username = "RocketClicker";
    private final String password = "root";

    private Rocket() {
        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeMouseListener(this.mouseHandler);
            JitterModule jitter = new JitterModule();

        } catch (NativeHookException exception) {
            exception.printStackTrace();
        }

        try {
            this.robot = new Robot();
        } catch (AWTException aWTException) {}


        this.random = new Random();
        this.timer = new Timer();


        this.thread = new Thread(this);
        this.thread.start();

    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1L);
                if (Rocket.getInstance().canClick() && isToggled() && isEnabled() && this.timer.isComplete(this.timer.convertToMS(getClickerDelay()))) {
                    setSkipNext(true);
                    this.robot.mousePress(16);

                    if (MainTab.getInstance().isClickSoundsChecked()) {
                        try {
                            if (MainTab.getInstance().getSound1().isSelected()) {
                                audioInputStream = AudioSystem.getAudioInputStream(Rocket.class.getClassLoader().getResource("me/MarioML/RocketClicker/assets/click1.wav"));
                            } else if (MainTab.getInstance().getSound2().isSelected()) {
                                audioInputStream = AudioSystem.getAudioInputStream(Rocket.class.getClassLoader().getResource("me/MarioML/RocketClicker/assets/click2.wav"));
                            }

                            Clip clip = AudioSystem.getClip();
                            clip.open(audioInputStream);

                            float volume = -9.0F - this.random.nextFloat() * 11.0F;
                            System.out.println(volume);

                            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                            gainControl.setValue(volume);

                            clip.start();

                        } catch (Exception exception) {
                        }
                    }

                    this.robot.mouseRelease(16);
                    this.timer.reset();
                }
            } catch (Exception exception) {}
        }
    }

    public boolean canClick() {
        if (Rocket.getInstance().inClicker()) {
            return false;
        }

        if (MainTab.getInstance().isMCOnlyChecked() && Rocket.getInstance().inWindow()) {
            return true;
        } else if (MainTab.getInstance().isMCOnlyChecked() && !Rocket.getInstance().inWindow()) {
            return false;
        }

        if (Rocket.getInstance().isEnabled() && Rocket.getInstance().isToggled()) {
            return true;
        }

        return false;
    }

    /*public interface User32 extends StdCallLibrary {
        WinDef.HWND GetForegroundWindow();
        User32 INSTANCE = Native.loadLibrary("user32", User32.class);
        int GetWindowTextA(PointerType param1PointerType, byte[] param1ArrayOfByte, int param1Int);
    }*/

    public float getClickerDelay() {
        if (21 > getClickerCps()) {
            return getClickerCps();
        }
        return (getClickerCps() - this.random.nextInt(getClickerRand()));
    }

    public boolean inClicker() {
        /*
        byte[] buffer = new byte[1024];
        WinDef.HWND hwnd = User32.INSTANCE.GetForegroundWindow();
        User32.INSTANCE.GetWindowTextA(hwnd, buffer, 1024);
        if (Native.toString(buffer).toLowerCase().contains("rocket")) {
            return true;
        } */
        return false;
    }

    public boolean inWindow() {
        /*
        byte[] buffer = new byte[1024];
        WinDef.HWND hwnd = User32.INSTANCE.GetForegroundWindow();
        User32.INSTANCE.GetWindowTextA(hwnd, buffer, 1024);
        if (Native.toString(buffer).toLowerCase().contains("minecraft")) {
            return true;
        } */

        return false;
    }


    public static Rocket getInstance() {
        if (instance == null) {
            instance = new Rocket();
        }
        return instance;
    }

    public void setClickerCps(int clickerCps) {
        this.clickerCps = clickerCps;
    }

    public int getClickerCps() {
        return this.clickerCps;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean active) {
        this.enabled = active;
    }

    public boolean isToggled() {
        return this.toggled;
    }

    public void setToggled(boolean enabled) {
        this.toggled = enabled;
    }

    public boolean isSkipNext() {
        return this.skipNext;
    }

    public void setSkipNext(boolean skipNext) {
        this.skipNext = skipNext;
    }

    public boolean setJitterEnabled(boolean activated) {return this.jitterEnabled = activated; }

    public boolean isJitterEnabled() { return this.jitterEnabled; }

    public String getVersion()  { return version; }

    public String getUsername() { return username; }

    public String getEmail() { return email; }

    public String getPassword() { return password; }

    public int getClickerRand() { return this.clickerRand; }

}

