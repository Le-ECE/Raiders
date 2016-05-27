package com.mygdx.game;

public class SaveManager {
private String tempName;
private int tempDifficulty;
private int tempTimeSeconds;
private Save loadedSave;

public SaveManager(){
Save newSave=new Save("No Name",0,0);
this.loadedSave=newSave;
}
public void setManagerName(String newName){
	this.tempName=newName;
}

public void setManagerDifficulty(int newDifficulty){
	this.tempDifficulty=newDifficulty;
}

public void setManagerTimeSeconds(int newTimeSeconds){
	this.tempTimeSeconds=newTimeSeconds;
}

public String getManagerName(){
	return this.tempName;
}

public int getManagerDifficulty(){
	return this.tempDifficulty;
}

public int getManagerTimeSeconds(){
	return this.tempTimeSeconds;
}

public void setSave(Save passSave){
this.loadedSave=passSave;
this.tempName=this.loadedSave.getName();
this.tempDifficulty=this.loadedSave.getDifficulty();
this.tempTimeSeconds=this.loadedSave.getTimeSeconds();
}


}
