package com.example.finalspace;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Player implements GameObject {
    private Rect rectangle;
    private int color;
    private Bitmap sprite;
    private AnimationPlayer animationPlayer;

    public Player(Rect rectangle, int color) {
        this.rectangle = rectangle;
        this.color = color;
        BitmapFactory bf = new BitmapFactory();
        sprite = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources() ,R.drawable.player);
        animationPlayer = new AnimationPlayer();
        animationPlayer.createAnimation(new Bitmap[] {sprite}, "Idle");
        animationPlayer.setAnimation("Idle");
    }

    public Rect getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rect rectangle) {
        this.rectangle = rectangle;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Bitmap getSprite() {
        return sprite;
    }

    public void setSprite(Bitmap sprite) {
        this.sprite = sprite;
    }

    public AnimationPlayer getAnimationPlayer() {
        return animationPlayer;
    }

    public void setAnimationPlayer(AnimationPlayer animationPlayer) {
        this.animationPlayer = animationPlayer;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint  = new Paint();
        paint.setColor(color);
        //canvas.drawRect(rectangle, paint);
        animationPlayer.draw(rectangle);
    }
    @Override
    public void update() {

    }
    public void update(Point point) {
        //left top right bottom
        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2, point.x + rectangle.width()/2, point.y + rectangle.height()/2);
    }
}
