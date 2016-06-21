package com.helbreath.model;

public class Stats {
	
	private int level;
	private long experience;
	private long nextLevelExperience = 0;
	
	private int strength;
	private int dexterity;
	private int vitality;
	private int charisma;
	private int intelligence;
	private int magic;
	
	private int pointsToSet;

	public Stats(int level, int experience, int strength, int dexterity, int vitality, int charisma, int intelligence, int magic){
		this.setLevel(level, experience);
		this.setStats(strength, dexterity, vitality, charisma, intelligence, magic);
	}
	
	private void setLevel(int level, int experience){
		this.level = level;
		this.experience = experience;
		this.nextLevelExperience = 100;//saca de db.
	}
	
	private void setStats(int strength, int dexterity, int vitality, int charisma, int intelligence, int magic){
		this.strength = strength;
		this.dexterity = dexterity;
		this.vitality = vitality;
		this.charisma = charisma;
		this.intelligence = intelligence;
		this.magic = magic;
	}
	
	public void addPoints(int strength, int dexterity, int vitality, int charisma, int intelligence, int magic){
		this.strength += strength;
		this.dexterity += dexterity;
		this.vitality += vitality;
		this.charisma += charisma;
		this.intelligence += intelligence;
		this.magic += magic;
	}
	
	public int getMaxHealth(){
		return this.getVit() * 3 + this.getLevel() * 2 + this.getStr() / 2; 
	}
	
	public int getMaxMana(){
		return this.getMag() * 2 + this.getLevel() * 2 + this.getInt() / 2;
	}
	
	public int getMaxStamina(){
		return this.getStr() * 2 + this.getLevel() * 2;
	}
	
	public int getMaxCarryWeight(){
		return this.getStr() * 5 + this.getLevel() * 5;
	}
	
	public int getStr(){
		return this.strength;
	}
	
	public int getDex(){
		return this.dexterity;
	}
	
	public int getVit(){
		return this.vitality;
	}
	
	public int getChar(){
		return this.charisma;
	}
	
	public int getInt(){
		return this.intelligence;
	}
	
	public int getMag(){
		return this.magic;
	}
	
	public int getLevel(){
		return this.level;
	}
	
	public void getExperience(int amount){
		this.experience += amount;
		if(this.experience > this.nextLevelExperience){
			this.experience = this.experience - this.nextLevelExperience;
			this.nextLevelExperience = 200; // leer de db.
			this.level++;
		}
	}
}
