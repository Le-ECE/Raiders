package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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

	public SplashScreen(MainGame game) {
		this.game = game;
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		tweenManager = new TweenManager();
		Tween.registerAccessor(Sprite.class, new SpriteManager());

		speed = 250.0f;

		background = new Texture("splash.png");

		splashTitle = new Texture("splashtitle.png");
		splashTitleSprite = new Sprite(splashTitle);
		splashTitleSprite.setPosition(0f, 768 - splashTitleSprite.getHeight());

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
		rollAnim = new Animation(0.05f, framesRoll);

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

	@Override
	public void render(float delta) {

		tweenManager.update(delta);

		time += Gdx.graphics.getDeltaTime();
		batch.begin();
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.draw(background, 0, 0, 1200f, 768f);

		walkX += Gdx.graphics.getDeltaTime() * speed;

		batch.draw(walkAnim.getKeyFrame(time, true), 1300 - walkX, 100, 64f, 128f);
		batch.draw(rollAnim.getKeyFrame(time, true), 1450 - walkX, 100, 128f, 128f);

		splashTitleSprite.draw(batch);
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
		walk.dispose();
		roll.dispose();
		background.dispose();
		splashTitle.dispose();

	}

}
