package com.helbreath.model;

import java.awt.Point;

public class HGServer {

	public static void main(String[] args){
		Map newMap = new Map("test");
		MapLoader.getInstance().createMapFile("test2", new Grid(5,5));
	}
	
	public static void movementRequest(Player character, String mapName, Point position){
		WLServer.getInstance().movementRequest(character, mapName, position);
	}
}
