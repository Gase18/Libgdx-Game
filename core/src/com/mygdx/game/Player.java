/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author Glissted
 */
public class Player extends Sprite {

    private int shieldStrength;

    public int getShieldStrength() {
        return shieldStrength;
    }

    public void setShieldStrength(int shieldStrength) {
        this.shieldStrength = shieldStrength;
    }
    private float speed;
    private int fireRate;
    private FileHandle shotFile;
    private int lastShot = 0;

    public Player(Texture texture, int xpos, int ypos, int shield,
            float sp, int fRate) {
        super(texture);
        super.setPosition((xpos), ypos);
        shieldStrength = shield;
        speed = sp;
        shotFile = Gdx.files.internal("shot.png");
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

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

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
        return lastShot;
    }

    public void setLastShot(int lastShot) {
        this.lastShot = lastShot;
    }

    public void move() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && this.getX() < Gdx.graphics.getWidth() - this.getWidth()) {
            this.setX(this.getX() + 2);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && this.getX() > 0) {
            this.setX(this.getX() - 2);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && this.getY() < Gdx.graphics.getHeight() - this.getHeight()) {
            this.setY(this.getY() + 2);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && this.getY() > 0) {
            this.setY(this.getY() - 2);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ALT_LEFT)) {
            this.flip(true, false);
        }

    }
}
