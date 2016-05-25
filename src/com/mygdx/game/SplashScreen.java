package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import tween.SpriteManager;

/**
 * The SplashScreen class shows an animation of the main character running
 * across the screen with a boulder following, displaying the company name on
 * the screen. Once the company name fades, the game is taken to the main menu.
 * <p>
 * <b>Instance Variables</b>
 * <p>
 * <b>batch</b> Instance of SpriteBatch that allows for elements to display on
 * the screen.
 * <p>
 * <b>game</b> Instance of MainGame that allows for screen changes.
 * <p>
 * <b>walkAnim</b> Instance of Animation that allows for animation of walking
 * frames.
 * <p>
 * <b>rollAnim</b> Instance of Animation that allows for animation of rolling
 * frames.
 * <p>
 * <b>framesWalk</b> Array of TextureRegion that contains the frames for walk
 * animation.
 * <p>
 * <b>framesRoll</b> Array of TextureRegion that contains the frames for roll
 * animation.
 * <p>
 * <b>splashTitleSprite</b> Instance of Sprite that loads the sprite for the
 * company title.
 * <p>
 * <b>walk</b> Instance of Texture that loads the spritesheet for walk
 * animation.
 * <p>
 * <b>roll</b> Instance of Texture that loads the spritesheet for roll
 * animation.
 * <p>
 * <b>background</b> Instance of Texture that loads the background image.
 * <p>
 * <b>splashTitle</b> Instance of Texture that loads the splashscreen image.
 * <p>
 * <b>time</b> Float variable used to keep track of time for animation.
 * <p>
 * <b>speed</b> Float variable used to determine speed of animation.
 * <p>
 * <b>walkX</b> Float variable used to determine the user's location.
 * <p>
 * <b>tweenManager</b> Instance of TweenManager used to animate the text.
 * 
 * @author Brian Tran
 * @version 2.0.0 19/05/2016
 *
 */

public class SplashScreen implements Screen {

	SpriteBatch batch;

	MainGame game;

	Animation walkAnim;
	Animation rollAnim;

	TextureRegion[] framesWalk;
	TextureRegion[] framesRoll;

	Sprite splashTitleSprite;

	Texture walk;
	Texture roll;
	Texture background;
	Texture splashTitle;

	float time;
	float speed;
	float walkX;

	TweenManager tweenManager;

	/**
	 * The constructor for this class takes in an instance of MainGame and in
	 * order to allow for changing of game screens.
	 * 
	 * @param game MainGame instance used for changing screens
	 */
	public SplashScreen(MainGame game) {
		this.game = game;
	}

	/**
	 * The show method is used to initialize all of the variables, as well as
	 * set all the positions, and sizes for each of the sprites to be drawn on
	 * screen. The show method runs every time the application is ran.
	 */
	@Override
	public void show() {
		batch = new SpriteBatch();

		tweenManager = new TweenManager();
		Tween.registerAccessor(Sprite.class, new SpriteManager());

		speed = 250.0f;

		background = new Texture("splash.png");

		splashTitle = new Texture("splashtitle.png");
		splashTitleSprite = new Sprite(splashTitle);
		splashTitleSprite.setPosition(0f, 750 - splashTitleSprite.getHeight());

		roll = new Texture("roll_left.png");
		walk = new Texture("left_walk.png");

		TextureRegion[][] temp_left = TextureRegion.split(walk, 32, 64);
		TextureRegion[][] temp_roll = TextureRegion.split(roll, 32, 32);
		framesWalk = new TextureRegion[4];
		framesRoll = new TextureRegion[4];

		int index = 0;

		for (int rows = 0; rows < 2; rows++) {
			for (int col = 0; col < 2; col++) {
				framesWalk[index] = temp_left[rows][col];
				framesRoll[index] = temp_roll[rows][col];
				index++;
			}
		}

		walkAnim = new Animation(0.15f, framesWalk);
		rollAnim = new Animation(0.10f, framesRoll);

		Tween.set(splashTitleSprite, SpriteManager.ALPHA).target(0f).start(tweenManager);
		Tween.to(splashTitleSprite, SpriteManager.ALPHA, 2f).target(1f).repeatYoyo(1, 4f)
		.setCallback(new TweenCallback() {
			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				dispose();
				((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu(batch, game));
			}
		}).start(tweenManager);

		Gdx.gl.glClearColor(.8f, .8f, .8f, 1);
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

		tweenManager.update(delta);

		time += Gdx.graphics.getDeltaTime();
		batch.begin();

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.draw(background, 0, 0, 1200f, 768f);

		walkX += Gdx.graphics.getDeltaTime() * speed;

		batch.draw(walkAnim.getKeyFrame(time, true), 1300 - walkX, 100, 64f, 128f);
		batch.draw(rollAnim.getKeyFrame(time, true), 1450 - walkX, 100, 128f, 128f);

		splashTitleSprite.draw(batch);
		batch.end();

		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu(batch, game));
		}
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
		dispose();
	}

	/**
	 * This method disposes of all unused resources in order to prevent more
	 * memory from being taken up.
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		walk.dispose();
		roll.dispose();
		//background.dispose();
		splashTitle.dispose();

	}

}
