package com.github.hanyaeger.tutorial.entities;

import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.SceneBorderCrossingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.media.SoundClip;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.tutorial.entities.map.Wall;

/**
 * The type Bullet.
 */
public class Bullet extends DynamicSpriteEntity implements Collider, SceneBorderCrossingWatcher {
    private Player player;

    protected int damage;
    private double bulletAngle;

    private int bulletSpeed;
   public int boundaryCrossings = 0;
   public int nrBounces = 0;

    public Bullet(String sprite, Size size, Player player, int speed, int damage) {
        super(sprite, player.getAnchorLocation(), size);
        this.player = player;
        this.damage = damage;
        this.bulletSpeed = speed;
        bulletAngle = player.angle;
        setMotion(this.bulletSpeed, bulletAngle);
        setRotate(bulletAngle);
        var firesound = new SoundClip("audio/fire.wav");
        firesound.play();
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
        makeBounceSound();
        nrBounces++;
    }

    public void makeBounceSound(){
        var bounceSound = new SoundClip("audio/bounce.wav");
        bounceSound.play();
    }
    public void checkCountBoundaryCrossings(int maxBoundaryCrossings) {
        // also when the bullet hits the border for the third time it should be removed

        if (boundaryCrossings >= maxBoundaryCrossings) {
            remove();
        }
    }
    public int getNrBounces(){
        return nrBounces;
    }

    public Player getPlayer(){
        return player;
    }



}
