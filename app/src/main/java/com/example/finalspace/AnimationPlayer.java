package com.example.finalspace;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Rect;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.example.finalspace.MainThread.canvas;

public class AnimationPlayer {
    private ArrayList<Bitmap[]> animations = new ArrayList<Bitmap[]>();
    private ArrayList<String> animationNames = new ArrayList<String>();
    private int animationIndex = -1;
    private int frameIndex = 0;
    private long lastFrame = System.currentTimeMillis();
    public AnimationPlayer() {

    }
    public String createAnimation(Bitmap[] animation, String name) {
        animations.add(animation);
        animationNames.add(name);
        return name; //returns the index of the add animation
    }
    public String getCurrentAnimationName() {
        if(animationIndex != -1) {return animationNames.get(animationIndex);}
        else {return "";}
    }
    public void setAnimation (String name) {
        for (int i = 0; i < animationNames.size(); i++) {
            if(animationNames.get(i).equals(name)) {
                animationIndex = i;
                break;
            }
        }
    }
    public Bitmap update() {
        Bitmap[] animation = animations.get(animationIndex);
        if(System.currentTimeMillis() - lastFrame >= 100) {
            frameIndex++;
            if(frameIndex == animation.length) {
                frameIndex = 0;
            }
            lastFrame = System.currentTimeMillis();
        }
        return animation[frameIndex];
    }
    public void draw(Rect destination) {
        canvas.drawBitmap(update(), null, destination, new Paint());
    }
    public Bitmap[] convertIdArrayToBitmapArray(int[] IDs) {
        Bitmap[] bitmaps = new Bitmap[IDs.length];
        BitmapFactory bf = new BitmapFactory();
        for(int i = 0; i < IDs.length; i++){
            bitmaps[i] = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), IDs[i]);
        }
        return bitmaps;
    }
}
