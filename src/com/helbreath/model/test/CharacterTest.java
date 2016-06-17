package com.helbreath.model.test;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import com.helbreath.model.Map;
import com.helbreath.model.Player;
import com.helbreath.model.WLServer;

public class CharacterTest {
	private Player player = null;
	
	@Before
	public void init(){
		player = new Player("testChar", "test2", new Point(1,1));
	}
	
	@Test
	public void playerMovesToTheAssignedPosition(){
		WLServer.getInstance().registerMap(new Map("test2"));
		
		player.moveTo(new Point(0,0));
	}
}
