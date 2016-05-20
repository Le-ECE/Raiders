package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Difficulty implements Screen {

	private int difficulty;

	private boolean drawDarkEasy = true;
	private boolean drawDarkMedium = true;
	private boolean drawDarkHard = true;

	Texture easy;
	Texture background;
	Texture medium;
	Texture hard;
	Texture title;
	Texture easyDark;
	Texture medDark;
	Texture hardDark;
	Texture back;
	Texture backDark;
	Texture go;
	Texture goDark;

	Rectangle goRect;
	Rectangle easyRect;
	Rectangle medRect;
	Rectangle hardRect;
	Rectangle backRect;

	ShapeRenderer sr;

	Sprite goDarkSprite;
	Sprite goSprite;
	Sprite titleSprite;
	Sprite easySprite;
	Sprite mediumSprite;
	Sprite hardSprite;
	Sprite backgroundSprite;
	Sprite easyDarkSprite;
	Sprite medDarkSprite;
	Sprite hardDarkSprite;
	Sprite backSprite;
	Sprite backDarkSprite;

	private SpriteBatch batch;

	private MainGame game;

	public Difficulty(SpriteBatch batch, MainGame game) {
		this.batch = batch;
		this.game = game;
	}

	@Override
	public void show() {

		// go dark create
		goDark = new Texture("go_dark.png");

		// go dark sprite create
		goDarkSprite = new Sprite(goDark);
		goDarkSprite.setSize(250, 105);
		goDarkSprite.setPosition(((Gdx.graphics.getWidth() / 4) * 3) - (goDarkSprite.getWidth() / 2), 10);

		// go create
		go = new Texture("go.png");

		// go sprite create
		goSprite = new Sprite(go);
		goSprite.setSize(250, 105);
		goSprite.setPosition(((Gdx.graphics.getWidth() / 4) * 3) - (goSprite.getWidth() / 2), 10);

		// back dark create
		backDark = new Texture("back_dark.png");

		// back dark sprite create
		backDarkSprite = new Sprite(backDark);
		backDarkSprite.setSize(250, 105);
		backDarkSprite.setPosition((Gdx.graphics.getWidth() / 4) - (backDarkSprite.getWidth() / 2), 10);

		// back create
		back = new Texture("backbutton.png");

		// back sprite create
		backSprite = new Sprite(back);
		backSprite.setSize(250, 105);
		backSprite.setPosition((Gdx.graphics.getWidth() / 4) - (backSprite.getWidth() / 2), 10);

		// title create
		title = new Texture("difficulty_title.png");
		// easy button create
		easy = new Texture("easy.png");
		// medium button create
		medium = new Texture("medium.png");
		// hard button create
		hard = new Texture("hard.png");

		// easy dark create
		easyDark = new Texture("easy_dark.png");
		// medium dark create
		medDark = new Texture("medium_dark.png");
		// hard dark create
		hardDark = new Texture("hard_dark.png");

		// title sprite create
		titleSprite = new Sprite(title);
		titleSprite.setPosition(0, 768 - titleSprite.getHeight());
		titleSprite.setSize(1200, 300);
		// easy sprite create
		easySprite = new Sprite(easy);
		easySprite.setSize(290, 110);
		easySprite.setPosition((Gdx.graphics.getWidth() / 2) - (easySprite.getWidth() / 2), 400);

		// medium sprite create
		mediumSprite = new Sprite(medium);
		mediumSprite.setSize(290, 110);
		mediumSprite.setPosition((Gdx.graphics.getWidth() / 2) - (mediumSprite.getWidth() / 2), 280);

		// hard sprite create
		hardSprite = new Sprite(hard);
		hardSprite.setSize(290, 110);
		hardSprite.setPosition((Gdx.graphics.getWidth() / 2) - (hardSprite.getWidth() / 2), 160);

		// easy dark sprite create
		easyDarkSprite = new Sprite(easyDark);
		easyDarkSprite.setSize(290, 110);
		easyDarkSprite.setPosition((Gdx.graphics.getWidth() / 2) - (easyDarkSprite.getWidth() / 2), 400);

		// medium dark sprite create
		medDarkSprite = new Sprite(medDark);
		medDarkSprite.setSize(290, 110);
		medDarkSprite.setPosition((Gdx.graphics.getWidth() / 2) - (medDarkSprite.getWidth() / 2), 280);

		// hard dark sprite create
		hardDarkSprite = new Sprite(hardDark);
		hardDarkSprite.setSize(290, 110);
		hardDarkSprite.setPosition((Gdx.graphics.getWidth() / 2) - (hardDarkSprite.getWidth() / 2), 160);

		// easy rectangle create
		easyRect = new Rectangle(easySprite.getX(), easySprite.getY(), easySprite.getWidth(), easySprite.getHeight());

		// medium rectangle create
		medRect = new Rectangle(mediumSprite.getX(), mediumSprite.getY(), mediumSprite.getWidth(),
				mediumSprite.getHeight());

		// hard rectangle create
		hardRect = new Rectangle(hardSprite.getX(), hardSprite.getY(), hardSprite.getWidth(), hardSprite.getHeight());

		// back rectangle create
		backRect = new Rectangle(backSprite.getX(), backSprite.getY(), backSprite.getWidth(), backSprite.getHeight());

		// go rectangle create
		goRect = new Rectangle(goSprite.getX(), goSprite.getY(), goSprite.getWidth(), goSprite.getHeight());

		// background create
		background = new Texture("splash.png");

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		batch.draw(background, 0, 0, 1200f, 768f);
		titleSprite.draw(batch);

		backDarkSprite.draw(batch);
		goDarkSprite.draw(batch);

		if (drawDarkEasy)
			easyDarkSprite.draw(batch);
		else {
			medDarkSprite.draw(batch);
			hardDarkSprite.draw(batch);
			easySprite.draw(batch);
		}
		if (drawDarkMedium)
			medDarkSprite.draw(batch);
		else {
			easyDarkSprite.draw(batch);
			mediumSprite.draw(batch);
			hardDarkSprite.draw(batch);
		}
		if (drawDarkHard)
			hardDarkSprite.draw(batch);
		else {
			easyDarkSprite.draw(batch);
			medDarkSprite.draw(batch);
			hardSprite.draw(batch);
		}

		if (backRect.contains(Gdx.input.getX(), Gdx.input.getY() - 600)) {
			backSprite.draw(batch);
			if (Gdx.input.justTouched()) {
				game.setScreen(new MainMenu(batch, game));
			}
		}
		if (easyRect.contains(Gdx.input.getX(), Gdx.input.getY() + 185)) {
			easySprite.draw(batch);
			if (Gdx.input.justTouched()) {
				difficulty = 0;
				drawDarkEasy = false;
				drawDarkMedium = true;
				drawDarkHard = true;
			}
		}
		if (medRect.contains(Gdx.input.getX(), Gdx.input.getY() - 70)) {
			mediumSprite.draw(batch);
			if (Gdx.input.justTouched()) {
				difficulty = 0;
				drawDarkMedium = false;
				drawDarkEasy = true;
				drawDarkHard = true;
				mediumSprite.draw(batch);
			}
		}
		if (hardRect.contains(Gdx.input.getX(), Gdx.input.getY() - 300)) {
			hardSprite.draw(batch);
			if (Gdx.input.justTouched()) {
				difficulty = 2;
				drawDarkHard = false;
				drawDarkEasy = true;
				drawDarkMedium = true;
				hardSprite.draw(batch);
			}
		}
		if (goRect.contains(Gdx.input.getX(), Gdx.input.getY() - 600)) {
			goSprite.draw(batch);
			if (Gdx.input.justTouched()) {
				game.setScreen(new GameScreen(batch, game, difficulty));
			}
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
