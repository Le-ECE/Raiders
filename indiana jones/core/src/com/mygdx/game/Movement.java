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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Movement implements Screen {
	Animation animation_right;
	Animation animation_left;
	Animation animation_up;
	Animation animation_down;
	
	TextureRegion[] frames_right;
	TextureRegion[] frames_left;
	TextureRegion[] frames_up;
	TextureRegion[]	frames_down;
	
	MainGame game;
		
	SpriteBatch batch;
	
	Sprite indianaJones;
	Sprite column;
	Sprite boulder;

	Rectangle body;
	Rectangle collide;
	Rectangle collideBoulder;
	Rectangle interp;

	Texture indianaText;
	Texture walk_right;
	Texture walk_left;
	Texture walk_up;
	Texture walk_down;
	Texture colText;
	Texture boulderText;
	
	TiledMap map;
	TiledMapRenderer tmRender;

	OrthographicCamera camera;

	float indianaX;
	float indianaY;
	float speed;
	float time;

	boolean catched = true;	
	
	public Movement (SpriteBatch batch, MainGame game){
		this.batch = batch;
		this.game = game;
		create();
	}
	
/**
 * The create() method is an overridden method that is used to instantiate and
 * give values to all of the variables. Create() is called every time the 
 * application is run. The create() method also sets the cursor to remain within
 * the window and be non visible with a built in GDX method setCursorCatched ().
 * Two for loops are used to transfer the 2D array of TextureRegion to a 1D array
 * of TextureRegion for animation.
 */
	public void create() {
		// TODO Auto-generated method stub
		
		//stage = new Stage();

		Gdx.input.setCursorCatched (catched);
		
		//tiledmap renderer
		map = new TmxMapLoader().load("maps/newmap.tmx");
		tmRender = new OrthogonalTiledMapRenderer (map);
		
		//camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false,1216,768);
		camera.update();

		colText = new Texture(Gdx.files.internal("column.png"));
		column = new Sprite (colText);
		
		boulderText = new Texture (Gdx.files.internal("boulder_0.png"));
		boulder = new Sprite (boulderText);

		indianaText = new Texture ("indianajones.png");
		indianaJones = new Sprite (indianaText);

		indianaJones.setSize(48f, 96f);

		collide = column.getBoundingRectangle();
		collideBoulder = boulder.getBoundingRectangle();
		body = indianaJones.getBoundingRectangle();
		interp = new Rectangle (body.getX(),body.getY(),body.getWidth(),body.getHeight());
		
		indianaX = 30;
		indianaY = 300;

		speed = 250.0f;

		walk_right = new Texture ("right_walk.png");
		walk_left = new Texture ("left_walk.png");
		walk_up = new Texture ("up_walk.png");
		walk_down = new Texture ("down_walk.png");

		TextureRegion[][] temp_right = TextureRegion.split(walk_right, 32, 64);
		frames_right = new TextureRegion[4];
		TextureRegion[][] temp_left = TextureRegion.split(walk_left, 32, 64);
		frames_left = new TextureRegion[4];
		TextureRegion[][] temp_up = TextureRegion.split(walk_up, 32, 64);
		frames_up = new TextureRegion[4];
		TextureRegion[][]temp_down = TextureRegion.split(walk_down, 32, 64);
		frames_down = new TextureRegion[4];

		int index = 0;
		//transfer 2D array of sprite sheet to 1d array for animation
		for (int rows = 0;rows<2;rows++){
			for (int col = 0;col<2;col++){
				frames_right[index]=temp_right[rows][col];
				frames_left [index]=temp_left[rows][col];
				frames_up [index]=temp_up[rows][col];
				frames_down[index]=temp_down[rows][col];
				index++;
			}
		}
		animation_up = new Animation (0.25f, frames_up);
		animation_left = new Animation (0.25f,frames_left);
		animation_right = new Animation (0.25f, frames_right);
		animation_down = new Animation (0.25f, frames_down);
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
	public void dispose() {
		// TODO Auto-generated method stub

	}
	/**
	 * Unused overridden method.
	 */
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	/**
	 * The render() method is an overridden method from the GDX library.
	 * The render method is run every frame, and as a result, updates the screen
	 * every frame with new information. This allows for animations of sprites and
	 * such to be easier. Things are displayed on the screen using a SpriteBatch,
	 * which acts as a container of sorts for the screen elements.Various for 
	 * loops are used to detect user input, as for collision and detection.
	 */
	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(.8f, .8f, .8f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		boulder.setPosition(500f, 300f);
		column.setPosition(250f, 250f);
		
		body.setPosition(indianaX,indianaY);
		collide.setPosition(250f,250f);
		collideBoulder.setPosition(boulder.getX(),boulder.getY());
		
		time += Gdx.graphics.getDeltaTime();

		//render tile map
		tmRender.setView(camera);
		tmRender.render();
		camera.update();

		batch.begin();
		
		//draw sprites
		column.draw(batch);;
		boulder.draw(batch);

		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
			Gdx.input.setCursorCatched(!catched);
			catched=!catched;
		}
		if (Gdx.input.isKeyPressed(Keys.D)||Gdx.input.isKeyPressed (Keys.DPAD_RIGHT)){
			interp.setPosition(indianaX+4,indianaY);
			if (!interp.overlaps (collide)&&!interp.overlaps(collideBoulder)){
				indianaX+=Gdx.graphics.getDeltaTime()*speed;
			}
			interp.setPosition(indianaX,indianaY);
			if (indianaX+indianaJones.getWidth()>=1200){
				indianaX=1200-indianaJones.getWidth();
			}
			batch.draw(animation_right.getKeyFrame(time,true),(int)indianaX,(int)indianaY,64f,128f);
		}
		else if (Gdx.input.isKeyPressed(Keys.A)||Gdx.input.isKeyPressed (Keys.DPAD_LEFT)){
			interp.setPosition(indianaX-4,indianaY);
			if (!interp.overlaps(collide)&&!interp.overlaps(collideBoulder)){
				indianaX-=Gdx.graphics.getDeltaTime()*speed;
			}
			interp.setPosition(indianaX,indianaY);
			if (indianaX<=-17){
				indianaX=-17;
			}
			batch.draw(animation_left.getKeyFrame (time,true), (int)indianaX, (int)indianaY, 64f,128f);
		}
		else if (Gdx.input.isKeyPressed(Keys.S)||Gdx.input.isKeyPressed (Keys.DPAD_DOWN)){
			interp.setPosition(indianaX,indianaY-4);
			if (!interp.overlaps(collide)&&!interp.overlaps(collideBoulder)){
				indianaY-=Gdx.graphics.getDeltaTime()*speed;
			}
			interp.setPosition(indianaX,indianaY);
			if (indianaY<=0){
				indianaY=0;
			}
			batch.draw (animation_down.getKeyFrame(time, true),(int)indianaX,(int)indianaY,64f,128f);
		}
		else if (Gdx.input.isKeyPressed(Keys.W)||Gdx.input.isKeyPressed (Keys.DPAD_UP)){
			interp.setPosition(indianaX,indianaY+4);
			if (!interp.overlaps(collide)&&!interp.overlaps(collideBoulder)){
				indianaY+=Gdx.graphics.getDeltaTime()*speed;
			}
			interp.setPosition(indianaX,indianaY);
			if (indianaY+indianaJones.getHeight()>=768){
				indianaY=768-indianaJones.getHeight();
			}
			batch.draw(animation_up.getKeyFrame(time, true), (int)indianaX, (int)indianaY, 64f, 128f);
		}
		else{
			batch.draw(indianaJones, (int)indianaX, (int)indianaY, 64f, 128f);
		}

		batch.end();
	}
	/**
	 * Unused overridden method.
	 */
	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}


}
