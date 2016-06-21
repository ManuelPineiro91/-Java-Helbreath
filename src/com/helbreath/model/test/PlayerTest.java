package com.helbreath.model.test;

import java.awt.Point;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.helbreath.model.HGServer;
import com.helbreath.model.Map;
import com.helbreath.model.Player;
import com.helbreath.model.WLServer;

public class PlayerTest {
	private Player player = null;
	
	@Before
	public void init(){
		HGServer.getInstance().loadMaps();
		this.player = new Player("test", "test2", new Point(0,0));
	}
	
	private Player getPlayer(){
		return this.player;
	}
	
	/*@Test
	public void playerMovesToTheAssignedPosition(){		
		HGServer.getInstance().loadMaps();
		Assert.assertTrue(WLServer.getInstance().isTileAvailable("test2", new Point(1,1)));
		player = new Player("testChar", "test2", new Point(1,1));
		Assert.assertFalse(WLServer.getInstance().isTileAvailable("test2", new Point(1,1)));
		
		this.getPlayer().moveTo(new Point(0,0));
		
		Assert.assertEquals(new Point(0,0), this.getPlayer().getPosition());
		Assert.assertTrue(WLServer.getInstance().isTileAvailable("test2", new Point(1,1)));
	}
	
	@Test
	public void playerCantMoveToTheAssignedPosition(){
		HGServer.getInstance().loadMaps();
		System.out.println("first");
		WLServer.getInstance().printMap("test2");
		Assert.assertTrue(WLServer.getInstance().isTileAvailable("test2", new Point(1,1)));
		player = new Player("testChar", "test2", new Point(1,1));

		WLServer.getInstance().printMap("test2");
		
		WLServer.getInstance().setTileUnavailable("test2", new Point(0,0));
		
		WLServer.getInstance().printMap("test2");
		
		player.moveTo(new Point(0,0));
		
		Assert.assertEquals(new Point(1,1), this.getPlayer().getPosition());
	}
	
	@Test
	public void playerStartPositionUnavailable(){
		HGServer.getInstance().loadMaps();
		WLServer.getInstance().setTileUnavailable("test2", new Point(1,1));

		player = new Player("testChar", "test2", new Point(1,1));
		
		Assert.assertEquals(new Point(0,0), this.getPlayer().getPosition());
	}
	*/
	
	@Test
	public void playerGetsHitHPGoesToTenTest(){
		this.getPlayer().setHealth(20);
		this.getPlayer().hit(10);
		Assert.assertEquals(10, this.getPlayer().getHealth());
	}
	
	@Test
	public void playerGetsHitHPGoesToZeroPlayerIsDeadTest(){
		this.getPlayer().setHealth(this.getPlayer().getMaxHealth());
		Assert.assertTrue(this.getPlayer().isAlive());
		this.getPlayer().hit(100);
		Assert.assertFalse(this.getPlayer().isAlive());
	}
	
	@Test
	public void playerGetsHitMoreThanHisHealthHealthGoesToZeroTest(){
		this.getPlayer().setHealth(20);
		this.getPlayer().hit(30);
		Assert.assertEquals(0, this.getPlayer().getHealth());
	}
	
	@Test
	public void playerGetsHealedTenHealthGoesFromTenToTwentyTest(){
		this.getPlayer().setHealth(10);
		Assert.assertEquals(10, this.getPlayer().getHealth());
		this.getPlayer().heal(10);
		Assert.assertEquals(20, this.getPlayer().getHealth());
	}
	
	@Test
	public void playerGetsHealedMoreThanHisMaxHealthHealthGoesToMaxTest(){
		int playerMaxHealth = this.getPlayer().getMaxHealth();
		this.getPlayer().setHealth(10);
		Assert.assertEquals(10, this.getPlayer().getHealth());
		this.getPlayer().heal(1000);
		Assert.assertEquals(playerMaxHealth, this.getPlayer().getHealth());
	}
	
	@Test
	public void playerLevelsUpHealthGoesUpByTwoTest(){
		int playerMaxHealth = this.player.getMaxHealth();
		this.player.getExperience(200);
		Assert.assertEquals(playerMaxHealth + 2, this.getPlayer().getMaxHealth());
	}
	
	@Test
	public void playerLevelsUpManaGoesUpByTwoTest(){
		int playerMaxMana = this.getPlayer().getMaxMana();
		this.player.getExperience(200);
		Assert.assertEquals(playerMaxMana + 2, this.getPlayer().getMaxMana());
	}
	
	@Test
	public void playerLevelUpStaminaGoesUpByTwoTest(){
		int playerMaxStamina = this.getPlayer().getMaxStamina();
		this.player.getExperience(200);
		Assert.assertEquals(playerMaxStamina + 2, this.getPlayer().getMaxStamina());
	}
	
	@Test
	public void playerLevelsUpCarryWeightGoesUpByFiveTest(){
		int playerMaxCarryWeight = this.getPlayer().getMaxCarryWeight();
		this.player.getExperience(200);
		Assert.assertEquals(playerMaxCarryWeight + 5, this.getPlayer().getMaxCarryWeight());
	}
	
	@Test
	public void playerAddsTwoPointToStrHealthGoesUpByOneTest(){
		int playerMaxHealth = this.getPlayer().getMaxHealth();
		this.getPlayer().addPoints(2,0,0,0,0,0);
		Assert.assertEquals(playerMaxHealth + 1, this.getPlayer().getMaxHealth());
	}
	
	@Test
	public void playerAddsOnePointToStrStaminaGoesUpByTwoTest(){
		int playerMaxStamina = this.getPlayer().getMaxStamina();
		this.getPlayer().addPoints(1, 0, 0, 0, 0, 0);
		Assert.assertEquals(playerMaxStamina + 2, this.getPlayer().getMaxStamina());
	}
	
	@Test
	public void playerAddsOnePointToStrCarryWeightGoesUpByFiveTest(){
		int playerMaxCarryWeight = this.getPlayer().getMaxCarryWeight();
		this.getPlayer().addPoints(1, 0, 0, 0, 0, 0);
		Assert.assertEquals(playerMaxCarryWeight + 5, this.getPlayer().getMaxCarryWeight());
	}
	
	@Test
	public void playerAddsOnePointToVitHealthGoesUpByThreeTest(){
		int playerMaxHealth = this.getPlayer().getMaxHealth();
		this.getPlayer().addPoints(0,0,1,0,0,0);
		Assert.assertEquals(playerMaxHealth + 3, this.getPlayer().getMaxHealth());
	}
	
	@Test
	public void playerAddsTwoPointsToIntManaGoesUpByOneTest(){
		int playerMaxMana = this.getPlayer().getMaxMana();
		this.getPlayer().addPoints(0,0,0,0,2,0);
		Assert.assertEquals(playerMaxMana + 1, this.getPlayer().getMaxMana());
	}
	
	@Test
	public void playerAddsOnePointToMagManaGoesUpByTwoTest(){
		int playerMaxMana = this.getPlayer().getMaxMana();
		this.getPlayer().addPoints(0,0,0,0,0,1);
		Assert.assertEquals(playerMaxMana + 2, this.getPlayer().getMaxMana());
	}
}