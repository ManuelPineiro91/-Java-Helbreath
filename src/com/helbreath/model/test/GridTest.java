package com.helbreath.model.test;

import org.junit.Assert;
import org.junit.Test;

import com.helbreath.model.Grid;

public class GridTest {

	private Grid _grid = null;
	
	private Grid getGrid(){
		return this._grid;
	}
	
	@Test
	public void gridWidthIsFiftyAndWidthForty(){
		this._grid = new Grid(50, 40);
		Assert.assertEquals(50, this.getGrid().getWidth());
		Assert.assertEquals(40, this.getGrid().getHeight());
	}
	
	@Test
	public void setTileTileUnavailable(){
		this._grid = new Grid(10, 10);
		Assert.assertTrue(this.getGrid().getTileAt(5, 3).isAvailable());
		this.getGrid().setUnavailable(5, 3);
		Assert.assertFalse(this.getGrid().getTileAt(5, 3).isAvailable());
	}
	
	@Test
	public void setTileTileAvailable(){
		this._grid = new Grid(10, 10);
		this.getGrid().setUnavailable(5, 3);
		Assert.assertFalse(this.getGrid().getTileAt(5, 3).isAvailable());
		this.getGrid().setAvailable(5, 3);
		Assert.assertTrue(this.getGrid().getTileAt(5, 3).isAvailable());
	}
}
