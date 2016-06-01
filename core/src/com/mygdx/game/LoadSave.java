package com.mygdx.game;

import java.io.*;

import javax.swing.*;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LoadSave implements Screen {
	private SpriteBatch batch;
	private MainGame game;
	private String name;
	private int difficulty;
	private int timeSeconds; 
	JFileChooser choose =new JFileChooser();
	
	
public LoadSave(SpriteBatch batch,MainGame game){
	this.batch=batch;
	this.game=game;
	
}
	// add value for current time, make load save a screen, have it call setscreen gamescreen instead of difficulty calling it
	 public void fileRead(){
		 try{
			 BufferedReader firstRead=new BufferedReader(new FileReader("//"+choose.getCurrentDirectory()+"//"));
		firstRead.close();
		 }
		 catch(IOException e)
		 {
			 JOptionPane.showMessageDialog(null, "The save file is corrupt.","ATTENTION",JOptionPane.ERROR_MESSAGE);
		 }
	 }
	 
	 public void create(){
		 if(!(new File(System.getProperty("user.dir")+"//saves").exists())){
		    	new File(System.getProperty("user.dir")+"//saves").mkdirs();
		 }
	     choose.setCurrentDirectory(new File(System.getProperty("user.dir")+"//saves")); 
	 
	 }
	 
	 public void loadGame(){
		 game.setScreen(new GameScreen(batch, game,name, difficulty,0));
		 
	 }
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}

