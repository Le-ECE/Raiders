package com.mygdx.game;

public class Save {
private String name;
private int currentDifficulty;
private int timeSeconds;

public Save(){
}

public Save(String newName, int newDifficulty,int newTimeSeconds){
this.name=newName;
this.currentDifficulty=newDifficulty;
this.timeSeconds=newTimeSeconds;
}

public String getName(){
return this.name;	
}

public int getDifficulty(){
return this.currentDifficulty;	
}

public int getTimeSeconds(){
return this.timeSeconds;	
}

public void setName(String newName){
this.name=newName;	
}

public void setDifficulty(int newDifficulty){
this.currentDifficulty=newDifficulty;
}

public void setTimeSeconds(int newTimeSeconds){
this.timeSeconds=newTimeSeconds;
}

public String getTotalTime(){
return String.format("%02d:%02d:%02d", this.timeSeconds/3600, (this.timeSeconds%3600)/60, this.timeSeconds%60);
}

}