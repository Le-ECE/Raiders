package managers;

import java.util.Stack;

import com.mygdx.game.MainGame;
import com.mygdx.game.states.GameState;
import com.mygdx.game.states.Play;

/**
 * The GameStateManager is, once implemented, responsible for managing the
 * current game state the program is on. It accomplishes this by using Java's
 * Stack class, to pop and push states.
 * 
 * @author Brian Tran
 * @version 1.0 13/05/2016
 * 
 *          <p>
 *          <b>Instance Variables</b>
 *          <p>
 *          <b>game</b> Instance of MainGame used to manage the screen of the
 *          game
 *          <p>
 *          <b>gameStates</b> Instance of the Stack class used to manage and
 *          store the different game states
 *          <p>
 *          <b>PLAY</b> Used to set the first value of the Stack.
 *
 */
public class GameStateManager {

	private MainGame game;

	private Stack<GameState> gameStates;

	public static final int PLAY = 1;

	/**
	 * The GameStateManager constructor takes a MainGame from Play and other
	 * classes and sets the current MainGame to the value. The constructor also
	 * instantiates the gameStates variable, and then pushes the first value of
	 * Stack.
	 * 
	 * @param game
	 *            Handles on screen elements
	 */
	public GameStateManager(MainGame game) {
		this.game = game;
		gameStates = new Stack<GameState>();
		pushState(PLAY);
	}

	/**
	 * Returns game.
	 * 
	 * @return game
	 */
	public MainGame getGame() {
		return game;
	}

	/**
	 * Looks at the first object of the stack.
	 * 
	 * @param dt
	 *            Delta time of frames
	 */
	public void update(float dt) {
		gameStates.peek();
	}

	/**
	 * Renders the first object of the stack.
	 */
	public void render() {
		gameStates.peek().render();
	}

	/**
	 * Returns a game state.
	 * 
	 * @param state
	 *            First object of stack
	 * @return GameState State of game
	 */
	private GameState getState(int state) {
		if (state == PLAY) {
			return new Play(this);
		}
		return null;
	}

	/**
	 * Sets the top state to a value that is overloaded.
	 * 
	 * @param state
	 *            top value
	 */
	public void setState(int state) {
		popState();
		pushState(state);
	}

	/**
	 * Pushes the overloaded state onto the Stack
	 * 
	 * @param state
	 *            Top value to push
	 */
	public void pushState(int state) {
		gameStates.push(getState(state));
	}

	/**
	 * Pops the top object off of the Stack
	 */
	public void popState() {
		GameState gs = gameStates.pop();
		gs.dispose();
	}
}
