

package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
 * @version 4.0 08.06.2016
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
 * <b>pauseTextSprite</b> Instance of Sprite used to store a custom texture sprite
 * <p>
 * <b>backSprite</b> Instance of Sprite used to store a custom texture sprite
 * <p>
 * <b>quitSprite</b> Instance of Sprite used to store a custom texture sprite
 * <p>
 * <b>darkBackSprite</b> Instance of Sprite used to store a custom texture sprite
 * <p>
 * <b>darkQuitSprite</b> Instance of Sprite used to store a custom texture sprite
 * <p>
 * <b>boulder1Sprite</b> Instance of Sprite used to store a custom texture sprite
 * <p>
 * <b>successSprite</b> Instance of Sprite used to store a custom texture sprite
 * <p>
 * <b>scoreSprite</b> Instance of Sprite used to store a custom texture sprite
 * <p>
 * <b>menuDarkSprite</b> Instance of Sprite used to store a custom texture sprite
 * <p>
 * <b>menuSprite</b> Instance of Sprite used to store a custom texture sprite
 * <p>
 * <b>goDarkSprite</b> Instance of Sprite used to store a custom texture sprite
 * <p>
 * <b>goSprite</b> Instance of Sprite used to store a custom texture sprite
 * <p>
 * <b>treasureSprite</b> Instance of Sprite used to store a custom texture sprite
 * <p>
 * <b>treasureOverlaySprite</b> Instance of Sprite used to store a custom texture sprite
 * <p>
 * <b>body</b> Instance of Rectangle used to detect collision of main sprite
 * <p>
 * <b>interp</b> Instance of Rectangle used to prerender collision of sprites
 * <p>
 * <b>backRect</b> Instance of Rectangle used to detect mouse input with back button
 * <p>
 * <b>quitRect</b> Instance of Rectangle used to detect mouse input with quit button
 * <p>
 * <b>endRect</b> Instance of Rectangle used to player collision with level end
 * <p>
 * <b>menuRect</b> Instance of Rectangle used to detect mouse input with menu button
 * <p>
 * <b>goRect</b> Instance of Rectangle used to detect mouse input with go button
 * <p>
 * <b>collisionArray</b> ArrayList of Rectangle used to store collision boxes of map
 * <p>
 * <b>boulderArr</b> ArrayList of Rectangle used to store boulder locations and properties
 * <p>
 * <b>boulderXArr</b> ArrayList of Integer used to store x-coordinates of all boulders on the map
 * <p>
 * <b>boulderYArr</b> ArrayList of Integer used to store y-coordinates of all boulders on the map
 * <p>
 * <b>distanceArr</b> ArrayList of Integer used to store max distance traveled of all boulders on the map
 * <p>
 * <b>directionArr</b> ArrayList of String used to store direction of boulder travel of all boulders on the map
 * <p>
 * <b>mapList</b> ArrayList of TiledMap used to store game maps
 * <p>
 * <b>Textures</b>
 * <p>
 * <b>pauseOverlay</b> Instance of Texture used to store texture for pause screen overlay
 * <p>
 * <b>pauseText</b> Instance of Texture used to store texture for pause text
 * <p>
 * <b>backButton</b> Instance of Texture used to store texture for back button
 * <p>
 * <b>quitButton</b> Instance of Texture used to store texture for quit button
 * <p>
 * <b>boulder1</b> Instance of Texture used to store texture for boulder
 * <p>
 * <b>successText</b> Instance of Texture used to store level end text
 * <p>
 * <b>scoreText</b> Instance of Texture used to store score text
 * <p>
 * <b>menuDarkText</b> Instance of Texture used to store dark menu button
 * <p>
 * <b>menuText</b> Instance of Texture used to store menu button
 * <p>
 * <b>goText</b> Instance of Texture used to store go button
 * <p>
 * <b>goDarkText</b> Instance of Texture used to store dark go button
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
 * <b>treasureText</b> Instance of Texture used to store texture for treasure item
 * <p>
 * <b>treasureOverlayText</b> Instance of Texture used to store texture for treasure overlay prompt
 * <p>
 * <b>start</b> Instance of Ellipse used to store player start position
 * <p>
 * <b>currentMap</b> Instance of TiledMap used to store the current custom .tmx map file
 * <p>
 * <b>tmRender</b> Instance of TileMapRenderer used to render custom .tmx map file
 * <p>
 * <b>tweenManager</b> Instance of TweenManager used to manage sprite animations
 * <p>
 * <b>camera</b> Instance of OrthographicCamera used to display the map
 * <p>
 * <b>totalTime</b> float value representing total time user took to complete the level
 * <p>
 * <b>interpY</b> float value representing y-coord of interp collision box
 * <p>
 * <b>indianaX</b> float value used to store x-coord of main sprite
 * <p>
 * <b>indianaY</b> float value used to store y-coord of main sprite
 * <p>
 * <b>speed</b> float value used to determine move speed of main sprite (pixels per second)
 * <p>
 * <b>time</b> float value used to store duration of a frame (used for animation)
 * <p>
 * <b>direction</b> String value representing direction of player movement
 * <p>
 * <b>name</b> String value representing user's name
 * <p>
 * <b>paused</b> boolean value used to determine if game is paused
 * <p>
 * <b>priority</b> boolean value used to determine whether or not priorityDraw() method is called
 * <p>
 * <b>gameEnded</b> boolean value used to determine if game has ended
 * <p>
 * <b>catched</b> boolean value used to determine if cursor is visible
 * <p>
 * <b>retrieved</b> boolean value used to determine if treasure has been retrieved
 * <p>
 * <b>timeSeconds</b> int value representing amount of time elapsed
 * <p>
 * <b>difficulty</b> int value representing the level difficulty
 * <p>
 * <b>b</b> Instance of Boulder class used to update and draw boulder locations
 * <p>
 * <b>desertMusic</b> Instance of Music used to stream music
 * <p>
 * <b>earthMusic</b> Instance of Music used to stream music
 * <p>
 * <b>iceMusic</b> Instance of Music used to stream music
 * <p>
 * <b>timerFont</b> Instance of BitmapFont used to display custom windows fonts for the timer.
 * <p>
 * <b>dialogFont</b> Instance of BitmapFont used to display custom windows fonts for dialog boxes.
 */
