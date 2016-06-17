package com.helbreath.model.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.helbreath.model.Tile;

public class TileTest {

	private Tile _tile = null;
	
	private Tile getTile(){
		return this._tile;
	}
	
	@Before
	public void init(){
		this._tile = new Tile();
	}

	@Test
	public void tileIsAvailableAtInitializationTest(){
		Assert.assertTrue(this.getTile().isAvailable());
	}
	
	@Test
	public void tileIsUnavailableTest(){
		this.getTile().setUnavailable();
		Assert.assertFalse(this.getTile().isAvailable());
	}
	
	@Test
	public void tileIsAvailableAfterBeingUnavailableTest(){
		this.getTile().setUnavailable();
		Assert.assertFalse(this.getTile().isAvailable());
		this.getTile().setAvailable();
		Assert.assertTrue(this.getTile().isAvailable());
	}
	
}
