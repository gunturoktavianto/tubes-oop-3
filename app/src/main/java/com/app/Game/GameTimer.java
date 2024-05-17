package com.app.Game;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    private Timer timer;
    private long startTime; 
    private long currentTime;
    private long passedTime; //Total waktu game

    public GameTimer(Timer Time){
        this.timer = Time;
    }

    public void start(){
        startTime = System.currentTimeMillis();
        
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                currentTime = System.currentTimeMillis();
                passedTime = (currentTime - startTime) / 1000;
                System.out.println("Game Time: " + passedTime + " seconds");
            }
        }, 0, 1000
        );
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
        }
    }
}