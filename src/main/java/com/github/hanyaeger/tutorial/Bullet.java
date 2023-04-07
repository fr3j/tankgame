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
    private int bulletAngle;

   public int boundaryCrossings = 0;

    public Bullet(String sprite, Size size, Player player, int speed, int damage) {
        super(sprite, player.getLoopPosition(), size);
        this.player = player;
        this.damage = damage;
        setMotion(speed, player.getAngle());
        setRotate(player.getAngle());
        bulletAngle = player.getAngle();
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


    int getAngle() {
        return this.bulletAngle;
    }
    private void setAngle(int angle) {
         this.bulletAngle = angle;
    }
    @Override
    public void notifyBoundaryCrossing(SceneBorder border) {
        // implement that the bullet bounces back when it hits the border

 

        if (border == SceneBorder.LEFT || border == SceneBorder.RIGHT) {
            setAngle(360 - bulletAngle);
            setMotion(3, bulletAngle);
            setRotate(getAngle());


        } else if (border == SceneBorder.TOP || border == SceneBorder.BOTTOM) {
            setAngle(180 - bulletAngle);
            setMotion(3, bulletAngle);
            setRotate(getAngle());
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
