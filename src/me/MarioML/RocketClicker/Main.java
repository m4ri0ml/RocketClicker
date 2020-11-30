package me.MarioML.RocketClicker;

import me.MarioML.RocketClicker.ui.Frame;
import org.jnativehook.GlobalScreen;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static Main main;


    public static void main(String[] args) {

        Frame.getInstance().createGui();
    }
}