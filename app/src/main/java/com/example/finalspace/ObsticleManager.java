package com.example.finalspace;

import android.graphics.Canvas;

import java.util.ArrayList;

public class ObsticleManager implements GameObject{
    private ArrayList<Obsticle> obsticles = new ArrayList<Obsticle>();
    private int ticksToSpawn;
    private int tick;
    public ObsticleManager(int ticksToSpawn) {
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
            obsticles.add(new Obsticle());
            tick = ticksToSpawn;
        }
        int size = obsticles.size();
        if(size > 0) { for(int i = 0; i < size; i++) {
            Obsticle obsticle = obsticles.get(i);
            obsticle.update();
            if(obsticle.getPos().y > Constants.VIEW_HEIGHT + obsticle.getScale()) {
               obsticles.remove(i);
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
}
