package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import java.util.ArrayList;
import java.io.*;
import javax.swing.*;
public class HighScore implements Screen {
 

 ArrayList<Save> listSave = new ArrayList<Save>();
 String currentName;
 Save currentFile;
JFileChooser choose=new JFileChooser();

 
	public void printer(){

	}

	public void scoreRead(){
		PrintWriter emptyWrite;
        BufferedReader scoreReader;
		try{
		if(!(new File("//"+choose.getCurrentDirectory()+"//"+"HighScore"+MainGame.EXT).isFile())){
			emptyWrite=new PrintWriter(new FileWriter("//"+choose.getCurrentDirectory()+"//"+"HighScore"+MainGame.EXT));
				emptyWrite.close();
			}
		}
			catch(IOException e){
				// should not throw io exception, make a promt anyways
			}
		}
	
	
	@Override
	public void show() {
			if(!(new File(System.getProperty("user.dir")+"//highscore").exists())){
				new File(System.getProperty("user.dir")+"//highscore").mkdirs();
			}
			choose.setCurrentDirectory(new File(System.getProperty("user.dir")+"//highscore")); 
			
		
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
