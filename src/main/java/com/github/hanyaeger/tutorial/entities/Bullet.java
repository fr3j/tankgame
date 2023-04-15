package com.github.hanyaeger.tutorial.entities;

import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.SceneBorderCrossingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.tutorial.entities.map.Wall;

/**
 * The type Bullet.
 */
public class Bullet extends DynamicSpriteEntity implements Collider, SceneBorderCrossingWatcher {
    private final Player player;

    protected int damage;
    private double bulletAngle;

    private int bulletSpeed;
   public int boundaryCrossings = 0;

    public Bullet(String sprite, Size size, Player player, int speed, int damage) {
        super(sprite, player.getLoopPosition(), size);
        this.player = player;
        this.damage = damage;
        this.bulletSpeed = speed;
        bulletAngle = player.angle;
        setMotion(this.bulletSpeed, bulletAngle);
        setRotate(bulletAngle);
        //setRotate(player.getAngle());
    }

    @Override
    public void notifyBoundaryCrossing(SceneBorder border) {
        if (border == SceneBorder.LEFT || border == SceneBorder.RIGHT) {
            mirrorAngle(0);
            boundaryCrossings++;
        } else if (border == SceneBorder.TOP || border == SceneBorder.BOTTOM) {
            mirrorAngle(180);
            boundaryCrossings++;
        }
        checkCountBoundaryCrossings(2);

    }

    public void mirrorAngle(int parameter) {
        double newAngle = parameter-bulletAngle;
        setRotate(newAngle);
        setMotion(3, newAngle);
        this.bulletAngle = newAngle;
    }
    public void checkCountBoundaryCrossings(int maxBoundaryCrossings) {
        // also when the bullet hits the border for the third time it should be removed

        if (boundaryCrossings >= maxBoundaryCrossings) {
            remove();
        }
    }

    public Player getPlayer(){
        return player;
    }


}
