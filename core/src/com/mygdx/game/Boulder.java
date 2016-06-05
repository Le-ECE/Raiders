package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;

/**
 * The Boulder class is responsible for the rendering and movement of boulders. Boulders and their
 * locations are stored in a .tmx map file. Those objects and properties are extracted and used
 * by the Boulder class. This class does not have a display function, but simply serves as a data
 * class for boulder calculations and data extraction.
 * 
 * @author Brian Tran
 * @version 4.0 03/06/2016
 * 
 * <p>
 * <b>Instance Variables</b>
 * <p>
 * <b>TiledMap</b> Instance of TiledMap used to store the custom .tmx files for maps
 * <p>
 * <b>boulderArr</b> Array of Rectangle used to store locations and properties of boulders
 * <p>
 * <b>numOfBoulders</b> Int representing how many boulders are on the map.
 * <p>
 * <b>returningArr</b> boolean array representing a boulder returning from end position
 * <p>
 *
 */
public class Boulder{

	private TiledMap map;

	ArrayList <Rectangle> boulderArr = new ArrayList <Rectangle>();
	

	//boolean isReturning = false;

	private int numOfBoulders;
	
	boolean [] returningArr;

	/**
	 * The Boulder constructor is responsible for passing in a TiledMap for use, as well as
	 * initializing the global variable numOfBoulders and the boolean array returningArr.
	 * 
	 * @param map TiledMap representing current game map
	 */
	public Boulder (TiledMap map){
		this.map = map;
		numOfBoulders = Integer.parseInt(map.getProperties().get("numOfBoulders", String.class));
		returningArr = new boolean [numOfBoulders];
	}

	/**
	 * This method is responsible for returning a Rectangle array representing each boulder
	 * object on the map. The first for loop runs through all the boulder objects in the
	 * map layer "properties". The first if statement determines if the object is a Rectangle.
	 * If so, it is added to the array.
	 * 
	 * @return boulderArr Rectangle array representing boulder objects
	 */
	public ArrayList <Rectangle> getBoulders(){
		// boulder1 create
		boulderArr.clear();
		for (MapObject object : map.getLayers().get("properties").getObjects()) {
			if (object instanceof RectangleMapObject) {
				boulderArr.add(((RectangleMapObject) object).getRectangle());
			}
		}

		return boulderArr;

	}

	/**
	 * This method is responsible for returning an integer array of the maximum distances each
	 * boulder on the map is allowed to travel. The first for loop runs through the properties
	 * of each boulder, adding every distance property to the integer array.
	 * 
	 * @return distanceArr Integer array representing every max distance of boulder on the map
	 */
	public ArrayList<Integer> getDistance(){

		numOfBoulders = Integer.parseInt(map.getProperties().get("numOfBoulders", String.class));

		ArrayList <Integer> distanceArr = new ArrayList <Integer>();

		for (int x = 1 ; x <= numOfBoulders; x++){
			distanceArr.add(Integer.parseInt(map.getLayers().get ("properties").getObjects().get ("boulder"+x).getProperties().get ("distance", String.class)));
		}
		
		return distanceArr;
	}

	/**
	 * This method is responsible for returning a String array of the directions each boulder
	 * object on the map is supposed to travel in. The first for loop runs through the properties
	 * of each boulder, add every direction property to the String array.
	 * @return
	 */
	public ArrayList <String> getDirection(){

		ArrayList <String> directionArr = new ArrayList <String>();

		for (int x = 1; x <= numOfBoulders; x++){
			directionArr.add(String.valueOf(map.getLayers().get ("properties").getObjects().get ("boulder"+x).getProperties().get("direction")));
		}
		return directionArr;
	}

	/**
	 * This method is responsible for returning an integer array of the starting x-coordinates
	 * of all of the boulder objects on the map. The first for loop runs through the boulder array
	 * and adds its x-values to the array.
	 * @return xArr integer array containing starting x-coords of all boulders
	 */
	public ArrayList <Integer> getStartX(){
		ArrayList <Integer> xArr = new ArrayList <Integer>();

		for (int x = 0; x < boulderArr.size(); x++){
			xArr.add((int)boulderArr.get(x).x);
		}
		return xArr;

	}

	/**
	 * This method is responsible for returning an integer array of the starting y-coordinates
	 * of all of the boulder objects on the map. The first for loop runs through the boulder array
	 * and adds its y-values to the array.
	 * @return yArr integer array containing starting y-coords of all boulders
	 */
	public ArrayList <Integer> getStartY(){
		ArrayList <Integer> yArr = new ArrayList <Integer>();

		for (int x = 0; x < boulderArr.size(); x++){
			yArr.add((int)boulderArr.get(x).y);
		}
		return yArr;

	}

	/**
	 * This method is responsible for changing the x and y values of the boulders in
	 * the GameScreen class. The first if statement determines if the boulder is traveling
	 * in a horizontal or vertical plane. The second if statement determines if the boulder
	 * is at its starting point. If so, the boulder is not in a returning state. The third
	 * if statement determines if the boulder is at its maximum point. If so, it is in a
	 * returning state. The fourth if statement determines if the leftward traveling boulder
	 * is at its starting location. If so, it is not in a return state. The fifth if statement
	 * determines if the leftward boulder is at its minimum point. If so, it is in a returning
	 * state. The sixth if statement determines if the boulder is traveling up. If so, the seventh
	 * if statement determines if the boulder is at its starting y value. If so, it is not in a
	 * return state. The eighth if statement determines if the boulder is at is maximum point. If
	 * so, it is in a return state. The ninth if statement determines if a downward traveling boulder
	 * is at its starting y value. If so, it is not in a return state. The tenth if statement determines
	 * if the boulder is at its minimum point. If so, it is in a return state.
	 * 
	 * @param index int representing the index of the array, or what boulder this method refers to
	 * @param distance string representing the maximum distance of the boulder
	 * @param direction string representing the direction of travel of the boulder
	 */
	public void update (int index, int distance, String direction){
		int tempX = GameScreen.boulderXArr.get(index);
		int tempY = GameScreen.boulderYArr.get(index);
		
		numOfBoulders = Integer.parseInt(map.getProperties().get("numOfBoulders", String.class));

		ArrayList <Integer> startY = getStartY();
		ArrayList <Integer>	startX = getStartX();

		if (direction.equals("left")||direction.equals("right")){
			if (direction.equals("right")){
				if (tempX == startX.get(index)) returningArr[index] = true;
				if (tempX == startX.get(index) + distance) returningArr[index] = false;
					GameScreen.boulderXArr.set(index, tempX+=(returningArr[index]?2:-2));
			}
			else{
				if (tempX == startX.get(index)) returningArr[index] = true;
				if (tempX == startX.get(index) - distance) returningArr[index] = false;
					GameScreen.boulderXArr.set(index, tempX+=(returningArr[index]?-2:2));
			}
		}
		else{
			if (direction.equals("up")){
				if (tempY == startY.get(index)) returningArr[index] = true;
				if (tempY == startY.get(index) + distance) returningArr[index] = false;
					GameScreen.boulderYArr.set(index, tempY+=(returningArr[index]?2:-2));
			}
			else{
				if (tempY == startY.get(index)) returningArr[index] = true;
				if (tempY == startY.get(index) - distance) returningArr[index] = false;
					GameScreen.boulderYArr.set(index, tempY+=(returningArr[index]?-2:2));
			}
		}
	}

}
