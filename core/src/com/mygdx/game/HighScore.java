package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.io.*;
import javax.swing.*;
public class HighScore implements Screen {

	//private SpriteBatch batch;
	//private MainGame game;
	ArrayList<Save> listSaveEasy;
	ArrayList<Save> listSaveMed;
	ArrayList<Save> listSaveHard;
	String currentName;
	Save currentFile;
	JFileChooser choose=new JFileChooser();


	public HighScore(){
		//this.batch=batch;
		//this.game=game;
	}
	
	public void printer(){

	}

	public static void addSave(Save passSave){
		HighScore newScore=new HighScore();
		newScore.scoreRead();
		if(passSave.getDifficulty()>=0&&passSave.getDifficulty()<=1){
			newScore.listSaveEasy.add(passSave);
		}
			if(passSave.getDifficulty()>=2&&passSave.getDifficulty()<=3){
				newScore.listSaveMed.add(passSave);
			}
				if(passSave.getDifficulty()>=4&&passSave.getDifficulty()<=5){
					newScore.listSaveHard.add(passSave);
				}
		newScore.scoreSorter();
		newScore.scoreWrite();
		for(int a=0;a<newScore.listSaveEasy.size();a++){
		JOptionPane.showMessageDialog(null, newScore.listSaveEasy.get(a).getName(),"attention",JOptionPane.ERROR_MESSAGE);
		JOptionPane.showMessageDialog(null, newScore.listSaveEasy.get(a).getDifficulty(),"attention",JOptionPane.ERROR_MESSAGE);
		JOptionPane.showMessageDialog(null, newScore.listSaveEasy.get(a).getTimeSeconds(),"attention",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void scoreWrite(){
		
	}

	public void scoreRead(){
		if(!(new File(System.getProperty("user.dir")+"//highscore").exists())){
			new File(System.getProperty("user.dir")+"//highscore").mkdirs();
		}
		choose.setCurrentDirectory(new File(System.getProperty("user.dir")+"//highscore")); 
		
		listSaveEasy = new ArrayList<Save>();
		listSaveMed = new ArrayList<Save>();
		listSaveHard = new ArrayList<Save>();
		PrintWriter emptyWrite;
		BufferedReader scoreReader;
		BufferedReader nullReader;
		String input1;
		String input2;
		String input3;
		//  String inputSave;
		//  String[]inputSplit;
		// new changes

		int inputDifficulty=0;
		int inputTime=0;
		String inputName="No Name";
		boolean writable=false;
		char[]illegalChar={47,92,58,42,63,34,60,62};
		
		try{
			if(!(new File("//"+choose.getCurrentDirectory()+"//"+"HighScore"+MainGame.EXT).isFile())){
				emptyWrite=new PrintWriter(new FileWriter("//"+choose.getCurrentDirectory()+"//"+"HighScore"+MainGame.EXT));
				emptyWrite.close();
			}
			scoreReader=new BufferedReader(new FileReader("//"+choose.getCurrentDirectory()+"//"+"HighScore"+MainGame.EXT));
			nullReader=new BufferedReader(new FileReader("//"+choose.getCurrentDirectory()+"//"+"HighScore"+MainGame.EXT));
			while(nullReader.readLine()!=null&&nullReader.readLine()!=null&&nullReader.readLine()!=null){
				input1=scoreReader.readLine().trim();
				input2=scoreReader.readLine().trim();
				input3=scoreReader.readLine().trim();
			
					if(!(input1.isEmpty()||input2.isEmpty()||input3.isEmpty())){
		
						try{
						inputDifficulty=Integer.parseInt(input1);
						inputTime=Integer.parseInt(input2);
						inputName=input3;
						writable=true;
						if(inputDifficulty<0||inputDifficulty>5)
							writable=false;
						if(inputTime<0)
							writable=false;
						for(int a=0;a<inputName.length();a++)
							for(int b=0;b<illegalChar.length;b++)
								if(inputName.charAt(a)==illegalChar[b])
									writable=false;
						}
					
						catch(NumberFormatException er){
						//this part checks for no parsing
							//	JOptionPane.showMessageDialog(null, "didnt parse correctly,remove this","attention",JOptionPane.ERROR_MESSAGE);
						}	
					}
					System.out.println(writable);
			if(writable){
						if(inputDifficulty>=0&&inputDifficulty<=1){
							listSaveEasy.add(new Save(inputName,inputDifficulty,inputTime));
						}
							if(inputDifficulty>=2&&inputDifficulty<=3){
								listSaveMed.add(new Save(inputName,inputDifficulty,inputTime));
							}
								if(inputDifficulty>=4&&inputDifficulty<=5){
									listSaveHard.add(new Save(inputName,inputDifficulty,inputTime));
								}
			}
								
							
			}
			scoreReader.close();
			nullReader.close();
		}
								
					catch(IOException e){
						// should not throw io exception, make a promt anyways
					}
			}


			@Override
			public void show() {	
scoreRead();
scoreSorter();
scoreWrite();
			}

			public void scoreSorter(){

			}

			@Override
			public void render(float delta) {
				// TODO Auto-generated method stub
				// display sorted saves here
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
