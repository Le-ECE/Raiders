package com.mygdx.game;

import com.badlogic.gdx.Input.Keys;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * This class, using Java's FileIO, reads the user's score in game, sorts the
 * score using a selection sort algorithm, and then writes the user's score on a
 * save file. This class is also responsible for printing the highscores on a
 * piece of paper if the user wishes. The class contains a render method, and
 * implements the Screen interface. This is to be able to display the highscores
 * on a highscore screen through the main menu.
 * 
 * @author David Le
 * @version 4.0 03/06/2016
 * 
 *          <p>
 *          <b>Instance Variables</b>
 *          <p>
 *          <b>batch</b> Instance of SpriteBatch used to render on screen
 *          elements
 *          <p>
 *          <b>game</b> Instance of MainGame used to change between screens
 *          <p>
 *          <b>scoreboardFont</b> Instance of BitmapFont used to draw text using
 *          custom Windows fonts
 *          <p>
 *          <b>listSaveEast</b> ArrayList of Save used to store data of easy
 *          difficulty saves
 *          <p>
 *          <b>listSaveMed</b> ArrayList of Save used to store data of medium
 *          difficulty saves
 *          <p>
 *          <b>listSaveHard</b> ArrayList of Save used to store data of hard
 *          difficulty saves
 *          <p>
 *          <b>currentName</b> String representing the user's name
 *          <p>
 *          <b>currentFile</b> Instance of Save used to save to a file
 *          <p>
 *          <b>choose</b> Instance of file chooser which allows the user to
 *          choose files to save in
 *          <p>
 *          <b>nextRect</b> Instance of Rectangle that detects mouse input for
 *          the next button
 *          <p>
 *          <b>prevRect</b> Instance of Rectangle that detects mouse input for
 *          the previous button
 *          <p>
 *          <b>scoreboardEasyText</b> Instance of Texture used to store custom
 *          images of easy scoreboard
 *          <p>
 *          <b>scoreboardMedText</b> Instance of Texture used to store custom
 *          images of medium scoreboard
 *          <p>
 *          <b>scoreboardHardText</b> Instance of Texture used to store custom
 *          images of hard scoreboard
 *          <p>
 *          <b>backgroundText</b> Instance of Texture used to store custom
 *          images of the background
 *          <p>
 *          <b>nextText</b> Instance of Texture used to store custom images of
 *          the next button
 *          <p>
 *          <b>nextDarkText</b> Instance of Texture used to store custom images
 *          of the dark next button
 *          <p>
 *          <b>prevText</b> Instance of Texture used to store custom images of a
 *          previous button
 *          <p>
 *          <b>prevDarkText</b> Instance of Texture used to store custom images
 *          of a dark previous button
 *          <p>
 *          <b>menuDarkText</b> Instance of Texture used to store custom images
 *          of a dark menu button
 *          <p>
 *          <b>menuText</b> Instance of Texture used to store custom images of a
 *          menu button
 *          <p>
 *          <b>scoreboardEasySprite</b> Instance of Sprite used to make a sprite
 *          of scoreboardEasyText
 *          <p>
 *          <b>scoreboardMedSprite</b> Instance of Sprite used to make a sprite
 *          of scoreboardMedText
 *          <p>
 *          <b>scoreboardHardSprite</b> Instance of Sprite used to make a sprite
 *          of scoreboardHardText
 *          <p>
 *          <b>backgroundSprite</b> Instance of Sprite class used to make a
 *          sprite from backgroundText
 *          <p>
 *          <b>nextSprite</b> Instance of Sprite class used to make a sprite
 *          from nextText
 *          <p>
 *          <b>nextDarkSprite</b> Instance of Sprite used to make a Sprite from
 *          nextDarkText
 *          <p>
 *          <b>prevSprite</b> Instance of Sprite used to make a sprite from
 *          prevText
 *          <p>
 *          <b>prevDarkSprite</b> Instance of Sprite used to make a sprite from
 *          prevDarkText
 *          <p>
 *          <b>menuDarkSprite</b> Instance of Sprite used to make a sprite from
 *          menuDarkText
 *          <p>
 *          <b>menuSprite</b> Instance of Sprite used to make a sprite from
 *          menuText
 *          <p>
 *          <b>pageNum</b> Integer representing page of highscore the user is on
 *
 */
