package com.helbreath.model;

import com.helbreath.model.Equipment.EPosition;

public interface IWearable {
	public EPosition getPosition();

	public int getDef();

	public int getPA();

	public int getMR();

	public int getMA();
}
