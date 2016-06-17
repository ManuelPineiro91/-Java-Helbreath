package com.helbreath.model;

public class Tile {
	
	private boolean _available = true;
	private boolean _zoneChange = false;
	
	public boolean isAvailable(){
		return this._available;
	}
	
	public boolean isChangeZoneTile(){
		return this._zoneChange;
	}
	
	public void setAsChangeZoneTile(){
		this._zoneChange = true;
	}
	
	public void setAsNoChangeZoneTile(){
		this._zoneChange = false;
	}
	
	public void setAvailable(){
		this._available = true;
	}
	
	public void setUnavailable(){
		this._available = false;
	}
}
