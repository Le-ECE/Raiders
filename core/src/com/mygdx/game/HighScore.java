package com.mygdx.game;

import com.badlogic.gdx.Input.Keys;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;

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
 * This class, using Java's FileIO, reads the user's score in game, sorts the score using a selection
 * sort algorithm, and then writes the user's score on a save file. This class is also responsible
 * for printing the highscores on a piece of paper if the user wishes. The class contains a render
 * method, and implements the Screen interface. This is to be able to display the highscores on a
 * highscore screen through the main menu.
 * 
 * @author David Le
 * @version 4.0 03/06/2016
 * 
 * <p>
 * <b>Instance Variables</b>
 * <p>
 * <b>batch</b> Instance of SpriteBatch used to render on screen elements
 * <p>
 * <b>game</b> Instance of MainGame used to change between screens
 * <p>
 * <b>scoreboardFont</b> Instance of BitmapFont used to draw text using custom Windows fonts
 * <p>
 * <b>listSaveEast</b> ArrayList of Save used to store data of easy difficulty saves
 * <p>
 * <b>listSaveMed</b> ArrayList of Save used to store data of medium difficulty saves
 * <p>
 * <b>listSaveHard</b> ArrayList of Save used to store data of hard difficulty saves
 * <p>
 * <b>currentName</b> String representing the user's name
 * <p>
 * <b>currentFile</b> Instance of Save used to save to a file
 * <p>
 * <b>choose</b> Instance of file chooser which allows the user to choose files to save in
 * <p>
 * <b>nextRect</b> Instance of Rectangle that detects mouse input for the next button
 * <p>
 * <b>prevRect</b> Instance of Rectangle that detects mouse input for the previous button
 * <p>
 * <b>scoreboardEasyText</b> Instance of Texture used to store custom images of easy scoreboard
 * <p>
 * <b>scoreboardMedText</b> Instance of Texture used to store custom images of medium scoreboard
 * <p>
 * <b>scoreboardHardText</b> Instance of Texture used to store custom images of hard scoreboard
 * <p>
 * <b>backgroundText</b> Instance of Texture used to store custom images of the background
 * <p>
 * <b>nextText</b> Instance of Texture used to store custom images of the next button
 * <p>
 * <b>nextDarkText</b> Instance of Texture used to store custom images of the dark next button
 * <p>
 * <b>prevText</b> Instance of Texture used to store custom images of a previous button
 * <p>
 * <b>prevDarkText</b> Instance of Texture used to store custom images of a dark previous button
 * <p>
 * <b>menuDarkText</b> Instance of Texture used to store custom images of a dark menu button
 * <p>
 * <b>menuText</b> Instance of Texture used to store custom images of a menu button
 * <p>
 * <b>scoreboardEasySprite</b> Instance of Sprite used to make a sprite of scoreboardEasyText
 * <p>
 * <b>scoreboardMedSprite</b> Instance of Sprite used to make a sprite of scoreboardMedText
 * <p>
 * <b>scoreboardHardSprite</b> Instance of Sprite used to make a sprite of scoreboardHardText
 * <p>
 * <b>backgroundSprite</b> Instance of Sprite class used to make a sprite from backgroundText
 * <p>
 * <b>nextSprite</b> Instance of Sprite class used to make a sprite from nextText
 * <p>
 * <b>nextDarkSprite</b> Instance of Sprite used to make a Sprite from nextDarkText
 * <p>
 * <b>prevSprite</b> Instance of Sprite used to make a sprite from prevText
 * <p>
 * <b>prevDarkSprite</b> Instance of Sprite used to make a sprite from prevDarkText
 * <p>
 * <b>menuDarkSprite</b> Instance of Sprite used to make a sprite from menuDarkText
 * <p>
 * <b>menuSprite</b> Instance of Sprite used to make a sprite from menuText
 * <p>
 * <b>pageNum</b> Integer representing page of highscore the user is on
 *
 */
public class HighScore implements Screen, Printable {

 static SpriteBatch batch;

 static MainGame game;
 
 BitmapFont scoreboardFont;

 ArrayList<Save> listSaveEasy;
 ArrayList<Save> listSaveMed;
 ArrayList<Save> listSaveHard;

 String currentName;

 Save currentFile;

 JFileChooser choose = new JFileChooser();
 
 Rectangle nextRect;
 Rectangle prevRect;
 Rectangle backRect;

