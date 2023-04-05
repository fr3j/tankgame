package com.github.hanyaeger.tutorial;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.SceneBorderCrossingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.userinput.KeyListener;
import javafx.scene.input.KeyCode;

import java.util.Set;

public class Player extends DynamicSpriteEntity implements SceneBorderCrossingWatcher, KeyListener {


    public Player (Coordinate2D location){

        super("sprites/tank.png", location, new Size(40,40));
    }


    @Override
    public void notifyBoundaryCrossing(SceneBorder sceneBorder) {
        setAnchorLocationX(getSceneWidth());
        setAnchorLocationY(getSceneHeight());
    }
    @Override
    public void onPressedKeysChange(Set<KeyCode> pressedKeys){
        if(pressedKeys.contains(KeyCode.LEFT)){
            setRotationSpeed(1);

        } else if(pressedKeys.contains(KeyCode.RIGHT)){
            setRotationSpeed(-1);

        } else if(pressedKeys.contains(KeyCode.UP)){
            setMotion(3,getRotationSpeed());;

        } else if(pressedKeys.contains(KeyCode.DOWN)){
            setMotion(3,getRotationSpeed());

        } else if(pressedKeys.isEmpty()){
            setSpeed(0);
            setRotationSpeed(0);
        }
    }

}
