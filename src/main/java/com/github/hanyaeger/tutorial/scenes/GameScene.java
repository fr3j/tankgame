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
        setBackgroundAudio("audio/gameaudio.mp3");
    }

    @Override
    public void setupEntities() {
        var scoreboard1 = new Scoreboard(new Coordinate2D(100, 50));
        addEntity(scoreboard1);
        var player1 = new Player1(new Coordinate2D(100, 100), this, scoreboard1);
        addEntity(player1);

        var scoreboard2 = new Scoreboard(new Coordinate2D(700, 450));
        addEntity(scoreboard2);
        var player2 = new Player2(new Coordinate2D(700, 500), this, scoreboard2);
        addEntity(player2);


    }


    public void gameOver() {
        game.setActiveScene(2);
    }


    public void addEntityToScene(YaegerEntity entity) {
        addEntity(entity);
    }


    @Override
    public void setupTileMaps() {
        addTileMap(new WallTileMap());
    }
}
