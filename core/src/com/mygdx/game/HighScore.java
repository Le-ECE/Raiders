package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.io.*;
import javax.swing.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.*;
public class HighScore implements Screen,Printable{

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
	
	@Override
	public int print(Graphics g, PageFormat pf,int page) throws PrinterException {
		if(page>0)
			return NO_SUCH_PAGE;
		
		Graphics2D g2d = (Graphics2D)g;
		    g2d.translate(pf.getImageableX(), pf.getImageableY());
		    g.drawString("High Scores", 275, 50);
		    g.drawString("Name", 150, 100);
		    g.drawString("Time of Completion [Seconds]",350, 100);
		    g.drawString("Easy Difficulty", 25, 150);
		    for(int a=0;a<listSaveEasy.size();a++){
	g.drawString(listSaveEasy.get(a).getName(), 150, 175+a*15);
g.drawString(""+listSaveEasy.get(a).getTimeSeconds(), 350, 175+a*15);
				}
		    g.drawString("Medium Difficulty", 25, 340);
		       for(int b=0;b<listSaveMed.size();b++){
		    		g.drawString(listSaveMed.get(b).getName(), 150, 365+b*15);
		    		g.drawString(""+listSaveMed.get(b).getTimeSeconds(), 350, 365+b*15);
					}
				
		       g.drawString("Hard Difficulty", 25, 510);
		       for(int c=0;c<listSaveHard.size();c++){
		    		g.drawString(listSaveMed.get(c).getName(), 150, 535+c*15);
		    		g.drawString(""+listSaveMed.get(c).getTimeSeconds(), 350, 535+c*15);
					}
	return PAGE_EXISTS; 
	}
	
	public void printer(){
		
PrinterJob newJob = PrinterJob.getPrinterJob();


boolean printDiag=newJob.printDialog();
newJob.setPrintable(this);

if(printDiag){
	try{
		scoreLimit();
		newJob.print();
	}
	catch(PrinterException e){
	}
}
	
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
	}
	
	public void scoreLimit(){
		if(listSaveEasy.size()>10)
			listSaveEasy.subList(10,listSaveEasy.size()).clear();
		if(listSaveMed.size()>10)
			listSaveMed.subList(10,listSaveMed.size()).clear();
		if(listSaveHard.size()>10)
			listSaveHard.subList(10,listSaveHard.size()).clear();
	}
	
	public void scoreWrite(){
		PrintWriter scoreWrite;
		if(!(new File(System.getProperty("user.dir")+"//highscore").exists())){
			new File(System.getProperty("user.dir")+"//highscore").mkdirs();
		}
		choose.setCurrentDirectory(new File(System.getProperty("user.dir")+"//highscore")); 
		try{
				scoreWrite=new PrintWriter(new FileWriter("//"+choose.getCurrentDirectory()+"//"+"HighScore"+MainGame.EXT));
				scoreLimit();
				for(Save a:listSaveEasy){
				scoreWrite.println(a.getDifficulty());
				scoreWrite.println(a.getTimeSeconds());
				scoreWrite.println(a.getName());
				}
				
				for(Save b:listSaveMed){
					scoreWrite.println(b.getDifficulty());
					scoreWrite.println(b.getTimeSeconds());
					scoreWrite.println(b.getName());
					}
				
				for(Save c:listSaveHard){
					scoreWrite.println(c.getDifficulty());
					scoreWrite.println(c.getTimeSeconds());
					scoreWrite.println(c.getName());
					}
				scoreWrite.close();
		}
		catch(IOException e){
			// should not have prompt
		}
	}
	
	public void scoreSorter(){
		int d;
		Save temp;
for(int a=0;a<listSaveEasy.size();a++){
d=a;
for(int b=a;b<listSaveEasy.size();b++)
	if(listSaveEasy.get(b).getTimeSeconds()<listSaveEasy.get(d).getTimeSeconds())
		d=b;
	if(d!=a){
		temp=listSaveEasy.get(a);
		listSaveEasy.set(a,listSaveEasy.get(d));
		listSaveEasy.set(d,temp);
	}
}	

for(int a=0;a<listSaveMed.size();a++){
d=a;
for(int b=a;b<listSaveMed.size();b++)
	if(listSaveMed.get(b).getTimeSeconds()<listSaveMed.get(d).getTimeSeconds())
		d=b;
	if(d!=a){
		temp=listSaveMed.get(a);
		listSaveMed.set(a,listSaveMed.get(d));
		listSaveMed.set(d,temp);
	}
}

for(int a=0;a<listSaveHard.size();a++){
d=a;
for(int b=a;b<listSaveHard.size();b++)
	if(listSaveHard.get(b).getTimeSeconds()<listSaveHard.get(d).getTimeSeconds())
		d=b;
	if(d!=a){
		temp=listSaveHard.get(a);
		listSaveHard.set(a,listSaveHard.get(d));
		listSaveHard.set(d,temp);
	}
}

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
							writable=false;
						}	
					}
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
// call with button
printer();
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
