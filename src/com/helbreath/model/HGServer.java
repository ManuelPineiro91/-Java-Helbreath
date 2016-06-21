package com.helbreath.model;

import java.awt.Point;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HGServer {

	private ServerSocket svSocket = null;
	private Socket[] clients = null;
	
	private final int SERVER_PORT = 4321;
	private final static HGServer INSTANCE = new HGServer();
	private final int MAX_CLIENTS = 1;
	
	private HGServer(){
	}

	public static HGServer getInstance() {
		return INSTANCE;
	}
	
	public void init(){
		try {
			this.svSocket = new ServerSocket(this.SERVER_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.clients = new Socket[MAX_CLIENTS];
		
		try {
			this.clients[0] = this.svSocket.accept();
			System.out.println("client connected: " + this.clients[0].isConnected());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadMaps(){
		WLServer.getInstance().registerMap(new Map("test2"));			
	}
	
	public void movementRequest(Player character, String mapName, Point position){
		WLServer.getInstance().movementRequest(character, mapName, position);
	}

	public Point setStartPosition(String mapName, Point position) {
		return WLServer.getInstance().setStartPosition(mapName, position);
	}
	
	public void registerClient(){
		
	}
	
	
}
