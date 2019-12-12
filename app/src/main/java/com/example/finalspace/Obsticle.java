package com.example.finalspace;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.media.Image;

public class Obsticle implements GameObject {
    private int speed;
    public Obsticle() {
        speed = (int)(20 * Math.random()) + 10;
    }
    @Override
    public void draw(Canvas canvas) {
    }
    @Override
    public void update() {

    }

    public void update(Point point) {

    }
}
