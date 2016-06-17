package com.helbreath.model;

import java.awt.Point;

public class Player {
	private String _name;
	private String _location;
	private Point _position; 
	
	public Player(String name, String location, Point position){
		this._name = name;
		this._location = location;
		this._position = position;
	}
	
	public String getLocation(){
		return this._location;
	}
	
	public Point getPosition(){
		return this._position;
	}
	
	public void moveTo(Point pos){
		HGServer.movementRequest(this, this.getLocation(), this.getPosition());
	}
	
	public void setPosition(Point position){
		this._position = position;
	}
}
