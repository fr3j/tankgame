package com.github.hanyaeger.tutorial.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.tutorial.scenes.GameScene;
import javafx.scene.input.KeyCode;

import java.util.Set;


public class Player2 extends Player{


    public Player2(Coordinate2D location, GameScene gamescene, Scoreboard scoreboard) {
        super(location, gamescene, scoreboard);
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

        } else if (pressedKeys.contains(KeyCode.UP)) {
            setMotion(3, angle);

        } else if (pressedKeys.contains(KeyCode.DOWN)) {
            setMotion(3, angle + 180);

        } else if (pressedKeys.contains(KeyCode.ENTER)) {
            shoot();
        } else if (pressedKeys.isEmpty()) {
            setSpeed(0);
        }
        this.angle = newAngle;


    }
}
