package com.example.finalspace;

import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

public class ObjectManager implements GameObject{
    private ArrayList<Obsticle> obsticles = new ArrayList<Obsticle>();
    private ArrayList<Heart> hearts = new ArrayList<Heart>();
    private int ticksToSpawn;
    private int tick;
    private Rect bottom = new Rect();
    public ObjectManager(int ticksToSpawn) {
        this.ticksToSpawn = ticksToSpawn;
        this.tick = ticksToSpawn;
        bottom.set(0, Constants.VIEW_HEIGHT + 300, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT + 500);
    }
    @Override
    public void draw(Canvas canvas) {
        int obsSize = obsticles.size();
        if(obsSize > 0) { for(int i = 0; i < obsSize; i++) {
            Obsticle obsticle = obsticles.get(i);
            obsticle.draw(canvas);
        }
        }
        int heartSize = hearts.size();
        if(heartSize > 0) { for(int i = 0; i < heartSize; i++) {
            Heart heart = hearts.get(i);
            heart.draw(canvas);
        }
        }
    }

    @Override
    public void update() {
        tick--;
        if (tick <= 0) {
            // astroids
            int astroidNumb = (int) (Math.random()) + 1;
            for (int i = 0; i < (astroidNumb); i++) {
                obsticles.add(new Obsticle());
            }
            // hearts
            if(chance(2)) {hearts.add(new Heart());}
            tick = ticksToSpawn;
        }
        int obsSize = obsticles.size();
        if (obsSize > 0) {
            for (int i = 0; i < obsSize; i++) {
                Obsticle obsticle = obsticles.get(i);
                obsticle.update();
            }
        }
        int heartSize = hearts.size();
        if (heartSize > 0) {
            for (int i = 0; i < heartSize; i++) {
                Heart heart = hearts.get(i);
                heart.update();
            }
        }
    }

    public boolean checkCollisions(Player player) {
        int size = obsticles.size();
        if(size > 0) {for (int i = 0; i < size; i++) {
            Obsticle obs = obsticles.get(i);
            if(obs.playerCollide(player)) {
                obsticles.remove(i);
                return true;
            }
            removeWhenBellow(i);
        }}
        return false;
    }
    public boolean checkHeartCollisions(Player player) {
        int size = hearts.size();
        if(size > 0) { for(int i = 0; i < size; i ++) {
            Heart heart = hearts.get(i);
            if(heart.playerCollide(player)) {
                hearts.remove(i);
                return true;
            }
        }
        }
        return false;
    }
    public boolean chance(int percent) {
        int numb = (int)(Math.random() * 100);
        if(numb <= percent) {
            return true;
        }
        else {
            return false;
        }
    }

    public void removeWhenBellow(int index) {
        Obsticle obsticle = obsticles.get(index);
        if(Rect.intersects(bottom, obsticle.getRect())) {
            obsticles.remove(index);
        }
    }
}

