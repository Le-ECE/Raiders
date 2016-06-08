package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * 
 * The Difficulty class is used to allow the user to select the difficulty of
 * the game. The difficulties that are available are easy, medium, and hard. The
 * user does this through a series of buttons, labeled "Easy", "Medium", and
 * "Hard". The user navigates these buttons with their mouse. Pressing the "Go!"
 * button allows to user to enter the level with the selected difficulty. If the
 * user does not have a difficulty selected, the "Go!" button will not be
 * available to be selected. The included "Back" button will allow the user to
 * go back to the main menu.
 * 
 * <p>
 * <b>Instance Variables</b>
 * <p>
 * <b>difficulty</b> Int variable that stores the difficulty index the user has
 * selected.
 * <p>
 * <b>drawDarkEasy</b> Boolean variable that determines which easy button to
 * draw.
 * <p>
 * <b>drawDarkMedium</b> Boolean variable that determines which medium button to
 * draw.
 * <p>
 * <b>drawDarkHard</b> Boolean variable that determines which hard button to
 * draw.
 * <p>
 * <b>easy</b> Instance of the Texture class that loads the easy button.
 * <p>
 * <b>background</b> Instance of the Texture class that loads the background.
 * <p>
 * <b>medium</b> Instance of the Texture class that loads the medium button.
 * <p>
 * <b>hard</b> Instance of the Texture class that loads the hard button.
 * <p>
 * <b>title</b> Instance of the Texture class that loads the title.
 * <p>
 * <b>easyDark</b> Instance of the Texture class that loads the dark version of
 * the easy button.
 * <p>
 * <b>medDark</b> Instance of the Texture class that loads the dark version of
 * the medium button.
 * <p>
 * <b>hardDark</b> Instance of the Texture class that loads the dark version of
 * the hard button.
 * <p>
 * <b>back</b> Instance of the Texture class that loads the back button.
 * <p>
 * <b>backDark</b> Instance of the Texture class that loads the dark version of
 * the back button.
 * <p>
 * <b>go</b> Instance of the Texture class that loads the go button.
 * <p>
 * <b>goDark</b> Instance of the Texture class that loads the dark version of
 * the go button.
 * <p>
 * <b>goRect</b> Instance of the Rectangle class that loads the Rectangle for
 * the go button.
 * <p>
 * <b>easyRect</b> Instance of the Rectangle class that loads the Rectangle for
 * the easy button.
 * <p>
 * <b>medRect</b> Instance of the Rectangle class that loads the Rectangle for
 * the medium button.
 * <p>
 * <b>hardRect</b> Instance of the Rectangle class that loads the Rectangle for
 * the hard button.
 * <p>
 * <b>goDarkSprite</b> Instance of the Sprite class that loads the dark go
 * button sprite.
 * <p>
 * <b>goSprite</b> Instance of the Sprite class that loads the go button sprite.
 * <p>
 * <b>titleSprite</b> Instance of the Sprite class that loads the title sprite.
 * <p>
 * <b>easySprite</b> Instance of the Sprite class that loads the easy button
 * sprite.
 * <p>
 * <b>mediumSprite</b> Instance of the Sprite class that loads the medium button
 * sprite.
 * <p>
 * <b>hardSprite</b> Instance of the Sprite class that loads the hard button
 * sprite.
 * <p>
 * <b>backgroundSprite</b> Instance of the Sprite class that loads the
 * background sprite.
 * <p>
 * <b>easyDarkSprite</b> Instance of the Sprite class that loads the dark easy
 * button.
 * <p>
 * <b>medDarkSprite</b> Instance of the Sprite class that loads the dark medium
 * button.
 * <p>
 * <b>hardDarkSprite</b> Instance of the Sprite class that loads the dark hard
 * button.
 * <p>
 * <b>backSprite</b> Instance of the Sprite class that loads the back button
 * sprite.
 * <p>
 * <b>backDarkSprite</b> Instance of the Sprite class that loads the dark back
 * button sprite.
 * <p>
 * <b>batch</b> Instance of the SpriteBatch class that allows the user to draw
 * object on the screen.
 * <p>
 * <b>game</b> Instance of the MainGame class that allows the user to change
 * screens.
 * 
 * @author Brian Tran
 * @version 4.0 03/06/2016
 *
 */
public class Difficulty implements Screen {

	private int difficulty = -1;
	private String name;
	private int highLight;

	private boolean drawDarkEasy = true;
	private boolean drawDarkMedium = true;
	private boolean drawDarkHard = true;

	Texture easy;
	Texture background;
	Texture medium;
	Texture hard;
	Texture title;
	Texture easyDark;
	Texture medDark;
	Texture hardDark;
	Texture back;
	Texture backDark;
	Texture go;
	Texture goDark;

	Rectangle goRect;
	Rectangle easyRect;
	Rectangle medRect;
	Rectangle hardRect;
	Rectangle backRect;

