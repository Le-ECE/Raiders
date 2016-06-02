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
	
	private String nameOfSave;
	//private Save selectedSave;
	
	JFileChooser choose =new JFileChooser();
	
	
public LoadSave(SpriteBatch batch,MainGame game){
	this.batch=batch;
	this.game=game;
	
}
//https://docs.oracle.com/javase/tutorial/essential/io/dirs.html
	// add value for current time, make load save a screen, have it call setscreen gamescreen instead of difficulty calling it
public void fileSelector(){
	//add name of save to create, make create work
	nameOfSave="ass.txt";
}

public boolean fileRead(){
	char[]illegalChar={47,92,58,42,63,34,60,62};
	boolean illegalTrue=false;
	
	
		 try{
			 System.out.print(choose.getCurrentDirectory().toString()+nameOfSave);	
			 BufferedReader firstRead=new BufferedReader(new FileReader("//"+choose.getCurrentDirectory()+"//"+nameOfSave));
			 BufferedReader nullRead=new BufferedReader(new FileReader("//"+choose.getCurrentDirectory()+"//"+nameOfSave));
			if(nullRead.readLine()!=null){
			name=firstRead.readLine().trim();
			if(name.isEmpty())
				illegalTrue=true;
			for(int a=0;a<name.length();a++){
				for(int b=0;b<illegalChar.length;b++){
				if(name.charAt(a)==illegalChar[b]){
					illegalTrue=true;
				}
				}
			}
			if(illegalTrue){
				//name=" ";
				return true;
			}
			}
			else{
				return true;
			}
				
			try{
			if(nullRead.readLine()!=null)
				difficulty=Integer.parseInt(firstRead.readLine());
			else
				return true;
			if(nullRead.readLine()!=null)
				timeSeconds=Integer.parseInt(firstRead.readLine());
			else
			return true;
			if(difficulty<0||difficulty>5){
				//difficulty=-1;
				return true;
			}
			if(timeSeconds<0){
				//timeSeconds=-1;
				return true;
			}
			}
			catch(NumberFormatException er){
				return true; // fix corrupted stuff
			}
		 
			 firstRead.close();
		 nullRead.close();
		 return false;
		 }
//		 catch(FileNotFoundException err){
	//		 JOptionPane.showMessageDialog(null, "The save file cannot be found.","ATTENTION",JOptionPane.ERROR_MESSAGE);
		//	 corrupted=true;
		 //}
		 catch(IOException e)
		 {
			 return true;
		 }
	 }
	 
	 public void create(){
		
			// move to render after
			
	 }
	 
	@Override
	public void show() {

		 choose.setCurrentDirectory(new File(System.getProperty("user.dir")));;
			if(!(new File(System.getProperty("user.dir")+"//saves").exists())){
				new File(System.getProperty("user.dir")+"//saves").mkdirs();
			}
			choose.setCurrentDirectory(new File(System.getProperty("user.dir")+"//saves")); 
		fileSelector();
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		// when they press load button
		
	//	fileRead();
		System.out.println(corrupted);
		if(fileRead()){
			 JOptionPane.showMessageDialog(null, "The save file is corrupt.","Attention",JOptionPane.ERROR_MESSAGE);
				game.setScreen(new MainMenu(batch, game));
		}
				else{
		game.getSaveManager().setSave(new Save(name,difficulty,timeSeconds));
	 game.setScreen(new GameScreen(batch, game,name, difficulty,timeSeconds));
		}
		
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

