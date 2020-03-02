package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class MyGdxGame extends ApplicationAdapter {
    
    private SpriteBatch batch;
    Enemy tiefighter;
    private Player xwing;
    private Texture background;
    private FileHandle shotFile;
    private List<Enemy> enemyList = new ArrayList<Enemy>();
    private List<Shot> shotList = new ArrayList<>();
    private List<Shot> enemyShotList = new ArrayList<>();
    
    int tieDirection = 1;
    float tieSpeed = 2.0f;
    
    @Override
    public void create() {
        FileHandle xwingFile = Gdx.files.internal("xwing2.png");
        FileHandle tieFile = Gdx.files.internal("tiefighter2.png");
        FileHandle tieAdvFile = Gdx.files.internal("tieAdvanced.png");
        FileHandle backgroundFile = Gdx.files.internal("background.jpg");
        background = new Texture(backgroundFile);
        
        shotFile = Gdx.files.internal("shot.png");
        tiefighter = new Enemy(new Texture(tieFile), 200, 350, 5, 1, 2.0f, 20);
        tiefighter.scale(0.1f);
        xwing = new Player(new Texture(xwingFile), 200, 10, 5, 2.5f, 10);
        xwing.scale(0.2f);
        enemyList.add(new Enemy(new Texture(tieAdvFile), 50, 400, 20, -1, 3.0f, 50));
        enemyList.add(new Enemy(new Texture(tieFile), 50, 300, 0, 1, 2.0f, 100));
        enemyList.add(new Enemy(new Texture(tieFile), 200, 300, 0, 1, 2.0f, 100));
        batch = new SpriteBatch();
        
    }
    
    void move() {
        xwing.setLastShot(xwing.getLastShot()+1);
        if (Gdx.input.isKeyPressed(Keys.SPACE) && xwing.getLastShot()>xwing.getFireRate()) {
            Shot newShot = new Shot(new Texture("shot.png"));
            Shot newShot2 = new Shot(new Texture("shot.png"));
            newShot2.setPosition(xwing.getX() + -21, xwing.getY() + 2);
            newShot.setPosition(xwing.getX() + 60, xwing.getY() + 2);
            newShot.setScale(0.2f);
            newShot2.setScale(0.2f);
            shotList.add(newShot);
            shotList.add(newShot2);
            xwing.setLastShot(0);
            
        }
        for (Sprite s : shotList) {
            s.translateY(5);
        }
        for (Shot xwing_shot : shotList) {
            xwing_shot.translateY(xwing_shot.getSpeed());
            for (Enemy en : enemyList) {
                if (xwing_shot.isDisabled() == false
                        && xwing_shot.getBoundingRectangle().overlaps(en.getBoundingRectangle())) {
                    if (en.isDisabled() == false) {
                        xwing_shot.setDisabled(true);
                    }
                    en.decreaseShield(1);
                    if (en.getShieldStrength() < 1) {
                        en.setDisabled(true);
                    }
                }
            }
        }
        for (Shot enemy_shot : enemyShotList) {
            enemy_shot.translateY(-enemy_shot.getSpeed());
            if (enemy_shot.isDisabled() == false
                    && enemy_shot.getBoundingRectangle().overlaps(xwing.getBoundingRectangle())) {
                enemy_shot.setDisabled(true);
                xwing.decreaseShield(1);
                if (xwing.getShieldStrength() < 1) {
                    System.exit(0);
                }
            }
        }
        for(Enemy en : enemyList){
            en.move();
            if(en.getLastShot()>en.getFireRate()&&en.isDisabled() == false){
                enemyShotList.add(en.shoot());
                en.setLastShot(0);
            }
        }
    }
    
    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        move();
        tiefighter.move();
        for (Enemy e : enemyList) {
            e.move();
        }
        xwing.move();
        
        batch.begin();
        batch.draw(background, 10, 10);
        xwing.draw(batch);
        for (Enemy e : enemyList) {
            if(!e.isDisabled()){
                e.draw(batch);
            }
        }
        for (Sprite s : shotList) {
            s.draw(batch);
        }
        batch.end();
    }
    
    @Override
    public void dispose() {
        batch.dispose();
    }
}
