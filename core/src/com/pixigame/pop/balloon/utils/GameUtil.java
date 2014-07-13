package com.pixigame.pop.balloon.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by nemo on 7/12/2014.
 */
public class GameUtil {
    private static World currentWorld;

    public static final float screenWidth;
    public static final float screenHeight;
    public static final float balloonInitPositionY;
    public static final float balloonInitPositionX;
    public static final float minBalloonRadius;

    static {
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        balloonInitPositionY = screenHeight/-2;
        minBalloonRadius = screenWidth / 25;
        balloonInitPositionX = screenWidth/4;
    }

    public static World getWorld(){
        if(currentWorld == null) {
            Vector2 gravity = new Vector2(0, 9.81f);
            currentWorld = new World(gravity, false);
        }
        return currentWorld;
    }



}
