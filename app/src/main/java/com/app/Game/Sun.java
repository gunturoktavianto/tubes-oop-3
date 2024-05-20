package com.app.Game;


public class Sun {
    private static int sun = 25;

    public static synchronized void generateSun() {
        sun += 25;
    }

    public static synchronized int getSun()
    {
        return sun;
    }

    public static synchronized void setSun(int s)
    {
        sun = s;
    }
}
