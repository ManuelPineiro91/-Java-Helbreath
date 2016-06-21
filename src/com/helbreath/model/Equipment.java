package com.helbreath.model;

public class Equipment {

	private final int NUMBER_OF_SLOTS = 10;
	
	public enum EPosition{
		Head(0), BodyLower(1), BodyUpper(2), Legs(3), Boots(4), Ring(5), Neck(6), Back(7), RHand(8), LHand(9);
		
		private final int value;
		
		private EPosition(int value){
			this.value = value;
		} 
	}
	
	private IWearable[] slots;
	private EmptySlot empty;
	
	public Equipment(){
		this.slots = new IWearable[this.NUMBER_OF_SLOTS];
		this.empty = new EmptySlot();
	}
	
	private IWearable[] getSlots(){
		return this.slots;
	}
	
	public void set(IWearable item){
		this.getSlots()[item.getPosition().value] = item;
	}
	
	public IWearable remove(EPosition position){
		IWearable item = this.getSlots()[position.value];
		this.getSlots()[position.value] = this.empty;
		return item;
	}
	
	private int getDef(int pos){
		return this.getSlots()[pos].getDef();
	}
	
	public int getTotalDef(){
		int totalDef = 0;
		for(int i=0; i < this.NUMBER_OF_SLOTS; i++){
			totalDef += this.getSlots()[i].getDef();
		}
		return totalDef;
	}
	
	private int getPA(int pos){
		return this.getSlots()[pos].getPA();
	}
	
	public int getTotalPA(){
		int totalPA = 0;
		for(int i=0; i < this.NUMBER_OF_SLOTS; i++){
			totalPA += this.getSlots()[i].getPA();
		}
		return totalPA;
	}
	
	private int getMR(int pos){
		return this.getSlots()[pos].getMR();
	}
	
	public int getTotalMR(){
		int totalMR = 0;
		for(int i=0; i < this.NUMBER_OF_SLOTS; i++){
			totalMR += this.getSlots()[i].getMR();
		}
		return totalMR;
	}
	
	private int getMA(int pos){
		return this.getSlots()[pos].getMA();
	}
	
	public int getTotalMA(){
		int totalMA = 0;
		for(int i=0; i < this.NUMBER_OF_SLOTS; i++){
			totalMA += this.getSlots()[i].getMA();
		}
		return totalMA;
	}
}
