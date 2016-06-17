package com.helbreath.model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Map {
	
	private String _name = "";
	private Grid _grid = null;
	
	public Map(String name){
		MapLoader.getInstance().loadMap(name, this);
	}
	
	public String getName(){
		return this._name;
	}
	
	public Grid getGrid(){
		return this._grid;
	}
	
	public boolean isChangeZoneTile(int x, int y){
		return this.getGrid().isChangeZoneTile(x,y);
	}
	
	public boolean isTileAvailable(int x, int y){
		return this.getGrid().isTileAvailable(x, y);
	}
	
	public int getSize(){
		return this._grid.getSize();
	}
	
	public void setAvailable(int x, int y){
		this._grid.setAvailable(x, y);
	}
	
	public void setUnavailable(int x, int y){
		this._grid.setUnavailable(x, y);
	}

	public void setName(String name) {
		this._name = name;
	}

	public void setGrid(Grid grid) {
		this._grid = grid;
	}

	public int getWidth() {
		return this.getGrid().getWidth();
	}

	public int getHeight() {
		return this.getGrid().getHeight();
	}
}
