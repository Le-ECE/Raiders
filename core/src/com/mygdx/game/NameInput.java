package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * The NameInput, which implements the Screen interface, is responsible for
 * displaying a screen that allows the user to enter a String as their username.
 * This username will be used later on for highscores, and for saves. The
 * NameInput screen contains a title, labeled "Enter you name", a textfield, and
 * two buttons. The textfield (uses libGDX TextField) allows the user to type
 * and store their username. The two buttons are "Go", and "Back". Upon pressing
 * the "Go" button, the user is taken to the Difficulty selection menu if their
 * username is valid (is not null / does not exceed 25 characters / does not
 * contain illegal characters). Upon pressing the "Back" button, the user is
 * taken back to the main menu.
 * 
 * @author Brian Tran
 * @version 4.0 07.06.2016
 * 
 *          <p>
 *          <b>Instance Variables</b>
 *          <p>
 *          <b>batch</b> Instance of SpriteBatch used to render on screen
 *          elements
 *          <p>
 *          <b>game</b> Instance of MainGame used to manage multiple screens.
 *          <p>
 *          <b>backgroundText</b> Instance of Texture used to load background
 *          image
 *          <p>
 *          <b>titleText</b> Instance of Texture used to load title image
 *          <p>
 *          <b>userNameField</b> Instance of TextField used to take user String
 *          input
 *          <p>
 *          <b>stage</b> Instance of Stage used to contain on screen elements
 *          (such as buttons)
 *          <p>
 *          <b>font</b> Instance of BitmapFont used to store and draw text in
 *          custom fonts
 *          <p>
 *          <b>name</b> String used to represent inputted username
 *          <p>
 *          <b>errorCheck</b> int used as errorcheck for username
 */
public class NameInput implements Screen {

	SpriteBatch batch;

	MainGame game;

	Texture backgroundText;
	Texture titleText;

	TextField userNameField;

	Stage stage;

	BitmapFont font;

	String name;

	int errorCheck;

	/**
	 * The NameInput constructor is used to take in a SpriteBatch and a MainGame
	 * from other classes as parameters. This allows for the NameInput class to
	 * make use of the SpriteBatch and MainGame without having to create new
	 * ones. SpriteBatch being used for rendering on screem elements, and
	 * MainGame being used to manage on screen elements.
	 * 
	 * @param batch SpriteBatch used to render on-screen elements
	 * @param game MainGame used to manage multiple screens
	 */
	public NameInput(SpriteBatch batch, MainGame game) {
		this.batch = batch;
		this.game = game;
	}

