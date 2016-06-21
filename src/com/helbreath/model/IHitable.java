package com.helbreath.model;

public interface IHitable {
	
	public int getHealth();
	public int getMaxHealth();
	public boolean isAlive();
	public void hit(int rawDamage);
	public void setHealth(int totalHealth);
	public void heal(int amount);
}
