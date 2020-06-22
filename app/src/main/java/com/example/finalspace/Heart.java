package com.example.finalspace;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Heart implements GameObject {
    private int speed;
    private int scale;
    private Rect rect;
    private Bitmap sprite;
    private Point pos = new Point();
    private AnimationPlayer animationPlayer = new AnimationPlayer();
    public Heart() {
        speed = (int)(20 * Math.random()) + 10;
        scale = 100 * (int)((Math.random() * 2) + 1);
        int xPos = (int)(Math.random() * Constants.VIEW_WIDTH);
        pos.set(xPos, -scale);
        BitmapFactory bf = new BitmapFactory();
        sprite = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.repairkit);
        animationPlayer.createAnimation(new Bitmap[] {sprite}, "idle");
        animationPlayer.setAnimation("idle");
    }
    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        //canvas.drawRect(rect, paint);
        animationPlayer.draw(rect);
    }

    @Override
    public void update() {
        pos.offset(0, speed);
        rect = new Rect(pos.x - scale/2, pos.y - scale/2, pos.x + scale/2, pos.y + scale/2);
    }

    public boolean playerCollide(Player player) {
        return Rect.intersects(rect, player.getRectangle());
    }
}
