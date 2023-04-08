package com.github.hanyaeger.tutorial.scenes;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.YaegerEntity;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.hanyaeger.api.scenes.TileMapContainer;
import com.github.hanyaeger.tutorial.Game;
import com.github.hanyaeger.tutorial.Player;
import com.github.hanyaeger.tutorial.entities.map.WallTileMap;

public class GameScene extends DynamicScene implements TileMapContainer {
    public final Game game;
    public GameScene(Game game) {
        this.game = game;
    }

    @Override
    public void setupScene() {

    }

    @Override
    public void setupEntities() {
        var player = new Player(new Coordinate2D(100, 100), this);
        addEntity(player);
    }


    public void gameOver() {
        game.setActiveScene(0);
    }


    public void addEntityToScene(YaegerEntity entity) {
        addEntity(entity);
    }


    @Override
    public void setupTileMaps() {
        addTileMap(new WallTileMap());
    }
}
