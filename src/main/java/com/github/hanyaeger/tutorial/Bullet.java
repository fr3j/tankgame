package com.github.hanyaeger.tutorial;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.SceneBorderCrossingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.tutorial.Player;

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

//    @Override
//    public void onCollision(Collider collider) {
//        if(collider instanceof Ship collidedShip) {
//            if (collidedShip instanceof Enemy && weapon.owner instanceof Player) {
//                collidedShip.takeDamage(damage);
//                this.remove();
//            }
//            else if (collidedShip instanceof Player && weapon.owner instanceof Enemy){
//                collidedShip.takeDamage(damage);
//                this.remove();
//            }
//            if (collidedShip.getHealth() <= 0) {
//                collidedShip.death();
//
//            }
//        }
//    }
//
    @Override
    public void notifyBoundaryCrossing(SceneBorder border) {
        if (border == SceneBorder.LEFT || border == SceneBorder.RIGHT) {
            double newAngle = -bulletAngle;
            setRotate(newAngle);
            setMotion(3, newAngle);
            this.bulletAngle = newAngle;
        } else if (border == SceneBorder.TOP || border == SceneBorder.BOTTOM) {
            double newAngle = -bulletAngle;
            setRotate(newAngle);
            setMotion(3, newAngle);
            this.bulletAngle = newAngle;
        }



        boundaryCrossings++;
    }
    public void checkCountBoundaryCrossings() {
        // also when the bullet hits the border for the third time it should be removed

        boundaryCrossings++;
        if (boundaryCrossings >= 3) {
            remove();
        }
    }

}
