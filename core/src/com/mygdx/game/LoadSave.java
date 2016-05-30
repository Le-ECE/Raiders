package com.mygdx.game;

import java.io.*;

import javax.swing.*;

public class LoadSave {
	JFileChooser choose =new JFileChooser();
	
	
public LoadSave(){
	 if(!(new File(System.getProperty("user.dir")+"//saves").exists())){
	    	new File(System.getProperty("user.dir")+"//saves").mkdirs();
	 }
     choose.setCurrentDirectory(new File(System.getProperty("user.dir")+"//saves"));
}
	// add value for current time, make load save a screen, have it call setscreen gamescreen instead of difficulty calling it
	 public void fileRead(){
		 try{
			 BufferedReader firstRead=new BufferedReader(new FileReader("//"+choose.getCurrentDirectory()+"//"));
		 }
		 catch(IOException e)
		 {
			 JOptionPane.showMessageDialog(null, "The save file is corrupt.","ATTENTION",JOptionPane.ERROR_MESSAGE);
		 }
	 }
public static void main(String[]args){
	
	new LoadSave();
	System.out.print("test");
}
}

