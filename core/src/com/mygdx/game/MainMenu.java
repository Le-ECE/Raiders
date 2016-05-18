package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
 * project. Upon pressing the "Play" button, the user is currently taken to the
 * basic game screen, however things such as name entries and difficulty
 * selection will be implemented.
 * 
 * @author Brian Tran
 * @version 1.0 13/05/2016
 * 
 *          <p>
 *          <b>Instance Variables</b>
 *          <p>
 *          <b>game</b> Instance of Game that manages multiple displays on the
 *          screen
 *          <p>
 *          <b>stage</b> Instance of Stage that manages all elements on the
 *          screen such as buttons
 *          <p>
 *          <b>batch</b> Instance of SpriteBatch that acts as a container for
 *          onscreen elements
 *          <p>
 *          <b>bg</b> Instance of Texture that stores an image that acts as the
 *          menu background
 * 
 */
public class MainMenu implements Screen {
	MainGame game;

	Stage stage;

	SpriteBatch batch;

	Texture bg;
	Texture pB;
	Texture qB;
	Texture title;

	Image ttl;
	Image bG;

	Skin skin;

	Pixmap pix;

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
	 * title, and background are added to the stage.
	 */
	@Override
	public void show() {
		stage = new Stage();
		bg = new Texture("splash.png");
		pB = new Texture("playbutton.png");
		qB = new Texture("quit.png");
		skin = new Skin();
		pix = new Pixmap(100, 100, Format.RGBA8888);

		title = new Texture("title_new.png");
		ttl = new Image(title);
		ttl.setPosition(0, 720 - title.getHeight());

		bG = new Image(bg);
		bG.setPosition(0, 0);
		bG.setWidth(1200);
		bG.setHeight(768);

		stage.addActor(bG);
		stage.addActor(ttl);

		Gdx.input.setInputProcessor(stage);

		pix.setColor(Color.RED);
		pix.fill();
		skin.add("red", pB);
		skin.add("overlay", qB);
		skin.add("pb", new Texture("playbutton.png"));
		skin.add("qover", new Texture("quit.png"));

		TextButtonStyle tb = new TextButtonStyle();
		TextButtonStyle tb1 = new TextButtonStyle();

		tb.up = skin.newDrawable("red", Color.LIGHT_GRAY);
		tb.down = skin.newDrawable("red", Color.DARK_GRAY);
		tb.over = skin.newDrawable("pb");
		tb.font = new BitmapFont();

		tb1.up = skin.newDrawable("overlay", Color.LIGHT_GRAY);
		tb1.down = skin.newDrawable("overlay", Color.DARK_GRAY);
		tb1.over = skin.newDrawable("qover");
		tb1.font = new BitmapFont();

		final TextButton playButton = new TextButton("", tb);
		final TextButton quitButton = new TextButton("", tb1);

		playButton.setPosition(475, 300);
		playButton.setWidth(250f);
		playButton.setHeight(105f);

		quitButton.setPosition(475, 150);
		quitButton.setWidth(250f);
		quitButton.setHeight(105f);

		playButton.addCaptureListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				dispose();
				game.setScreen(new GameScreen(batch, game, 0));
			}
		});

		quitButton.addCaptureListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				batch.dispose();
				dispose();
				Gdx.app.exit();
			}
		});

		stage.addActor(playButton);
		stage.addActor(quitButton);
	}

	/**
	 * The render() method is an overridden, looped method. The render method
	 * runs once every frame. As a result, graphics and screen elements are
	 * updated every frame through this method. Stage.draw() is called to draw
	 * all of the elements contained in the stage (such as buttons) onto the
	 * screen.
	 */
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(.8f, .8f, .8f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
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
		// batch.dispose();
		stage.dispose();
		bg.dispose();
		pB.dispose();
		qB.dispose();
		title.dispose();
		skin.dispose();
		pix.dispose();
	}

}