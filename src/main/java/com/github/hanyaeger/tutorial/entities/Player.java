package com.github.hanyaeger.tutorial.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.entities.SceneBorderCrossingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.api.userinput.KeyListener;
import com.github.hanyaeger.tutorial.entities.Bullet;
import com.github.hanyaeger.tutorial.entities.map.Wall;
import com.github.hanyaeger.tutorial.entities.map.WallTileMap;
import com.github.hanyaeger.tutorial.scenes.GameScene;
import javafx.scene.input.KeyCode;


import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;


public class Player extends DynamicSpriteEntity implements SceneBorderCrossingWatcher, KeyListener, Collided {
    public double angle;
    public Bullet bullet;
    private GameScene gamescene;
    double rotationIncrement = 10;
    private boolean canShoot = true;
    private Timer shootTimer = new Timer();


    public Player(Coordinate2D location, GameScene gamescene) {
        super("sprites/tank.png", location, new Size(40, 40));
        this.gamescene = gamescene;
        this.angle = 0;
    }


    @Override
    public void notifyBoundaryCrossing(SceneBorder sceneBorder) {
        setAnchorLocationX(getSceneWidth());
        setAnchorLocationY(getSceneHeight());
    }

    @Override
    public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
        double degreeIncrement = 90;
        double totalRotation = 0;
        double newAngle = angle;

        setMotion(0, angle);

        if (pressedKeys.contains(KeyCode.A)) {
            newAngle = this.angle + rotationIncrement;
            setRotate(newAngle);
        } else if (pressedKeys.contains(KeyCode.D)) {
            newAngle = this.angle - rotationIncrement;
            setRotate(newAngle);

        } else if (pressedKeys.contains(KeyCode.W)) {
            setMotion(1, angle);

        } else if (pressedKeys.contains(KeyCode.S)) {
            System.out.println("down");
            setMotion(1, angle + 180);

        } else if (pressedKeys.contains(KeyCode.SPACE)) {
            shoot();
        } else if (pressedKeys.isEmpty()) {
            setSpeed(0);
        }
        this.angle = newAngle;


    }
    @Override
    public void onCollision(Collider collider) {
        if (collider instanceof Bullet && collider != this) {
            remove();
            // TODO: fix dat de bullet niet gelijk de player zelf doodmaakt
        }
       else if (collider instanceof Wall) {
            setSpeed(0);
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


    public Coordinate2D getLoopPosition() {
        // Calculate the position of the bullet based on the angle of the player
        var originalPosition = getAnchorLocation();
        double offsetX = Math.cos(Math.toRadians(angle)) * getWidth() / 2;
        double offsetY = Math.sin(Math.toRadians(angle)) * getHeight() / 2;
        return new Coordinate2D(originalPosition.getX() + offsetX, originalPosition.getY() + offsetY);
    }
}