	Sprite goDarkSprite;
	Sprite goSprite;
	Sprite titleSprite;
	Sprite easySprite;
	Sprite mediumSprite;
	Sprite hardSprite;
	Sprite backgroundSprite;
	Sprite easyDarkSprite;
	Sprite medDarkSprite;
	Sprite hardDarkSprite;
	Sprite backSprite;
	Sprite backDarkSprite;

	private SpriteBatch batch;

	private MainGame game;

	public static boolean playing;

	/**
	 * The Difficulty constructor takes in a SpriteBatch and a MainGame in order
	 * to allow for the rendering of elements and the changing of screens.
	 * 
	 * @param batch
	 *            SpriteBatch used to display all sprites and elements.
	 * @param game
	 *            MainGame used to change game screens.
	 * @param name
	 * 			  String used to store username.
	 */
	public Difficulty(SpriteBatch batch, MainGame game, String name) {
		this.name = name;
		this.batch = batch;
		this.game = game;
	}

	/**
	 * The show method is used to initialize all of the variables, as well as
	 * set all the positions, and sizes for each of the sprites to be drawn on
	 * screen. The show method runs every time the application is ran.
	 */
	@Override
	public void show() {

		// go dark create
		goDark = new Texture("assets/go_dark.png");

		// go dark sprite create
		goDarkSprite = new Sprite(goDark);
		goDarkSprite.setSize(250, 105);
		goDarkSprite.setPosition(((Gdx.graphics.getWidth() / 4) * 3) - (goDarkSprite.getWidth() / 2), 10);

		// go create
		go = new Texture("assets/go.png");

		// go sprite create
		goSprite = new Sprite(go);
		goSprite.setSize(250, 105);
		goSprite.setPosition(((Gdx.graphics.getWidth() / 4) * 3) - (goSprite.getWidth() / 2), 10);

		// back dark create
		backDark = new Texture("assets/back_dark.png");

		// back dark sprite create
		backDarkSprite = new Sprite(backDark);
		backDarkSprite.setSize(250, 105);
		backDarkSprite.setPosition((Gdx.graphics.getWidth() / 4) - (backDarkSprite.getWidth() / 2), 10);

		// back create
		back = new Texture("assets/backbutton.png");

		// back sprite create
		backSprite = new Sprite(back);
		backSprite.setSize(250, 105);
		backSprite.setPosition((Gdx.graphics.getWidth() / 4) - (backSprite.getWidth() / 2), 10);

		// title create
		title = new Texture("assets/difficulty_title.png");
		// easy button create
		easy = new Texture("assets/easy.png");
		// medium button create
		medium = new Texture("assets/medium.png");
		// hard button create
		hard = new Texture("assets/hard.png");

		// easy dark create
		easyDark = new Texture("assets/easy_dark.png");
		// medium dark create
		medDark = new Texture("assets/medium_dark.png");
		// hard dark create
		hardDark = new Texture("assets/hard_dark.png");

		// title sprite create
		titleSprite = new Sprite(title);
		titleSprite.setPosition(0, 768 - titleSprite.getHeight());
		titleSprite.setSize(1200, 300);
		// easy sprite create
		easySprite = new Sprite(easy);
		easySprite.setSize(290, 110);
		easySprite.setPosition((Gdx.graphics.getWidth() / 2) - (easySprite.getWidth() / 2), 400);

		// medium sprite create
		mediumSprite = new Sprite(medium);
		mediumSprite.setSize(290, 110);
		mediumSprite.setPosition((Gdx.graphics.getWidth() / 2) - (mediumSprite.getWidth() / 2), 280);

		// hard sprite create
		hardSprite = new Sprite(hard);
		hardSprite.setSize(290, 110);
		hardSprite.setPosition((Gdx.graphics.getWidth() / 2) - (hardSprite.getWidth() / 2), 160);

		// easy dark sprite create
		easyDarkSprite = new Sprite(easyDark);
		easyDarkSprite.setSize(290, 110);
		easyDarkSprite.setPosition((Gdx.graphics.getWidth() / 2) - (easyDarkSprite.getWidth() / 2), 400);

		// medium dark sprite create
		medDarkSprite = new Sprite(medDark);
		medDarkSprite.setSize(290, 110);
		medDarkSprite.setPosition((Gdx.graphics.getWidth() / 2) - (medDarkSprite.getWidth() / 2), 280);

		// hard dark sprite create
		hardDarkSprite = new Sprite(hardDark);
		hardDarkSprite.setSize(290, 110);
		hardDarkSprite.setPosition((Gdx.graphics.getWidth() / 2) - (hardDarkSprite.getWidth() / 2), 160);

		// easy rectangle create
		easyRect = new Rectangle(easySprite.getX(), easySprite.getY(), easySprite.getWidth(), easySprite.getHeight());

		// medium rectangle create
		medRect = new Rectangle(mediumSprite.getX(), mediumSprite.getY(), mediumSprite.getWidth(),
				mediumSprite.getHeight());

		// hard rectangle create
		hardRect = new Rectangle(hardSprite.getX(), hardSprite.getY(), hardSprite.getWidth(), hardSprite.getHeight());

		// back rectangle create
		backRect = new Rectangle(backSprite.getX(), backSprite.getY(), backSprite.getWidth(), backSprite.getHeight());

		// go rectangle create
		goRect = new Rectangle(goSprite.getX(), goSprite.getY(), goSprite.getWidth(), goSprite.getHeight());

		// background create
		background = new Texture("assets/splash.png");

		highLight = 1;
	}

