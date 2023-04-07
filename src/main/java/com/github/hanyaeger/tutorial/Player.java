package com.github.hanyaeger.tutorial;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.SceneBorderCrossingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.userinput.KeyListener;
import com.github.hanyaeger.tutorial.scenes.GameScene;
import javafx.scene.input.KeyCode;

import java.util.Set;



public class Player extends DynamicSpriteEntity implements SceneBorderCrossingWatcher, KeyListener, Collided {
    public int angle;
    public Bullet bullet;
    private GameScene gamescene;

    public Player (Coordinate2D location, GameScene gamescene){

        super("sprites/tank.png", location, new Size(40,40));
        this.gamescene = gamescene;
    }


    @Override
    public void notifyBoundaryCrossing(SceneBorder sceneBorder) {
        setAnchorLocationX(getSceneWidth());
        setAnchorLocationY(getSceneHeight());
    }
    @Override
    public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
        double rotationAnimation = 1;
        double rotationTank;
        System.out.println(getRotationSpeed());

        if (pressedKeys.contains(KeyCode.LEFT)) {
            setRotationSpeed(rotationAnimation);
            //setAngle(getAngle() + rotationIncrement);
            applyRotation();
            rotationTank = rotationAnimation*getFrames();
        } else if (pressedKeys.contains(KeyCode.RIGHT)) {
            setRotationSpeed(-rotationAnimation);
            //setAngle(getAngle() - rotationIncrement);
            applyRotation();
        } else if (pressedKeys.contains(KeyCode.UP)) {
            setMotion(3, getRotation());
            setRotationSpeed(0);
            setAngle(getAngle());
        } else if (pressedKeys.contains(KeyCode.DOWN)) {
            setMotion(3, getRotation());
            setRotationSpeed(0);
            setAngle(getAngle());
        } else if (pressedKeys.contains(KeyCode.ENTER)) {
            shoot();
        } else if (pressedKeys.isEmpty()) {
            setSpeed(0);
            setRotationSpeed(0);
        }
    }


     int getAngle() {

        return this.angle;

    }
    private int setAngle(int angle) {
        this.angle = angle;
        return 0;
    }


    @Override
    public void onCollision(Collider collider) {
        if (collider instanceof Bullet) {
            //System.out.println("Collision");
        }
    }

    public void shoot(){
        Bullet bullet = new Bullet("sprites/bullet.png", new Size(30,30), this, 4,1);
        gamescene.addEntityToScene(bullet);

    }
    public Coordinate2D getLoopPosition() {
        // Calculate the position of the bullet based on the angle of the player
        var originalPosition = getAnchorLocation();
        double offsetX = Math.cos(Math.toRadians(angle)) * getWidth() / 2;
        double offsetY = Math.sin(Math.toRadians(angle)) * getHeight() / 2;
        return new Coordinate2D(originalPosition.getX() + offsetX, originalPosition.getY() + offsetY);
    }
}
