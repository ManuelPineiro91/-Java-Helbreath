package com.helbreath.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.helbreath.model.exception.FileExistsException;

public class MapLoader {
	
	private static final MapLoader INSTANCE = new MapLoader();
	
	private MapLoader(){}
	
	public static MapLoader getInstance(){
		return INSTANCE;
	}
	
	private static final String NAME = "NAME";
	private static final String WIDTH = "WIDTH";
	private static final String HEIGHT = "HEIGHT";
	private static final String GRID = "GRID";
	private static final String PATH = "C:" + File.separator + "Maps" + File.separator;
	
	public void loadMap(String name, Map map){
		this.createMapFromJSONObject(this.readFromJSONFile(MapLoader.PATH + name + ".txt"), map);
		System.out.println("Map at: " + MapLoader.PATH + name + ".txt" + " loaded.");
		System.out.println();
		map.printMapGrid();
	}
	
	public void deleteMapFile(String name) throws FileNotFoundException{
		System.out.println("Trying to delete file: " + MapLoader.PATH + name + ".txt");
	
		Path path = Paths.get(MapLoader.PATH + name + ".txt");
		
		try{
			Files.delete(path);				
		}catch(NoSuchFileException e){
			System.out.println(e.getMessage());
		}catch(DirectoryNotEmptyException e){
			System.out.println(e.getMessage());
		}catch(IOException e){
			System.out.println(e.getMessage());
		}catch(SecurityException e){
			System.out.println(e.getMessage());
		}
	}
	
	public void createMapFile(String name, Grid grid){
		this.writeToFile(this.createMapJSONObject(name, grid));
	}
	
	private JSONObject createMapJSONObject(String name, Grid grid){
		JSONObject obj = new JSONObject();
		
		obj.put(MapLoader.NAME, name);
		obj.put(MapLoader.WIDTH, grid.getWidth());
		obj.put(MapLoader.HEIGHT, grid.getHeight());
		obj.put(MapLoader.GRID, this.booleanArrayToJSONArray(this.gridMatrixToArray(grid)));
		
		return obj;
	}
	
	private void writeToFile(JSONObject obj){
		FileWriter fileWriter;
		File file;
		try{
			file = new File(MapLoader.PATH +  obj.get(MapLoader.NAME) + ".txt");
			if(!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			
			fileWriter = new FileWriter(file);
			fileWriter.write(obj.toJSONString());
			
			System.out.println("File created at: " + file.getAbsolutePath());
			
			fileWriter.flush();
			fileWriter.close();
			fileWriter = null;
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	
	private JSONObject readFromJSONFile(String path){
		File file = new File(path);
		JSONObject obj = null;
		FileReader fReader;
		
		try{
			JSONParser jsonParser = new JSONParser();
			fReader = new FileReader(file);
			obj = (JSONObject)jsonParser.parse(fReader);
			fReader.close();
			fReader = null;
		} catch (IOException | ParseException e){
			System.out.println(e.getMessage());
		}
		
		return obj;
	}
	
	private void createMapFromJSONObject(JSONObject obj, Map map){
		map.setName((String)obj.get(MapLoader.NAME));
		map.setGrid(this.createGridFromArray(this.createBooleanArrayFromJSONArray((JSONArray)obj.get(MapLoader.GRID)),
																					(int)(long)obj.get(MapLoader.WIDTH),
																					(int)(long)obj.get(MapLoader.HEIGHT)));
	}

	private boolean[] createBooleanArrayFromJSONArray(JSONArray jsonArr){
		Iterator<Boolean> iterator = jsonArr.iterator();
		boolean[] arr = new boolean[jsonArr.size()];
		int i = 0;
		while(iterator.hasNext()){
			arr[i] = iterator.next();
			i++;
		}
		
		return arr;
	}
	
	private Grid createGridFromArray(boolean[] arr, int width, int height){
		Grid grid = new Grid(width, height);
		
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				if(arr[(i * width + j)])
					grid.setAvailable(j, i);
				else 
					grid.setUnavailable(j, i);
			}
		}
		
		return grid;
	}
	
	private static JSONArray booleanArrayToJSONArray(boolean[] arr){
		JSONArray jsonArr = new JSONArray();
		for(int i = 0; i < arr.length; i++)
			jsonArr.add(arr[i]);
		
		return jsonArr;
	}
	
	private static boolean[] gridMatrixToArray(Grid grid){
		int width = grid.getWidth();
		int height = grid.getHeight();
		
		boolean[] arrayGrid = new boolean[width * height];
		
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				arrayGrid[i * width + j] = grid.getTileAt(j, i).isAvailable();
			}
		}
		
		return arrayGrid;
	}
}
