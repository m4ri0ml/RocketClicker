package me.MarioML.RocketClicker.util;

public class Timer
{
    private final long startTime;
    private long lastTime;

    public Timer() {
        this.lastTime = -1L;


        this.lastTime = now();
        this.startTime = this.lastTime;
    }


    public static long now() { return System.currentTimeMillis(); }


    public boolean isComplete(int milliseconds) {
        long d = getElapsedTime();
        return (d > milliseconds);
    }


    public short convertToMS(float perSecond) { return (short)(int)(1000.0F / perSecond); }



    public void reset() { this.lastTime = now(); }



    public long getElapsedTime() { return now() - this.lastTime; }
}
