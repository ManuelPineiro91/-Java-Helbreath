package com.helbreath.model;

import java.util.HashMap;

public class PlayerLoader {
	private static final PlayerLoader INSTANCE = new PlayerLoader();
	
	private PlayerLoader(){}
	
	//private HashMap<String, String> 
	
	public static PlayerLoader getInstance(){
		return INSTANCE;
	}
	
	
}
