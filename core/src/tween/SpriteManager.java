package tween;

import com.badlogic.gdx.graphics.g2d.Sprite;

import aurelienribon.tweenengine.TweenAccessor;

/**
 * This class allows for the changing of Alpha values in text
 * based on an imported sprite.
 * 
 * @author Robin Stu modified by Brian Tran
 * @version 2.0 19/05/2016
 */
public class SpriteManager implements TweenAccessor<Sprite> {

	public static final int ALPHA = 0;

	/**
	 * This method gets the current values of the sprite,
	 * such as alpha values and colors.
	 */
	@Override
	public int getValues(Sprite arg0, int arg1, float[] arg2) {
		switch (arg1) {
		case ALPHA:
			arg2[0] = arg0.getColor().a;
			return 1;
		default:
			assert false;
			return -1;
		}
	}

	/**
	 * This method sets the new values of the sprite,
	 * which allow for new states of transparency.
	 */
	@Override
	public void setValues(Sprite arg0, int arg1, float[] arg2) {
		switch (arg1) {
		case ALPHA:
			arg0.setColor(arg0.getColor().r, arg0.getColor().g, arg0.getColor().b, arg2[0]);
			break;
		default:
			assert false;
		}
	}

}
