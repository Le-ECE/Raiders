package com.mygdx.game;
import java.io.*;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
public class SaveManager{

JFileChooser choose = new JFileChooser();
private Save loadedSave;
static final String EXT=".sav";

public SaveManager(){
this.loadedSave=new Save("No Name",0,0);
}

public void setSave(Save newSave){
	this.loadedSave=newSave;
}

public Save getSave(){
	return this.loadedSave;
}


public void writeSave(){
	choose.setCurrentDirectory(new File(System.getProperty("user.dir")));;
	if(!(new File(System.getProperty("user.dir")+"//saves").exists())){
		new File(System.getProperty("user.dir")+"//saves").mkdirs();
	}
	choose.setCurrentDirectory(new File(System.getProperty("user.dir")+"//saves")); 

	//JOptionPane.showMessageDialog(null, loadedSave.getName()+" "+loadedSave.getDifficulty()+" "+loadedSave.getTimeSeconds(),"shit",JOptionPane.ERROR_MESSAGE);
	
	String saveName="";
	
	Calendar newCalendar=Calendar.getInstance();
	newCalendar.setTimeInMillis(System.currentTimeMillis());
	//saveName=newCalendar.getTime().toString();
	
	saveName=newCalendar.get(Calendar.DAY_OF_MONTH)+"-"+newCalendar.get(Calendar.MONTH)+"-"+newCalendar.get(Calendar.YEAR);
	String writeName=loadedSave.getName()+" "+saveName+EXT;
	System.out.println(writeName);
	try{
		PrintWriter saveWriter=new PrintWriter(new FileWriter("//"+choose.getCurrentDirectory()+"//"+writeName));
		//PrintWriter saveWriter=new PrintWriter(new FileWriter(""));
		saveWriter.println(loadedSave.getName());
	saveWriter.println(loadedSave.getDifficulty());
		saveWriter.println(loadedSave.getTimeSeconds());
		saveWriter.close();
	}
	catch(IOException e){
		JOptionPane.showMessageDialog(null, "Error saving your gamestate.","Attention",JOptionPane.ERROR_MESSAGE);
	}
	}
}