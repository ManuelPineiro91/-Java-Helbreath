package com.helbreath.model;

import com.helbreath.model.Equipment.EPosition;
import com.helbreath.model.exception.EmptySlotException;

public class EmptySlot implements IWearable {

	@Override
	public EPosition getPosition() {
		throw new EmptySlotException();
	}

	@Override
	public int getDef() {
		return 0;
	}

	@Override
	public int getPA() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMR() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMA() {
		// TODO Auto-generated method stub
		return 0;
	}
}
