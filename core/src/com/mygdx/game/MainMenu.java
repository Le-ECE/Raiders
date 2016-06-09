package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * The MainMenu class displays the main menu screen for the user that controls
 * general program flow. From the main menu, the user will be able to select
 * options that will allow them to play the game at varying difficulties, view
 * the instructions to the game, quit the game, and more. The main menu also
 * displays a graphic background, with custom made buttons and the title of the
 * project. Upon pressing the "Play" button, the user is taken to a screen where
 * they can input their name. From that screen, if a valid name has been entered,
 * the user is taken to a difficulty selection menu, which will then take them
 * to the game with a map loaded depending on the difficulty. Upon pressing the
 * "Load" button, the user is taken to a screen where they may choose which save
 * to load from a save file the user saves to. Upon choosing a save, the user
 * is taken to the game screen with all of the save data to start with. Upon
 * pressing the "Scores" button, the user is taken to a screen containing three
 * pages of the top 10 times for each difficulty. From this screen, the user
 * may print the scores, or go back to the menu. Upon pressing the "Rules" button,
 * the user is taken to a screen with multiple pages that contains information
 * regarding the game, and how to play it. Upon pressing the "Quit" button, the
 * game closes. Upon pressing the "?" button in the top right of the menu, a
 * .chm file is opened that contains more in-depth information about the game,
 * as well as credit for various artwork used in the game.
 * 
 * @author Brian Tran
 * @version 4.0 07.06.2016
 * 
 *          <p>
 *          <b>Instance Variables</b>
 *          <p>
 *          <b>game</b> Instance of MainGame that manages multiple displays on
 *          the screen
 *          <p>
 *          <b>stage</b> Instance of Stage that manages all elements on the
 *          screen such as buttons
 *          <p>
 *          <b>batch</b> Instance of SpriteBatch that acts as a container for
 *          onscreen elements
 *          <p>
 *          <b>skin</b> Instance of Skin that allows for the customization of
 *          onscreen elements.
 *          <p>
 *          <b>bG</b> Instance of Image used to draw the background image.
 *          <p>
 *          <b>ttl</b> Instance of Image used to draw the title image.
 * 
 */
public class MainMenu implements Screen {
	MainGame game;

	Stage stage;

	SpriteBatch batch;

	Image bG;

	Image ttl;

	Skin skin;

	public MainMenu(SpriteBatch batch, MainGame game) {
		this.batch = batch;
		this.game = game;
	}

