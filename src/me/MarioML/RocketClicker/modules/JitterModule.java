package me.MarioML.RocketClicker.modules;

import me.MarioML.RocketClicker.Rocket;
import me.MarioML.RocketClicker.ui.MainTab;

import java.awt.*;
import java.util.Random;

public class JitterModule {
    private Robot robot;
    private Thread thread;

    public JitterModule() {
        try {
            this.robot = new Robot();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.thread = new Thread(new JitterModuleRunnable());
        this.thread.start();
    }

    public class JitterModuleRunnable implements Runnable {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (Rocket.getInstance().canClick() && Rocket.getInstance().isJitterEnabled()) {
                    int v = 5;
                    int h = 5;
                    long timeout = (1000 / 50);

                    if (MainTab.getInstance().isOnlyVerChecked()) {
                        JitterModule.this.robot.mouseMove(JitterModule.this.getXMove(1), JitterModule.this.getYMove(5));
                    }else if (MainTab.getInstance().isOnlyHorChecked()) {
                        JitterModule.this.robot.mouseMove(JitterModule.this.getXMove(5), JitterModule.this.getYMove(1));
                    }else{
                        JitterModule.this.robot.mouseMove(JitterModule.this.getXMove(v), JitterModule.this.getYMove(h));
                    }

                    try {
                        Thread.sleep(timeout);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    private int getXMove(int move) {
        Random ran = new Random();
        int max = move;
        int min = -move;
        int x = (int) MouseInfo.getPointerInfo().getLocation().getX();
        return min + ran.nextInt(max + 1 - min) + x;
    }


    private int getYMove(int move) {
        Random ran = new Random();
        int max = move;
        int min = -move;
        int y = (int) MouseInfo.getPointerInfo().getLocation().getY();
        return min + ran.nextInt(max + 1 - min) + y;
    }

}
