package net.colinjohnson.game;

import java.io.File;
import java.util.ArrayList;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Map {
	private Vector2 spawn;
	private Image mapTexture;
	private World world;
	private ArrayList<ObstacleEntity> obstacles = new ArrayList<ObstacleEntity>();
	private FileHandle handle;
	
	public Map(){
		loadMap(0);
	} // Map Constructor
	
	public void update() {
		world.step(1/60f, 6, 2);
	} // update
	
	public void loadMap(int mapNumber){
		switch (mapNumber) {
		case 0:
			System.out.println("loading test map");
			testMap();
			break;

		default:
			break;
		}
	} // loadMap
	
	private void testMap(){
		File mapFile = new File("CacheMap.png");
		setHandle(new FileHandle(mapFile));
		setMapTexture(new Image(new Texture(new FileHandle(mapFile))));
		setWorld(new World(new Vector2(0, 0), false));
		spawn = new Vector2(400f, 2800f);
		
		// test hitboxes
		obstacles.add(new ObstacleEntity(spawn.x - 200, spawn.y - 200, 150, 150, this));
	} // testMap
	
	public void addObstacle(float x, float y, float width, float height){
		obstacles.add(new ObstacleEntity(x, y, width, height, this));
	}

	public Image getMapTexture() {
		return mapTexture;
	} // getMapTexture

	public void setMapTexture(Image mapTexture) {
		this.mapTexture = mapTexture;
		mapTexture.setScale(2f);
	} // setMapTexture

	public Vector2 getSpawn() {
		return spawn;
	} // getSpawn

	public void setSpawn(Vector2 spawn) {
		this.spawn = spawn;
	} // setSpawn

	public World getWorld() {
		return world;
	} // getWorld

	public void setWorld(World world) {
		this.world = world;
	} // setWorld

	public FileHandle getHandle() {
		return handle;
	}

	public void setHandle(FileHandle handle) {
		this.handle = handle;
	}

	public ArrayList<ObstacleEntity> getObstacles() {
		return obstacles;
	}
} // Map
