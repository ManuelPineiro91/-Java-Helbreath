package com.helbreath.model;

import java.awt.Point;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.helbreath.model.exception.UnexistantMapException;

public class WLServer {
	
	private final static WLServer INSTANCE = new WLServer();
	
	private Set _maps = null;
	
	private WLServer(){
		this._maps = new HashSet<Map>();
	}
	
	public static WLServer getInstance(){
		return INSTANCE;
	}
	
	private Set getMaps(){
		return this._maps;
	}
	
	public void registerMap(Map map){
		this.getMaps().add(map);
	}
	
	private Map findMapByName(String name){
		Iterator iterator = this.getMaps().iterator();
		Map current = null;
		while(iterator.hasNext()){
			current = ((Map)iterator.next());
			if(current.getName().equals(name)){
				return current;
			}
		}
		
		throw new UnexistantMapException();
	}
	
	public boolean isTileAvailable(String name, Point position){
		try{
			return this.findMapByName(name).isTileAvailable(position.x, position.y);
		}catch(UnexistantMapException e){
			throw e;
		}
	}
	
	public void setTileAvailable(String mapName, Point coord){
		this.findMapByName(mapName).setAvailable(coord.x, coord.y);		
	}

	public void setTileUnavailable(String mapName, Point coord){
		this.findMapByName(mapName).setUnavailable(coord.x, coord.y);		
	}
	
	public void movementRequest(Player character, String mapName, Point position) {
		if(this.isTileAvailable(mapName, position)){
			if(this.isMapChangeZone(mapName, position)){
				//character.setLocation(linkedmap);
				//character.setPosition(linkedMapStarLoc);
			}else{
				this.setTileAvailable(mapName, character.getPosition());
				character.setPosition(position);
				this.setTileUnavailable(mapName, position);				
			}
		}
	}
	
	private boolean isMapChangeZone(String mapName, Point position){
		try{
			return this.findMapByName(mapName).isChangeZoneTile(position.x, position.y);
		}catch(UnexistantMapException e){
			throw e;
		}
	}

	public Point setStartPosition(String mapName, Point position) {
		try{
			return this.findMapByName(mapName).setPosition(position);
		}catch(UnexistantMapException e){
			throw e;
		}
	}

	public void printMap(String mapName) {
		try{
			this.findMapByName(mapName).printMapGrid();
		}catch(UnexistantMapException e){
			throw e;
		}
	}
}
