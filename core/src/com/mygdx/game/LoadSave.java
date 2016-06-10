package com.mygdx.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This class is responsible for reading information from a save file that the
 * user is saving their game to (which includes information such as the current
 * level the user is on, their name, and their current time. This class also
 * creates a user interface that renders a screen and allows for the user to be
 * able to choose from their saves which one they would like to load.
 * 
 * <p>
 * <b>Instance Variables</b>
 * <p>
 * <b>name</b> String used to store name of player in save file
 * <p>
 * <b>difficulty</b> int used to store the stage the player is on from the save
 * file
 * <p>
 * <b>timeSeconds</b> int used to store the time the player has saved in the
 * save file
 * <p>
 * <b>emptyWrite</b> PrintWriter object used to write an empty file
 * <p>
 * <b>choose</b> Instance of JFileChooser used to select the file to load from
 * <p>
 * <b>newView</b> FileSystemView object used to find the default user document directory
 * <p>
 * @author David Le
 * @version 4.0 07.06.2016
 *
 */
public class LoadSave {

	private String name;

	private int difficulty;
	private int timeSeconds;
	private PrintWriter emptyWrite;

	private JFileChooser choose = new JFileChooser();
	private FileSystemView newView;

	/**
	 * The LoadSave constructor is used to take in a SpriteBatch and a MainGame
	 * from other classes as parameters. This allows for the LoadSave class to
	 * make use of the SpriteBatch and MainGame without having to create new
	 * ones. SpriteBatch being used for rendering on screen elements, and
	 * MainGame being used to manage on screen elements. The constructor checks with
	 * for if statements if a save directory, and writes one if it does not exist. 
	 * The constructor then reads the Game's default save file, and if valid, loads
	 * a new GameScreen object with the saved values. If the save is corrupted,
	 * an empty save is written to the default save file, and the user is returned
	 * to the main menu screen.
	 * 
	 * @param batch
	 *            SpriteBatch used to render on-screen elements
	 * @param game
	 *            MainGame used to manage multiple screens
	 */
	public LoadSave(SpriteBatch batch, MainGame game) {

		newView = choose.getFileSystemView();
		if (!new File(newView.getDefaultDirectory() + "//High Noon Data//RaidersSave//saves").exists()) {
			new File(newView.getDefaultDirectory() + "//High Noon Data//RaidersSave//saves").mkdirs();
		}
		choose.setCurrentDirectory(new File(newView.getDefaultDirectory() + "//High Noon Data//RaidersSave//saves"));
		try {
			if (!(new File("//" + choose.getCurrentDirectory() + "//" + "GameSave" + MainGame.EXT).isFile())) {
				emptyWrite = new PrintWriter(
						new FileWriter("//" + choose.getCurrentDirectory() + "//" + "GameSave" + MainGame.EXT));
				emptyWrite.close();
			}
		} catch (IOException e) {
		}

		if (fileRead()) {
			JOptionPane.showMessageDialog(null, "The save file is corrupted. Resetting game state...", "Attention",
					JOptionPane.ERROR_MESSAGE);
			try {
				emptyWrite = new PrintWriter(
						new FileWriter("//" + choose.getCurrentDirectory() + "//" + "GameSave" + MainGame.EXT));
				emptyWrite.close();
			} catch (IOException e) {
			}

			game.setScreen(new MainMenu(batch, game));
		} else {
			game.getSaveManager().setSave(new Save(name, difficulty, timeSeconds));
			game.setScreen(new GameScreen(batch, game, name, difficulty, timeSeconds));
		}

	}

	/**
	 * The fileRead method is responsible for reading the default game save
	 * and determining whether it is valid or corrupted. If valid, the
	 * LoadSave class will be passed the game save's values, and create
	 * a new GameScreen object with the save values. 
	 * 
	 * @return boolean used to determine if the the save is valid or corrupted.
	
	*/
	private boolean fileRead() {
		char[] illegalChar = { 47, 92, 58, 42, 63, 34, 60, 62 };
		boolean illegalTrue = false;

		try {
			if (!(new File("//" + choose.getCurrentDirectory() + "//" + "GameSave" + MainGame.EXT).isFile())) {
				emptyWrite = new PrintWriter(
						new FileWriter("//" + choose.getCurrentDirectory() + "//" + "GameSave" + MainGame.EXT));
				emptyWrite.close();
				return true;
			}

			BufferedReader firstRead = new BufferedReader(
					new FileReader("//" + choose.getCurrentDirectory() + "//" + "GameSave" + MainGame.EXT));
			BufferedReader nullRead = new BufferedReader(
					new FileReader("//" + choose.getCurrentDirectory() + "//" + "GameSave" + MainGame.EXT));

			if (nullRead.readLine() != null) {
				name = firstRead.readLine().trim();
				if (name.isEmpty())
					illegalTrue = true;
				for (int a = 0; a < name.length(); a++) {
					for (int b = 0; b < illegalChar.length; b++) {
						if (name.charAt(a) == illegalChar[b])
							illegalTrue = true;
					}
				}
				if (illegalTrue) {
					firstRead.close();
					nullRead.close();
					return true;
				}
			} else {
				firstRead.close();
				nullRead.close();
				return true;
			}

			if (nullRead.readLine() != null)
				difficulty = Integer.parseInt(firstRead.readLine());
			else {
				firstRead.close();
				nullRead.close();
				return true;
			}
			if (nullRead.readLine() != null)
				timeSeconds = Integer.parseInt(firstRead.readLine());
			else {
				firstRead.close();
				nullRead.close();
				return true;
			}
			if (difficulty < 0 || difficulty > 5) {
				firstRead.close();
				nullRead.close();
				return true;
			}
			if (timeSeconds < 0) {
				firstRead.close();
				nullRead.close();
				return true;
			}
			firstRead.close();
			nullRead.close();
			return false;
		} catch (NumberFormatException er) {
			return true;
		} catch (IOException e) {
			return true;
		}

	}
}
