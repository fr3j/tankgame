package com.github.hanyaeger.tutorial.entities.buttons;

import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.userinput.MouseButtonPressedListener;
import com.github.hanyaeger.tutorial.Game;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import com.github.hanyaeger.api.Coordinate2D;

public class StartButton extends TextEntity implements MouseButtonPressedListener {
    private final Game game;
    public StartButton (Coordinate2D initialLocation, Game game){
        super(initialLocation,"Play game");
        this.game = game;
        setFill(Color.PURPLE);
        setFont(Font.font("Roboto", FontWeight.BOLD, 30));
    }


    @Override
    public void onMouseButtonPressed(MouseButton mouseButton, Coordinate2D coordinate2D) {
        game.setActiveScene(1);
    }
}
