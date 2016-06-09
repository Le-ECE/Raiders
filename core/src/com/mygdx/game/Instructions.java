package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * This class is responsible for displaying the instructions screen, which
 * contains information regarding how to play the game, and what the game is
 * about. The instructions screen can be accessed from the main menu, and
 * contains five information screens that the user can scroll through using the
 * on screen arrow buttons or by using their arrow keys located on their
 * keyboard. A back button is located on the screen that takes the user back to
 * the main menu screen.
 * 
 * <p>
 * <b>Instance Variables</b>
 * <p>
 * <b>batch</b> Instance of SpriteBatch used to render on screen elements
 * <p>
 * <b>game</b> Instance of MainGame used to manage multiple game screens
 * <p>
 * <b>screen1Text</b> Instance of Texture used to load image of screen 1
 * <p>
 * <b>screen2Text</b> Instance of Texture used to load image of screen 2
 * <p>
 * <b>screen3Text</b> Instance of Texture used to load image of screen 3
 * <p>
 * <b>screen4Text</b> Instance of Texture used to load image of screen 4
 * <p>
 * <b>screen5Text</b> Instance of Texture used to load image of screen 5
 * <p>
 * <b>nextText</b> Instance of Texture used to load image of the next button
 * <p>
 * <b>prevText</b> Instance of Texture used to load image of the previous button
 * <p>
 * <b>nextDarkText</b> Instance of Texture used to load image of dark next
 * button
 * <p>
 * <b>prevDarkText</b> Instance of Texture used to load image of dark previous
 * button
 * <p>
 * <b>backgroundText</b> Instance of Texture used to load image of background
 * <p>
 * <b>backText</b> Instance of Texture used to load back button
 * <p>
 * <b>backDarkText</b> Instance of Texture used to load dark back button
 * <p>
 * <b>screen1Sprite</b> Instance of Sprite used to create sprite of screen 1
 * <p>
 * <b>screen2Sprite</b> Instance of Sprite used to create sprite of screen 2
 * <p>
 * <b>screen3Sprite</b> Instance of Sprite used to create sprite of screen 3
 * <p>
 * <b>screen4Sprite</b> Instance of Sprite used to create sprite of screen 4
 * <p>
 * <b>screen5Sprite</b> Instance of Sprite used to create sprite of screen 5
 * <p>
 * <b>nextSprite</b> Instance of Sprite used to create sprite of next button
 * <p>
 * <b>prevSprite</b> Instance of Sprite used to create sprite of previous button
 * <p>
 * <b>nextDarkSprite</b> Instance of Sprite used to create sprite of next dark
 * button
 * <p>
 * <b>prevDarkSprite</b> Instance of Sprite used to create sprite of previous
 * dark button
 * <p>
 * <b>backgroundSprite</b> Instance of Sprite used to create sprite of
 * background image
 * <p>
 * <b>backSprite</b> Instance of Sprite used to create a sprite of the back
 * button
 * <p>
 * <b>backDarkSprite</b> Instance of Sprite used to create a sprite of the dark
 * back button
 * <p>
 * <b>nextRect</b> Instance of Rectangle used to detect button click of next
 * button
 * <p>
 * <b>prevRect</b> Instance of Rectangle used to detect button click of previous
 * button
 * <p>
 * <b>backRect</b> Instance of Rectangle used to detect button click of back
 * button
 * <p>
 * <b>pageNum</b> int representing current page number of highscore screen
 * <p>
 * <b>leftKey</b> boolean true/false based on if left key was pressed
 * <p>
 * <b>rightKey</b> boolean true/false based on if right key was pressed
 * <p>
 * <b>escKey</b> boolean true/false based on if escape key was pressed
 * <p>
 * <b>screenList</b> Sprite array used to scroll between pages
 * 
 * @author Brian Tran
 * @version 4 07/06/2016
 *
 */
public class Instructions implements Screen {

	private SpriteBatch batch;

	private MainGame game;

	private Texture screen1Text;
	private Texture screen2Text;
	private Texture screen3Text;
	private Texture screen4Text;
	private Texture screen5Text;
	private Texture screen6Text;
	private Texture nextText;
	private Texture prevText;
	private Texture nextDarkText;
	private Texture prevDarkText;
	private Texture backgroundText;
	private Texture backText;
	private Texture backDarkText;

