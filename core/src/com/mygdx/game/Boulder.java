package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;

public class Boulder{

	private TiledMap map;

	ArrayList <Rectangle> boulderArr = new ArrayList <Rectangle>();

	private int numOfBoulders;

	public Boulder (TiledMap map){
		this.map = map;
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
		return distanceArr;
	}

	public ArrayList <String> getDirection(){

		numOfBoulders = Integer.parseInt(map.getProperties().get("numOfBoulders", String.class));
		
		System.out.println("num of boulders: "+numOfBoulders);

		ArrayList <String> directionArr = new ArrayList <String>();

		for (int x = 1; x <= numOfBoulders; x++){
			directionArr.add(map.getLayers().get ("properties").getObjects().get ("boulder"+x).getProperties().get("direction", String.class));
		}
		return directionArr;
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
		
		ArrayList <Integer> startY = getStartY();
		ArrayList <Integer>	startX = getStartX();
		
		for (int x = 0;x<startX.size();x++){
			System.out.println("***************************");
			System.out.println("start x "+x+" : "+startX.get(x));
			System.out.println("start y "+x+" : "+startY.get(x));
			System.out.println("***************************");
		}
		
		System.out.println("temp y: "+tempY );
		if (direction.equals("left")||direction.equals("right")){
			if (direction.equals("right")){
				if (tempX<startX.get(index)+distance){
					GameScreen.boulderXArr.set(index, tempX+=2);
				}
				else{
					GameScreen.boulderXArr.set(index, startX.get(index));
				}
			}
			else{
				if (tempX>startX.get(index)-distance){
					GameScreen.boulderXArr.set(index, tempX-=2);
				}
				else{
					GameScreen.boulderXArr.set(index, startX.get(index));
				}
			}
		}
		else{
			if (direction.equals("up")){
				if (tempY<startY.get(index)+distance){
					GameScreen.boulderYArr.set(index, tempY+=2);
				}
				else{
					GameScreen.boulderYArr.set(index, startY.get(index));
				}
			}
			else{
				if (tempY > startY.get(index) - distance){
					GameScreen.boulderYArr.set(index, tempY-=2);
				}
				else{
					GameScreen.boulderYArr.set(index, startY.get(index));
				}
			}
		}
	}

}
