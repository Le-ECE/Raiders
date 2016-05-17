/**
 * The Movement class is used to test the main character sprite's movement.
 * The class also includes a render of a custom made map, as well as
 * drawings of sprites. All of this is done on a single screen.
 * A looping method as well as a series of if statements (to check for
 * collision) are used to allow the sprite to traverse the screen.
 * It takes a 'sprite sheet', which is a grid of animation frames, and
 * splits it into a 2D array of type TextureRegion, which is then made into
 * a 1D array of the same type. That array, in conjunction with the built in
 * Animation class, is used to animate the sprite's walking cycle. 
 * 
 * @author Brian Tran
 * @version 1.0 13.05.2016
 * 
 * <p>
 * <b>Instance Variables</b>
 * <p>
 * <b>animation_right</b> Instance of Animation class used to animate right walking cycle
 * <p>
 * <b>animation_left</b> Instance of Animation class used to animate left walking cycle
 * <p>
 * <b>animation_up</b> Instance of Animation class used to animate up walking cycle
 * <p>
 * <b>animation_down</b> Instance of Animation class used to animate down walking cycle
 * <p>
 * <b>frames_right</b> TextureRegion array used to store frames for right walk cycle
 * <p>
 * <b>frames_left</b> TextureRegion array used to store frames for left walk cycle
 * <p>
 * <b>frames_up</b> TextureRegion array used to store frames for up walk cycle
 * <p>
 * <b>frames_down</b> TextureRegion array used to store frames for down walk cycle
 * <p>
 * <b>game</b> Instance of Game used to store screen information from MainGame
 * <p>
 * <b>batch</b> Instance of SpriteBatch used as container for screen elements
 * <p>
 * <b>indianaJones</b> Instance of Sprite used to store a custom texture sprite
 * <p>
 * <b>column</b> Instance of Sprite used to store a custom texture sprite
 * <p>
 * <b>boulder</b> Instance of Sprite used to store a custom texture sprite
 * <p>
 * <b>body</b> Instance of Rectangle used to detect collision of main sprite
 * <p>
 * <b>collide</b> Instance of Rectangle used to detect collision of column sprite
 * <p>
 * <b>collideBoulder</b> Instance of Rectangle used to detect collision of boulder sprite
 * <p>
 * <b>interp</b> Instance of Rectangle used to prerender collision of sprites
 * <p>
 * <b>indianaText</b> Instance of Texture used to store texture for an image
 * <p>
 * <b>walk_right</b> Instance of Texture used to store sprite sheet for right walk cycle
 * <p>
 * <b>walk_left</b> Instance of Texture used to store sprite sheet for left walk cycle
 * <p>
 * <b>walk_up</b> Instance of Texture used to store sprite sheet for up walk cycle
 * <p>
 * <b>walk_down</b> Instance of Texture used to store sprite sheet for down walk cycle
 * <p>
 * <b>colText</b> Instance of Texture used to store column texture
 * <p>
 * <b>boulderText</b> Instance of Texture used to store boulder texture
 * <p>
 * <b>map</b> Instance of TiledMap used to store custom .tmx map file
 * <p>
 * <b>tmRender</b> Instance of TileMapRenderer used to render custom .tmx map file
 * <p>
 * <b>camera</b> Instance of OrthographicCamera used to display the map
 * <p>
 * <b>indianaX</b> float value used to store x-coord of main sprite
 * <p>
 * <b>indianaY</b> float value used to store y-coord of main sprite
 * <p>
 * <b>speed</b> float value used to determine move speed of main sprite (pixels per second)
 * <p>
 * <b>time</b> float value used to store duration of a frame (used for animation)
 * <p>
 * <b>catched</b> boolean value used to determine if cursor is visible
 * 
 */

package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import tween.SpriteManager;

public class GameScreen implements Screen {
	Animation animation_right;
	Animation animation_left;
	Animation animation_up;
	Animation animation_down;

	TextureRegion[] frames_right;
	TextureRegion[] frames_left;
	TextureRegion[] frames_up;
	TextureRegion[] frames_down;

	ShapeRenderer sr;

	MainGame game;

	SpriteBatch batch;

	Sprite indianaJones;
	Sprite column;
	Sprite boulder;
	Sprite pauseTextSprite;
	Sprite backSprite;
	Sprite quitSprite;
	Sprite darkBackSprite;
	Sprite darkQuitSprite;

