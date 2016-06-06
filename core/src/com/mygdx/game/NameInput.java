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
	boolean notNull;
	boolean underRange;
	boolean notSpecial;
	
	public NameInput (SpriteBatch batch, MainGame game){
		this.batch = batch;
		this.game = game;
	}
	
	@Override
	public void show() {

		errorCheck=0;
		
		font = new BitmapFont (Gdx.files.internal("assets/riskofrain_bitmap.fnt"),Gdx.files.internal("assets/riskofrain_bitmap_0.png"),false);
		
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);
		
		Skin skin = new Skin();
		
		Texture tfBackgroundText = new Texture ("assets/textfield_background.png");
		
		Texture cursorText = new Texture ("assets/textcursor.png");
		
		Sprite cursorSprite = new Sprite (cursorText);
		cursorSprite.setSize(15,50);
		
		Image tfBackgroundImg = new Image (tfBackgroundText);
		tfBackgroundImg.setSize(600, 70);
		tfBackgroundImg.setPosition((Gdx.graphics.getWidth()/2)-(tfBackgroundImg.getWidth()/2), 400);
		
		skin.add ("textfieldbackground", tfBackgroundImg);
		skin.add("textfieldfont", font);
		skin.add("textfieldcursor", new Texture ("assets/cursor.png"));
		skin.add("gobuttontexture", new Texture ("assets/go.png"));
		skin.add("backbuttontexture", new Texture ("assets/backbutton.png"));
		skin.add("cursor", cursorSprite);
		
		//create go button style
		TextButtonStyle goBS = new TextButtonStyle();
		goBS.up = skin.newDrawable("gobuttontexture", Color.LIGHT_GRAY);
		goBS.down = skin.newDrawable("gobuttontexture", Color.DARK_GRAY);
		goBS.over = skin.newDrawable("gobuttontexture");
		goBS.font = new BitmapFont();
		
		//create back button style
		TextButtonStyle bBS = new TextButtonStyle();
		bBS.up = skin.newDrawable("backbuttontexture",Color.LIGHT_GRAY);
		bBS.down = skin.newDrawable("backbuttontexture", Color.DARK_GRAY);
		bBS.over = skin.newDrawable("backbuttontexture");
		bBS.font = new BitmapFont();
		
		//create background
		backgroundText = new Texture ("assets/splash.png");
		Image backgroundImage = new Image (backgroundText);
		backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		//create title
		titleText = new Texture ("assets/nameTitle.png");
		Image titleImage = new Image (titleText);
		titleImage.setPosition((Gdx.graphics.getWidth()/2)-(titleImage.getWidth()/2), Gdx.graphics.getHeight()-titleImage.getHeight());
		
		TextFieldStyle style = new TextFieldStyle();
		style.font = skin.getFont("textfieldfont");
		style.background = null;
		style.fontColor = Color.BLACK;
		style.cursor = skin.getDrawable ("cursor");
		
		//create text field
		userNameField = new TextField ("",style);
		userNameField.setPosition(310, 400);
		userNameField.setSize(550, 70);
		
		//create go button 
		TextButton goButton = new TextButton ("", goBS);
		goButton.setPosition(700, 100);
		goButton.setSize(290f, 110f);
		
		goButton.addCaptureListener(new ChangeListener(){
			public void changed (ChangeEvent event, Actor actor){
				name = userNameField.getText();
				errorCheck=nameCheck();
			}
		});
		
		//create back button
		TextButton backButton = new TextButton ("", bBS);
		backButton.setPosition(200, 100);
		backButton.setSize(290f, 110f);
		
		backButton.addCaptureListener(new ChangeListener(){
			public void changed (ChangeEvent event, Actor actor){
				game.setScreen(new MainMenu (batch, game));
			}
		});
		
		stage.addActor (backgroundImage);
		stage.addActor(titleImage);
		stage.addActor(tfBackgroundImg);
		stage.addActor(userNameField);
		stage.addActor(goButton);
		stage.addActor(backButton);
		stage.setKeyboardFocus(userNameField);
		//userNameField.getOnscreenKeyboard();
	}
	
	public int nameCheck(){
		char[] illegalChar = { 47, 92, 58, 42, 63, 34, 60, 62 };

		
		if (name.trim().isEmpty()){
			userNameField.setText("");
			return 1;
		}
		if (name.trim().length() >= 25){
				userNameField.setText("");
			return 2;
			}
			else{
		
				for (int x = 0; x < name.length();x++){
					for (int y = 0; y < illegalChar.length; y++){
						if (name.charAt(x) == illegalChar [y]){
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
			

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		batch.begin();
		
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {	
			game.setScreen(new MainMenu (batch, game));		
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			name = userNameField.getText();
			errorCheck=nameCheck();	
		}
		
		if (errorCheck==1){
			font.setColor (Color.YELLOW);
			font.draw(batch, "Your name cannot be blank.", 290, 390);
		}
		else 	if (errorCheck==2){
			font.setColor (Color.YELLOW);
			font.draw(batch, "Please enter a name under 25 letters long.", 290, 390);
		}
		else if (errorCheck==3){
			font.setColor (Color.YELLOW);
			font.draw(batch, "Please enter a name without special characters.", 290, 390);
		}
		else if (errorCheck==4){
			dispose();
			game.setSaveManager(new SaveManager());
			game.setScreen(new Difficulty(batch, game, name));
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