public class GameScreen implements Screen {
	private Animation animation_right;
	private Animation animation_left;
	private Animation animation_up;
	private Animation animation_down;
	private Animation boulder_up;
	private Animation boulder_down;
	private Animation boulder_left;
	private Animation boulder_right;

	private Music desertMusic;
	private Music earthMusic;
	private Music iceMusic;

	private BitmapFont timerFont;
	private BitmapFont dialogFont;

	private TextureRegion[] frames_right;
	private TextureRegion[] frames_left;
	private TextureRegion[] frames_up;
	private TextureRegion[] frames_down;
	private TextureRegion[] roller_up;
	private TextureRegion[] roller_down;
	private TextureRegion[] roller_left;
	private TextureRegion[] roller_right;
	

	private MainGame game;

	private SpriteBatch batch;

	private Sprite indianaJones;
	private Sprite pauseTextSprite;
	private Sprite backSprite;
	private Sprite quitSprite;
	private Sprite darkBackSprite;
	private Sprite darkQuitSprite;
	private Sprite boulder1Sprite;
	private Sprite successSprite;
	private Sprite scoreSprite;
	private Sprite menuDarkSprite;
	private Sprite menuSprite;
	private Sprite goDarkSprite;
	private Sprite goSprite;
	private Sprite treasureSprite;
	private Sprite treasureOverlaySprite;
	private Sprite saveSprite;
	private Sprite saveDarkSprite;

	private Rectangle body;
	private Rectangle interp;
	private Rectangle backRect;
	private Rectangle quitRect;
	private Rectangle endRect;
	private Rectangle menuRect;
	private Rectangle saveRect;
	private Rectangle goRect;

	private ArrayList<Rectangle> collisionArray = new ArrayList<Rectangle>();
	private ArrayList<Rectangle> boulderArr = new ArrayList<Rectangle>();
	public static ArrayList<Integer> boulderXArr;
	public static ArrayList<Integer> boulderYArr;
	private ArrayList<Integer> distanceArr;
	private ArrayList<String> directionArr;

	private ArrayList<TiledMap> mapList = new ArrayList<TiledMap>();

	private Texture indianaText;
	private Texture walk_right;
	private Texture walk_left;
	private Texture walk_up;
	private Texture walk_down;
	private Texture pauseOverlay;
	private Texture pauseText;
	private Texture backButton;
	private Texture quitButton;
	private Texture backDark;
	private Texture quitDark;
	private Texture boulder1;
	private Texture successText;
	private Texture scoreText;
	private Texture menuDarkText;
	private Texture menuText;
	private Texture goText;
	private Texture goDarkText;
	private Texture treasureText;
	private Texture treasureOverlayText;
	private Texture saveButton;
	private Texture saveDark;
	private Texture roll_up;
	private Texture roll_down;
	private Texture roll_left;
	private Texture roll_right;

	private Ellipse start;

	private TiledMap currentMap;
	private TiledMapRenderer tmRender;

	private TweenManager tweenManager;

	private OrthographicCamera camera;

	private float indianaX;
	private float indianaY;
	private float speed;
	private float time;
	private float totalTime;
	private float interpY;

	private String direction;
	private String name;

