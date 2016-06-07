package com.mygdx.game;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

public class SaveManager {

 JFileChooser choose = new JFileChooser();
 FileSystemView newView; 
 private Save loadedSave;

 public SaveManager() {
  this.loadedSave = new Save("No Name", 0, 0);
 }

 public void setSave(Save newSave) {
  this.loadedSave = newSave;
 }

 public Save getSave() {
  return this.loadedSave;
 }

 public void writeSave() {
	 newView = choose.getFileSystemView();
  if (!new File(newView.getDefaultDirectory()+"//High Noon Data//RaidersSave//saves").exists()) {
   new File(newView.getDefaultDirectory()+"//High Noon Data//RaidersSave//saves").mkdirs();
  }
 choose.setCurrentDirectory(new File(newView.getDefaultDirectory()+"//High Noon Data//RaidersSave//saves"));
 
  String saveName = "";

  Calendar newCalendar = Calendar.getInstance();
  newCalendar.setTimeInMillis(System.currentTimeMillis());

  saveName = newCalendar.get(Calendar.DAY_OF_MONTH) + "~" + newCalendar.get(Calendar.MONTH) + "~"
    + newCalendar.get(Calendar.YEAR) + " " + newCalendar.get(Calendar.HOUR) + "«"
    + newCalendar.get(Calendar.MINUTE) + "«" + newCalendar.get(Calendar.SECOND);
  String writeName = loadedSave.getName() + " " + saveName + MainGame.EXT;
  try {
   PrintWriter saveWriter = new PrintWriter(
     new FileWriter("//" + choose.getCurrentDirectory() +"//"+ writeName));
 
   saveWriter.println(loadedSave.getName());
   saveWriter.println(loadedSave.getDifficulty());
   saveWriter.println(loadedSave.getTimeSeconds());
   saveWriter.close();
  } catch (IOException e) {
   // fix the goddamn cursor lock when switching
   JOptionPane.showMessageDialog(null, "Error saving your gamestate.", "Attention", JOptionPane.ERROR_MESSAGE);
  }
 }
}