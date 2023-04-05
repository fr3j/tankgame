package com.github.hanyaeger.tutorial.scenes;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.hanyaeger.tutorial.Game;
import com.github.hanyaeger.tutorial.Player;

public class GameScene extends DynamicScene {
    public final Game game;
    public GameScene(Game game) {
        this.game = game;
    }

    @Override
    public void setupScene() {

    }

    @Override
    public void setupEntities() {
        var player = new Player(new Coordinate2D(100, 100));
        addEntity(player);
    }
}