	private boolean catched = true;
	private boolean paused = false;
	private boolean priority;
	private boolean gameEnded;
	private boolean retrieved;

	private int difficulty;
	private int timeSeconds;

	private Boulder b;

	private Rectangle[] boulderCollision;

	/**
	 * The GameScreen constructor is responsible for taking values in from other
	 * classes in order to be used.
	 * 
	 * @param batch
	 *            SpriteBatch used for rendering screen elements
	 * @param game
	 *            MainGame used for switching between screens
	 * @param name
	 *            String used for username storage
	 * @param difficulty
	 *            int used for difficulty selection
	 * @param timeSeconds
	 *            int used for time storage
	 */
	public GameScreen(SpriteBatch batch, MainGame game, String name, int difficulty, int timeSeconds) {
		this.batch = batch;
		this.game = game;
		this.name = name;
		this.difficulty = difficulty;
		this.timeSeconds = timeSeconds;
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
		currentMap.dispose();
		dialogFont.dispose();

		indianaText.dispose();
		walk_right.dispose();
		walk_left.dispose();
		walk_up.dispose();
		walk_down.dispose();
		pauseOverlay.dispose();
		pauseText.dispose();
		backButton.dispose();
		quitButton.dispose();
		saveButton.dispose();
		saveDark.dispose();
		backDark.dispose();
		quitDark.dispose();
		boulder1.dispose();
		successText.dispose();
		scoreText.dispose();
		menuDarkText.dispose();
		menuText.dispose();
		goText.dispose();
		goDarkText.dispose();
		treasureText.dispose();
		treasureOverlayText.dispose();
	}

