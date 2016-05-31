/**
 * The GameScreen class is used to display the main character sprite's movement,
 * as well as render the maps and collision for the game.
 * The class also includes a render of a custom made map, as well as
 * drawings of sprites. All of this is done on a single screen.
 * A looping method as well as a series of if statements (to check for
 * collision) are used to allow the sprite to traverse the screen.
 * It takes a 'sprite sheet', which is a grid of animation frames, and
 * splits it into a 2D array of type TextureRegion, which is then made into
 * a 1D array of the same type. That array, in conjunction with the built in
 * Animation class, is used to animate the sprite's walking cycle. For-each loops
 * are used to render the collision rectangles for the custom map files, as well
 * as for the map properties.
 * 
 * @author Brian Tran
 * @version 2.0 19.05.2016
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
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import tween.SpriteManager;

public class GameScreen implements Screen {
	Animation animation_right;
	Animation animation_left;
	Animation animation_up;
	Animation animation_down;

	Music desertMusic;
	Music earthMusic;
	Music iceMusic;

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
	Sprite boulder1Sprite;
	Sprite gameOverSprite;
	Sprite successSprite;
	Sprite scoreSprite;
	Sprite menuDarkSprite;
	Sprite menuSprite;
	Sprite goDarkSprite;
	Sprite goSprite;

	Rectangle body;
	Rectangle interp;
	Rectangle backRect;
	Rectangle quitRect;
	Rectangle endRect;
	Rectangle menuRect;
	Rectangle goRect;

	ArrayList<Rectangle> collisionArray = new ArrayList<Rectangle>();
	ArrayList<Rectangle> boulderArr = new ArrayList<Rectangle>();
	public static ArrayList<Integer>boulderXArr;
	public static ArrayList<Integer>boulderYArr;
	ArrayList <Integer> distanceArr;
	ArrayList <String> directionArr;

	ArrayList<TiledMap> mapList = new ArrayList<TiledMap>();

	Texture indianaText;
	Texture walk_right;
	Texture walk_left;
	Texture walk_up;
	Texture walk_down;
	Texture pauseOverlay;
	Texture pauseText;
	Texture backButton;
	Texture quitButton;
	Texture backDark;
	Texture quitDark;
	Texture boulder1;
	Texture gameOverText;
	Texture successText;
	Texture scoreText;
	Texture menuDarkText;
	Texture menuText;
	Texture goText;
	Texture goDarkText;

	Ellipse start;

	TiledMap currentMap;
	TiledMapRenderer tmRender;

	TweenManager tweenManager;

	OrthographicCamera camera;

	float indianaX;
	float indianaY;
	float speed;
	float time;
	float totalTime;
	float interpY;

	String direction;

	boolean catched = true;
	boolean paused = false;
	boolean mapCollide = false;
	boolean priority;
	boolean gameEnded;

	private int difficulty;
	private int timeSeconds;
	private int boulderX;
	private int boulderY;
	
	Boulder b;

	public GameScreen(SpriteBatch batch, MainGame game, int difficulty,int timeSeconds) {
		this.batch = batch;
		this.game = game;
		this.difficulty = difficulty;
		this.timeSeconds=timeSeconds;
		create();
	}

	/**
	 * The create() method is an overridden method that is used to instantiate
	 * and give values to all of the variables. Create() is called every time
	 * the application is run. The create() method also sets the cursor to
	 * remain within the window and be non visible with a built in GDX method
	 * setCursorCatched (). Multiple for loops are used to create animation
	 * frames as well as to import collision hitboxes from the map.
	 */
	public void create() {

		sr = new ShapeRenderer();

		MainGame.mainMusic.dispose();

		// set music
		desertMusic = Gdx.audio.newMusic(Gdx.files.internal("desert_music.mp3"));
		desertMusic.setLooping(true);

		earthMusic = Gdx.audio.newMusic(Gdx.files.internal("grass_theme.mp3"));
		earthMusic.setLooping(true);

		iceMusic = Gdx.audio.newMusic(Gdx.files.internal("ice_theme.mp3"));
		iceMusic.setLooping(true);

		musicPlay();

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
		// fills array with maps
		occupyArray("dust_map.tmx");
		occupyArray("dust_map2.tmx");
		occupyArray("earth_map.tmx");
		occupyArray("earth_map2.tmx");
		occupyArray("snow_map.tmx");
		occupyArray("snow_map2.tmx");

		// sets current map based on difficulty
		setCurrentMap(difficulty);
		
		// create boulder object
		b = new Boulder (currentMap);
		
		//occupy x array
		boulderXArr = b.getStartX();
		
		//occupy y array
		boulderYArr = b.getStartY();
		
		//occupy distance array
		distanceArr = b.getDistance();
		
		//occupy direction array
		directionArr = b.getDirection();

		// create game over text
		gameOverText = new Texture("gameover_text.png");

		gameOverSprite = new Sprite(gameOverText);

		// create success text
		successText = new Texture("levelcomplete_text.png");

		successSprite = new Sprite(successText);
		successSprite.setPosition(0, Gdx.graphics.getHeight() - (successSprite.getHeight() - 85));

		// create scoreboard
		scoreText = new Texture("score_panel.png");

		scoreSprite = new Sprite(scoreText);
		scoreSprite.setPosition((Gdx.graphics.getWidth() / 2) - (scoreSprite.getWidth() / 2), 120);

		// create menu button
		menuDarkText = new Texture("menu_dark.png");
		menuText = new Texture("menu.png");

		menuDarkSprite = new Sprite(menuDarkText);
		menuDarkSprite.setPosition((Gdx.graphics.getWidth() / 5) - (menuDarkSprite.getWidth() / 2), 5);
		menuDarkSprite.setSize(290f, 110f);

		menuSprite = new Sprite(menuText);
		menuSprite.setPosition((Gdx.graphics.getWidth() / 5) - (menuSprite.getWidth() / 2), 5);
		menuSprite.setSize(290f, 110f);

		menuRect = menuDarkSprite.getBoundingRectangle();

		// create go button
		goDarkText = new Texture("go_dark.png");
		goText = new Texture("go.png");

		goDarkSprite = new Sprite(goDarkText);
		goDarkSprite.setPosition((3 * Gdx.graphics.getWidth() / 5), 5);
		goDarkSprite.setSize(290f, 110f);

		goSprite = new Sprite(goText);
		goSprite.setPosition((3 * Gdx.graphics.getWidth() / 5), 5);
		goSprite.setSize(290f, 110f);

		goRect = goDarkSprite.getBoundingRectangle();

		indianaText = new Texture("indianajones.png");
		indianaJones = new Sprite(indianaText);

		indianaJones.setSize(32f, 64f);

		// indianaX = start.x;
		// indianaY = start.y;
		createMap();

		body = indianaJones.getBoundingRectangle();
		interp = new Rectangle(body.x, body.y, body.width, body.height - 25);

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

		// success animation
		Tween.set(successSprite, SpriteManager.ALPHA).target(0.5f).start(tweenManager);
		Tween.to(successSprite, SpriteManager.ALPHA, 0.3f).target(1f).repeatYoyo(Tween.INFINITY, 0f)
				.start(tweenManager);

		
		// for (MapObject object :
		// currentMap.getLayers().get("collision").getObjects()) {
		// if (object instanceof RectangleMapObject) {
		// collisionArray.add(((RectangleMapObject) object).getRectangle());
		// }
		// }

//		Boulder b = new Boulder(currentMap);
//		ArrayList <Integer> arrList = b.getDistance();
//		for (int x : arrList){
//			System.out.println("integers: "+x);
//		}
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
	 * This method disposes of all unused resources in order to prevent more
	 * memory from being taken up.
	 */
	@Override
	public void dispose() {
		desertMusic.dispose();
		earthMusic.dispose();
		iceMusic.dispose();
		game.dispose();
		indianaText.dispose();
		walk_right.dispose();
		walk_left.dispose();
		walk_up.dispose();
		walk_down.dispose();
		currentMap.dispose();
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

		Gdx.input.setCursorCatched(catched);
		
		tweenManager.update(delta);

		Gdx.gl.glClearColor(.8f, .8f, .8f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		body.setPosition(indianaX, indianaY);
		float tempTime = time;
		// keep track of elapsed time
		if (!paused)
			time += Gdx.graphics.getDeltaTime();
		if ((int) tempTime != (int) time)
			System.out.println((int) time);

		// set camera view
		tmRender.setView(camera);
		// render tile map
		tmRender.render();
		// update camera view
		camera.update();

		batch.begin();
		
		// draw boulders
		if (!paused){
			for (int x = 0; x < boulderArr.size(); x++) {
				System.out.println("direction: "+directionArr.get(x));
				System.out.println("distance: "+distanceArr.get(x));
				b.update(x, distanceArr.get(x).intValue(), directionArr.get(x));
				System.out.println ("x coord "+x+": "+boulderXArr.get(x));
				System.out.println("y coord "+x+": "+boulderYArr.get(x));
				boulder1Sprite.setPosition(boulderXArr.get(x), boulderYArr.get(x));
				boulder1Sprite.draw(batch);
			}
		}

		if (!paused && !gameEnded) {
			gameUpdate();
		} else if (paused) {
			batch.draw(indianaJones, (int) indianaX, (int) indianaY, 48f, 96f);
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
			if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
				paused = false;
				Gdx.input.setCursorCatched(!catched);
				catched = !catched;
			}
		} else {
			drawOverlay();
		}

		batch.end();
	}

//	private void drawBoulder(int boulderNum,String direction, int distance,String rotation){
//	if (direction.equals("up"))
//			batch.draw(boulder1, (int) boulderXArr.get(boulderNum),(int)boulderYArr.get(boulderNum),64f,64f);
//	}
	
//	private boolean boulderCheck() {
//		return true;
//	}

	/**
	 * The gameUpdate method takes in user input and updates the player's
	 * current location based on the keys the user enters. This method is called
	 * everytime the render method runs, as long as the game is not in a paused
	 * state.
	 */
	public void gameUpdate() {

		interpY = indianaY;
		priority = false;
		direction = "none";
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			Gdx.input.setCursorCatched(!catched);
			catched = !catched;
			paused = true;
		}

		if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) {
			interp.setPosition(indianaX + 4, interpY);
			if (!collidesWithMap()) {
				indianaX += Gdx.graphics.getDeltaTime() * speed;
			}
			endCheck();

			interp.setPosition(indianaX, interpY);

			if (indianaX + indianaJones.getWidth() >= 1200) {
				indianaX = 1200 - indianaJones.getWidth();
			}
			if (!priority)
				direction = "right";
			priority = true;

		}
		if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) {

			interp.setPosition(indianaX - 4, interpY);

			if (!collidesWithMap()) {
				indianaX -= Gdx.graphics.getDeltaTime() * speed;
			}
			endCheck();

			interp.setPosition(indianaX, interpY);

			if (indianaX <= -17) {
				indianaX = -17;
			}
			if (!priority)
				direction = "left";
			priority = true;

		}
		if (Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) {

			interp.setPosition(indianaX, interpY - 4);

			if (!collidesWithMap()) {
				indianaY -= Gdx.graphics.getDeltaTime() * speed;
			}
			endCheck();

			interp.setPosition(indianaX, interpY);

			if (indianaY <= 0) {
				indianaY = 0;
			}
			if (!priority)
				direction = "down";
			priority = true;

		}
		if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.DPAD_UP)) {

			interp.setPosition(indianaX, interpY + 4);

			if (!collidesWithMap()) {
				indianaY += Gdx.graphics.getDeltaTime() * speed;
			}
			endCheck();

			interp.setPosition(indianaX, interpY);

			if (indianaY + indianaJones.getHeight() >= 768) {
				indianaY = 768 - indianaJones.getHeight();
			}
			if (!priority)
				direction = "up";
			priority = true;

		} else {
			if (!priority)
				direction = "none";
			priority = true;

		}
		if (gameEnded) {
			totalTime = time;
			System.out.println("total time: "+totalTime);
			drawOverlay();
		}
		priorityDraw();
	}

	/**
	 * This method uses a for loop and an if statement to determine if the
	 * user's walking hitbox collides with the map boundaries. If the user
	 * collides with the map, a result of true is returned.
	 * 
	 * @return result boolean based on if player collides with map
	 */
	private boolean collidesWithMap() {
		boolean result = false;
		for (int x = 0; x < collisionArray.size(); x++) {
			if (interp.overlaps(collisionArray.get(x))) {
				result = true;
				break;
			}
		}
		return result;
	}

	private boolean mapEnds() {
		boolean result = false;
		if (interp.overlaps(endRect)) {
			result = true;
		}
		return result;
	}

	/**
	 * The priority draw method is called every render loop. This method
	 * ensures, through a series of if statements, that if the user inputs
	 * multiple keys at once, only the first key's animation will play, instead
	 * of having multiple walking animations play at the same time.
	 */
	private void priorityDraw() {
		if (direction.equals("up"))
			batch.draw(animation_up.getKeyFrame(time, true), (int) indianaX, (int) indianaY, 48f, 96f);
		else if (direction.equals("down"))
			batch.draw(animation_down.getKeyFrame(time, true), (int) indianaX, (int) indianaY, 48f, 96f);
		else if (direction.equals("left"))
			batch.draw(animation_left.getKeyFrame(time, true), (int) indianaX, (int) indianaY, 48f, 96f);
		else if (direction.equals("right"))
			batch.draw(animation_right.getKeyFrame(time, true), (int) indianaX, (int) indianaY, 48f, 96f);
		else if (direction.equals("none"))
			batch.draw(indianaJones, (int) indianaX, (int) indianaY, 48f, 96f);
	}

	/**
	 * This method calls the dispose method once the screen has been changed.
	 */
	@Override
	public void hide() {
		this.dispose();
	}

	/**
	 * The occupyArray method adds TileMaps to the TileMap ArrayList. This
	 * allows for easy traversing through each of the maps.
	 * 
	 * @param path
	 *            Path of the map file.
	 */
	private void occupyArray(String path) {
		TiledMap map = new TmxMapLoader().load(path);
		mapList.add(map);
	}

	/**
	 * The setCurrentMap method sets the current game map based on the user's
	 * entered difficulty. It does this with an index variable used to traverse
	 * the ArrayList of maps.
	 * 
	 * @param index
	 *            integer used to traverse ArrayList
	 */
	private void setCurrentMap(int index) {
		currentMap = mapList.get(index);
	}

	private void endCheck() {
		if (mapEnds() && difficulty % 2 == 0) {
			difficulty++;
			setCurrentMap(difficulty);
			b = new Boulder (currentMap);
			boulder1.dispose();
			createMap();
			System.out.println("stage end");
		} else if (mapEnds() && difficulty % 2 != 0) {
			System.out.println("level complete");
			gameEnded = true;
			game.getSaveManager().getSave().setName("let user input name here");
			game.getSaveManager().getSave().setDifficulty(difficulty);
			game.getSaveManager().getSave().setTimeSeconds(timeSeconds+(int)totalTime);
		}
	}

	private void drawOverlay() {
		Gdx.input.setCursorCatched(false);

		batch.draw(pauseOverlay, 0, 0);
		successSprite.draw(batch);
		scoreSprite.draw(batch);
		menuDarkSprite.draw(batch);
		goDarkSprite.draw(batch);

		if (menuRect.contains(Gdx.input.getX(), Gdx.input.getY() - 610)) {
			menuSprite.draw(batch);
			if (Gdx.input.justTouched()) {
				game.setScreen(new MainMenu(batch, game));
			}
		}

		if (goRect.contains(Gdx.input.getX(), Gdx.input.getY() - 610)) {
			goSprite.draw(batch);
			if (Gdx.input.justTouched()) {
				catched = true;
				difficulty++;
				gameEnded = false;
				setCurrentMap(difficulty);
				musicPlay();
				createMap();
			}
		}
	}

	private void createMap() {

		b = new Boulder (currentMap);
		
		tmRender = new OrthogonalTiledMapRenderer(currentMap);

		boulderArr.clear();
		
		boulderXArr.clear();
		
		boulderYArr.clear();
		
		distanceArr.clear();
		
		directionArr.clear();
		
		collisionArray = new ArrayList<Rectangle>();
		
		// set collision
		for (MapObject object : currentMap.getLayers().get("collision").getObjects()) {
			if (object instanceof RectangleMapObject) {
				collisionArray.add(((RectangleMapObject) object).getRectangle());
			}
		}

		boulderArr = b.getBoulders();
		
		boulderXArr = b.getStartX();
		
		boulderYArr = b.getStartY();
		
		distanceArr = b.getDistance();
		
		directionArr = b.getDirection();

		boulder1 = new Texture("boulder_1.png");
		boulder1Sprite = new Sprite(boulder1);
		boulder1Sprite.setSize(64f, 64f);

		// set player start position
		for (MapObject object : currentMap.getLayers().get("start_end").getObjects()) {
			if (object instanceof EllipseMapObject) {
				start = ((EllipseMapObject) object).getEllipse();
			}
			if (object instanceof RectangleMapObject) {
				endRect = ((RectangleMapObject) object).getRectangle();
			}
		}

		// camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1200, 768);
		camera.update();

		// set start position
		indianaX = start.x;
		indianaY = start.y;

	}
	
	public void musicPlay(){
		if (difficulty == 0) {
			desertMusic.play();
			earthMusic.stop();
			iceMusic.stop();
		} else if (difficulty == 2) {
			earthMusic.play();
			desertMusic.stop();
			iceMusic.stop();
		} else {
			iceMusic.play();
			earthMusic.stop();
			desertMusic.stop();
		}
	}

}
