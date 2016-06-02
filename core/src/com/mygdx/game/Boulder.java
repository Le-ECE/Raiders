package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;

public class Boulder{

	private TiledMap map;

	ArrayList <Rectangle> boulderArr = new ArrayList <Rectangle>();
	

	boolean isReturning = false;

	private int numOfBoulders;
	
	boolean [] returningArr;

	public Boulder (TiledMap map){
		this.map = map;
		numOfBoulders = Integer.parseInt(map.getProperties().get("numOfBoulders", String.class));
		returningArr = new boolean [numOfBoulders];
	}

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

	public ArrayList<Integer> getDistance(){

		numOfBoulders = Integer.parseInt(map.getProperties().get("numOfBoulders", String.class));

		ArrayList <Integer> distanceArr = new ArrayList <Integer>();

		for (int x = 1 ; x <= numOfBoulders; x++){
			distanceArr.add(Integer.parseInt(map.getLayers().get ("properties").getObjects().get ("boulder"+x).getProperties().get ("distance", String.class)));
		}
		
		for (int x: distanceArr){
			System.out.println("distance: "+x);
		}
		System.out.println(" break ");
		
		return distanceArr;
	}

	public ArrayList <String> getDirection(){

		//System.out.println("num of boulders: "+numOfBoulders);

		ArrayList <String> directionArr = new ArrayList <String>();

		for (int x = 1; x <= numOfBoulders; x++){
			directionArr.add(String.valueOf(map.getLayers().get ("properties").getObjects().get ("boulder"+x).getProperties().get("direction")));
		}
		for (String s : directionArr){
			System.out.println("direction: "+s);
		}
		System.out.println("   break   ");
		return directionArr;
	}

	public ArrayList <Boolean> getYoyo(){

		numOfBoulders = Integer.parseInt(map.getProperties().get("numOfBoulders", String.class));

		ArrayList <Boolean> yoyoArr = new ArrayList <Boolean>();

		for (int x = 1; x <= numOfBoulders; x++){
			yoyoArr.add (Boolean.parseBoolean(map.getLayers().get("properties").getObjects().get("boulder"+x).getProperties().get("yoyo", String.class)));
		}

		return yoyoArr;

	}

	public ArrayList <Integer> getStartX(){
		ArrayList <Integer> xArr = new ArrayList <Integer>();

		for (int x = 0; x < boulderArr.size(); x++){
			xArr.add((int)boulderArr.get(x).x);
		}
		return xArr;

	}

	public ArrayList <Integer> getStartY(){
		ArrayList <Integer> yArr = new ArrayList <Integer>();

		for (int x = 0; x < boulderArr.size(); x++){
			yArr.add((int)boulderArr.get(x).y);
		}
		return yArr;

	}

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
