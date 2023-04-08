package com.github.hanyaeger.tutorial.entities.map;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

public class Wall extends SpriteEntity {

    public Wall(Coordinate2D initialPosition, Size size, String sprite) {
        super(sprite, initialPosition, size);
    }
}