	/**
	 * The show method is an overridden method from the Screen interface. The
	 * show method is responsible for initializing variables, setting button
	 * styles, and managing input.
	 */
	@Override
	public void show() {

		errorCheck = 0;

		font = new BitmapFont(Gdx.files.internal("assets/riskofrain_bitmap.fnt"),
				Gdx.files.internal("assets/riskofrain_bitmap_0.png"), false);

		stage = new Stage();

		Gdx.input.setInputProcessor(stage);

		Skin skin = new Skin();

		Texture tfBackgroundText = new Texture("assets/textfield_background.png");

		Texture cursorText = new Texture("assets/textcursor.png");

		Sprite cursorSprite = new Sprite(cursorText);
		cursorSprite.setSize(15, 50);

		Image tfBackgroundImg = new Image(tfBackgroundText);
		tfBackgroundImg.setSize(600, 70);
		tfBackgroundImg.setPosition((Gdx.graphics.getWidth() / 2) - (tfBackgroundImg.getWidth() / 2), 400);

		skin.add("textfieldbackground", tfBackgroundImg);
		skin.add("textfieldfont", font);
		skin.add("textfieldcursor", new Texture("assets/cursor.png"));
		skin.add("gobuttontexture", new Texture("assets/go.png"));
		skin.add("backbuttontexture", new Texture("assets/backbutton.png"));
		skin.add("cursor", cursorSprite);

		// create go button style
		TextButtonStyle goBS = new TextButtonStyle();
		goBS.up = skin.newDrawable("gobuttontexture", Color.LIGHT_GRAY);
		goBS.down = skin.newDrawable("gobuttontexture", Color.DARK_GRAY);
		goBS.over = skin.newDrawable("gobuttontexture");
		goBS.font = new BitmapFont();

		// create back button style
		TextButtonStyle bBS = new TextButtonStyle();
		bBS.up = skin.newDrawable("backbuttontexture", Color.LIGHT_GRAY);
		bBS.down = skin.newDrawable("backbuttontexture", Color.DARK_GRAY);
		bBS.over = skin.newDrawable("backbuttontexture");
		bBS.font = new BitmapFont();

		// create background
		backgroundText = new Texture("assets/splash.png");
		Image backgroundImage = new Image(backgroundText);
		backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// create title
		titleText = new Texture("assets/nameTitle.png");
		Image titleImage = new Image(titleText);
		titleImage.setPosition((Gdx.graphics.getWidth() / 2) - (titleImage.getWidth() / 2),
				Gdx.graphics.getHeight() - titleImage.getHeight());

		TextFieldStyle style = new TextFieldStyle();
		style.font = skin.getFont("textfieldfont");
		style.background = null;
		style.fontColor = Color.BLACK;
		style.cursor = skin.getDrawable("cursor");

		// create text field
		userNameField = new TextField("", style);
		userNameField.setPosition(310, 400);
		userNameField.setSize(550, 70);

		// create go button
		TextButton goButton = new TextButton("", goBS);
		goButton.setPosition(700, 100);
		goButton.setSize(290f, 110f);

		goButton.addCaptureListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				name = userNameField.getText();
				errorCheck = nameCheck();
			}
		});

		// create back button
		TextButton backButton = new TextButton("", bBS);
		backButton.setPosition(200, 100);
		backButton.setSize(290f, 110f);

		backButton.addCaptureListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new MainMenu(batch, game));
			}
		});

		stage.addActor(backgroundImage);
		stage.addActor(titleImage);
		stage.addActor(tfBackgroundImg);
		stage.addActor(userNameField);
		stage.addActor(goButton);
		stage.addActor(backButton);
		stage.setKeyboardFocus(userNameField);
		// userNameField.getOnscreenKeyboard();
	}

	/**
	 * The nameCheck method is used to determine if the name the user has
	 * entered is valid. This means that the name does not exceed 25
	 * characters, is not null, and does not contain any illegal characters.
	 * The nameCheck method is a return method that returns an int based
	 * on what sort of error the name contains. The first if statement is
	 * used to determine if the name the user enters is empty. If it is,
	 * the method returns 1 and sets the text field to be empty. The second
	 * if statement is used to determine if the name exceeds 25 characters.
	 * If it does, the method returns 2 and sets the text field to be empty.
	 * Otherwise, the first and second for loops, as well as the
	 * third if statement are used to determine if the name the user enters
	 * contains any illegal characters. If so, the textfield is set to empty,
	 * and the method returns 3. The third for loop and fourth if statement
	 * are used to format the name and capitalize it. If it gets to this stage,
	 * the method returns 4.
	 * 
	 * @return int used to determine what type of error the name contains
	 */
	public int nameCheck() {
		char[] illegalChar = { 47, 92, 58, 42, 63, 34, 60, 62 };

		if (name.trim().isEmpty()) {
			userNameField.setText("");
			return 1;
		}
		if (name.trim().length() >= 25) {
			userNameField.setText("");
			return 2;
		} else {

			for (int x = 0; x < name.length(); x++) {
				for (int y = 0; y < illegalChar.length; y++) {
					if (name.charAt(x) == illegalChar[y]) {
						userNameField.setText("");
						return 3;
					}
				}
			}
		}

		char currentChar = ' ';
		String formatString = "";

		String[] arrayString = name.split("\\s+");

		for (int a = 0; a < arrayString.length; a++) {
			currentChar = arrayString[a].charAt(0);
			if (currentChar >= 'a' && currentChar <= 'z') {
				currentChar = Character.toUpperCase(currentChar);
			}
			formatString = formatString + currentChar + arrayString[a].substring(1) + " ";

		}
		name = formatString.trim();
		return 4;
	}

	/**
	 * The render method is an overridden method from the Screen interface. The
	 * render method loops every frame of the game. The render method is used to
	 * elements to the screen, as well as take in user input. The first if statement
	 * is used to bring the user back to the main menu if the user presses the escape
	 * key. The second if statement is used to enter and check the name if the user
	 * presses the enter key. The third if statement is used to determine what displays
	 * if the nameCheck method returns 1. If it does, it gives a notification that the
	 * name cannot be blank. The fourth if statement is used to determine what displays
	 * if the nameCheck method returns 2. If it does, it gives a notification that the
	 * name cannot exceed 25 characters. The fifth if statement is used to determine what
	 * displays if the nameCheck method returns 3. If it does, it gives a notification that
	 * the username cannot contain special characters. The sixth if statement is used
	 * to determine what displays if the nameCheck method returns 4. If it does, the screen
	 * is set to the Difficulty selection menu, with the name passed through. The seventh
	 * is statement is used to close the game when the user presses the CTRL + W shortcut.
	 * 
	 */
	/*
	 * post java doc: 
	 */
	@Override
	public void render(float delta) {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		batch.begin();

		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			game.setScreen(new MainMenu(batch, game));
		}

		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			name = userNameField.getText();
			errorCheck = nameCheck();
		}

		if (errorCheck == 1) {
			font.setColor(Color.YELLOW);
			font.draw(batch, "Your name cannot be blank.", 290, 390);
		} else if (errorCheck == 2) {
			font.setColor(Color.YELLOW);
			font.draw(batch, "Please enter a name under 25 letters long.", 290, 390);
		} else if (errorCheck == 3) {
			font.setColor(Color.YELLOW);
			font.draw(batch, "Please enter a name without special characters.", 290, 390);
		} else if (errorCheck == 4) {
			dispose();
			game.setSaveManager(new SaveManager());
			game.setScreen(new Difficulty(batch, game, name));
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
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#pause()
	 */
	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	/**
	 * The hide method runs the dispose method everytime the screen is switched.
	 */
	@Override
	public void hide() {
		// TODO Auto-generated method stub

		dispose();
	}

	/**
	 * The dispose method is used to dispose of textures and resources in order
	 * to free up system memory.
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		backgroundText.dispose();
		font.dispose();
		titleText.dispose();
	}

}