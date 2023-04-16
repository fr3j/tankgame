package com.github.hanyaeger.tutorial.scenes;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.YaegerEntity;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.hanyaeger.api.scenes.TileMapContainer;
import com.github.hanyaeger.tutorial.Game;
import com.github.hanyaeger.tutorial.entities.Player;
import com.github.hanyaeger.tutorial.entities.Player1;
import com.github.hanyaeger.tutorial.entities.Player2;
import com.github.hanyaeger.tutorial.entities.Scoreboard;
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
        var player1 = new Player1(new Coordinate2D(100, 100), this);
        addEntity(player1);
        var scoreboard1 = new Scoreboard(new Coordinate2D(100, 50), player1);
        addEntity(scoreboard1);
        var player2 = new Player2(new Coordinate2D(700, 500), this);
        addEntity(player2);
        var scoreboard2 = new Scoreboard(new Coordinate2D(700, 450), player2);
        addEntity(scoreboard2);

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