	/**
	 * The show() method instantiates and gives value to all of the variables.
	 * The show() method is run every time the MainMenu class is called. The
	 * method also allows for button inputs to be taken by using a GDX built in
	 * method called setInputProcessor. Buttons and their listeners that take in
	 * user input from the buttons are added as well. Finally, the buttons,
	 * title, and background are added to the stage. The first if statement
	 * is used to determine if the main menu music is currently playing. If
	 * not, main menu music is played from a certain point.
	 */
	@Override
	public void show() {

		Texture bg;
		Texture pB;
		Texture lB;
		Texture hB;
		Texture qB;
		Texture title;
		Texture iB;
		Texture chmB;

		if (!MainGame.mainMusic.isPlaying()) {
			MainGame.mainMusic.play();
			MainGame.mainMusic.setPosition(14f);
		}

		stage = new Stage();
		bg = new Texture("assets/splash.png");
		lB = new Texture("assets/load.png");
		hB = new Texture("assets/high_score.png");
		pB = new Texture("assets/playbutton.png");
		qB = new Texture("assets/quit.png");
		chmB = new Texture("assets/chm_button.png");
		skin = new Skin();
		iB = new Texture("assets/instructions_button.png");

		title = new Texture("assets/title_new.png");
		ttl = new Image(title);
		ttl.setPosition(0, 720 - title.getHeight());

		bG = new Image(bg);
		bG.setPosition(0, 0);
		bG.setWidth(1200);
		bG.setHeight(768);

		ttl.setColor(ttl.getColor().r, ttl.getColor().g, ttl.getColor().b, 0);

		ttl.addAction(Actions.fadeIn(2f));

		stage.addActor(bG);

		stage.addActor(ttl);

		Gdx.input.setInputProcessor(stage);

		skin.add("red", pB);
		skin.add("load", lB);
		skin.add("overlay", qB);
		skin.add("score", hB);
		skin.add("instructions", iB);
		skin.add("help", chmB);
		skin.add("pb", new Texture("assets/playbutton.png"));
		skin.add("lb", new Texture("assets/load.png"));
		skin.add("hb", new Texture("assets/high_score.png"));
		skin.add("iB", new Texture("assets/instructions_button.png"));
		skin.add("chmB", new Texture("assets/chm_button.png"));
		skin.add("qover", new Texture("assets/quit.png"));

		TextButtonStyle tb = new TextButtonStyle();
		TextButtonStyle tb1 = new TextButtonStyle();
		TextButtonStyle tb2 = new TextButtonStyle();
		TextButtonStyle tb3 = new TextButtonStyle();
		TextButtonStyle tb4 = new TextButtonStyle();
		TextButtonStyle tb5 = new TextButtonStyle();
		tb.up = skin.newDrawable("red", Color.LIGHT_GRAY);
		tb.down = skin.newDrawable("red", Color.DARK_GRAY);
		tb.over = skin.newDrawable("pb");
		tb.font = new BitmapFont();

		tb1.up = skin.newDrawable("overlay", Color.LIGHT_GRAY);
		tb1.down = skin.newDrawable("overlay", Color.DARK_GRAY);
		tb1.over = skin.newDrawable("qover");
		tb1.font = new BitmapFont();

		tb2.up = skin.newDrawable("load", Color.LIGHT_GRAY);
		tb2.down = skin.newDrawable("load", Color.DARK_GRAY);
		tb2.over = skin.newDrawable("lb");
		tb2.font = new BitmapFont();

		tb3.up = skin.newDrawable("score", Color.LIGHT_GRAY);
		tb3.down = skin.newDrawable("score", Color.DARK_GRAY);
		tb3.over = skin.newDrawable("hb");
		tb3.font = new BitmapFont();

		tb4.up = skin.newDrawable("instructions", Color.LIGHT_GRAY);
		tb4.down = skin.newDrawable("instructions", Color.DARK_GRAY);
		tb4.over = skin.newDrawable("iB");
		tb4.font = new BitmapFont();

		tb5.up = skin.newDrawable("help", Color.LIGHT_GRAY);
		tb5.down = skin.newDrawable("help", Color.DARK_GRAY);
		tb5.over = skin.newDrawable("chmB");
		tb5.font = new BitmapFont();

		final TextButton playButton = new TextButton("", tb);
		final TextButton loadButton = new TextButton("", tb2);
		final TextButton quitButton = new TextButton("", tb1);
		final TextButton highScoreButton = new TextButton("", tb3);
		final TextButton instructionsButton = new TextButton("", tb4);
		final TextButton helpButton = new TextButton("", tb5);

		playButton.setPosition(475, 400);
		playButton.setWidth(250f);
		playButton.setHeight(105f);
		playButton.setColor(playButton.getColor().r, playButton.getColor().g, playButton.getColor().b, 0);

		playButton.addAction(Actions.fadeIn(1.5f));

		loadButton.setPosition(475, 250);
		loadButton.setWidth(250f);
		loadButton.setHeight(105f);
		loadButton.setColor(loadButton.getColor().r, loadButton.getColor().g, loadButton.getColor().b, 0);

		loadButton.addAction(Actions.fadeIn(1.5f));

		highScoreButton.setPosition(200, 250); // 750 for right
		highScoreButton.setWidth(250f);
		highScoreButton.setHeight(105f);
		highScoreButton.setColor(highScoreButton.getColor().r, highScoreButton.getColor().g,
				highScoreButton.getColor().b, 0);

		highScoreButton.addAction(Actions.fadeIn(1.5f));

		instructionsButton.setPosition(750, 250);
		instructionsButton.setWidth(250f);
		instructionsButton.setHeight(105f);
		instructionsButton.setColor(instructionsButton.getColor().r, instructionsButton.getColor().g,
				instructionsButton.getColor().b, 0);

		instructionsButton.addAction(Actions.fadeIn(1.5f));

		quitButton.setPosition(475, 100);
		quitButton.setWidth(250f);
		quitButton.setHeight(105f);
		quitButton.setColor(quitButton.getColor().r, quitButton.getColor().g, quitButton.getColor().b, 0);

		quitButton.addAction(Actions.fadeIn(1.5f));

		helpButton.setPosition(1085, 510);
		helpButton.setWidth(64f);
		helpButton.setHeight(64f);
		helpButton.setColor(helpButton.getColor().r, helpButton.getColor().g, helpButton.getColor().b, 0);

		helpButton.addAction(Actions.fadeIn(1.5f));

		playButton.addCaptureListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new NameInput(batch, game));
			}
		});

		loadButton.addCaptureListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				dispose();
				game.setSaveManager(new SaveManager());
				new LoadSave(batch, game);

			}
		});

		highScoreButton.addCaptureListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				dispose();
				game.setSaveManager(new SaveManager());
				game.setScreen(new HighScore(batch, game));
			}
		});

		instructionsButton.addCaptureListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				dispose();
				game.setScreen(new Instructions(batch, game));
			}
		});

		quitButton.addCaptureListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				batch.dispose();
				dispose();
				Gdx.app.exit();
			}
		});

		helpButton.addCaptureListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				try {
					Runtime.getRuntime().exec("hh.exe help.chm");
				} catch (Exception e) {

				}
			}
		});

		stage.addActor(playButton);
		stage.addActor(loadButton);
		stage.addActor(highScoreButton);
		stage.addActor(instructionsButton);
		stage.addActor(quitButton);
		stage.addActor(helpButton);
	}

	/**
	 * The render() method is an overridden, looped method. The render method
	 * runs once every frame. As a result, graphics and screen elements are
	 * updated every frame through this method. Stage.draw() is called to draw
	 * all of the elements contained in the stage (such as buttons) onto the
	 * screen. The first if statement is used to set the screen to the NameInput
	 * class if the enter key is pressed. The second if statement is used to close
	 * the game when the user presses CTRL + W shortcut. The third if statement is
	 * used to provide the Play button function when the user presses the CTRL + UP
	 * shortcut. The fourth if statement is used to provide the Load button function
	 * when the user presses the CTRL + ENTER shortcut. The fifth if statement is
	 * used to provide the Score button when the user presses the CTRL + LEFT shortcut.
	 * The sixth if statement is used to provide the Rules button function when the
	 * user presses the CTRL + RIGHT shortcut. The seventh if statement is used to
	 * provide the Quit function when the user presses the CTRL + DOWN shortcut.
	 */
	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(.8f, .8f, .8f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			game.setScreen(new NameInput(batch, game));
		}
		
		if (Gdx.input.isKeyPressed (Keys.CONTROL_LEFT)&&Gdx.input.isKeyJustPressed(Keys.W)){
			batch.dispose();
			Gdx.app.exit();
		}
		
		if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT)&&Gdx.input.isKeyJustPressed(Keys.UP)){
			game.setScreen(new NameInput (batch,game));
		}
		
		if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT)&&Gdx.input.isKeyJustPressed(Keys.ENTER)){
			dispose();
			game.setSaveManager (new SaveManager());
			new LoadSave (batch,game);
		}
		
		if (Gdx.input.isKeyPressed (Keys.CONTROL_LEFT)&&Gdx.input.isKeyJustPressed(Keys.LEFT)){
			dispose();
			game.setSaveManager(new SaveManager());
			game.setScreen(new HighScore(batch, game));
		}
		
		if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT)&&Gdx.input.isKeyJustPressed(Keys.RIGHT)){
			dispose();
			game.setScreen(new Instructions(batch, game));
		}
		
		if (Gdx.input.isKeyPressed (Keys.CONTROL_LEFT)&&Gdx.input.isKeyJustPressed(Keys.DOWN)){
			batch.dispose();
			dispose();
			Gdx.app.exit();
		}
		
		batch.end();

		stage.act();
		stage.draw();
		ttl.act(delta);
	}

	/**
	 * Unused overridden method.
	 */
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	/**
	 * Unused overridden method.
	 */
	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	/**
	 * Unused overridden method.
	 */
	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	/**
	 * Unused overridden method.
	 */
	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	/**
	 * dispose() is an overridden method that disposes of the resources once
	 * exited in order to prevent memory leaks.
	 */
	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
	}

}