	/**
	 * The render() method is an overridden method from the GDX library. The
	 * render method is run every frame, and as a result, updates the screen
	 * every frame with new information. This allows for animations of sprites
	 * and such to be easier. Things are displayed on the screen using a
	 * SpriteBatch, which acts as a container of sorts for the screen
	 * elements.Various for loops are used to detect user input, as for
	 * collision and detection.
	 */
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		batch.draw(background, 0, 0, 1200f, 768f);
		titleSprite.draw(batch);

		backDarkSprite.draw(batch);
		goDarkSprite.draw(batch);

		if (drawDarkEasy)
			easyDarkSprite.draw(batch);
		else {
			medDarkSprite.draw(batch);
			hardDarkSprite.draw(batch);
			easySprite.draw(batch);
		}
		if (drawDarkMedium)
			medDarkSprite.draw(batch);
		else {
			easyDarkSprite.draw(batch);
			mediumSprite.draw(batch);
			hardDarkSprite.draw(batch);
		}
		if (drawDarkHard)
			hardDarkSprite.draw(batch);
		else {
			easyDarkSprite.draw(batch);
			medDarkSprite.draw(batch);
			hardSprite.draw(batch);
		}

		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			if (Gdx.input.isKeyJustPressed(Keys.DPAD_UP)) {
				if (highLight == 1)
					highLight = 3;
				else
					highLight--;
			}
		}

		if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			if (Gdx.input.isKeyJustPressed(Keys.DPAD_DOWN)) {
				if (highLight == 3)
					highLight = 1;
				else
					highLight++;
			}
		}

		if (backRect.contains(Gdx.input.getX(), Gdx.input.getY() - 600) || Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			backSprite.draw(batch);
			if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
				game.setScreen(new MainMenu(batch, game));
			}
		}
		if (easyRect.contains(Gdx.input.getX(), Gdx.input.getY() + 185) || highLight == 1) {
			if (easyRect.contains(Gdx.input.getX(), Gdx.input.getY() + 185))
				easySprite.draw(batch);
			if (Gdx.input.justTouched() || highLight == 1) {
				highLight = 1;
				difficulty = 0;
				drawDarkEasy = false;
				drawDarkMedium = true;
				drawDarkHard = true;
			}
		}
		if (medRect.contains(Gdx.input.getX(), Gdx.input.getY() - 70) || highLight == 2) {
			if (medRect.contains(Gdx.input.getX(), Gdx.input.getY() - 70))
				mediumSprite.draw(batch);
			if (Gdx.input.justTouched() || highLight == 2) {
				highLight = 2;
				difficulty = 2;
				drawDarkMedium = false;
				drawDarkEasy = true;
				drawDarkHard = true;
				mediumSprite.draw(batch);
			}
		}
		if (hardRect.contains(Gdx.input.getX(), Gdx.input.getY() - 300) || highLight == 3) {
			if (hardRect.contains(Gdx.input.getX(), Gdx.input.getY() - 300))
				hardSprite.draw(batch);
			if (Gdx.input.justTouched() || highLight == 3) {
				highLight = 3;
				difficulty = 4;
				drawDarkHard = false;
				drawDarkEasy = true;
				drawDarkMedium = true;
				hardSprite.draw(batch);
			}
		}
		if (goRect.contains(Gdx.input.getX(), Gdx.input.getY() - 625) || Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			if (difficulty > -1) {
				goSprite.draw(batch);
				if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Keys.ENTER)) {
					game.setScreen(new GameScreen(batch, game, name, difficulty, 0));// add
																						// current
																						// time
																						// from
																						// save
					dispose();
				}
			}
		}

		batch.end();
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
		this.dispose();

	}

	/**
	 * This method disposes of all unused resources in order to prevent more
	 * memory from being taken up.
	 */
	@Override
	public void dispose() {
		easy.dispose();
		background.dispose();
		medium.dispose();
		hard.dispose();
		title.dispose();
		easyDark.dispose();
		medDark.dispose();
		hardDark.dispose();
		back.dispose();
		backDark.dispose();
		go.dispose();
		goDark.dispose();

	}

}
