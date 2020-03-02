/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author Glissted
 */
public class Enemy extends Sprite {

    private int shieldStrength;
    private int direction;
    private float speed;
    private boolean disabled = false;
    private int fireRate;

    public int getFireRate() {
        return fireRate;
    }

    public void setFireRate(int fireRate) {
        this.fireRate = fireRate;
    }

    public FileHandle getShotFile() {
        return shotFile;
    }

    public void setShotFile(FileHandle shotFile) {
        this.shotFile = shotFile;
    }

    public int getLastShot() {
        return LastShot;
    }

    public void setLastShot(int LastShot) {
        this.LastShot = LastShot;
    }
    private FileHandle shotFile;
    private int LastShot = 0;

    public Enemy(Texture texture, int xpos, int ypos, int shield,
            int dir, float sp, int fRate) {
        super(texture);
        super.setPosition((xpos), ypos);
        direction = dir;
        shieldStrength = shield;
        speed = sp;
        shotFile = Gdx.files.internal("green_shot.png");
        fireRate = fRate;
    }

    public Shot shoot() {
        Shot newShot = new Shot(new Texture(shotFile),
                (int) (this.getX() + this.getWidth() / 2),
                (int) (this.getY()),
                -1, 2.0f);

        return newShot;
    }

    public void decreaseShield(int amount) {
        shieldStrength -= amount;
        if (shieldStrength < 0) {
            shieldStrength = 0;
        }
    }

    public int getShieldStrength() {
        return shieldStrength;
    }

    public void setShieldStrength(int shieldStrength) {
        this.shieldStrength = shieldStrength;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void move() {
        if (this.getX() < 0 || this.getX()
                > Gdx.graphics.getWidth() - this.getWidth()) {
            this.setDirection(-this.getDirection());
        }
        this.translateX(this.getDirection() * this.getSpeed());
    }
}
