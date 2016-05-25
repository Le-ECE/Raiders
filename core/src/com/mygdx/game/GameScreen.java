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

	Rectangle body;
	Rectangle interp;
	Rectangle backRect;
	Rectangle quitRect;

	ArrayList<Rectangle> collisionArray = new ArrayList<Rectangle>();
	ArrayList<Rectangle> boulderArr = new ArrayList<Rectangle>();

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

	Ellipse start;

	TiledMap currentMap;
	TiledMapRenderer tmRender;

	TweenManager tweenManager;

	OrthographicCamera camera;

	float indianaX;
	float indianaY;
	float speed;
	float time;
	float interpY;

	String direction;

	boolean catched = true;
	boolean paused = false;
	boolean mapCollide = false;
	boolean priority;

	private int difficulty;

	public GameScreen(SpriteBatch batch, MainGame game, int difficulty) {
		this.batch = batch;
		this.game = game;
		this.difficulty = difficulty;
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

		// quit dark
	at org.eclipse.ui.internal.ide.application.IDEWorkbenchWindowAdvisor$4.partHidden(IDEWorkbenchWindowAdvisor.java:331)
	at org.eclipse.ui.internal.PartService$11.run(PartService.java:214)
	at org.eclipse.core.runtime.SafeRunner.run(SafeRunner.java:42)
	at org.eclipse.ui.internal.PartService.partHidden(PartService.java:211)
	at org.eclipse.ui.internal.WorkbenchPage$25.run(WorkbenchPage.java:5190)
	at org.eclipse.core.runtime.SafeRunner.run(SafeRunner.java:42)
	at org.eclipse.ui.internal.WorkbenchPage.firePartHidden(WorkbenchPage.java:5187)
	at org.eclipse.ui.internal.WorkbenchPage$E4PartListener.partHidden(WorkbenchPage.java:233)
	at org.eclipse.e4.ui.internal.workbench.PartServiceImpl$5.run(PartServiceImpl.java:277)
	at org.eclipse.core.runtime.SafeRunner.run(SafeRunner.java:42)
	at org.eclipse.e4.ui.internal.workbench.PartServiceImpl.firePartHidden(PartServiceImpl.java:274)
	at org.eclipse.e4.ui.internal.workbench.PartServiceImpl.access$1(PartServiceImpl.java:272)
	at org.eclipse.e4.ui.internal.workbench.PartServiceImpl$1.handleEvent(PartServiceImpl.java:91)
	at org.eclipse.e4.ui.services.internal.events.UIEventHandler$1.run(UIEventHandler.java:40)
	at org.eclipse.swt.widgets.Synchronizer.syncExec(Synchronizer.java:186)
	at org.eclipse.ui.internal.UISynchronizer.syncExec(UISynchronizer.java:145)
	at org.eclipse.swt.widgets.Display.syncExec(Display.java:4761)
	at org.eclipse.e4.ui.internal.workbench.swt.E4Application$1.syncExec(E4Application.java:211)
	at org.eclipse.e4.ui.services.internal.events.UIEventHandler.handleEvent(UIEventHandler.java:36)
	at org.eclipse.equinox.internal.event.EventHandlerWrapper.handleEvent(EventHandlerWrapper.java:197)
	at org.eclipse.equinox.internal.event.EventHandlerTracker.dispatchEvent(EventHandlerTracker.java:197)
	at org.eclipse.equinox.internal.event.EventHandlerTracker.dispatchEvent(EventHandlerTracker.java:1)
	at org.eclipse.osgi.framework.eventmgr.EventManager.dispatchEvent(EventManager.java:230)
	at org.eclipse.osgi.framework.eventmgr.ListenerQueue.dispatchEventSynchronous(ListenerQueue.java:148)
	at org.eclipse.equinox.internal.event.EventAdminImpl.dispatchEvent(EventAdminImpl.java:135)
	at org.eclipse.equinox.internal.event.EventAdminImpl.sendEvent(EventAdminImpl.java:78)
	at org.eclipse.equinox.internal.event.EventComponent.sendEvent(EventComponent.java:39)
	at org.eclipse.e4.ui.services.internal.events.EventBroker.send(EventBroker.java:85)
	at org.eclipse.e4.ui.internal.workbench.UIEventPublisher.notifyChanged(UIEventPublisher.java:59)
	at org.eclipse.emf.common.notify.impl.BasicNotifierImpl.eNotify(BasicNotifierImpl.java:374)
	at org.eclipse.e4.ui.model.application.ui.impl.ElementContainerImpl.setSelectedElement(ElementContainerImpl.java:171)
	at org.eclipse.e4.ui.workbench.renderers.swt.StackRenderer$9.widgetSelected(StackRenderer.java:1084)
	at org.eclipse.swt.widgets.TypedListener.handleEvent(TypedListener.java:248)
	at org.eclipse.swt.widgets.EventTable.sendEvent(EventTable.java:84)
	at org.eclipse.swt.widgets.Display.sendEvent(Display.java:4362)
	at org.eclipse.swt.widgets.Widget.sendEvent(Widget.java:1113)
	at org.eclipse.swt.widgets.Widget.sendEvent(Widget.java:1137)
	at org.eclipse.swt.widgets.Widget.sendEvent(Widget.java:1122)
	at org.eclipse.swt.widgets.Widget.notifyListeners(Widget.java:788)
	at org.eclipse.swt.custom.CTabFolder.setSelection(CTabFolder.java:3158)
	at org.eclipse.swt.custom.CTabFolder.onMouse(CTabFolder.java:1841)
	at org.eclipse.swt.custom.CTabFolder$1.handleEvent(CTabFolder.java:330)
	at org.eclipse.swt.widgets.EventTable.sendEvent(EventTable.java:84)
	at org.eclipse.swt.widgets.Display.sendEvent(Display.java:4362)
	at org.eclipse.swt.widgets.Widget.sendEvent(Widget.java:1113)
	at org.eclipse.swt.widgets.Display.runDeferredEvents(Display.java:4180)
	at org.eclipse.swt.widgets.Display.readAndDispatch(Display.java:3769)
	at org.eclipse.e4.ui.internal.workbench.swt.PartRenderingEngine$4.run(PartRenderingEngine.java:1127)
	at org.eclipse.core.databinding.observable.Realm.runWithDefault(Realm.java:337)
	at org.eclipse.e4.ui.internal.workbench.swt.PartRenderingEngine.run(PartRenderingEngine.java:1018)
	at org.eclipse.e4.ui.internal.workbench.E4Workbench.createAndRunUI(E4Workbench.java:156)
	at org.eclipse.ui.internal.Workbench$5.run(Workbench.java:694)
	at org.eclipse.core.databinding.observable.Realm.runWithDefault(Realm.java:337)
	at org.eclipse.ui.internal.Workbench.createAndRunWorkbench(Workbench.java:606)
	at org.eclipse.ui.PlatformUI.createAndRunWorkbench(PlatformUI.java:150)
	at org.eclipse.ui.internal.ide.application.IDEApplication.start(IDEApplication.java:139)
	at org.eclipse.equinox.internal.app.EclipseAppHandle.run(EclipseAppHandle.java:196)
	at org.eclipse.core.runtime.internal.adaptor.EclipseAppLauncher.runApplication(EclipseAppLauncher.java:134)
	at org.eclipse.core.runtime.internal.adaptor.EclipseAppLauncher.start(EclipseAppLauncher.java:104)
	at org.eclipse.core.runtime.adaptor.EclipseStarter.run(EclipseStarter.java:380)
	at org.eclipse.core.runtime.adaptor.EclipseStarter.run(EclipseStarter.java:235)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)
	at java.lang.reflect.Method.invoke(Unknown Source)
	at org.eclipse.equinox.launcher.Main.invokeFramework(Main.java:669)
	at org.eclipse.equinox.launcher.Main.basicRun(Main.java:608)
	at org.eclipse.equinox.launcher.Main.run(Main.java:1515)

