package com.helbreath.model.test;

import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.helbreath.model.Grid;
import com.helbreath.model.Map;
import com.helbreath.model.MapLoader;
import com.helbreath.model.exception.FileExistsException;

public class MapLoaderTest {

	private Map map;
	private String testName = "testName";
	
	private Map getMap(){
		return this.map;
	}
	
	@Test
	public void nameOnCreationIsTestName(){
		MapLoader.getInstance().createMapFile(this.testName, new Grid(2,2));
		
		map = new Map(this.testName);
		
		Assert.assertEquals(this.getMap().getName(), this.testName);
		
		try{
			MapLoader.getInstance().deleteMapFile(this.testName);
		}catch(FileNotFoundException | FileExistsException e){
			System.out.println(e.getClass());
		}
	}
}
