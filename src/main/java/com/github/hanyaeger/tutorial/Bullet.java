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
    /**
     * Damage that is done by bullets fired by this weapon
     */
    protected int damage;

    public Bullet(String sprite, Size size, Player player, int speed, int damage) {
        super(sprite, player.getAnchorLocation(), size);
        this.player = player;
        this.damage = damage;
        setMotion(speed, player.getAngle());
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

    @Override
    public void notifyBoundaryCrossing(SceneBorder border) {
        remove();
    }
}
