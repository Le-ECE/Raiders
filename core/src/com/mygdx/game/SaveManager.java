package com.mygdx.game;
import java.io.*;
import java.util.*;
public class SaveManager{

private Save loadedSave;

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
	String saveName="";
	Calendar newCalendar=Calendar.getInstance();
	newCalendar.setTimeInMillis(System.currentTimeMillis());
	saveName=newCalendar.toString();
}
}