	private Sprite screen1Sprite;
	private Sprite screen2Sprite;
	private Sprite screen3Sprite;
	private Sprite screen4Sprite;
	private Sprite screen5Sprite;
	private Sprite screen6Sprite;
	private Sprite nextSprite;
	private Sprite prevSprite;
	private Sprite nextDarkSprite;
	private Sprite prevDarkSprite;
	private Sprite backgroundSprite;
	private Sprite backSprite;
	private Sprite backDarkSprite;

	private Rectangle nextRect;
	private Rectangle prevRect;
	private Rectangle backRect;

	private int pageNum;

	private boolean leftKey;
	private boolean rightKey;
	private boolean escKey;

	private Sprite[] screenList;

	/**
	 * The Instructions constructor is used to take in a SpriteBatch and a
	 * MainGame as parameters and use them in this class for rendering and
	 * switching screens, respectively.
	 * 
	 * @param batch
	 *            SpriteBatch used to render on-screen elements
	 * @param game
	 *            MainGame used to switch and manage screens
	 */
	public Instructions(SpriteBatch batch, MainGame game) {
		this.batch = batch;
		this.game = game;
	}

	/**
	 * The show method is an overridden method from the Screen interface that
	 * gets run once at the beginning of the class execution. It is used to
	 * initialize variables, and set preferences to sprites and other on screen
	 * elements.
	 */
	@Override
	public void show() {
		// initialize array
		screenList = new Sprite[6];

		// create background texture
		backgroundText = new Texture("splash.png");
		backgroundSprite = new Sprite(backgroundText);
		backgroundSprite.setSize(1200f, 768f);

		// set page number
		pageNum = 0;

		// create back button
		backText = new Texture("backbutton.png");
		backSprite = new Sprite(backText);
		backSprite.setSize(290f, 110f);
		backSprite.setPosition(Gdx.graphics.getWidth() / 2 - backSprite.getWidth() / 2, 90f);

		// create back dark button
		backDarkText = new Texture("back_dark.png");
		backDarkSprite = new Sprite(backDarkText);
		backDarkSprite.setSize(290f, 110f);
		backDarkSprite.setPosition(Gdx.graphics.getWidth() / 2 - backDarkSprite.getWidth() / 2, 90f);

		// create screen 1
		screen1Text = new Texture("screen1.png");
		screen1Sprite = new Sprite(screen1Text);
		screenList[0] = screen1Sprite;

		// create screen 2
		screen2Text = new Texture("screen2.png");
		screen2Sprite = new Sprite(screen2Text);
		screenList[1] = screen2Sprite;

		// create screen 3
		screen3Text = new Texture("screen3.png");
		screen3Sprite = new Sprite(screen3Text);
		screenList[2] = screen3Sprite;

		// create screen 4
		screen4Text = new Texture("screen4.png");
		screen4Sprite = new Sprite(screen4Text);
		screenList[3] = screen4Sprite;

		// create screen 5
		screen5Text = new Texture("screen5.png");
		screen5Sprite = new Sprite(screen5Text);
		screenList[4] = screen5Sprite;
		
		//create screen 6
		screen6Text = new Texture ("screen6.png");
		screen6Sprite = new Sprite (screen6Text);
		screenList[5] = screen6Sprite;

		// create next button
		nextText = new Texture("next_arrow.png");
		nextSprite = new Sprite(nextText);
		nextSprite.setPosition(800f, 80f);
		nextSprite.setSize(252f, 140f);

		// create dark next button
		nextDarkText = new Texture("next_arrow_dark.png");
		nextDarkSprite = new Sprite(nextDarkText);
		nextDarkSprite.setPosition(800f, 80f);
		nextDarkSprite.setSize(252f, 140f);

		// create previous button
		prevText = new Texture("prev_arrow.png");
		prevSprite = new Sprite(prevText);
		prevSprite.setPosition(148f, 80f);
		prevSprite.setSize(252f, 140f);

		// create dark previous button
		prevDarkText = new Texture("prev_arrow_dark.png");
		prevDarkSprite = new Sprite(prevDarkText);
		prevDarkSprite.setPosition(148f, 80f);
		prevDarkSprite.setSize(252f, 140f);

		// create next button rectangle
		nextRect = nextDarkSprite.getBoundingRectangle();

		// create previous button rectangle
		prevRect = prevDarkSprite.getBoundingRectangle();

		// create back button rectangle
		backRect = backDarkSprite.getBoundingRectangle();

	}

