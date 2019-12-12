package com.example.finalspace;

import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    public static String TAG = "Final Space";
    private MainThread thread;
    private Player player;
    private Point playerIdealPoint;
    private Point playerPoint;
    private ObsticleManager obsticleManager = new ObsticleManager(50);
    private int lives = 3;
    
    public GamePanel(Context context){
        super(context);
        getHolder().addCallback(this);
        Constants.CURRENT_CONTEXT = context;
        thread = new MainThread(getHolder(), this);
        // player
        int playerSize = 200;
        player = new Player(new Rect(0, 0, playerSize, playerSize), Color.rgb(255, 50, 50));
        playerPoint = new Point(Constants.VIEW_WIDTH/2, Constants.VIEW_HEIGHT + 100);
        playerIdealPoint = new Point(Constants.VIEW_WIDTH/2, Constants.VIEW_HEIGHT/2);
        setFocusable(true);
        
    }

    public MainThread getThread() {
        return thread;
    }

    public void setThread(MainThread thread) {
        this.thread = thread;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Point getPlayerIdealPoint() {
        return playerIdealPoint;
    }

    public void setPlayerIdealPoint(Point playerIdealPoint) {
        this.playerIdealPoint = playerIdealPoint;
    }

    public Point getPlayerPoint() {
        return playerPoint;
    }

    public void setPlayerPoint(Point playerPoint) {
        this.playerPoint = playerPoint;
    }

    public ObsticleManager getObsticleManager() {
        return obsticleManager;
    }

    public void setObsticleManager(ObsticleManager obsticleManager) {
        this.obsticleManager = obsticleManager;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
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
            case MotionEvent.ACTION_DOWN:
                if(player == null) {triggerRebirth(Constants.CURRENT_CONTEXT);}
            case MotionEvent.ACTION_MOVE:
                if(player != null)playerIdealPoint.set((int)event.getX(), (int)event.getY());
                break;
        }
        return true;
    }
    
    public void update() {
        if(player != null) {
            int offsetX = (playerIdealPoint.x - playerPoint.x) / 20;
            int offsetY = (playerIdealPoint.y - playerPoint.y) / 20;
            playerPoint.offset(offsetX, offsetY);
            player.update(playerPoint);
            if(obsticleManager.checkCollisions(player)) {
                lives--;
                if(lives <= 0) {
                    player = null;
                }
            }
        }
        obsticleManager.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        // paint for text
        Paint textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(48f);
        textPaint.setTextAlign(Paint.Align.CENTER);
        // paint for text
        canvas.drawColor(Color.rgb(30, 30, 30));
        if(player != null) {player.draw(canvas);}
        else {canvas.drawText("You Died", Constants.VIEW_WIDTH/2, Constants.VIEW_HEIGHT/2, textPaint);}
        obsticleManager.draw(canvas);
        canvas.drawText("Lives: "+lives, Constants.VIEW_WIDTH/2, 100, textPaint);
    }
    public static void triggerRebirth(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(context.getPackageName());
        ComponentName componentName = intent.getComponent();
        Intent mainIntent = Intent.makeRestartActivityTask(componentName);
        context.startActivity(mainIntent);
        Runtime.getRuntime().exit(0);
    }
}
