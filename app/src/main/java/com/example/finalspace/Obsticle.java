package com.example.finalspace;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.Image;

public class Obsticle implements GameObject {
    private int speed;
    private Point pos = new Point();
    private AnimationPlayer animationPlayer = new AnimationPlayer();
    private Rect rect;
    private int scale;
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public AnimationPlayer getAnimationPlayer() {
        return animationPlayer;
    }

    public void setAnimationPlayer(AnimationPlayer animationPlayer) {
        this.animationPlayer = animationPlayer;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public Obsticle() {
        speed = (int)(20 * Math.random()) + 10;
        int xPos = (int)(Math.random() * Constants.VIEW_WIDTH);
        scale = 100 * (int)((Math.random() * 2) + 1);
        pos.set(xPos, -scale);
        BitmapFactory bf = new BitmapFactory();
        Bitmap sprite1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources() ,R.drawable.astroid1);
        Bitmap sprite2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources() ,R.drawable.astroid2);
        Bitmap sprite3 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources() ,R.drawable.astroid3);
        Bitmap sprite4 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources() ,R.drawable.astroid4);
        Bitmap sprite5 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources() ,R.drawable.astroid5);
        Bitmap sprite6 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources() ,R.drawable.astroid6);
        Bitmap sprite7 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources() ,R.drawable.astroid7);
        animationPlayer.createAnimation(new Bitmap[]{sprite1, sprite2, sprite3, sprite4, sprite5, sprite6, sprite7}, "roll");
        animationPlayer.setAnimation("roll");
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