	/**
	 * The show() method is an overridden method from the Screen interface. This
	 * method is run once at the beginning of the application run time. This
	 * method is used to create and initialize variables as well as run settings
	 * that should be run at the beginning of the application, such as the
	 * playing of music. First and second for loop is used to fill the texture
	 * region arrays with images from the spritesheet to be used for animation.
	 */
	@Override
	public void show() {
		MainGame.mainMusic.dispose();

		// set music
		desertMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/desert_music.mp3"));
		desertMusic.setLooping(true);

		earthMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/grass_theme.mp3"));
		earthMusic.setLooping(true);

		iceMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/ice_theme.mp3"));
		iceMusic.setLooping(true);

		musicPlay();

		// create font
		timerFont = new BitmapFont(Gdx.files.internal("assets/8bit_bitmap.fnt"),
				Gdx.files.internal("assets/8bit_bitmap_0.png"), false);
		dialogFont = new BitmapFont(Gdx.files.internal("assets/riskofrain_bitmap.fnt"),
				Gdx.files.internal("assets/riskofrain_bitmap_0.png"), false);

		// set accessor
		Tween.registerAccessor(Sprite.class, new SpriteManager());

		// set tween manager
		tweenManager = new TweenManager();

		// pause overlay
		pauseOverlay = new Texture("assets/pause_overlay.png");

		// pause text
		pauseText = new Texture("assets/pause_text.png");
		pauseTextSprite = new Sprite(pauseText);

		// back button
		backButton = new Texture("assets/backbutton.png");
		backSprite = new Sprite(backButton);
		backSprite.setPosition(Gdx.graphics.getWidth() / 2 - 145, 400f);
		backSprite.setSize(290f, 110f);

		// back dark button
		backDark = new Texture("assets/back_dark.png");
		darkBackSprite = new Sprite(backDark);
		darkBackSprite.setPosition(Gdx.graphics.getWidth() / 2 - 145, 400f);
		darkBackSprite.setSize(290f, 110f);

		// back rectangle
		backRect = new Rectangle(backSprite.getX(), backSprite.getY(), backSprite.getWidth(), backSprite.getHeight());

		// save button
		saveButton = new Texture("assets/save.png");
		saveSprite = new Sprite(saveButton);
		saveSprite.setPosition(Gdx.graphics.getWidth() / 2 - 145, 250f);
		saveSprite.setSize(290f, 110f);

		// save dark button
		saveDark = new Texture("assets/save_dark.png");
		saveDarkSprite = new Sprite(saveDark);
		saveDarkSprite.setPosition(Gdx.graphics.getWidth() / 2 - 145, 250f);
		saveDarkSprite.setSize(290f, 110f);

		// save rectangle
		saveRect = new Rectangle(saveSprite.getX(), saveSprite.getY(), saveSprite.getWidth(), saveSprite.getHeight());

		// quit button
		quitButton = new Texture("assets/quit.png");
		quitSprite = new Sprite(quitButton);
		quitSprite.setPosition(Gdx.graphics.getWidth() / 2 - 145, 100f);
		quitSprite.setSize(290f, 110f);

		// quit dark button
		quitDark = new Texture("assets/quit_dark.png");
		darkQuitSprite = new Sprite(quitDark);
		darkQuitSprite.setPosition(Gdx.graphics.getWidth() / 2 - 145, 100f);
		darkQuitSprite.setSize(290f, 110f);

		// quit rectangle
		quitRect = new Rectangle(quitSprite.getX(), quitSprite.getY(), quitSprite.getWidth(), quitSprite.getHeight());

		// create treasure
		treasureText = new Texture("assets/Oscar.png");
		treasureSprite = new Sprite(treasureText);

		treasureSprite.setPosition(900f, 100f);

		Gdx.input.setCursorCatched(catched);

		// tiledmap renderer
		// fills array with maps
		occupyArray("assets/dust_map.tmx");
		occupyArray("assets/dust_map2.tmx");
		occupyArray("assets/earth_map.tmx");
		occupyArray("assets/earth_map2.tmx");
		occupyArray("assets/snow_map.tmx");
		occupyArray("assets/snow_map2.tmx");

		treasureOverlayText = new Texture("assets/treasure_overlay.png");
		treasureOverlaySprite = new Sprite(treasureOverlayText);

		// sets current map based on difficulty
		setCurrentMap(difficulty);

		// create boulder object
		b = new Boulder(currentMap);

		// occupy x array
		boulderXArr = b.getStartX();

		// occupy y array
		boulderYArr = b.getStartY();

		// occupy distance array
		distanceArr = b.getDistance();

		// occupy direction array
		directionArr = b.getDirection();

		// create boulder collision array
		boulderCollision = new Rectangle[directionArr.size()];

		// create success text
		successText = new Texture("assets/levelcomplete_text.png");

		successSprite = new Sprite(successText);
		successSprite.setPosition(0, Gdx.graphics.getHeight() - (successSprite.getHeight() - 85));

		// create scoreboard
		scoreText = new Texture("assets/score_panel.png");

		scoreSprite = new Sprite(scoreText);
		scoreSprite.setPosition((Gdx.graphics.getWidth() / 2) - (scoreSprite.getWidth() / 2), 120);

		// create menu button
		menuDarkText = new Texture("assets/menu_dark.png");
		menuText = new Texture("assets/menu.png");

		menuDarkSprite = new Sprite(menuDarkText);
		menuDarkSprite.setPosition((Gdx.graphics.getWidth() / 5) - (menuDarkSprite.getWidth() / 2), 5);
		menuDarkSprite.setSize(290f, 110f);

		menuSprite = new Sprite(menuText);
		menuSprite.setPosition((Gdx.graphics.getWidth() / 5) - (menuSprite.getWidth() / 2), 5);
		menuSprite.setSize(290f, 110f);

		menuRect = menuDarkSprite.getBoundingRectangle();

		// create go button
		goDarkText = new Texture("assets/go_dark.png");
		goText = new Texture("assets/go.png");

		goDarkSprite = new Sprite(goDarkText);
		goDarkSprite.setPosition((3 * Gdx.graphics.getWidth() / 5), 5);
		goDarkSprite.setSize(290f, 110f);

		goSprite = new Sprite(goText);
		goSprite.setPosition((3 * Gdx.graphics.getWidth() / 5), 5);
		goSprite.setSize(290f, 110f);

		goRect = goDarkSprite.getBoundingRectangle();

		indianaText = new Texture("assets/indianajones.png");
		indianaJones = new Sprite(indianaText);

		indianaJones.setSize(32f, 64f);

		// indianaX = start.x;
		// indianaY = start.y;
		createMap();

		body = indianaJones.getBoundingRectangle();
		interp = new Rectangle(body.x, body.y, body.width, body.height - 25);

		speed = 250.0f;

		walk_right = new Texture("assets/right_walk.png");
		walk_left = new Texture("assets/left_walk.png");
		walk_up = new Texture("assets/up_walk.png");
		walk_down = new Texture("assets/down_walk.png");
		
		roll_up = new Texture("assets/roll_up.png");
		roll_down = new Texture("assets/roll_down.png");
		roll_left = new Texture("assets/roll_left.png");
		roll_right = new Texture("assets/roll_right.png");

		TextureRegion[][] temp_right = TextureRegion.split(walk_right, 32, 64);
		frames_right = new TextureRegion[4];
		TextureRegion[][] temp_left = TextureRegion.split(walk_left, 32, 64);
		frames_left = new TextureRegion[4];
		TextureRegion[][] temp_up = TextureRegion.split(walk_up, 32, 64);
		frames_up = new TextureRegion[4];
		TextureRegion[][] temp_down = TextureRegion.split(walk_down, 32, 64);
		frames_down = new TextureRegion[4];
		
		TextureRegion[][] temp_roll_up = TextureRegion.split(roll_up, 32, 32);
		roller_up = new TextureRegion[4];
		TextureRegion[][] temp_roll_down = TextureRegion.split(roll_down, 32, 32);
		roller_down = new TextureRegion[4];
		TextureRegion[][] temp_roll_left = TextureRegion.split(roll_left, 32, 32);
		roller_left = new TextureRegion[4];
		TextureRegion[][] temp_roll_right = TextureRegion.split(roll_right, 32, 32);
		roller_right = new TextureRegion[4];

		int index = 0;
		// transfer 2D array of sprite sheet to 1d array for animation
		for (int rows = 0; rows < 2; rows++) {
			for (int col = 0; col < 2; col++) {
				frames_right[index] = temp_right[rows][col];
				frames_left[index] = temp_left[rows][col];
				frames_up[index] = temp_up[rows][col];
				frames_down[index] = temp_down[rows][col];
				roller_up[index]=temp_roll_up[rows][col];
				roller_down[index]=temp_roll_down[rows][col];
				roller_left[index]=temp_roll_left[rows][col];
				roller_right[index]=temp_roll_right[rows][col];
				index++;
			}
		}
		animation_up = new Animation(0.13f, frames_up);
		animation_left = new Animation(0.13f, frames_left);
		animation_right = new Animation(0.13f, frames_right);
		animation_down = new Animation(0.13f, frames_down);
		
		boulder_up=new Animation(0.07f, roller_up);
		boulder_down=new Animation(0.07f, roller_down);
		boulder_left=new Animation(0.07f, roller_left);
		boulder_right=new Animation(0.07f, roller_right);

		pauseTextSprite.setPosition(0, 780 - pauseTextSprite.getHeight());

		// pause text animation
		Tween.set(pauseTextSprite, SpriteManager.ALPHA).target(0.5f).start(tweenManager);
		Tween.to(pauseTextSprite, SpriteManager.ALPHA, 0.5f).target(1f).repeatYoyo(Tween.INFINITY, 0f)
		.start(tweenManager);

		// success animation
		Tween.set(successSprite, SpriteManager.ALPHA).target(0.5f).start(tweenManager);
		Tween.to(successSprite, SpriteManager.ALPHA, 0.3f).target(1f).repeatYoyo(Tween.INFINITY, 0f)
		.start(tweenManager);

		// treasure animation
		Tween.set(treasureSprite, SpriteManager.ALPHA).target(0.5f).start(tweenManager);
		Tween.to(treasureSprite, SpriteManager.ALPHA, 0.3f).target(1f).repeatYoyo(Tween.INFINITY, 0f)
		.start(tweenManager);
	}