 Texture scoreboardEasyText;
 Texture scoreboardMedText;
 Texture scoreboardHardText;
 Texture backgroundText;
 Texture nextText;
 Texture nextDarkText;
 Texture prevText;
 Texture prevDarkText;
 Texture backText;
 Texture backDarkText;

 Sprite scoreboardEasySprite;
 Sprite scoreboardMedSprite;
 Sprite scoreboardHardSprite;
 Sprite backgroundSprite;
 
 Sprite nextSprite;
 Sprite nextDarkSprite;
 Sprite prevSprite;
 Sprite prevDarkSprite;
 Sprite backSprite;
 Sprite backDarkSprite;
 int pageNum;

 /**
  * The HighScore constructor is responsible for taking in a SpriteBatch and a MainGame. SpriteBatch
  * is used for rendering onscreen elements, while the MainGame is used for setting game screens.
  * 
  * @param batch SpriteBatch used to render on screen elements
  * @param game MainGame used to switch between displays
  */
 public HighScore(SpriteBatch batch, MainGame game) {
  HighScore.batch = batch;
  HighScore.game = game;
 }

 /**
  * An overridden method of the Printable used to be able to print the results from the high score
  * page if the user wishes to print. The first for loop is used to format the easy names and time
  * on the paper. The second for loop is used to format the medium names and time on the paper. The
  * third for loop is used to format the hard names and time on the paper.
  * 
  * @param g Graphics used to draw text for printing
  * @param pf PageFormat used to format page for text
  * @param page integer representing page of printing
  * @return PAGE_EXISTS int representing an existing page
  * @return NO_SUCH_PAGE int representing no pages
  */
 @Override
 public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
  if (page > 0)
   return NO_SUCH_PAGE;

  Graphics2D g2d = (Graphics2D) g;
  g2d.translate(pf.getImageableX(), pf.getImageableY());
  g.drawString("High Scores", 275, 50);
  g.drawString("Name", 150, 100);
  g.drawString("Time of Completion [Seconds]", 350, 100);
  g.drawString("Easy Difficulty", 25, 150);
  for (int a = 0; a < listSaveEasy.size(); a++) {
   g.drawString(listSaveEasy.get(a).getName(), 150, 175 + a * 15);
   g.drawString("" + listSaveEasy.get(a).getTimeSeconds(), 350, 175 + a * 15);
  }
  g.drawString("Medium Difficulty", 25, 340);
  for (int b = 0; b < listSaveMed.size(); b++) {
   g.drawString(listSaveMed.get(b).getName(), 150, 365 + b * 15);
   g.drawString("" + listSaveMed.get(b).getTimeSeconds(), 350, 365 + b * 15);
  }

