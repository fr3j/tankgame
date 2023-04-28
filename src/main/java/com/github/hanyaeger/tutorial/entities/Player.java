package com.github.hanyaeger.tutorial.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.SceneBorderCrossingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.media.SoundClip;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.api.userinput.KeyListener;
import com.github.hanyaeger.tutorial.entities.map.Wall;
import com.github.hanyaeger.tutorial.scenes.GameScene;
import javafx.scene.input.KeyCode;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;


public abstract class Player extends DynamicSpriteEntity implements SceneBorderCrossingWatcher, KeyListener, Collided {
    public double angle;
    public Bullet bullet;
    private GameScene gamescene;
    double rotationIncrement = 10;
    private boolean canShoot = true;
    private Timer shootTimer = new Timer();
    private int lives = 3;
    private Scoreboard scoreboard;
    private Coordinate2D previousPosition;



    public Player(Coordinate2D location, GameScene gamescene, Scoreboard scoreboard) {
        super("sprites/tank.png", location, new Size(40, 40));
        this.gamescene = gamescene;
        this.angle = 0;
        this.scoreboard = scoreboard;
        scoreboard.setLives(lives);
    }


    @Override
    public void notifyBoundaryCrossing(SceneBorder sceneBorder) {
        setAnchorLocationX(getSceneWidth());
        setAnchorLocationY(getSceneHeight());
    }

    @Override
    public abstract void onPressedKeysChange(Set<KeyCode> pressedKeys);
    @Override
    public void onCollision(Collider collider) {
        if (collider instanceof Bullet && ((Bullet) collider).getPlayer() != this) {
            ((Bullet) collider).remove();
            lives--;
            scoreboard.setLives(lives);
            var explosionSound = new SoundClip("audio/explosion.wav");
            explosionSound.play();
            setNewPlayerPosition();
            if (lives == 0) {
                remove();
                gamescene.gameOver();
            }

        } else if (collider instanceof Wall) {
            setSpeed(-1);
            // TODO: fix dat de player niet door de wall heen kan
        }
    }



    public void shoot() {
        if (canShoot) {
            Bullet bullet = new Bullet("sprites/bullet.png", new Size(30, 30), this, 4, 1);
            gamescene.addEntityToScene(bullet);
            canShoot = false;
            shootTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    canShoot = true;
                }
            }, 1000);

        }
    }




    public void setNewPlayerPosition() {
        int random = (int) (Math.random() * 4);
        Coordinate2D newPosition = switch (random) {
            case 0 -> new Coordinate2D(100, 100);
            case 1 -> new Coordinate2D(100, 500);
            case 2 -> new Coordinate2D(700, 100);
            case 3 -> new Coordinate2D(700, 500);
            default -> null;
        };
        if (newPosition.equals(previousPosition)) {
            setNewPlayerPosition();
        } else {
            setAnchorLocation(newPosition);
            previousPosition = newPosition;
        }
    }

}
