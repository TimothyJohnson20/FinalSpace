package com.example.finalspace;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class AnimationPlayer {
    private ArrayList<Bitmap[]> animations = new ArrayList<Bitmap[]>();
    private ArrayList<String> animationNames = new ArrayList<String>();
    private int animationIndex = -1;
    private int frameIndex = -1;
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
    public Bitmap nextFrame() {
        frameIndex++;
        Bitmap[] animation = animations.get(animationIndex);
        if(frameIndex == animation.length) { frameIndex = 0; }
        return animation[frameIndex];
    }
}
