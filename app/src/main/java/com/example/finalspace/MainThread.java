package com.example.finalspace;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {
    public static final int MAX_FPS = 60;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;



    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;

    }

    public static int getMaxFps() {
        return MAX_FPS;
    }

    public double getAverageFPS() {
        return averageFPS;
    }

    public SurfaceHolder getSurfaceHolder() {
        return surfaceHolder;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public boolean isRunning() {
        return running;
    }

    public static Canvas getCanvas() {
        return canvas;
    }

    @Override
    public void run() {
        long startTime;
        long timeMillis = 1000/MAX_FPS;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000/MAX_FPS;

        while(running){
            startTime = System.nanoTime();
            canvas = null;

            try{
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            }catch(Exception e) {e.printStackTrace();
        }
            finally{
                if (canvas != null){
                    try{
                    }
                    catch(Exception e) {e.printStackTrace();}
                    }
                    }
            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;
            try{
                if (waitTime > 0){
                    this.sleep(waitTime);
                }
            }catch (Exception e) {e.printStackTrace();

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == MAX_FPS){
                averageFPS = 1000 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println(averageFPS);
            }

            }

    }
}

    public void setAverageFPS(double averageFPS) {
        this.averageFPS = averageFPS;
    }

    public void setSurfaceHolder(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public static void setCanvas(Canvas canvas) {
        MainThread.canvas = canvas;
    }
}
