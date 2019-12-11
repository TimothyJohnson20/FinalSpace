package com.example.finalspace;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private Player player;
    private Point playerIdealPoint;
    private Point playerPoint;
    
    public GamePanel(Context context){
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        // player
        player = new Player(new Rect(100, 100, 200, 200), Color.rgb(255, 50, 50));
        playerPoint = new Point(150, 150);
        playerIdealPoint = new Point(150, 150);
        setFocusable(true);
        
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
                
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(true){
            try{
                thread.setRunning(false);
                thread.join();
            }
            catch(Exception e) {e.printStackTrace();}
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_DOWN:
                playerIdealPoint.set((int)event.getX(), (int)event.getY());
        }
        return true;
    }
    
    public void update() {
        int offsetX = (playerIdealPoint.x - playerPoint.x)/20;
        int offsetY = (playerIdealPoint.y - playerPoint.y)/20;
        playerPoint.offset(offsetX, offsetY);
        player.update(playerPoint);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.rgb(100, 100, 255));
        player.draw(canvas);
        
    }
    
    
}
