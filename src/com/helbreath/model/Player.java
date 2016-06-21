package com.helbreath.model;

import java.awt.Point;

public class Player implements IHitable {
	private String _name;
	private String _location;
	private Point _position;

	private Stats stats;
	
	private int currentHealth; 
	
	public Player(String name, String location, Point position){
		this._name = name;
		this._location = location;
		this.setPosition(HGServer.getInstance().setStartPosition(this._location, position));
		this.setStats();
	}
	
	private void setStats(){
		this.stats = new Stats(1, 0, 10, 10, 10, 10, 10, 10); //load desde db
	}
	
	public String getLocation(){
		return this._location;
	}
	
	public Point getPosition(){
		return this._position;
	}
	
	public void moveTo(Point pos){
		HGServer.getInstance().movementRequest(this, this.getLocation(), pos);
	}
	
	public void setPosition(Point position){
		this._position = position;
	}
	
	public Stats getStats(){
		return this.stats;
	}
	
	@Override
	public int getMaxHealth(){
		return this.getStats().getMaxHealth();
	}

	@Override
	public int getHealth() {
		return this.currentHealth;
	}

	@Override
	public boolean isAlive() {
		return this.getHealth() > 0;
	}

	@Override
	public void hit(int rawDamage) {
		this.currentHealth -= rawDamage;
		
		if(this.getHealth() < 0) this.currentHealth = 0;
	}

	@Override
	public void setHealth(int health){
		this.currentHealth = health;
		if(this.getHealth() > this.getMaxHealth()) this.currentHealth = this.getMaxHealth();
	}

	@Override
	public void heal(int amount) {
		this.currentHealth += Math.abs(amount);
		if(this.getHealth() > this.getMaxHealth()) this.currentHealth = this.getMaxHealth();
	}

	public void getExperience(int amount) {
		this.getStats().getExperience(amount);		
	}

	public int getMaxMana() {
		return this.getStats().getMaxMana();
	}

	public int getMaxStamina() {
		return this.getStats().getMaxStamina();
	}

	public int getMaxCarryWeight() {
		return this.getStats().getMaxCarryWeight();
	}

	public void addPoints(int strength, int dexterity, int vitality, int charisma, int intelligence, int magic) {
		this.getStats().addPoints(strength, dexterity, vitality, charisma, intelligence, magic);
	}
}
