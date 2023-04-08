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

//import static javafx.scene.control.skin.TabPaneSkin;
import com.github.hanyaeger.core.entities.motion.InitializationBuffer;


public class Player extends DynamicSpriteEntity implements SceneBorderCrossingWatcher, KeyListener, Collided {
    public double angle;
    public Bullet bullet;
    private GameScene gamescene;

    double rotationIncrement = 10;

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

        if (pressedKeys.contains(KeyCode.LEFT)) {
            newAngle = this.angle + rotationIncrement;
            setRotate(newAngle);
        } else if (pressedKeys.contains(KeyCode.RIGHT)) {
            newAngle = this.angle - rotationIncrement;
            setRotate(newAngle);
            //setRotationSpeed(-degreeIncrement);
            //setMotion(0, getRotationSpeed());

        } else if (pressedKeys.contains(KeyCode.UP)) {
            setMotion(1, angle);

        } else if (pressedKeys.contains(KeyCode.DOWN)) {
            System.out.println("down");
            setMotion(1, angle + 180);

        } else if (pressedKeys.contains(KeyCode.ENTER)) {
            shoot();
        } else if (pressedKeys.isEmpty()) {
            setSpeed(0);
        }
        this.angle = newAngle;


    }

//void updateRotation() {
//    totalRotation += degreeIncrement;
//    if (totalRotation >= 360) {
//        totalRotation = 0;
//    }
//    update(fr);
//}


//    int getAngle() {
//
//        return this.angle;
//
//    }
//
//    private int setAngle(int angle) {
//        this.angle = angle;
//        return 0;
//    }


    @Override
    public void onCollision(Collider collider) {
        if (collider instanceof Bullet) {
            //System.out.println("Collision");
        }
    }

    public void shoot() {
        Bullet bullet = new Bullet("sprites/bullet.png", new Size(30, 30), this, 4, 1);
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


//    @Override
//    public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
//        double rotationAnimation = 1;
//        System.out.println(getRotationSpeed());
//
//        if (pressedKeys.contains(KeyCode.LEFT)) {
//            setRotationSpeed(rotationAnimation);
//
//
//        } else if (pressedKeys.contains(KeyCode.RIGHT)) {
//            setRotationSpeed(-rotationAnimation);
//            //setAngle(getAngle() - rotationIncrement);
//            applyRotation();
//        } else if (pressedKeys.contains(KeyCode.UP)) {
//            setMotion(3, ???);
//         //   setRotationSpeed(0);
//       //     setAngle(getAngle());
//        } else if (pressedKeys.contains(KeyCode.DOWN)) {
//        //    setMotion(3, getRotation());
//            setRotationSpeed(0);
//        //    setAngle(getAngle());
//        } else if (pressedKeys.contains(KeyCode.ENTER)) {
//            shoot();
//        } else if (pressedKeys.isEmpty()) {
//            setSpeed(0);
//            setRotationSpeed(0);
//        }
