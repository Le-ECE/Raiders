package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Movement implements Screen {
	Animation animation_right;
	Animation animation_left;
	Animation animation_up;
	Animation animation_down;
	TextureRegion[] frames_right;
	TextureRegion[] frames_left;
	TextureRegion[] frames_up;
	TextureRegion[]	frames_down;
	float time;
	Game game;
	SpriteBatch batch;
	Sprite indianaJones;
	Sprite column;

	Rectangle body;
	Rectangle collide;
	Rectangle intersection = new Rectangle();

	Stage stage;

	Texture indianaText;
	Texture walk_right;
	Texture walk_left;
	Texture walk_up;
	Texture walk_down;
	Texture colText;

	float indianaX;
	float indianaY;
	float speed;

	//Rectangle r = indianaJones.getBoundingRectangle();

	boolean catched = true;

	public Movement (Game game){
		this.game=game;
		create();
	}

	public void create() {
		// TODO Auto-generated method stub
		batch = new SpriteBatch();

		stage = new Stage();

		Gdx.input.setCursorCatched (catched);

		colText = new Texture(Gdx.files.internal("column.png"));
		column = new Sprite (colText);

		collide = column.getBoundingRectangle();

		FileHandle indianaFileHandle = Gdx.files.internal("indianajones.png");
		indianaText = new Texture (indianaFileHandle);
		indianaJones = new Sprite (indianaText);

		body = indianaJones.getBoundingRectangle();

		indianaJones.setSize(48f, 96f);

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
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(.8f, .8f, .8f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		time += Gdx.graphics.getDeltaTime();


		body.setPosition(indianaX,indianaY);
		collide.setPosition(250f,250f);

		batch.begin();
		batch.draw(column,250f,250f);
		if (Intersector.intersectRectangles(body, collide, intersection)){
			if (intersection.x>body.x){
				System.out.println("collides left!");
				indianaX = collide.x-30;
			}
			if (intersection.y>body.y){
				System.out.println("collides bottom!");
			}
			if (intersection.x+intersection.width<body.x+body.width){
				System.out.println("collides with right!");
			}
			if (intersection.y+intersection.height<body.y+body.height){
				System.out.println("collides with top!");
			}
		}

		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
			Gdx.input.setCursorCatched(!catched);
			catched=!catched;
		}
		// TODO Auto-generated method stub
		if (Gdx.input.isKeyPressed(Keys.D)||Gdx.input.isKeyPressed (Keys.DPAD_RIGHT)){
			indianaX+=Gdx.graphics.getDeltaTime()*speed;
			if (indianaX+indianaJones.getWidth()>=1200){
				indianaX=1200-indianaJones.getWidth();
			}
			batch.draw(animation_right.getKeyFrame(time,true),(int)indianaX,(int)indianaY,64f,128f);
		}
		else if (Gdx.input.isKeyPressed(Keys.A)||Gdx.input.isKeyPressed (Keys.DPAD_LEFT)){
			indianaX-=Gdx.graphics.getDeltaTime()*speed;
			if (indianaX<=-17){
				indianaX=-17;
			}
			batch.draw(animation_left.getKeyFrame (time,true), (int)indianaX, (int)indianaY, 64f,128f);
		}
		else if (Gdx.input.isKeyPressed(Keys.S)||Gdx.input.isKeyPressed (Keys.DPAD_DOWN)){
			indianaY-=Gdx.graphics.getDeltaTime()*speed;
			if (indianaY<=0){
				indianaY=0;
			}
			batch.draw (animation_down.getKeyFrame(time, true),(int)indianaX,(int)indianaY,64f,128f);
		}
		else if (Gdx.input.isKeyPressed(Keys.W)||Gdx.input.isKeyPressed (Keys.DPAD_UP)){
			indianaY+=Gdx.graphics.getDeltaTime()*speed;
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

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}


}