	/**
	 * The render() method is an overridden method from the GDX library. The
	 * render method is run every frame, and as a result, updates the screen
	 * every frame with new information. This allows for animations of sprites
	 * and such to be easier. Things are displayed on the screen using a
	 * SpriteBatch, which acts as a container of sorts for the screen elements.
	 * First if statement is used to save the game state when the keyboard
	 * shortcut Ctrl + S is used. Second if statement is used to increment the
	 * timer when the game is not paused or finished. Third if structure is used
	 * to update the game whenever it is not paused. If it is paused, this if
	 * statement draws the pause overlay. First for loop draws all of the
	 * boulders when the game is paused. Fourth if statement is used to quit to
	 * the main menu when the quit button is pressed. Fifth if statement is used
	 * to resume the game when the back button is pressed. Sixth if statement is
	 * used to resume the game when the escape key is pressed while the game is
	 * in pause state. Seventh if statement is used to draw the treasure item if
	 * the map properties state that the map contains one.
	 */
	//post doc notes : fixed paused buttons and added saves
	@Override
	public void render(float delta) {

		Gdx.input.setCursorCatched(catched);

		tweenManager.update(delta);

		Gdx.gl.glClearColor(.8f, .8f, .8f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		body.setPosition(indianaX, indianaY);

		if ((Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) || Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT))
				&& Gdx.input.isKeyPressed(Keys.S) && !gameEnded) {
			saveGame();
			game.getSaveManager().writeSave();
		}

		if (!paused && !gameEnded)
			time += Gdx.graphics.getDeltaTime();

		// set camera view
		tmRender.setView(camera);
		// render tile map
		tmRender.render();
		// update camera view
		camera.update();

		batch.begin();

		// draw font
		timerFont.setColor(Color.BLACK);
		timerFont.draw(batch, "Time [ " + (timeSeconds + (int) time) + " ]", 100f, 700f);
		timerFont.setColor(Color.WHITE);
		timerFont.draw(batch, "Time [ " + (timeSeconds + (int) time) + " ]", 105f, 705f);