	Rectangle body;
	Rectangle collide;
	Rectangle collideBoulder;
	Rectangle interp;
	Rectangle backRect;
	Rectangle quitRect;

	// Rectangle [] collisionArray = new Rectangle [100];
	ArrayList<Rectangle> collisionArray = new ArrayList<Rectangle>();

	Texture indianaText;
	Texture walk_right;
	Texture walk_left;
	Texture walk_up;
	Texture walk_down;
	Texture colText;
	Texture boulderText;
	Texture pauseOverlay;
	Texture pauseText;
	Texture backButton;
	Texture quitButton;
	Texture backDark;
	Texture quitDark;

	TiledMap map;
	TiledMapRenderer tmRender;

	TweenManager tweenManager;

	OrthographicCamera camera;

	float indianaX;
	float indianaY;
	float speed;
	float time;
	float interpY;

	boolean catched = true;
	boolean paused = false;
	boolean mapCollide = false;

	public GameScreen(SpriteBatch batch, MainGame game) {
		this.batch = batch;
		this.game = game;
		create();
	}

	/**
	 * The create() method is an overridden method that is used to instantiate
	 * and give values to all of the variables. Create() is called every time
	 * the application is run. The create() method also sets the cursor to
	 * remain within the window and be non visible with a built in GDX method
	 * setCursorCatched (). Two for loops are used to transfer the 2D array of
	 * TextureRegion to a 1D array of TextureRegion for animation.
	 */
	public void create() {

		sr = new ShapeRenderer();

		// set accessor
		Tween.registerAccessor(Sprite.class, new SpriteManager());

		// set tween manager
		tweenManager = new TweenManager();

		// pause overlay
		pauseOverlay = new Texture("pause_overlay.png");

		// pause text
		pauseText = new Texture("pause_text.png");
		pauseTextSprite = new Sprite(pauseText);

		// back button
		backButton = new Texture("backbutton.png");
		backSprite = new Sprite(backButton);
		backSprite.setPosition(Gdx.graphics.getWidth() / 2 - 145, 400f);
		backSprite.setSize(290f, 110f);

		// back dark button
		backDark = new Texture("back_dark.png");
		darkBackSprite = new Sprite(backDark);
		darkBackSprite.setPosition(Gdx.graphics.getWidth() / 2 - 145, 400f);
		darkBackSprite.setSize(290f, 110f);

		// back rectangle
		backRect = new Rectangle(backSprite.getX(), backSprite.getY(), backSprite.getWidth(), backSprite.getHeight());

		// quit button
		quitButton = new Texture("quit.png");
		quitSprite = new Sprite(quitButton);
		quitSprite.setPosition(Gdx.graphics.getWidth() / 2 - 145, 250f);
		quitSprite.setSize(290f, 110f);

		// quit dark button
		quitDark = new Texture("quit_dark.png");
		darkQuitSprite = new Sprite(quitDark);
		darkQuitSprite.setPosition(Gdx.graphics.getWidth() / 2 - 145, 250f);
		darkQuitSprite.setSize(290f, 110f);

		// quit rectangle
		quitRect = new Rectangle(quitSprite.getX(), quitSprite.getY(), quitSprite.getWidth(), quitSprite.getHeight());

		// stage = new Stage();

		Gdx.input.setCursorCatched(catched);

		// tiledmap renderer
		map = new TmxMapLoader().load("snow_map.tmx");
		tmRender = new OrthogonalTiledMapRenderer(map);

		// camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1216, 768);
		camera.update();

		colText = new Texture(Gdx.files.internal("column.png"));
		column = new Sprite(colText);

		boulderText = new Texture(Gdx.files.internal("boulder_0.png"));
		boulder = new Sprite(boulderText);

		indianaText = new Texture("indianajones.png");
		indianaJones = new Sprite(indianaText);

		indianaJones.setSize(32f, 64f);

		indianaX = 70;
		indianaY = 120;
		
		collide = column.getBoundingRectangle();
		collideBoulder = boulder.getBoundingRectangle();
		body = indianaJones.getBoundingRectangle();
		interp = new Rectangle(body.x, body.y, body.width, body.height-25);



		speed = 250.0f;

		walk_right = new Texture("right_walk.png");
		walk_left = new Texture("left_walk.png");
		walk_up = new Texture("up_walk.png");
		walk_down = new Texture("down_walk.png");

		TextureRegion[][] temp_right = TextureRegion.split(walk_right, 32, 64);
		frames_right = new TextureRegion[4];
		TextureRegion[][] temp_left = TextureRegion.split(walk_left, 32, 64);
		frames_left = new TextureRegion[4];
		TextureRegion[][] temp_up = TextureRegion.split(walk_up, 32, 64);
		frames_up = new TextureRegion[4];
		TextureRegion[][] temp_down = TextureRegion.split(walk_down, 32, 64);
		frames_down = new TextureRegion[4];

		int index = 0;
		// transfer 2D array of sprite sheet to 1d array for animation
		for (int rows = 0; rows < 2; rows++) {
			for (int col = 0; col < 2; col++) {
				frames_right[index] = temp_right[rows][col];
				frames_left[index] = temp_left[rows][col];
				frames_up[index] = temp_up[rows][col];
				frames_down[index] = temp_down[rows][col];
				index++;
			}
		}
		animation_up = new Animation(0.25f, frames_up);
		animation_left = new Animation(0.25f, frames_left);
		animation_right = new Animation(0.25f, frames_right);
		animation_down = new Animation(0.25f, frames_down);

		pauseTextSprite.setPosition(0, 780 - pauseTextSprite.getHeight());
		// pause text animation
		Tween.set(pauseTextSprite, SpriteManager.ALPHA).target(0.5f).start(tweenManager);
		Tween.to(pauseTextSprite, SpriteManager.ALPHA, 0.5f).target(1f).repeatYoyo(Tween.INFINITY, 0f)
		.start(tweenManager);
		Rectangle temp;
		// int indexRec = 0;
		for (MapObject object : map.getLayers().get("collision").getObjects()) {
			if (object instanceof RectangleMapObject) {
				// collisionArray[indexRec] =
				// ((RectangleMapObject)object).getRectangle();
				collisionArray.add(((RectangleMapObject) object).getRectangle());
			}
		}

	}

