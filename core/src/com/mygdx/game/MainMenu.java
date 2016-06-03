package com.mygdx.game;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
 * project. Upon pressing the "Play" button, the user is currently taken to the
 * basic game screen, however things such as name entries and difficulty
 * selection will be implemented.
 * 
 * @author Brian Tran
 * @version 2.0 19/05/2016
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
 *          <b>bg</b> Instance of Texture that stores an image that acts as the
 *          menu background
 *          <p>
 *          <b>pB</b> Instance of Texture that stores an image that acts as the
 *          play button.
 *          <p>
 *          <b>qB</b> Instance of Texture that stores an image that acts as the
 *          quit button.
 *          <p>
 *          <b>title</b> Instance of Texture that stores an image that acts as
 *          the title.
 *          <p>
 *          <b>ttl</b> Instance of Image that loads the image of the title.
 *          <p>
 *          <b>bG</b> Instance of Image that loads the image of the background.
 *          <p>
 *          <b>skin</b> Instance of Skin that allows for the customization of
 *          onscreen elements.
 * 
 */
public class MainMenu implements Screen {
	MainGame game;



	Stage stage;

	SpriteBatch batch;

	Texture bg;
	Texture pB;
	Texture lB;
	Texture qB;
	Texture title;
	Texture iB;


	Image bG;

	Image ttl;

	Skin skin;

	// Pixmap pix;

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

		if (!MainGame.mainMusic.isPlaying()){
			MainGame.mainMusic.play();
			MainGame.mainMusic.setPosition (14f);
		}

		stage = new Stage();
		bg = new Texture("splash.png");
		lB=new Texture("load.png");
		pB = new Texture("playbutton.png");
		qB = new Texture("quit.png");
		skin = new Skin();
		iB = new Texture ("instructions_button.png");
		// pix = new Pixmap(100, 100, Format.RGBA8888);

		title = new Texture("title_new.png");
		ttl = new Image (title);
		ttl.setPosition(0, 720 - title.getHeight());

		bG = new Image(bg);
		bG.setPosition(0, 0);
		bG.setWidth(1200);
		bG.setHeight(768);

		ttl.setColor(ttl.getColor().r,ttl.getColor().g,ttl.getColor().b,0);

		ttl.addAction (Actions.fadeIn(2f));

		stage.addActor(bG);

		stage.addActor(ttl);


		Gdx.input.setInputProcessor(stage);

		skin.add("red", pB);
		skin.add("load", lB);
		skin.add("overlay", qB);
		skin.add("instructions", iB);
		skin.add("pb", new Texture("playbutton.png"));
		skin.add("lb", new Texture("load.png"));
		skin.add("iB", new Texture ("instructions_button.png"));
		skin.add("qover", new Texture("quit.png"));

		TextButtonStyle tb = new TextButtonStyle();
		TextButtonStyle tb1 = new TextButtonStyle();
		TextButtonStyle tb2 = new TextButtonStyle();
		TextButtonStyle tb3 = new TextButtonStyle();
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

		tb3.up = skin.newDrawable("instructions", Color.LIGHT_GRAY);
		tb3.down = skin.newDrawable("instructions", Color.DARK_GRAY);
		tb3.over = skin.newDrawable("iB");
		tb3.font = new BitmapFont();

		final TextButton playButton = new TextButton("", tb);
		final TextButton loadButton = new TextButton("", tb2);
		final TextButton quitButton = new TextButton("", tb1);
		final TextButton instructionsButton = new TextButton ("", tb3);

		playButton.setPosition(475, 400);
		playButton.setWidth(250f);
		playButton.setHeight(105f);
		playButton.setColor(playButton.getColor().r,playButton.getColor().g,playButton.getColor().b, 0);

		playButton.addAction(Actions.fadeIn (1.5f));

		loadButton.setPosition(475, 285);
		loadButton.setWidth(250f);
		loadButton.setHeight(105f);
		loadButton.setColor(loadButton.getColor().r,loadButton.getColor().g,loadButton.getColor().b, 0);

		loadButton.addAction(Actions.fadeIn (1.5f));

		instructionsButton.setPosition (475,170);
		instructionsButton.setWidth(250f);
		instructionsButton.setHeight(105f);
		instructionsButton.setColor(instructionsButton.getColor().r,instructionsButton.getColor().g,instructionsButton.getColor().b,0);

		instructionsButton.addAction(Actions.fadeIn(1.5f));

		quitButton.setPosition(475, 55);
		quitButton.setWidth(250f);
		quitButton.setHeight(105f);
		quitButton.setColor(quitButton.getColor().r,quitButton.getColor().g,quitButton.getColor().b, 0);

		quitButton.addAction(Actions.fadeIn(1.5f));



		playButton.addCaptureListener(new ChangeListener(){
			public void changed(ChangeEvent event, Actor actor) {
				String inputName="No Name";
				char[]illegalChar={47,92,58,42,63,34,60,62};
				boolean illegalTrue;
				//redo in libgdx
				// fix error with cancelling and exiting box
				while(true){
					illegalTrue=false;
					inputName=JOptionPane.showInputDialog(null,"Enter your full name.","Input",2);
					if(inputName==null){
						return;
					}
					inputName=inputName.trim();

					if(inputName.isEmpty()){
						JOptionPane.showMessageDialog(null, "Name cannot be blank.","Attention",JOptionPane.ERROR_MESSAGE);	
					}

					else if(inputName.length()>0&&inputName.length()<25){
						for(int a=0;a<inputName.length();a++){
							for(int b=0;b<illegalChar.length;b++){
								if(inputName.charAt(a)==illegalChar[b]){
									illegalTrue=true;
								}
							}
						}
						if(illegalTrue)
							JOptionPane.showMessageDialog(null, "Name cannot contain special characters.","Attention",JOptionPane.ERROR_MESSAGE);
						else
							break;	
					}
					else{
						JOptionPane.showMessageDialog(null, "Name must be under 25 Characters.","Attention",JOptionPane.ERROR_MESSAGE);
					}
				}
				char currentChar=' ';
				String formatString="";

				String[] arrayString=inputName.split("\\s+");

				for(int a=0;a<arrayString.length;a++){     
					currentChar=arrayString[a].charAt(0);
					if(currentChar>='a'&&currentChar<='z'){
						currentChar=Character.toUpperCase(currentChar);
					}
					formatString=formatString+currentChar+arrayString[a].substring(1)+" ";       

				}
				inputName=formatString.trim();
				dispose();
				game.setSaveManager(new SaveManager());
				game.setScreen(new Difficulty(batch, game,inputName));

			}
		});

		loadButton.addCaptureListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				dispose();
				game.setSaveManager(new SaveManager());
				game.setScreen(new LoadSave(batch,game));

			}
		});
		
		instructionsButton.addCaptureListener(new ChangeListener(){
			public void changed (ChangeEvent event, Actor actor){
				dispose();
				game.setScreen(new Instructions (batch,game));
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
		stage.addActor(loadButton);
		stage.addActor(instructionsButton);
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

		Gdx.gl.glClearColor(.8f, .8f, .8f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
		// batch.dispose();
		stage.dispose();
		bg.dispose();
		pB.dispose();
		qB.dispose();
		title.dispose();
		skin.dispose();
		//mainMusic.dispose();
		// pix.dispose();
	}

}