  g.drawString("Hard Difficulty", 25, 510);
  for (int c = 0; c < listSaveHard.size(); c++) {
   g.drawString(listSaveMed.get(c).getName(), 150, 535 + c * 15);
   g.drawString("" + listSaveMed.get(c).getTimeSeconds(), 350, 535 + c * 15);
  }
  return PAGE_EXISTS;
 }

 /**
  * This method controls the printing of the highscores. The first if statement is used to print
  * if the user agrees to the printing dialog.
  */
 public void printer() {

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
  * This method is responsible for adding the player's game values to the save arrays
  * at the end of each level. The player's game values are sorted into appropriate
  * save arrays that are based on the current difficulty. The first if statement is
  * used to add the Save to the Save array list if the difficulty levels are between
  * 0 and 1. The second if statement is used to add the Save to the Save array list if
  * the difficulty levels are between 2 and 3. The third if statement is used to add
  * the Save to the Save array if the difficulty is between 4 and 5.
  * 
  * @param passSave Save temporary save file
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
  * This method is responsible for limiting the size of the save arrays in the
  * case that they exceed the size of 10.
  */
 public void scoreLimit() {
  if (listSaveEasy.size() > 10)
   listSaveEasy.subList(10, listSaveEasy.size()).clear();
  if (listSaveMed.size() > 10)
   listSaveMed.subList(10, listSaveMed.size()).clear();
  if (listSaveHard.size() > 10)
   listSaveHard.subList(10, listSaveHard.size()).clear();
 }

 /**
  * This method writes the users high scores to a file in the format of difficulty, time, and name from top
  * to bottom. If the file that the method tries to write to does not exist, then a new, empty file is created.
  * The first if statement is used to check if the file exists. If it does not, then a new one is created. The
  * first for loop is used to write all of the save data from the easy difficulty. The second for loop is used
  * to write all of the save data from the medium difficulty. The third for loop is used to write all of the
  * save data from the hard difficulty.
  */
 public void scoreWrite() {
  PrintWriter scoreWrite;
  if (!(new File(System.getProperty("user.dir") + "//highscore").exists())) {
   new File(System.getProperty("user.dir") + "//highscore").mkdirs();
  }
  choose.setCurrentDirectory(new File(System.getProperty("user.dir") + "//highscore"));
  try {
   scoreWrite = new PrintWriter(
     new FileWriter("//" + choose.getCurrentDirectory() + "//" + "HighScore" + MainGame.EXT));
   scoreLimit();
   for (Save a : listSaveEasy) {
    scoreWrite.println(a.getDifficulty());
    scoreWrite.println(a.getTotalTime());
    scoreWrite.println(a.getName());
   }

   for (Save b : listSaveMed) {
    scoreWrite.println(b.getDifficulty());
    scoreWrite.println(b.getTotalTime());
    scoreWrite.println(b.getName());
   }

   for (Save c : listSaveHard) {
    scoreWrite.println(c.getDifficulty());
    scoreWrite.println(c.getTotalTime());
    scoreWrite.println(c.getName());
   }
   scoreWrite.close();
  } catch (IOException e) {
   // should not have prompt
  }
 }

 /**
  * This method is responsible for sorting the array of save data, based on the time
  * that the user completed in ascending order. This method uses a selection sort
  * algorithm to complete the sort. The first for loop is used to run through the 
  * easy save array.
  */
 public void scoreSorter() {
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

 public void scoreRead() {
  if (!(new File(System.getProperty("user.dir") + "//highscore").exists())) {
   new File(System.getProperty("user.dir") + "//highscore").mkdirs();
  }
  choose.setCurrentDirectory(new File(System.getProperty("user.dir") + "//highscore"));

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
   scoreReader = new BufferedReader(new FileReader("//" + choose.getCurrentDirectory() + "//" + "HighScore" + MainGame.EXT));
   nullReader = new BufferedReader(new FileReader("//" + choose.getCurrentDirectory() + "//" + "HighScore" + MainGame.EXT));
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

 @Override
 public void show() {
  scoreRead();
  scoreSorter();
  scoreWrite();
  
  pageNum = 0;
  
  // call with button
  //printer();
  
  //create font
  scoreboardFont = new BitmapFont (Gdx.files.internal("assets/riskofrain_bitmap.fnt"), Gdx.files.internal("assets/riskofrain_bitmap_0.png"),false);
  
  //create scoreboard
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
  backSprite.setPosition(Gdx.graphics.getWidth() / 2 - backSprite.getWidth() / 2, 0f);

  // create back dark button
  backDarkText = new Texture("assets/back_dark.png");
  backDarkSprite = new Sprite(backDarkText);
  backDarkSprite.setSize(290f, 110f);
  backDarkSprite.setPosition(Gdx.graphics.getWidth() / 2 - backDarkSprite.getWidth() / 2, 0f);
  
  //create next button
  nextText = new Texture ("assets/next_arrow.png");
  nextDarkText = new Texture ("assets/next_arrow_dark.png");
  
  nextSprite = new Sprite (nextText);
  nextSprite.setPosition(850, 0);
  nextSprite.setSize(252, 140);
  
  nextDarkSprite = new Sprite (nextDarkText);
  nextDarkSprite.setPosition(850, 0);
  nextDarkSprite.setSize(252, 140);
  
  //create previous button
  prevText = new Texture ("assets/prev_arrow.png");
  prevDarkText = new Texture ("assets/prev_arrow_dark.png");
  
  prevSprite = new Sprite (prevText);
  prevSprite.setPosition(100, 0);
  prevSprite.setSize(252, 140);
  
  prevDarkSprite = new Sprite (prevDarkText);
  prevDarkSprite.setPosition(100, 0);
  prevDarkSprite.setSize(252, 140);
  
  //create background
  backgroundText = new Texture ("assets/splash.png");
  backgroundSprite = new Sprite (backgroundText);
  backgroundSprite.setSize(1200, 768);

  //next rect create
  nextRect = nextSprite.getBoundingRectangle();
  
  //prev rect create
  prevRect = prevSprite.getBoundingRectangle();
   
  // create back button rectangle
  backRect = backDarkSprite.getBoundingRectangle();
 }

 @Override
 public void render(float delta) {
  scoreboardFont.setColor(Color.BLACK);
  // TODO Auto-generated method stub
  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

  batch.begin();
 backgroundSprite.draw(batch);


  if (pageNum == 0){
   scoreboardEasySprite.draw(batch);
   for (int x = 0; x < (listSaveEasy.size() > 5 ? 5 : listSaveEasy.size()); x++){
    scoreboardFont.draw(batch, listSaveEasy.get(x).getName(), 150, 550-(85*x));
    scoreboardFont.draw(batch, "Easy", 320, 550-(85*x));
    scoreboardFont.draw(batch, ""+listSaveEasy.get(x).getTotalTime(), 430, 550-(85*x));
   }
   if (listSaveEasy.size()>5){
    for (int x = 5; x < (listSaveEasy.size()> 10 ? 10 : listSaveEasy.size()); x++){
     scoreboardFont.draw(batch, listSaveEasy.get(x).getName(), 710, 550 - (85*(x-5)));
     scoreboardFont.draw(batch, "Easy", 870, 550-(85*(x-5)));
     scoreboardFont.draw(batch, ""+listSaveEasy.get(x).getTotalTime(), 980, 550-(85*(x-5)));
    }
   }
  }
  else if (pageNum == 1){
   scoreboardMedSprite.draw(batch);
   for (int x = 0; x < (listSaveMed.size() > 5 ? 5 : listSaveMed.size()); x++){
    scoreboardFont.draw(batch, listSaveMed.get(x).getName(), 150, 550-(85*x));
    scoreboardFont.draw(batch, "Medium", 320, 550-(85*x));
    scoreboardFont.draw(batch, ""+listSaveMed.get(x).getTotalTime(), 430, 550-(85*x));
   }
   if (listSaveMed.size()>5){
    for (int x = 5; x < (listSaveMed.size()> 10 ? 10 : listSaveMed.size()); x++){
     scoreboardFont.draw(batch, listSaveMed.get(x).getName(), 710, 550 - (85*(x-5)));
     scoreboardFont.draw(batch, "Medium", 870, 550-(85*(x-5)));
     scoreboardFont.draw(batch, ""+listSaveMed.get(x).getTotalTime(), 980, 550-(85*(x-5)));
    }
   }
  }
  else{
   scoreboardHardSprite.draw(batch);
   for (int x = 0; x < (listSaveHard.size() > 5 ? 5 : listSaveHard.size()); x++){
    scoreboardFont.draw(batch, listSaveHard.get(x).getName(), 150, 550-(85*x));
    scoreboardFont.draw(batch, "Hard", 320, 550-(85*x));
    scoreboardFont.draw(batch, ""+listSaveHard.get(x).getTotalTime(), 430, 550-(85*x));
   }
   if (listSaveHard.size()>5){
    for (int x = 5; x < (listSaveHard.size()> 10 ? 10 : listSaveHard.size()); x++){
     scoreboardFont.draw(batch, listSaveHard.get(x).getName(), 710, 550 - (85*(x-5)));
     scoreboardFont.draw(batch, "Hard", 870, 550-(85*(x-5)));
     scoreboardFont.draw(batch, ""+listSaveHard.get(x).getTotalTime(), 980, 550-(85*(x-5)));
    }
   }
  }
  
  nextDarkSprite.draw(batch);
  prevDarkSprite.draw(batch);
   backDarkSprite.draw(batch);
    //draw background
 
   
  
  
  
  if (nextRect.contains(Gdx.input.getX(), Gdx.input.getY() - 645)|| Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
   nextSprite.draw(batch);
   if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Keys.DPAD_RIGHT)) {
   
    if (pageNum == 2) {
     pageNum = 0;
    } else {
     pageNum++;
    }
   }
  }
  if (prevRect.contains(Gdx.input.getX(), Gdx.input.getY() - 645) ||Gdx.input.isKeyJustPressed(Keys.LEFT)) {
   prevSprite.draw(batch);
   if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Keys.DPAD_LEFT)) {
    if (pageNum == 0) {
     pageNum = 2;
    } else {
     pageNum--;
    }
   }
  }
if (backRect.contains(Gdx.input.getX(), Gdx.input.getY() - 645) ||  Gdx.input.isKeyPressed(Keys.ESCAPE)) {
   backSprite.draw(batch);
   if (Gdx.input.justTouched()|| Gdx.input.isKeyPressed(Keys.ESCAPE)) {
    game.setScreen(new MainMenu(batch, game));
   }
  }
  
  batch.end();
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
