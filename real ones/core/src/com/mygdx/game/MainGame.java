package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainGame extends Game {
 SpriteBatch batch;
 Texture img;
 
 @Override
 public void create () {
  Pixmap pm = new Pixmap(Gdx.files.internal("cursor.png"));
  Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
  pm.dispose();
  batch = new SpriteBatch();
  mainMenu();
 }
 
 public void mainMenu(){
  setScreen (new MainMenu (this));
 }

 @Override
 public void render () {
  super.render();
 }
}