	/**
	 * The render method is an overridden method from the Screen interface. The
	 * render method runs once every frame, refreshing the screen and its
	 * elements. The render method is mostly used to draw onscreen elements with
	 * the SpriteBatch. The first if statement is used to set the leftKey
	 * boolean to true if the left arrow key has been pressed. The second if
	 * statement is used to set the rightKey boolean to true if the right key
	 * has been pressed. The third if statement is used to set the escKey
	 * boolean to true if the escape key has been pressed. The fourth if
	 * statement is used to determine what happens if the user's cursor hovers
	 * over the next button. The fifth if statement determines what happens if
	 * the user has clicked the mouse button while hovering over the button or
	 * if the user has clicked the right arrow key. The sixth if statement
	 * determines if the current page number is at the maximum allowed pages. If
	 * it is, page number is set to minimum page number to allow for looping.
	 * Otherwise, page number increments by one. The seventh if statement is
	 * used to determine what happens if the user's cursor hovers over the
	 * previous button. The eighth if statement is used to determine what will
	 * happen if the user has clicked the mouse while the cursor is over the
	 * button, or if the user has pressed the left arrow key. The ninth if
	 * statement is used to determine if the current page number is at its
	 * minimum. If it is, the page number is set to its maximum value to allow
	 * for looping. Otherwise, the page number decrements by one. The tenth if
	 * statement is used to determine what happens when the user hovers their
	 * cursor over the back button. The eleventh if statement is used to
	 * determine change the screen to the main menu if the user clicks the mouse
	 * button while the cursor is hovered over the back button. The twelfth
	 * if statement is used to close the game when the user presses the CTRL + W
	 * shortcut.
	 */
	/*
	 * post java doc: 
	 */
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		backgroundSprite.draw(batch);

		screenList[pageNum].draw(batch);

		nextDarkSprite.draw(batch);

		prevDarkSprite.draw(batch);

		backDarkSprite.draw(batch);

		if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			leftKey = true;
		}
		if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			rightKey = true;
		}
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			escKey = true;
		}

		if (nextRect.contains(Gdx.input.getX(), Gdx.input.getY() - 430) || rightKey) {
			nextSprite.draw(batch);
			if (Gdx.input.justTouched() || rightKey) {
				rightKey = false;
				if (pageNum == 5) {
					pageNum = 0;
				} else {
					pageNum++;
				}
			}
		}
		if (prevRect.contains(Gdx.input.getX(), Gdx.input.getY() - 430) || leftKey) {
			prevSprite.draw(batch);
			if (Gdx.input.justTouched() || leftKey) {
				leftKey = false;
				if (pageNum == 0) {
					pageNum = 5;
				} else {
					pageNum--;
				}
			}
		}
		if (backRect.contains(Gdx.input.getX(), Gdx.input.getY() - 440) || escKey) {
			backSprite.draw(batch);
			if (Gdx.input.justTouched() || escKey) {
				escKey = false;
				game.setScreen(new MainMenu(batch, game));
			}
		}
		
		if (Gdx.input.isKeyPressed (Keys.CONTROL_LEFT)&&Gdx.input.isKeyJustPressed(Keys.W)){
			batch.dispose();
			Gdx.app.exit();
		}

		batch.end();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#pause()
	 */
	@Override
	public void pause() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#resume()
	 */
	@Override
	public void resume() {

	}

	/**
	 * The hide method is an overridden method from the Screen interface that
	 * runs every time the screen is switched off of the current one. The
	 * dispose method is called so that the screen properly disposes of itself
	 * once done with.
	 */
	@Override
	public void hide() {
		dispose();
	}

	/**
	 * The dispose method is an overridden method that is used to dispose of all
	 * of the resources once this screen has been destroyed. This allows for
	 * more free system memory.
	 */
	@Override
	public void dispose() {
		screen1Text.dispose();
		screen2Text.dispose();
		screen3Text.dispose();
		screen4Text.dispose();
		screen5Text.dispose();
		nextText.dispose();
		prevText.dispose();
		nextDarkText.dispose();
		prevDarkText.dispose();
		backgroundText.dispose();
		backText.dispose();
		backDarkText.dispose();
	}

}
