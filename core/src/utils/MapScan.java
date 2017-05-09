package utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

import net.colinjohnson.game.Map;

public class MapScan {
	private Pixmap map;
	private Map targetMap;
	private int target;
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
		//target = Color.rgb888(targetColor);
		target= -2139062017;
		state = new pixelState[map.getHeight()][map.getWidth()];
		for (pixelState[] p : state) {
			for (@SuppressWarnings("unused") pixelState q : p) {
				q = pixelState.free;
			}			
		}
	} // MapScan Constructor
	
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
		int count = 0;
		
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
							count++;
							if (count%100 == 0) {
								System.out.println(count);
							}
							if (utils.Constants.DEBUG_MSGS)System.out.println("Found clear area at (" + x + ", " + y + ") width: " + width + "px, height" + height + "px.");
							
							// mark area as used
							for (int i = x; i < x + width; i++) {
								for(int j = y; j < y + height; j++){
									state[i][j] = pixelState.covered;
								}
							}
							
							// add new object
							//TODO: What is this width/2 needed?
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
