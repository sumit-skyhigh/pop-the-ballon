package com.pixigame.pop.balloon;

import com.badlogic.gdx.Game;
import com.pixigame.pop.balloon.screens.GameScreen;

public class PopBallonGame extends Game {
    @Override
    public void create() {
        setScreen(new GameScreen());
    }
}