public class HighScore implements Screen, Printable {

	static SpriteBatch batch;

	static MainGame game;

	private BitmapFont scoreboardFont;

	private ArrayList<Save> listSaveEasy;
	private ArrayList<Save> listSaveMed;
	private ArrayList<Save> listSaveHard;

	String currentName;

	Save currentFile;

	private JFileChooser choose = new JFileChooser();
	private FileSystemView newView;

	private Rectangle nextRect;
	private Rectangle prevRect;
	private Rectangle backRect;
	private Rectangle printRect;

	private Texture scoreboardEasyText;
	private Texture scoreboardMedText;
	private Texture scoreboardHardText;
	private Texture backgroundText;
	private Texture nextText;
	private Texture nextDarkText;
	private Texture prevText;
	private Texture prevDarkText;
	private Texture backText;
	private Texture backDarkText;
	private Texture printText;
	private Texture printDarkText;

	private Sprite scoreboardEasySprite;
	private Sprite scoreboardMedSprite;
	private Sprite scoreboardHardSprite;
	private Sprite backgroundSprite;

	private Sprite nextSprite;
	private Sprite nextDarkSprite;
	private Sprite prevSprite;
	private Sprite prevDarkSprite;
	private Sprite backSprite;
	private Sprite backDarkSprite;
	private Sprite printSprite;
	private Sprite printDarkSprite;

	private BufferedImage logo;

	private int pageNum;

	/**
	 * The HighScore constructor is responsible for taking in a SpriteBatch and
	 * a MainGame. SpriteBatch is used for rendering onscreen elements, while
	 * the MainGame is used for setting game screens.
	 * 
	 * @param batch
	 *            SpriteBatch used to render on screen elements
	 * @param game
	 *            MainGame used to switch between displays
	 */
	public HighScore(SpriteBatch batch, MainGame game) {
		HighScore.batch = batch;
		HighScore.game = game;
	}

	/**
	 * An overridden method of the Printable used to be able to print the
	 * results from the high score page if the user wishes to print. The first
	 * for loop is used to format the easy names and time on the paper. The
	 * second for loop is used to format the medium names and time on the paper.
	 * The third for loop is used to format the hard names and time on the
	 * paper.
	 * 
	 * @param g
	 *            Graphics used to draw text for printing
	 * @param pf
	 *            PageFormat used to format page for text
	 * @param page
	 *            integer representing page of printing
	 * @return PAGE_EXISTS int representing an existing page
	 * @return NO_SUCH_PAGE int representing no pages
	 */
	@Override
	public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
		if (page > 0)
			return NO_SUCH_PAGE;

		Graphics2D g2d = (Graphics2D) g;
		try {
			logo = ImageIO.read(new File("assets/score_title.png"));
		} catch (IOException e) {
			game.setScreen(new MainMenu(batch, game));
		}

		g2d.translate(pf.getImageableX(), pf.getImageableY());
		g.drawImage(logo, 0, 0, null);
		g.drawString("Name", 150, 165);
		g.drawString("Time of Completion [h/m/s]", 350, 165);
		g.drawString("Easy Difficulty", 25, 200);
		for (int a = 0; a < listSaveEasy.size(); a++) {
			g.drawString(listSaveEasy.get(a).getName(), 150, 215 + a * 15);
			g.drawString("" + listSaveEasy.get(a).getTotalTime(), 350, 215 + a * 15);
		}
		g.drawString("Medium Difficulty", 25, 390);
		for (int b = 0; b < listSaveMed.size(); b++) {
			g.drawString(listSaveMed.get(b).getName(), 150, 415 + b * 15);
			g.drawString("" + listSaveMed.get(b).getTotalTime(), 350, 415 + b * 15);
		}

