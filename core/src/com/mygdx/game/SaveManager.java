package com.mygdx.game;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
/**
 * The SaveManager class is responsible for saving the user's data into separate
 * save files. It does this using Save classes, which act as data classes that
 * SaveManager can use to retrieve data from.
 * 
 * @author David Le
 * @version 4.0 07.06.2016
 *
 *<p>
 *<b>Instance Variables</b>
 *<p>
 *<b>choose</b> Instance of JFileChooser used to select directory to save in
 *<p>
 *<b>newView</b> Instance of FileSystemView used to view the file directory
 *<p>
 *<b>loadedSave</b> Instance of Save used to extract data from
 */
public class SaveManager {

	JFileChooser choose = new JFileChooser();
	
	FileSystemView newView;
	
	private Save loadedSave;

	/**
	 * SaveManager constructor is responsible for initializing loadedSave to
	 * a new Save, with default parameters.
	 */
	public SaveManager() {
		this.loadedSave = new Save("No Name", 0, 0);
	}

	/**
	 * Set method for Save.
	 * @param newSave Save representing new Save data
	 */
	public void setSave(Save newSave) {
		this.loadedSave = newSave;
	}

	/**
	 * Get method for Save
	 * @return loadedSave Save representing current Save data
	 */
	public Save getSave() {
		return this.loadedSave;
	}

	/**
	 * The writeSave method is responsible for writing the user data to a
	 * labeled save file. The save file is labeled with the date, as well
	 * as the user's name. The save file contains the name, game stage, as
	 * well as the current user time. The first if statement is used to
	 * determine if the save directory exists. If it does not, a new save
	 * directory is created. The first try-catch is used to catch various
	 * IO exceptions.
	 //post java doc
	 //change write save 
	 */
	public void writeSave() {
		newView = choose.getFileSystemView();
		if (!new File(newView.getDefaultDirectory() + "//High Noon Data//RaidersSave//saves").exists()) {
			new File(newView.getDefaultDirectory() + "//High Noon Data//RaidersSave//saves").mkdirs();
		}
		choose.setCurrentDirectory(new File(newView.getDefaultDirectory() + "//High Noon Data//RaidersSave//saves"));


		try {
			PrintWriter saveWriter = new PrintWriter(
					new FileWriter("//" + choose.getCurrentDirectory() + "//" + "GameSave" + MainGame.EXT));


			saveWriter.println(loadedSave.getName());
			saveWriter.println(loadedSave.getDifficulty());
			saveWriter.println(loadedSave.getTimeSeconds());
			saveWriter.close();
		} catch (IOException e) {
		}
}
}