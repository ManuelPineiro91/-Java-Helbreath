package com.helbreath.model;

import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.helbreath.model.exception.NoTileAvailableException;

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
	
	public void printMapGrid(){
		System.out.println("Printing map:" + this.getName() + " grid.");
		int width = this.getWidth(), height = this.getHeight();
		
		for(int i = 0; i < height; i++){
			System.out.println();
			for(int j = 0; j < width; j++){
				System.out.print(this.isTileAvailable(j, i) + " ");
			}
		}
		System.out.println();
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

	public Point setPosition(Point position) {
		if(this.isTileAvailable(position.x, position.y)){
			this.setUnavailable(position.x, position.y);
			return position;
		}else{
			return this.closestPositionTo(position);
		}
	}

	private boolean isTileAvailable(Point coord){
		return this.getGrid().isTileAvailable(coord.x, coord.y);
	}
	
	private boolean isLastTile(Point coord){
		return (coord.x == this.getWidth()-1 && coord.y == this.getHeight() - 1);
	}
	
	private Point closestPositionTo(Point position) {
		boolean tilesAvailable = true;
		Point nextPosition = null;
		Point currentPosition = position;
		while(!this.isLastTile(currentPosition) && tilesAvailable){
			if(currentPosition.y > 0){
				if(currentPosition.x > 0){
					nextPosition = new Point(currentPosition.x-1, currentPosition.y - 1);
					if(this.isTileAvailable(nextPosition)) return nextPosition;
				}
				nextPosition = new Point(currentPosition.x, currentPosition.y - 1);
				if(this.isTileAvailable(nextPosition)) return nextPosition;
				
				if(currentPosition.x < this.getWidth() - 1){
					nextPosition = new Point(currentPosition.x + 1, currentPosition.y - 1);
					if(this.isTileAvailable(nextPosition)) return nextPosition;
				}
			}
			
			if(currentPosition.x > 0){
				nextPosition = new Point(currentPosition.x - 1, currentPosition.y);
				if(this.isTileAvailable(nextPosition)) return nextPosition;
			}
			if(currentPosition.x < this.getWidth() - 1){
				nextPosition = new Point(currentPosition.x + 1, currentPosition.y);
				if(this.isTileAvailable(nextPosition)) return nextPosition;
			}
			
			if(currentPosition.y < this.getHeight() - 1){
				if(currentPosition.x > 0){
					nextPosition = new Point(currentPosition.x - 1, currentPosition.y + 1);
					if(this.isTileAvailable(nextPosition)) return nextPosition;
				}
				
				nextPosition = new Point(currentPosition.x, currentPosition.y + 1);
				if(this.isTileAvailable(nextPosition)) return nextPosition;
				
				if(currentPosition.x < this.getWidth() - 1){
					nextPosition = new Point(currentPosition.x + 1, currentPosition.y + 1);
					if(this.isTileAvailable(nextPosition)) return nextPosition;
				}
			}
			
			if(currentPosition.x < this.getWidth() - 1){
				if(this.isTileAvailable( new Point(currentPosition.x + 1, currentPosition.y)))
					currentPosition = new Point(currentPosition.x + 1, currentPosition.y);
			} else if(currentPosition.y < this.getHeight() - 1){
				if(this.isTileAvailable(new Point(currentPosition.x, currentPosition.y + 1)))
					currentPosition = new Point(currentPosition.x + 1, currentPosition.y);
			}else{
				tilesAvailable = false;
			}
		}
		
		throw new NoTileAvailableException();
	}
}