	/**
	 * Unused overridden method.
	 */
	@Override
	public void resize(int width, int height) {

	}

	/**
	 * Unused overridden method.
	 */
	@Override
	public void pause() {

	}

	/**
	 * Unused overridden method.
	 */
	@Override
	public void resume() {

	}

	/**
	 * Unused overridden method.
	 */
	@Override
	public void dispose() {
		game.dispose();
		indianaText.dispose();
		walk_right.dispose();
		walk_left.dispose();
		walk_up.dispose();
		walk_down.dispose();
		colText.dispose();
		boulderText.dispose();
		map.dispose();
	}

	/**
	 * Unused overridden method.
	 */
	@Override
	public void show() {

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

		Gdx.gl.glClearColor(.8f, .8f, .8f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		boulder.setPosition(500f, 300f);
		column.setPosition(250f, 250f);

		body.setPosition(indianaX, indianaY);
		collide.setPosition(250f, 250f);
		collideBoulder.setPosition(boulder.getX(), boulder.getY());

		// keep track of elapsed time
		time += Gdx.graphics.getDeltaTime();

		// set camera view
		tmRender.setView(camera);
		// render tile map
		tmRender.render();
		// update camera view
		camera.update();

		batch.begin();

		// draw sprites
		column.draw(batch);
		boulder.draw(batch);

		if (!paused) {
			gameUpdate();
		} else {
			if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
				paused = false;
				Gdx.input.setCursorCatched(!catched);
				catched = !catched;
			} else {
				batch.draw(indianaJones, (int) indianaX, (int) indianaY, 48f, 96f);
			}
		}

		if (paused) {

			batch.draw(pauseOverlay, 0, 0);
			pauseTextSprite.draw(batch);
			darkBackSprite.draw(batch);
			darkQuitSprite.draw(batch);

			if (backRect.contains(Gdx.input.getX(), Gdx.input.getY())) {
				quitSprite.draw(batch);
				if (Gdx.input.justTouched()) {
					game.setScreen(new MainMenu(batch, game));
				}
			}
			if (quitRect.contains(Gdx.input.getX(), Gdx.input.getY())) {
				backSprite.draw(batch);
				if (Gdx.input.justTouched()) {
					paused = false;
					Gdx.input.setCursorCatched(!catched);
					catched = !catched;
				}
			}

		}

		batch.end();

//		for (int x = 0; x < collisionArray.size(); x++) {
//			sr.begin(ShapeType.Filled);
//			sr.rect(collisionArray.get(x).x, collisionArray.get(x).y, collisionArray.get(x).width,
//					collisionArray.get(x).height);
//			sr.end();
//		}
	}