		if (!paused && !gameEnded) {
			gameUpdate();
		} else if (paused) {
			for (int x = 0; x < boulderArr.size(); x++) {
				boulder1Sprite.setPosition(boulderXArr.get(x), boulderYArr.get(x));
				boulder1Sprite.draw(batch);
			}
			batch.draw(indianaJones, (int) indianaX, (int) indianaY, 48f, 96f);
			batch.draw(pauseOverlay, 0, 0);
			pauseTextSprite.draw(batch);
			darkBackSprite.draw(batch);
			saveDarkSprite.draw(batch);
			darkQuitSprite.draw(batch);
			
			//remove
			
			//if (Gdx.input.isKeyJustPressed(Keys.R)) {
			//	if (difficulty % 2 == 0) {
			//		time = 0;
			//		timeSeconds = 0;
			//		createMap();
			//	} else if (difficulty % 2 != 0) {
			//		difficulty -= 1;
			//		time = 0;
			//		timeSeconds = 0;
			//		createMap();
			//	}
			//	}

			if (backRect.contains(Gdx.input.getX(), Gdx.input.getY()+150)||Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
				backSprite.draw(batch);
				if (Gdx.input.justTouched()||Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
					paused = false;
					Gdx.input.setCursorCatched(!catched);
					catched = !catched;
				}
			}

			if (saveRect.contains(Gdx.input.getX(), Gdx.input.getY()-150) || Gdx.input.isKeyJustPressed(Keys.SPACE)) {

				saveSprite.draw(batch);
				if (Gdx.input.justTouched() || Gdx.input.isKeyPressed(Keys.SPACE)) {
					saveGame();
					game.getSaveManager().writeSave();
					paused = false;
					Gdx.input.setCursorCatched(!catched);
					catched = !catched;
				}
			}

			if (quitRect.contains(Gdx.input.getX(), Gdx.input.getY()-450) || Gdx.input.isKeyJustPressed(Keys.ENTER)) { 
				quitSprite.draw(batch);
				if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Keys.ENTER)) {
					game.setScreen(new MainMenu(batch, game));
				}
			}
		} else {
			drawOverlay();
		}

		// draw treasure
		if (Boolean.parseBoolean(currentMap.getProperties().get("containsTreasure", String.class)) && !retrieved) {
			treasureSprite.draw(batch);
		}

		batch.end();
	}

	/**
	 * The gameUpdate method takes in user input and updates the player's
	 * current location based on the keys the user enters. This method is called
	 * everytime the render method runs, as long as the game is not in a paused
	 * state. First for loop is used to update every boulder with the Boulder
	 * class, and to reset and draw the boulders in their new locations. First
	 * if statement is used to respawn the player if they collide with a
	 * boulder. Second if statement is used to set the retrieved boolean to true
	 * if the player has retrieved the treasure item. Third if statement is used
	 * to set paused boolean to true and to set mouse cursor to catched if the
	 * escape key is pressed. Fourth if statement is used to determine actions
	 * that are performed if the D key or the right arrow key is pressed. Fifth
	 * if statement updates player location as long as they don't collide with
	 * the map. Sixth if statement resets player location if the collide with
	 * outer map boundaries. Seventh if statement sets player movement direction
	 * to right. Eighth if statement determines actions to be performed if the A
	 * key or the left arrow key is pressed. Ninth if statement updates player
	 * location as long as they don't collide with the map. Tenth if statement
	 * resets player location if they collide with the outer map boundaries.
	 * Eleventh if statement sets player direction to left. Twelfth if statement
	 * determines actions to be performed if the S key or the down arrow key is
	 * pressed. Thirteenth if statement updates player location as long as they
	 * don't collide with the map. Fourteenth if statement resets player
	 * location if they collide with the outer map boundaries. Fifteenth if
	 * statement sets the player direction to down. Sixteenth if statement
	 * determines actions to be performed if the W key or the up arrow key is
	 * pressed. Seventeenth if statement updates player location as long as they
	 * don't collide with the map. Eighteenth if statement resets player
	 * location if they collide with outer map boundaries. Nineteenth if
	 * statement sets the player direction to up. Twentieth if statement sets
	 * player direction to none. Twenty-first if statement saves the game and
	 * resets timer/direction values if the player completes the game.
	 */
	public void gameUpdate() {

		String collide = "";

		interpY = indianaY;
		priority = false;
		direction = "none";

		// boulder draw
		for (int x = 0; x < boulderArr.size(); x++) {
			b.update(x, distanceArr.get(x).intValue(), directionArr.get(x));
			boulder1Sprite.setPosition(boulderXArr.get(x), boulderYArr.get(x));

			boulderCollision[x] = new Rectangle(boulderXArr.get(x), boulderYArr.get(x), 64, 64);
			if(directionArr.get(x).equals("up")){
				batch.draw(boulder_up.getKeyFrame(time, true), boulderXArr.get(x), boulderYArr.get(x), 64f, 64f);
			}
			else if(directionArr.get(x).equals("down")){
				batch.draw(boulder_down.getKeyFrame(time, true), boulderXArr.get(x), boulderYArr.get(x), 64f, 64f);
			}
			else if(directionArr.get(x).equals("left")){
				batch.draw(boulder_left.getKeyFrame(time, true),boulderXArr.get(x), boulderYArr.get(x), 64f, 64f);
			}
			else if(directionArr.get(x).equals("right")){
				batch.draw(boulder_right.getKeyFrame(time, true), boulderXArr.get(x), boulderYArr.get(x), 64f, 64f);
			}
			else{
				batch.draw(boulder1Sprite, boulderXArr.get(x), boulderYArr.get(x), 64f, 64f);	
			}
				
			}
			//boulder1Sprite.draw(batch);
		

		endCheck();

		if (collidesWithBoulders()) {
			indianaX = start.x;
			indianaY = start.y;

		}

		if (treasureRetrieved()) {
			retrieved = true;
		}

		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			Gdx.input.setCursorCatched(!catched);
			catched = !catched;
			paused = true;
		}
		if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) == false && Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT) == false) {

			if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) {
				interp.setPosition(indianaX + 4, interpY);
				if (!collidesWithMap() && !collide.equals("right")) {
					indianaX += Gdx.graphics.getDeltaTime() * speed;
				} else {
					collide = "right";
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

				if (!collidesWithMap() && !collide.equals("left")) {
					indianaX -= Gdx.graphics.getDeltaTime() * speed;
				} else {
					collide = "left";
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

				if (!collidesWithMap() && !collide.equals("down")) {
					indianaY -= Gdx.graphics.getDeltaTime() * speed;
				} else {
					collide = "down";
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

				if (!collidesWithMap() && !collide.equals("up")) {
					indianaY += Gdx.graphics.getDeltaTime() * speed;
				} else {
					collide = "up";
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
		}
		if (gameEnded) {
			saveGame();
			timeSeconds = 0;
			time = 0;
			direction = "none";

		}
		priorityDraw();
	}

	/**
	 * This method uses a for loop and an if statement to determine if the
	 * user's walking hitbox collides with the map boundaries. If the user
	 * collides with the map, a result of true is returned. First for loop runs
	 * through the array of collision rectangles. First if statement determines
	 * if the player collides with any of the collision rectangles.
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

	/**
	 * This method determines if the player collides with the end point of the
	 * level. First if statement determines if the player's hitbox rectangle
	 * collides with the rectangle of the end location.
	 * 
	 * @return result Boolean based on if the player collides with end point
	 */
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
	 * of having multiple walking animations play at the same time. First if
	 * statement draws the up animation if the direction of the player is up.
	 * Second if statement draws the down animation if the direction of the
	 * player is down. Third if statement draws the left animation if the
	 * direction of the player is left. Fourth if statement draws the right
	 * animation if the direction of the player is right. Fifth if statement
	 * draws a still image if the direction of the player is none.
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

	/**
	 * This method checks if the user has ended the stage or level. If the stage
	 * that the user has completed is not the final stage of the difficulty, a
	 * new map (the last stage) is loaded. If the stage that the user has
	 * completed is the final stage of the difficulty, a game winning overlay
	 * with the user's name and score is displayed and the game is saved. First
	 * if statement determines if the user has completed the level. Second if
	 * statement determines the actions to be performed if the stage the user
	 * has completed was the first stage of the difficulty. Third if statement
	 * draws the overlay and sets gameEnded to true if the map does not contain
	 * a treasure or if the treasure has been retrieved.
	 */
	private void endCheck() {
		if (mapEnds()) {

			if (difficulty % 2 == 0) {
				difficulty++;
				setCurrentMap(difficulty);
				b = new Boulder(currentMap);
				createMap();
				System.out.println("stage end");
			} else {
				if (retrieved
						|| !Boolean.parseBoolean(currentMap.getProperties().get("containsTreasure", String.class))) {
					System.out.println("level complete");
					gameEnded = true;
					drawOverlay();
				} else {
					treasureOverlaySprite.draw(batch);
				}
				if (retrieved) {
					saveGame();
					HighScore.addSave(game.getSaveManager().getSave());
				}
			}
		}
	}

	/**
	 * This method calls the save method in the SaveManager class from the
	 * MainGame class. The method stores the user's name, the difficulty of the
	 * stage they are currently on, and the total time that has elapsed.
	 */
	public void saveGame() {
		totalTime = time;
		game.getSaveManager().setSave(new Save(name, difficulty, timeSeconds + (int) totalTime));
	}

	/**
	 * This method draws the game ending overlay, which happens when the user
	 * has completed the final stage of the difficulty. This game ending overlay
	 * contains the user's name within a dialog box as well as the time it took
	 * for the user to complete the entire difficulty level. The first if
	 * statement determines what actions to be performed when the user's cursor
	 * is over the menu button. The second if statement changes the game screen
	 * to the main menu if the user clicks their mouse while the cursor is over
	 * the menu button. The third if statement determines what actions to be
	 * performed when the user's cursor is over the go button. The fourth if
	 * statement renders the new map and resets values when the user clicks
	 * their mouse while the cursor is over the go button. The fifth if
	 * statement determine actions to occur based on whether the level they
	 * completed is the last one or not.
	 */
	private void drawOverlay() {
		Gdx.input.setCursorCatched(false);

		batch.draw(pauseOverlay, 0, 0);
		successSprite.draw(batch); 
		scoreSprite.draw(batch);
		menuDarkSprite.draw(batch);
		goDarkSprite.draw(batch);

		// timer font
		timerFont.setColor(Color.BLACK);
		timerFont.draw(batch, "" + (int) totalTime + " Seconds", 685f, 470f);
		timerFont.setColor(Color.WHITE);
		timerFont.draw(batch, "" + (int) totalTime + " Seconds", 690f, 475f);

		// dialog font
		dialogFont.setColor(Color.BLACK);
		dialogFont.draw(batch, name + "!", 630f, 275f);

		if (menuRect.contains(Gdx.input.getX(), Gdx.input.getY() - 610) || Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			menuSprite.draw(batch);
			if (Gdx.input.justTouched() || Gdx.input.isKeyPressed(Keys.ESCAPE)) {
				game.setScreen(new MainMenu(batch, game));
			}
		}

		if (goRect.contains(Gdx.input.getX(), Gdx.input.getY() - 610) || Gdx.input.isKeyPressed(Keys.ENTER)) {
			goSprite.draw(batch);
			if (Gdx.input.justTouched() || Gdx.input.isKeyPressed(Keys.ENTER)) {
				if (difficulty < 5) {
					catched = true;
					difficulty++;
					gameEnded = false;
					setCurrentMap(difficulty);
					musicPlay();
					timeSeconds = 0;
					time = 0;
					direction = "none";
					createMap();

				} else {
					game.setScreen(new HighScore(batch, game));
				}

			}
		}
	}

	/**
	 * This method is responsible for rendering the map, and resetting all of
	 * the array values (such as collision array and boulder array). This method
	 * is used for when a new map is switched. The first for loop occupies the
	 * collision array with rectangles taken from the map file. The first if
	 * statement determines if the current object is a Rectangle. If so, it is
	 * added to the collision array. The second for loop runs through the map
	 * objects that represent the start and end points in the map file. The
	 * second if statement determines if the object is an Ellipse. If so, the
	 * start value is set to the Ellipse. The third if statement determines if
	 * the object is a Rectangle. If so, the end value is set to the Rectangle.
	 * The fourth if statement resets the retrieved value to false if the map
	 * contains a treasure item.
	 */
	private void createMap() {

		b = new Boulder(currentMap);

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

		// create boulder collision array
		boulderCollision = new Rectangle[directionArr.size()];

		boulder1 = new Texture("assets/boulder_1.png");
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

		if (Boolean.parseBoolean(currentMap.getProperties().get("containsTreasure", String.class))) {
			retrieved = false;
		} else {
			retrieved = true;
		}

	}

	/**
	 * This method plays different music files based on the difficulty of the
	 * level. The first if statement plays the desert theme if the user is on
	 * the first difficulty. The second if statement plays the earth theme if
	 * the user is on the second difficulty. The third if statement plays the
	 * ice theme if the user is on the third difficulty.
	 */
	public void musicPlay() {
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

	/**
	 * This method determines if the user collides with any boulder located on
	 * the map. The first for loop runs through the array of rectangles that
	 * represent the boulder hitboxes. The first if statement returns true if
	 * the player's hitbox collides with that of the boulder's.
	 * 
	 * @return result Boolean based on if the user collides with a boulder
	 */
	public boolean collidesWithBoulders() {
		boolean result = false;

		for (int x = 0; x < boulderCollision.length; x++) {
			if (boulderCollision != null) {
				if (interp.overlaps(boulderCollision[x])) {
					result = true;
					break;
				}
			}
		}
		return result;
	}

	/**
	 * This method determines if the user has collected the treasure item or
	 * not. The first if statement determines if the user has collided with the
	 * rectangle representing the treasure item.
	 * 
	 * @return result Boolean based on if the user collided with treasure item
	 */
	private boolean treasureRetrieved() {
		Rectangle treasureRect = treasureSprite.getBoundingRectangle();
		boolean result = false;

		if (interp.overlaps(treasureRect)) {
			result = true;
		}
		return result;
	}

}
