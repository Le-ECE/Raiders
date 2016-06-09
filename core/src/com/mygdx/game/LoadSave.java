package com.mygdx.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This class is responsible for reading information from a save file that the
 * user is saving their game to (which includes information such as the current
 * level the user is on, their name, and their current time. This class also
 * creates a user interface that renders a screen and allows for the user to
 * be able to choose from their saves which one they would like to load.
 * 
 * <p>
 * <b>Instance Variables</b>
 * <p>
 * <b>batch</b> Instance of SpriteBatch used to render on screen elements
 * <p>
 * <b>game</b> Instance of MainGame used to manage screen changes
 * <p>
 * <b>name</b> String used to store name of player in save file
 * <p>
 * <b>nameOfSave</b> String used to store name of the save file to load from
 * <p>
 * <b>difficulty</b> int used to store the stage the player is on from the save file
 * <p>
 * <b>timeSeconds</b> int used to store the time the player has saved in the save file
 * <p>
 * <b>savePanelText</b> Instance of Texture used to store image of load panel
 * <p>
 * <b>savePanelSprite</b> Instance of Sprite used to create a Sprite of the load panel
 * <p>
 * <b>choose</b> Instance of JFileChooser used to select the file to load from
 * 
 * @author David Le
 * @version 4.0 07.06.2016
 *
 */
public class LoadSave implements Screen {

	private SpriteBatch batch;

	private MainGame game;

	private String name;
	private String nameOfSave;

	private int difficulty;
	private int timeSeconds;

	Texture savePanelText;

	Sprite savePanelSprite;

	JFileChooser choose = new JFileChooser();

	/**
	 * The LoadSave constructor is used to take in a SpriteBatch and a MainGame as
	 * parameters to be used for rendering on screen elements and managing multiple
	 * screens, respectively.
	 * 
	 * @param batch SpriteBatch used to draw on-screen elements
	 * @param game MainGame used to manage multiple game screens
	 */
	public LoadSave(SpriteBatch batch, MainGame game) {
		this.batch = batch;
		this.game = game;

	}

	/**
	 * This method is used to select the current working file that the class loads
	 * data from.
	 */
	public void fileSelector() {
		// https://docs.oracle.com/javase/tutorial/essential/io/dirs.html
		// add value for current time, make load save a screen, have it call
		// setscreen gamescreen instead of difficulty calling it
		
		// add name of save to create, make create work
		nameOfSave = "ass.txt";
	}

	/**
	 * This method is used to read data from the file, as well as checking if
	 * the data read from the file is in valid format to be used. The first
	 * try-catch is used for catching various IO exceptions. The first if
	 * statement is used to determine if the current line is null. If it is, the
	 * BufferedReaders are terminated and the method returns true. If not,
	 * the String name is assigned as that current line. The second if statement
	 * determines if the name variable is empty. If it is, the boolean illegalTrue
	 * is set to true. The first and second for loops are used to run through the name
	 * and the array of illegal characters. The third if statement determines
	 * if the name contains any of the illegal characters. If it does, then the
	 * boolean illegalTrue is true. The fourth if statement is used to determine if
	 * illegalTrue is true. If it is, the BufferedReaders are closed, and the
	 * method returns true. The second try-catch is used for catching number format
	 * exceptions. The fifth if statement is used to determine if the current line is
	 * null. If it is, the BufferedReaders are closed, and the method returns true.
	 * Otherwise, the int difficulty variable is assigned to the number read from the
	 * file. The sixth if statement is used to determine if the next line is null. If
	 * it is, the BufferedReaders are terminated. Otherwise, the int timeSeconds variable
	 * gets assigned to the number read from the file. The seventh if statement is used
	 * to determine if the difficulty variable is within valid range. If not, the Buffered
	 * Readers are terminated, and the method returns true. The eighth if statement is
	 * used to determine if the timeSeconds variable is a negative number. If it is,
	 * the BufferedReaders are terminated, and the method returns true.
	 * 
	 * @return boolean true/false based on file validity
	 */
	public boolean fileRead() {
		char[] illegalChar = { 47, 92, 58, 42, 63, 34, 60, 62 };
		boolean illegalTrue = false;

		try {
			BufferedReader firstRead = new BufferedReader(
					new FileReader("//" + choose.getCurrentDirectory() + "//" + nameOfSave));
			BufferedReader nullRead = new BufferedReader(
					new FileReader("//" + choose.getCurrentDirectory() + "//" + nameOfSave));
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

			try {
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
			} catch (NumberFormatException er) {
				firstRead.close();
				nullRead.close();
				return true; // fix corrupted stuff
			}

			firstRead.close();
			nullRead.close();
			return false;
		}
		catch (IOException e) {
			return true;
		}
	}

	/**
	 * The show method is an overridden method from the Screen interface. The show
	 * method is responsible for initiating variables, setting any directories for
	 * file loading, and running any methods that need to run at the beginning of
	 * execution. The first if statement is used to determine if the saves folder
	 * exists. If it does not, one is created.
	 */
	@Override
	public void show() {

		// create save screen
		savePanelText = new Texture("assets/save_screen.png");

		savePanelSprite = new Sprite(savePanelText);
		savePanelSprite.setPosition(0, 0);

	//	if (!(new File(System.getProperty("user.dir") + "//saves").exists())) {
		//	new File(System.getProperty("user.dir") + "//saves").mkdirs();
		//}
		//choose.setCurrentDirectory(new File(System.getProperty("user.dir") + "//saves"));
		//fileSelector();
		// TODO Auto-generated method stub

	}

	/**
	 * The render method is an overridden method from the Screen interface. The render
	 * method loops once every frame of game execution. The first if statement is used
	 * to determine if there is anything wrong with the save file (based off of the
	 * conditions by the fileRead() method). If there is, a JOptionPane prompt dialog
	 * displays, notifying the user that there is a problem with the save file. If
	 * the fileRead() method is false, a new save is created with the information from
	 * the file, and the screen is set to a new GameScreen with all the information read
	 * from the load file.
	 */
	@Override
	public void render(float delta) {
		batch.begin();

		//backgroundSprite.draw(batch);
		// when they press load button
		//if (fileRead()) {
		//	JOptionPane.showMessageDialog(null, "The save file is corrupt.", "Attention", JOptionPane.ERROR_MESSAGE);
		//	game.setScreen(new MainMenu(batch, game));
		//} else {
		//	game.getSaveManager().setSave(new Save(name, difficulty, timeSeconds));
		//	game.setScreen(new GameScreen(batch, game, name, difficulty, timeSeconds));
		//}

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