	public void gameUpdate() {

		interpY = indianaY;

		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			Gdx.input.setCursorCatched(!catched);
			catched = !catched;
			paused = true;
		}

		// interp.overlaps(r)

		if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) {
			
			interp.setPosition(indianaX + 4, interpY);
//			sr.begin (ShapeType.Filled);
//			sr.setColor(Color.RED);
//			sr.rect(interp.x,interp.y,interp.width,interp.height);
//			sr.end();
//			sr.setColor (Color.BLUE);
			
			for (int x = 0; x < collisionArray.size(); x++) {
				if (interp.overlaps(collisionArray.get(x))) {
					mapCollide = true;
					System.out.println("map collides!");
					break;
				} else {
					mapCollide = false;
				}
			}
			
			if (!interp.overlaps(collide) && !interp.overlaps(collideBoulder) && mapCollide == false) {
				indianaX += Gdx.graphics.getDeltaTime() * speed;
			}
			
			interp.setPosition(indianaX, interpY);
			
			if (indianaX + indianaJones.getWidth() >= 1200) {
				indianaX = 1200 - indianaJones.getWidth();
			}
			
			batch.draw(animation_right.getKeyFrame(time, true), (int) indianaX, (int) indianaY, 48f, 96f);
			
		} else if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) {
			
			interp.setPosition(indianaX - 4, interpY);
			
			for (int x = 0; x < collisionArray.size(); x++) {
				if (interp.overlaps(collisionArray.get(x))) {
					mapCollide = true;
					System.out.println("map collides!");
					break;


				} else {
					mapCollide = false;
				}
			}
			
			if (!interp.overlaps(collide) && !interp.overlaps(collideBoulder) && mapCollide == false) {
				indianaX -= Gdx.graphics.getDeltaTime() * speed;
			}
			
			interp.setPosition(indianaX, interpY);
			
			if (indianaX <= -17) {
				indianaX = -17;
			}
			
			batch.draw(animation_left.getKeyFrame(time, true), (int) indianaX, (int) indianaY, 48f, 96f);
			
		} else if (Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) {
			
			interp.setPosition(indianaX, interpY - 4);
			
			for (int x = 0; x < collisionArray.size(); x++) {
				if (interp.overlaps(collisionArray.get(x))) {
					mapCollide = true;
					System.out.println("map collides!");
					break;


				} else {
					mapCollide = false;
				}
			}
			
			if (!interp.overlaps(collide) && !interp.overlaps(collideBoulder) && mapCollide == false) {
				indianaY -= Gdx.graphics.getDeltaTime() * speed;
			}
			
			interp.setPosition(indianaX, interpY);
			
			if (indianaY <= 0) {
				indianaY = 0;
			}
			
			batch.draw(animation_down.getKeyFrame(time, true), (int) indianaX, (int) indianaY, 48f, 96f);
			
		} else if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.DPAD_UP)) {
			
			interp.setPosition(indianaX, interpY + 4);
			
			for (int x = 0; x < collisionArray.size(); x++) {
				if (interp.overlaps(collisionArray.get(x))) {
					mapCollide = true;
					System.out.println("map collides!");
					break;
				} else {
					mapCollide = false;
				}
			}
			
			if (!interp.overlaps(collide) && !interp.overlaps(collideBoulder) && mapCollide == false) {
				indianaY += Gdx.graphics.getDeltaTime() * speed;
			} else
				System.out.println("collides!!!");
			
			interp.setPosition(indianaX, interpY);
			
			if (indianaY + indianaJones.getHeight() >= 768) {
				indianaY = 768 - indianaJones.getHeight();
			}
			
			batch.draw(animation_up.getKeyFrame(time, true), (int) indianaX, (int) indianaY, 48f, 96f);
			
		} else {
			batch.draw(indianaJones, (int) indianaX, (int) indianaY, 48f, 96f);
		}
		
		System.out.println("interp y: " + interpY);
		System.out.println("indiana y: " + indianaY);
	}

	/**
	 * Unused overridden method.
	 */
	@Override
	public void hide() {
		this.dispose();
	}

}