		g.drawString("Hard Difficulty", 25, 560);
		for (int c = 0; c < listSaveHard.size(); c++) {
			g.drawString(listSaveHard.get(c).getName(), 150, 575 + c * 15);
			g.drawString("" + listSaveHard.get(c).getTotalTime(), 350, 575 + c * 15);
		}
		return PAGE_EXISTS;
	}

	/**
	 * This method controls the printing of the highscores. The first if
	 * statement is used to print if the user agrees to the printing dialog.
	 */
	private void printer() {

		PrinterJob newJob = PrinterJob.getPrinterJob();

		boolean printDiag = newJob.printDialog();
		newJob.setPrintable(this);

		if (printDiag) {
			try {
				scoreLimit();
				newJob.print();
			} catch (PrinterException e) {
			}
		}

	}

	/**
	 * This method is responsible for adding the player's game values to the
	 * save arrays at the end of each level. The player's game values are sorted
	 * into appropriate save arrays that are based on the current difficulty.
	 * The first if statement is used to add the Save to the Save array list if
	 * the difficulty levels are between 0 and 1. The second if statement is
	 * used to add the Save to the Save array list if the difficulty levels are
	 * between 2 and 3. The third if statement is used to add the Save to the
	 * Save array if the difficulty is between 4 and 5.
	 * 
	 * @param passSave
	 *            Save temporary save file
	 */
	public static void addSave(Save passSave) {
		HighScore newScore = new HighScore(batch, game);
		newScore.scoreRead();
		if (passSave.getDifficulty() >= 0 && passSave.getDifficulty() <= 1) {
			newScore.listSaveEasy.add(passSave);
		}
		if (passSave.getDifficulty() >= 2 && passSave.getDifficulty() <= 3) {
			newScore.listSaveMed.add(passSave);
		}
		if (passSave.getDifficulty() >= 4 && passSave.getDifficulty() <= 5) {
			newScore.listSaveHard.add(passSave);
		}
		newScore.scoreSorter();
		newScore.scoreWrite();
	}

	/**
	 * This method is responsible for limiting the size of the save arrays in
	 * the case that they exceed the size of 10.
	 */
	private void scoreLimit() {
		if (listSaveEasy.size() > 10)
			listSaveEasy.subList(10, listSaveEasy.size()).clear();
		if (listSaveMed.size() > 10)
			listSaveMed.subList(10, listSaveMed.size()).clear();
		if (listSaveHard.size() > 10)
			listSaveHard.subList(10, listSaveHard.size()).clear();
	}

	/**
	 * This method writes the users high scores to a file in the format of
	 * difficulty, time, and name from top to bottom. If the file that the
	 * method tries to write to does not exist, then a new, empty file is
	 * created. The first if statement is used to check if the file exists. If
	 * it does not, then a new one is created. The first for loop is used to
	 * write all of the save data from the easy difficulty. The second for loop
	 * is used to write all of the save data from the medium difficulty. The
	 * third for loop is used to write all of the save data from the hard
	 * difficulty.
	 */
	private void scoreWrite() {
		PrintWriter scoreWrite;
		newView = choose.getFileSystemView();
		if (!new File(newView.getDefaultDirectory() + "//High Noon Data//RaidersSave//highscore").exists()) {
			new File(newView.getDefaultDirectory() + "//High Noon Data//RaidersSave//highscore").mkdirs();
		}
		choose.setCurrentDirectory(
				new File(newView.getDefaultDirectory() + "//High Noon Data//RaidersSave//highscore"));
		try {
			scoreWrite = new PrintWriter(
					new FileWriter("//" + choose.getCurrentDirectory() + "//" + "HighScore" + MainGame.EXT));
			scoreLimit();
			for (Save a : listSaveEasy) {
				scoreWrite.println(a.getDifficulty());
				scoreWrite.println(a.getTimeSeconds());
				scoreWrite.println(a.getName());
			}

			for (Save b : listSaveMed) {
				scoreWrite.println(b.getDifficulty());
				scoreWrite.println(b.getTimeSeconds());
				scoreWrite.println(b.getName());
			}

			for (Save c : listSaveHard) {
				scoreWrite.println(c.getDifficulty());
				scoreWrite.println(c.getTimeSeconds());
				scoreWrite.println(c.getName());
			}
			scoreWrite.close();
		} catch (IOException e) {
			game.setScreen(new MainMenu(batch, game));
		}
	}

	/**
	 * This method is responsible for sorting the array of save data, based on
	 * the time that the user completed in ascending order. This method uses a
	 * selection sort algorithm to complete the sort. The first for loop is used
	 * to run through the easy save array.
	 */
	private void scoreSorter() {
		int d;
		Save temp;
		for (int a = 0; a < listSaveEasy.size(); a++) {
			d = a;
			for (int b = a; b < listSaveEasy.size(); b++)
				if (listSaveEasy.get(b).getTimeSeconds() < listSaveEasy.get(d).getTimeSeconds())
					d = b;
			if (d != a) {
				temp = listSaveEasy.get(a);
				listSaveEasy.set(a, listSaveEasy.get(d));
				listSaveEasy.set(d, temp);
			}
		}

		for (int a = 0; a < listSaveMed.size(); a++) {
			d = a;
			for (int b = a; b < listSaveMed.size(); b++)
				if (listSaveMed.get(b).getTimeSeconds() < listSaveMed.get(d).getTimeSeconds())
					d = b;
			if (d != a) {
				temp = listSaveMed.get(a);
				listSaveMed.set(a, listSaveMed.get(d));
				listSaveMed.set(d, temp);
			}
		}

		for (int a = 0; a < listSaveHard.size(); a++) {
			d = a;
			for (int b = a; b < listSaveHard.size(); b++)
				if (listSaveHard.get(b).getTimeSeconds() < listSaveHard.get(d).getTimeSeconds())
					d = b;
			if (d != a) {
				temp = listSaveHard.get(a);
				listSaveHard.set(a, listSaveHard.get(d));
				listSaveHard.set(d, temp);
			}
		}

	}

	/**
	 * This method is used to write high score data (including name, time, and
	 * difficulty from a file. If the information from the file is valid, each
	 * piece of data is stored into its appropriate array. The first if
	 * statement determines if the high score file exists. If it does not, then
	 * a new high score file is created. The try-catch block is used to catch
	 * various IO exceptions. First while loop is used to run through the file,
	 * while it is not null. Second if statement determines if the file exists.
	 * If it does not, a new file writer is created. Third if statement is used
	 * to determine if the temporary string input variables are empty. If not,
	 * they are transferred to more permanent variables of appropriate type.
	 * Fourth if statement is used to determine if the difficulty int is
	 * "out of bounds". If it is, the writable boolean is set to false. The
	 * fifth if statement is used to determine if the time value is set to a
	 * negative number. If it is, the writable boolean is set to false. The
	 * sixth if statement, as well as the first and second for loop is used to
	 * determine if the inputs contain any illegal characters. If it does, the
	 * writable boolean is set to false. The seventh if statement is used to
	 * determine if the writable boolean is true. If it is, various if
	 * statements are used to store the data into the arrays of Saves.
	 */
	private void scoreRead() {
		newView = choose.getFileSystemView();
		if (!new File(newView.getDefaultDirectory() + "//High Noon Data//RaidersSave//highscore").exists()) {
			new File(newView.getDefaultDirectory() + "//High Noon Data//RaidersSave//highscore").mkdirs();
		}
		choose.setCurrentDirectory(
				new File(newView.getDefaultDirectory() + "//High Noon Data//RaidersSave//highscore"));

		listSaveEasy = new ArrayList<Save>();
		listSaveMed = new ArrayList<Save>();
		listSaveHard = new ArrayList<Save>();
		PrintWriter emptyWrite;
		BufferedReader scoreReader;
		BufferedReader nullReader;
		String input1;
		String input2;
		String input3;

		int inputDifficulty = 0;
		int inputTime = 0;
		String inputName = "No Name";
		boolean writable = false;
		char[] illegalChar = { 47, 92, 58, 42, 63, 34, 60, 62 };

		try {
			if (!(new File("//" + choose.getCurrentDirectory() + "//" + "HighScore" + MainGame.EXT).isFile())) {
				emptyWrite = new PrintWriter(
						new FileWriter("//" + choose.getCurrentDirectory() + "//" + "HighScore" + MainGame.EXT));
				emptyWrite.close();
			}
			scoreReader = new BufferedReader(
					new FileReader("//" + choose.getCurrentDirectory() + "//" + "HighScore" + MainGame.EXT));
			nullReader = new BufferedReader(
					new FileReader("//" + choose.getCurrentDirectory() + "//" + "HighScore" + MainGame.EXT));
			while (nullReader.readLine() != null && nullReader.readLine() != null && nullReader.readLine() != null) {
				input1 = scoreReader.readLine().trim();
				input2 = scoreReader.readLine().trim();
				input3 = scoreReader.readLine().trim();

				if (!(input1.isEmpty() || input2.isEmpty() || input3.isEmpty())) {

					try {
						inputDifficulty = Integer.parseInt(input1);
						inputTime = Integer.parseInt(input2);
						inputName = input3;
						writable = true;
						if (inputDifficulty < 0 || inputDifficulty > 5)
							writable = false;
						if (inputTime < 0)
							writable = false;
						for (int a = 0; a < inputName.length(); a++)
							for (int b = 0; b < illegalChar.length; b++)
								if (inputName.charAt(a) == illegalChar[b])
									writable = false;
					}

					catch (NumberFormatException er) {
						writable = false;
					}
				}
				if (writable) {
					if (inputDifficulty >= 0 && inputDifficulty <= 1) {
						listSaveEasy.add(new Save(inputName, inputDifficulty, inputTime));
					}
					if (inputDifficulty >= 2 && inputDifficulty <= 3) {
						listSaveMed.add(new Save(inputName, inputDifficulty, inputTime));
					}
					if (inputDifficulty >= 4 && inputDifficulty <= 5) {
						listSaveHard.add(new Save(inputName, inputDifficulty, inputTime));
					}
				}

			}
			scoreReader.close();
			nullReader.close();
		}

		catch (IOException e) {
			// should not throw io exception, make a promt anyways
		}
	}

	/**
	 * The show method is an overridden method from the Screen interface that is
	 * used to initialize the variables, and to run the required methods.
	 */
	@Override
	public void show() {
		scoreRead();
		scoreSorter();
		scoreWrite();

		pageNum = 0;

		// create font
		scoreboardFont = new BitmapFont(Gdx.files.internal("assets/riskofrain_bitmap.fnt"),
				Gdx.files.internal("assets/riskofrain_bitmap_0.png"), false);

		// create scoreboard
		scoreboardEasyText = new Texture("assets/highscores_screen.png");

		scoreboardEasySprite = new Sprite(scoreboardEasyText);

		scoreboardMedText = new Texture("assets/highscores_screen.png");

		scoreboardMedSprite = new Sprite(scoreboardMedText);

		scoreboardHardText = new Texture("assets/highscores_screen.png");

		scoreboardHardSprite = new Sprite(scoreboardHardText);

		// create back button
		backText = new Texture("assets/backbutton.png");
		backSprite = new Sprite(backText);
		backSprite.setSize(290f, 110f);
		backSprite.setPosition(300, 0f);

		// create back dark button
		backDarkText = new Texture("assets/back_dark.png");
		backDarkSprite = new Sprite(backDarkText);
		backDarkSprite.setSize(290f, 110f);
		backDarkSprite.setPosition(300, 0f);

		// create print button
		printText = new Texture("assets/print.png");
		printSprite = new Sprite(printText);
		printSprite.setSize(290f, 110f);
		printSprite.setPosition(600, 0f);

		// create print dark button
		printDarkText = new Texture("assets/print_dark.png");
		printDarkSprite = new Sprite(printDarkText);
		printDarkSprite.setSize(290f, 110f);
		printDarkSprite.setPosition(600, 0f);

		// create next button
		nextText = new Texture("assets/next_arrow.png");
		nextDarkText = new Texture("assets/next_arrow_dark.png");

		nextSprite = new Sprite(nextText);
		nextSprite.setPosition(925, 0);
		nextSprite.setSize(252, 140);

		nextDarkSprite = new Sprite(nextDarkText);
		nextDarkSprite.setPosition(925, 0);
		nextDarkSprite.setSize(252, 140);

		// create previous button
		prevText = new Texture("assets/prev_arrow.png");
		prevDarkText = new Texture("assets/prev_arrow_dark.png");

		prevSprite = new Sprite(prevText);
		prevSprite.setPosition(25, 0);
		prevSprite.setSize(252, 140);

		prevDarkSprite = new Sprite(prevDarkText);
		prevDarkSprite.setPosition(25, 0);
		prevDarkSprite.setSize(252, 140);

		// create background
		backgroundText = new Texture("assets/splash.png");
		backgroundSprite = new Sprite(backgroundText);
		backgroundSprite.setSize(1200, 768);

		// next rect create
		nextRect = nextSprite.getBoundingRectangle();

		// prev rect create
		prevRect = prevSprite.getBoundingRectangle();

		// create back button rectangle
		backRect = backDarkSprite.getBoundingRectangle();

		// print rect create
		printRect = printDarkSprite.getBoundingRectangle();
	}

	/**
	 * The render method is an overridden method from the Screen interface that
	 * runs once every frame. The render method is commonly used to render
	 * textures and sprites to the screen. The first if statement determines
	 * what information to display on the high scores screen if the screen is on
	 * the first page. The first for loop is used to draw the first 1-5 entries
	 * of the highscores for the easy category. The second if statement is used
	 * to determine what occurs if the highscores have more than 5 entries. If
	 * it does, then the second for loop is used to run through the rest of the
	 * highscores array to display the rest. The third if statement is used to
	 * determine what displays on the highscores screen if the screen is on the
	 * second page. If it is, the third for loop is used to run through the
	 * first 1-5 entries of the highscores in the medium category. The fourth if
	 * statement determines what happens if the highscores for medium have more
	 * than 5 entries. If it does, the fourth for loop is used to display the
	 * rest of the highscores entries. The fifth if statement is used to
	 * determine what is displayed on the screen if the screen is on the third
	 * page of the highscores screen. If it is, the fifth for loop is used to
	 * display the first 1-5 entries of the highscores. The sixth if statement
	 * is used to determine if the array of split names consists of more than 2
	 * words. If it is, a sixth for loop is used to add those words to the
	 * second word of the array. A seventh for loop is used to draw the names
	 * onto the screen. The seventh if statement is used to determine what
	 * occurs if the array of entries exceeds a length of 5. If it does, an
	 * eighth for loop is used to draw the entries of the hard difficulty onto
	 * the screen. The eighth if statement is used to determine if the array of
	 * split words from the entries' names exceed the length of two. If it does,
	 * the ninth for loop is used to add the extra words to the second word in
	 * the array. The tenth for loop is used to draw the names to the screen.
	 * The ninth if statement is used to determine what happens if the mouse is
	 * hovered over the next button. The tenth if statement determines what
	 * happens if the mouse is clicked. If it is, an eleventh if statement is
	 * used to determine what happens if the page number is at max. If it is,
	 * the page counter is reset to the minimum to allow for looping. The
	 * twelfth if statement is used to determine what happens if the page number
	 * is at minimum. If it is, the page counter is set to the maximum to allow
	 * for looping. The thirteenth if statement determines what happens if the
	 * cursor is hovered over the back button. The fourteenth if statement is
	 * used to change the screen to the main menu if the mouse button is click
	 * while the cursor is over the button. The fifteenth if statement is used
	 * to determine what happens if the cursor is hovering over the print
	 * button. The sixteenth if statement runs the print() method if the mouse
	 * button is clicked while hovering over the button, or if the CTRL + P
	 * shortcut is used. The seventeenth if statement is used to close the game
	 * when the CTRL + W shortcut is pressed.
	 */
	@Override
	public void render(float delta) {
		String[] wordSplit;
		scoreboardFont.setColor(Color.BLACK);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		backgroundSprite.draw(batch);

		if (pageNum == 0) {
			scoreboardEasySprite.draw(batch);
			for (int x = 0; x < (listSaveEasy.size() > 5 ? 5 : listSaveEasy.size()); x++) {
				wordSplit = listSaveEasy.get(x).getName().split("\\s+");
				if (wordSplit.length > 2) {
					for (int a = 2; a < wordSplit.length; a++) {
						wordSplit[1] = wordSplit[1] + " " + wordSplit[a];
					}
					wordSplit = Arrays.copyOf(wordSplit, 2);
				}
				for (int a = 0; a < wordSplit.length; a++) {
					scoreboardFont.draw(batch, wordSplit[a], 150, 550 - (85 * x + a * 25));
				}
				scoreboardFont.draw(batch, "Easy", 320, 550 - (85 * x));
				scoreboardFont.draw(batch, "" + listSaveEasy.get(x).getTotalTime(), 430, 550 - (85 * x));
			}
			if (listSaveEasy.size() > 5) {
				for (int x = 5; x < (listSaveEasy.size() > 10 ? 10 : listSaveEasy.size()); x++) {
					wordSplit = listSaveEasy.get(x).getName().split("\\s+");
					if (wordSplit.length > 2) {
						for (int a = 2; a < wordSplit.length; a++) {
							wordSplit[1] = wordSplit[1] + " " + wordSplit[a];
						}
						wordSplit = Arrays.copyOf(wordSplit, 2);
					}
					for (int a = 0; a < wordSplit.length; a++) {
						scoreboardFont.draw(batch, wordSplit[a], 710, 550 - (85 * (x - 5) + a * 25));
					}
					scoreboardFont.draw(batch, "Easy", 870, 550 - (85 * (x - 5)));
					scoreboardFont.draw(batch, "" + listSaveEasy.get(x).getTotalTime(), 980, 550 - (85 * (x - 5)));
				}
			}
		} else if (pageNum == 1) {
			scoreboardMedSprite.draw(batch);
			for (int x = 0; x < (listSaveMed.size() > 5 ? 5 : listSaveMed.size()); x++) {
				wordSplit = listSaveMed.get(x).getName().split("\\s+");
				if (wordSplit.length > 2) {
					for (int a = 2; a < wordSplit.length; a++) {
						wordSplit[1] = wordSplit[1] + " " + wordSplit[a];
					}
					wordSplit = Arrays.copyOf(wordSplit, 2);
				}
				for (int a = 0; a < wordSplit.length; a++) {
					scoreboardFont.draw(batch, wordSplit[a], 150, 550 - (85 * x + a * 25));
				}
				scoreboardFont.draw(batch, "Medium", 320, 550 - (85 * x));
				scoreboardFont.draw(batch, "" + listSaveMed.get(x).getTotalTime(), 430, 550 - (85 * x));
			}
			if (listSaveMed.size() > 5) {
				for (int x = 5; x < (listSaveMed.size() > 10 ? 10 : listSaveMed.size()); x++) {
					wordSplit = listSaveMed.get(x).getName().split("\\s+");
					if (wordSplit.length > 2) {
						for (int a = 2; a < wordSplit.length; a++) {
							wordSplit[1] = wordSplit[1] + " " + wordSplit[a];
						}
						wordSplit = Arrays.copyOf(wordSplit, 2);
					}
					for (int a = 0; a < wordSplit.length; a++) {
						scoreboardFont.draw(batch, wordSplit[a], 710, 550 - (85 * (x - 5) + a * 25));
					}
					scoreboardFont.draw(batch, "Medium", 870, 550 - (85 * (x - 5)));
					scoreboardFont.draw(batch, "" + listSaveMed.get(x).getTotalTime(), 980, 550 - (85 * (x - 5)));
				}
			}
		} else {
			scoreboardHardSprite.draw(batch);
			for (int x = 0; x < (listSaveHard.size() > 5 ? 5 : listSaveHard.size()); x++) {
				wordSplit = listSaveHard.get(x).getName().split("\\s+");
				if (wordSplit.length > 2) {
					for (int a = 2; a < wordSplit.length; a++) {
						wordSplit[1] = wordSplit[1] + " " + wordSplit[a];
					}
					wordSplit = Arrays.copyOf(wordSplit, 2);
				}
				for (int a = 0; a < wordSplit.length; a++) {
					scoreboardFont.draw(batch, wordSplit[a], 150, 550 - (85 * x + a * 25));
				}
				scoreboardFont.draw(batch, "Hard", 320, 550 - (85 * x));
				scoreboardFont.draw(batch, "" + listSaveHard.get(x).getTotalTime(), 430, 550 - (85 * x));
			}
			if (listSaveHard.size() > 5) {
				for (int x = 5; x < (listSaveHard.size() > 10 ? 10 : listSaveHard.size()); x++) {
					wordSplit = listSaveHard.get(x).getName().split("\\s+");
					if (wordSplit.length > 2) {
						for (int a = 2; a < wordSplit.length; a++) {
							wordSplit[1] = wordSplit[1] + " " + wordSplit[a];
						}
						wordSplit = Arrays.copyOf(wordSplit, 2);
					}
					for (int a = 0; a < wordSplit.length; a++) {
						scoreboardFont.draw(batch, wordSplit[a], 710, 550 - (85 * (x - 5) + a * 25));
					}
					scoreboardFont.draw(batch, "Hard", 870, 550 - (85 * (x - 5)));
					scoreboardFont.draw(batch, "" + listSaveHard.get(x).getTotalTime(), 980, 550 - (85 * (x - 5)));
				}
			}
		}

		nextDarkSprite.draw(batch);
		prevDarkSprite.draw(batch);
		backDarkSprite.draw(batch);
		printDarkSprite.draw(batch);
		// draw background

		if (nextRect.contains(Gdx.input.getX(), Gdx.input.getY() - 645) || Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			nextSprite.draw(batch);
			if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Keys.DPAD_RIGHT)) {
				if (pageNum == 2) {
					pageNum = 0;
				} else {
					pageNum++;
				}
			}
		}
		if (prevRect.contains(Gdx.input.getX(), Gdx.input.getY() - 645) || Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			prevSprite.draw(batch);
			if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Keys.DPAD_LEFT)) {
				if (pageNum == 0) {
					pageNum = 2;
				} else {
					pageNum--;
				}
			}
		}
		if (backRect.contains(Gdx.input.getX(), Gdx.input.getY() - 645) || Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			backSprite.draw(batch);
			if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
				game.setScreen(new MainMenu(batch, game));
			}
		}

		if (printRect.contains(Gdx.input.getX(), Gdx.input.getY() - 645) || Gdx.input.isKeyJustPressed(Keys.ENTER)
				|| Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) && Gdx.input.isKeyJustPressed(Keys.P)) {
			printSprite.draw(batch);
			if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Keys.ENTER)
					|| Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) && Gdx.input.isKeyJustPressed(Keys.P)) {
				printer();
			}
		}

		if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) && Gdx.input.isKeyJustPressed(Keys.W)) {
			batch.dispose();
			Gdx.app.exit();
		}

		batch.end();

	}

	/**
	 * Unused overridden method
	 */
	@Override
	public void resize(int width, int height) {

	}

	/**
	 * Unused overridden method
	 */
	@Override
	public void pause() {

	}

	/**
	 * Unused overridden method
	 */
	@Override
	public void resume() {

	}

	/**
	 * Unused overridden method
	 */
	@Override
	public void hide() {

	}

	/**
	 * Unused overridden method
	 */
	@Override
	public void dispose() {


		scoreboardEasyText.dispose();
		 scoreboardMedText.dispose();
		 scoreboardHardText.dispose();
		 backgroundText.dispose();
		 nextText.dispose();
		 nextDarkText.dispose();
		 prevText.dispose();
		 prevDarkText.dispose();
		 backText.dispose();
		 backDarkText.dispose();
		 printText.dispose();
		 printDarkText.dispose();
		
	}

}
