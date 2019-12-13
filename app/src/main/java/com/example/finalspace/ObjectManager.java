package com.example.finalspace;

import android.graphics.Canvas;

import java.util.ArrayList;

public class ObjectManager implements GameObject{
    private ArrayList<Obsticle> obsticles = new ArrayList<Obsticle>();
    private ArrayList<Heart> hearts = new ArrayList<Heart>();
    private int ticksToSpawn;
    private int tick;
    public ObjectManager(int ticksToSpawn) {
        this.ticksToSpawn = ticksToSpawn;
        this.tick = ticksToSpawn;
    }
    @Override
    public void draw(Canvas canvas) {
        int size = obsticles.size();
        if(size > 0) { for(int i = 0; i < size; i++) {
            Obsticle obsticle = obsticles.get(i);
            obsticle.draw(canvas);
        }
        }
    }

    @Override
    public void update() {
        tick--;
        if(tick <= 0) {
            // astroids
            int astroidNumb = (int)(Math.random()) + 1;
            for(int i = 0; i < (astroidNumb); i++) {
                obsticles.add(new Obsticle());
            }
            // hearts
            if(chance(20)) {
                hearts.add(new Heart());
            }
            tick = ticksToSpawn;
        }
        int size = obsticles.size();
        if(size > 0) { for(int i = 0; i < size; i++) {
            Obsticle obsticle = obsticles.get(i);
            obsticle.update();
            if(obsticle.getPos().y > Constants.VIEW_HEIGHT + obsticle.getScale()) {
               //obsticles.remove(i);
            }
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
        }}
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
}