!ENTRY org.eclipse.ui.workbench 4 2 2016-05-24 14:27:01.371
!MESSAGE Problems occurred when invoking code from plug-in: "org.eclipse.ui.workbench".
!STACK 0
java.lang.NoClassDefFoundError: org/eclipse/ui/internal/misc/StatusUtil
	at org.eclipse.ui.statushandlers.StatusManager.logError(StatusManager.java:279)
	at org.eclipse.ui.statushandlers.StatusManager.handle(StatusManager.java:201)
	at org.eclipse.ui.internal.JFaceUtil$3.show(JFaceUtil.java:73)
	at org.eclipse.jface.util.SafeRunnable.handleException(SafeRunnable.java:63)
	at org.eclipse.core.runtime.SafeRunner.handleException(SafeRunner.java:75)
	at org.eclipse.core.runtime.SafeRunner.run(SafeRunner.java:46)
	at org.eclipse.ui.internal.PartService.partHidden(PartService.java:211)
	at org.eclipse.ui.internal.WorkbenchPage$25.run(WorkbenchPage.java:5190)
	at org.eclipse.core.runtime.SafeRunner.run(SafeRunner.java:42)
	at org.eclipse.ui.internal.WorkbenchPage.firePartHidden(WorkbenchPage.java:5187)
	at org.eclipse.ui.internal.WorkbenchPage$E4PartListener.partHidden(WorkbenchPage.java:233)
	at org.eclipse.e4.ui.internal.workbench.PartServiceImpl$5.run(PartServiceImpl.java:277)
	at org.eclipse.core.runtime.SafeRunner.run(SafeRunner.java:42)
	at org.eclipse.e4.ui.internal.workbench.PartServiceImpl.firePartHidden(PartServiceImpl.java:274)
	at org.eclipse.e4.ui.internal.workbench.PartServiceImpl.access$1(PartServiceImpl.java:272)
	at org.eclipse.e4.ui.internal.workbench.PartServiceImpl$1.handleEvent(PartServiceImpl.java:91)
	at org.eclipse.e4.ui.services.internal.events.UIEventHandler$1.run(UIEventHandler.java:40)
	at org.eclipse.swt.widgets.Synchronizer.syncExec(Synchronizer.java:186)
	at org.eclipse.ui.internal.UISynchronizer.syncExec(UISynchronizer.java:145)
	at org.eclipse.swt.widgets.Display.syncExec(Display.java:4761)
	at org.eclipse.e4.ui.internal.workbench.swt.E4Application$1.syncExec(E4Application.java:211)
	at org.eclipse.e4.ui.services.internal.events.UIEventHandler.handleEvent(UIEventHandler.java:36)
	at org.eclipse.equinox.internal.event.EventHandlerWrapper.handleEvent(EventHandlerWrapper.java:197)
	at org.eclipse.equinox.internal.event.EventHandlerTracker.dispatchEvent(EventHandlerTracker.java:197)
	at org.eclipse.equinox.internal.event.EventHandlerTracker.dispatchEvent(EventHandlerTracker.java:1)
	at org.eclipse.osgi.framework.eventmgr.EventManager.dispatchEvent(EventManager.java:230)
	at org.eclipse.osgi.framework.eventmgr.ListenerQueue.dispatchEventSynchronous(ListenerQueue.java:148)
	at org.eclipse.equinox.internal.event.EventAdminImpl.dispatchEvent(EventAdminImpl.java:135)
	at org.eclipse.equinox.internal.event.EventAdminImpl.sendEvent(EventAdminImpl.java:78)
	at org.eclipse.equinox.internal.event.EventComponent.sendEvent(EventComponent.java:39)
	at org.eclipse.e4.ui.services.internal.events.EventBroker.send(EventBroker.java:85)
	at org.eclipse.e4.ui.internal.workbench.UIEventPublisher.notifyChanged(UIEventPublisher.java:59)
	at org.eclipse.emf.common.notify.impl.BasicNotifierImpl.eNotify(BasicNotifierImpl.java:374)
	at org.eclipse.e4.ui.model.application.ui.impl.ElementContainerImpl.setSelectedElement(ElementContainerImpl.java:171)
	at org.eclipse.e4.ui.workbench.renderers.swt.StackRenderer$9.widgetSelected(StackRenderer.java:1084)
	at org.eclipse.swt.widgets.TypedListener.handleEvent(TypedListener.java:248)
	at org.eclipse.swt.widgets.EventTable.sendEvent(EventTable.java:84)
	at org.eclipse.swt.widgets.Display.sendEvent(Display.java:4362)
	at org.eclipse.swt.widgets.Widget.sendEvent(Widget.java:1113)
	at org.eclipse.swt.widgets.Widget.sendEvent(Widget.java:1137)
	at org.eclipse.swt.widgets.Widget.sendEvent(Widget.java:1122)
	at org.eclipse.swt.widgets.Widget.notifyListeners(Widget.java:788)
	at org.eclipse.swt.custom.CTabFolder.setSelection(CTabFolder.java:3158)
	at org.eclipse.swt.custom.CTabFolder.onMouse(CTabFolder.java:1841)
	at org.eclipse.swt.custom.CTabFolder$1.handleEvent(CTabFolder.java:330)
	at org.eclipse.swt.widgets.EventTable.sendEvent(EventTable.java:84)
	at org.eclipse.swt.widgets.Display.sendEvent(Display.java:4362)
	at org.eclipse.swt.widgets.Widget.sendEvent(Widget.java:1113)
	at org.eclipse.swt.widgets.Display.runDeferredEvents(Display.java:4180)
	at org.eclipse.swt.widgets.Display.readAndDispatch(Display.java:3769)
	at org.eclipse.e4.ui.internal.workbench.swt.PartRenderingEngine$4.run(PartRenderingEngine.java:1127)
	at org.eclipse.core.databinding.observable.Realm.runWithDefault(Realm.java:337)
	at org.eclipse.e4.ui.internal.workbench.swt.PartRenderingEngine.run(PartRenderingEngine.java:1018)
	at org.eclipse.e4.ui.internal.workbench.E4Workbench.createAndRunUI(E4Workbench.java:156)
	at org.eclipse.ui.internal.Workbench$5.run(Workbench.java:694)
	at org.eclipse.core.databinding.observable.Realm.runWithDefault(Realm.java:337)
	at org.eclipse.ui.internal.Workbench.createAndRunWorkbench(Workbench.java:606)
	at org.eclipse.ui.PlatformUI.createAndRunWorkbench(PlatformUI.java:150)
	at org.eclipse.ui.internal.ide.application.IDEApplication.start(IDEApplication.java:139)
	at org.eclipse.equinox.internal.app.EclipseAppHandle.run(EclipseAppHandle.java:196)
	at org.eclipse.core.runtime.internal.adaptor.EclipseAppLauncher.runApplication(EclipseAppLauncher.java:134)
	at org.eclipse.core.runtime.internal.adaptor.EclipseAppLauncher.start(EclipseAppLauncher.java:104)
	at org.eclipse.core.runtime.adaptor.EclipseStarter.run(EclipseStarter.java:380)
	at org.eclipse.core.runtime.ad