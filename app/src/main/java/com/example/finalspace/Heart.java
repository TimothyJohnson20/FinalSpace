package com.example.finalspace;

import android.graphics.Canvas;

public class Heart implements GameObject {
    private int speed;
    public Heart() {
        speed = (int)(20 * Math.random()) + 10;
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void update() {

    }
}
