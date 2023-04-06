package com.github.hanyaeger.tutorial;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.EntitySpawner;

public class Weapon extends EntitySpawner {
    public Weapon(Coordinate2D initialLocation, Size size) {
        super(500);
    }

    @Override
    protected void spawnEntities() {
        
    }
}
