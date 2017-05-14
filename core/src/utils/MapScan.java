package utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

import net.colinjohnson.game.Map;

public class MapScan {
	private Pixmap map;
	private Map targetMap;
	private int target;
	private int target2;
	private int target3;
	private pixelState[][] state;
	
	enum pixelState {
		invalid,
		covered,
		free,
	}
	
	/**
	 * MapScan Constructor.
	 * @param filePath The name of the image to be processed.
	 * @param targetMap The game map to add boundaries to.
	 * @param targetColor The color on the image of the desired boundaries.
	 */
	public MapScan(Map targetMap, Color targetColor) {
		map = new Pixmap(targetMap.getHandle());	
		this.targetMap = targetMap;
		
		//TODO: Proper color conversion
		target = Color.rgba8888(new Color(0x808080ff));
		target2 = Color.rgba8888(new Color(0xff6e00ff));
		state = new pixelState[map.getHeight()][map.getWidth()];
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[0].length; j++) {
				state[i][j] = pixelState.invalid;
			}
		}
	} // MapScan Constructor

	/**
	 * Analyzes given image and adds Box2D bodies accordingly. (Method 4)
	 */
	public void scanMap4(){
		
		for (int y = 0; y < map.getHeight(); y++) {
			for (int x = 0; x < map.getWidth(); x++) {
				if (map.getPixel(x, y) == target || map.getPixel(x, y) == target2) {	
					 state[x][y] = pixelState.free;
				}
			}
		}
		
		for (int y = 0; y < map.getHeight(); y++) {
			for (int x = 0; x < map.getWidth(); x++) {		
				if (state[x][y] == pixelState.free) {					
					int X = 0;
					int Y = 0;
					boolean a = true;
									
					while(a){
						X++;
						Y++;
						for (int j = y; j < y + Y && a; j++) {
							for (int i = x; i < x + X && a; i++) {							
								if (state[i][j] != pixelState.free) {
									a = false;
									X--;
									Y--;
								}
							}
						}
					}
					
					a = true;
					while(a){
						X++;
						for (int j = y; j < y + Y && a; j++) {
							for (int i = x; i < x + X && a; i++) {							
								if (state[i][j] != pixelState.free) {
									a = false;
									X--;
								}
							}
						}
					}
					
					a = true;
					while(a){
						Y++;
						for (int j = y; j < y + Y && a; j++) {
							for (int i = x; i < x + X && a; i++) {							
								if (state[i][j] != pixelState.free) {
									a = false;
									Y--;
								}
							}
						}
					}
					
					targetMap.addObstacle((x + X/2) * 2, (targetMap.getMapTexture().getHeight() - y - Y/2) * 2, X * 2, Y * 2);
					
					for (int i = x; i < x + X; i++) {
						for (int j = y; j < y + Y; j++) {
							if (i < state.length && j < state[0].length) {
								state[i][j] = pixelState.covered;
							}						
						}
					}
				} else {
					state[x][y] = pixelState.invalid;
				}
			}
		}
	}
	
	/**
	 * Analyzes given image and adds Box2D bodies accordingly. (Method 3)
	 */
	public void scanMap3(){
		
		for (int y = 0; y < map.getHeight(); y++) {
			for (int x = 0; x < map.getWidth(); x++) {
				if (map.getPixel(x, y) > target + 10000 || map.getPixel(x, y) < target - 10000) {	
					 state[x][y] = pixelState.invalid;
				}
			}
		}
		
		for (int y = 0; y < map.getHeight(); y++) {
			for (int x = 0; x < map.getWidth(); x++) {		
				if (state[x][y] == pixelState.free) {					
					int X = 0;
					int Y = 0;
					boolean a = true;
									
					while(a){
						X++;
						Y++;
						for (int j = y; j < y + Y && a; j++) {
							for (int i = x; i < x + X && a; i++) {							
								if (state[i][j] != pixelState.free) {
									a = false;
									X--;
									Y--;
								}
							}
						}
					}
					
					targetMap.addObstacle((x + X/2) * 2, (targetMap.getMapTexture().getHeight() - y - Y/2) * 2, X * 2, Y * 2);
					
					for (int i = x; i < x + X; i++) {
						for (int j = y; j < y + Y; j++) {
							if (i < state.length && j < state[0].length) {
								state[i][j] = pixelState.covered;
							}						
						}
					}
				} else {
					state[x][y] = pixelState.invalid;
				}
			}
		}
	}
	
	/**
	 * Analyzes given image and adds Box2D bodies accordingly. (Method 2)
	 */
	public void scanMap2(){
		for (int y = 0; y < map.getHeight(); y++) {
			for (int x = 0; x < map.getWidth(); x++) {
				if (map.getPixel(x, y) == target) {
					int width = 0;
					while(map.getPixel(x + 1 + width, y)  == target){
						width++;
					}			
					x += width;				
					targetMap.addObstacle((x - width/2) * 2, (targetMap.getMapTexture().getHeight() - y) * 2, width * 2, 1 * 2);
				}
			}
		}
	}
	
	/**
	 * Analyzes given image and adds Box2D bodies accordingly. (Method 1)
	 */
	public void scanMap1(){
		
		// check each pixel individually
		for (int y = 0; y < map.getHeight(); y++) {
			for (int x = 0; x < map.getWidth(); x++) {
				
				// find box if pixel is valid				
				if (map.getPixel(x, y) == target ) {
					int width = 1;
					int height = Integer.MAX_VALUE;
					boolean invalid = false;
					
					if (state[x][y] == pixelState.covered) {
						invalid = true;
					}
					
					// establish horizontal size
					while(map.getPixel(x + width + 1, y) == target && !invalid){					
						width++;
					}
					
					// if enough horizontal room & area is not covered, establish vertical size
					if (width > 1 && !invalid) {	
						
						// loop through each column
						for (int i = 0; i < width; i++) {
							
							// measure sub-columns
							int currentHeight = 0;
							while(map.getPixel(x + i, y - currentHeight) == target){	
								currentHeight++;
							}
							
							// track smallest clear column height
							height = (currentHeight < height)? currentHeight: height;
						}
						
						// if enough vertical room, mark area as used and create object
						if (width > 1 && height > 1 && !invalid) {
							if (utils.Constants.DEBUG_MSGS)System.out.println("Found clear area at (" + x + ", " + y + ") width: " + width + "px, height" + height + "px.");
							
							// mark area as used
							for (int i = x; i < x + width; i++) {
								for(int j = y; j < y + height; j++){
									state[i][j] = pixelState.covered;
								}
							}
							
							// add new object
							targetMap.addObstacle((x + width/2) * 2, (targetMap.getMapTexture().getHeight() - y) * 2, width * 2, height * 2);
						}
					}
				
				// otherwise mark as invalid
				} else {
					state[x][y] = pixelState.invalid;
				}
			}
		}
	} // scanMap
} // MapScan.java
