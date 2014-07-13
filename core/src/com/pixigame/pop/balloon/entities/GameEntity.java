package com.pixigame.pop.balloon.entities;

/**
 * Created by nemo on 7/12/2014.
 */
public interface GameEntity {
    public int getContactGroup();

    public void render();

    public void disposeGameEntry();
}
