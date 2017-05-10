package net.colinjohnson.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Json;

import utils.MapScan;

import static utils.Constants.PPM;

public class MainClass extends Game {
	
	// Logic
	private PlayerEntity player;
	private boolean hasClicked = false;
	private Vector2 lastClick = new Vector2(0, 0);
	
	// Debug
	private boolean temp1 = true;
	private boolean temp2 = true;
	private boolean temp3 = true;
	private boolean temp4 = true;
	
	// Graphics
	private SpriteBatch batch;
	private BitmapFont font;
	private OrthographicCamera camera;
	private Box2DDebugRenderer b2dr;	
	private float imageScale = 2f;
	private int fps;
	public final int fpsTarget = 60;
	
	// Physics
	private Map map;

	@Override
	public void create() {
		
		// init variables
		map = new Map();
		batch = new SpriteBatch();
		font = new BitmapFont();		
		player = new PlayerEntity("Player_1", map);
		player.setWeapon(new Weapon(0, 0, Weapon.Gun.ak47));
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		b2dr = new Box2DDebugRenderer();
		
		// scan map
		MapScan scan = new MapScan(map, new Color(0.5f, 0.5f, 0.5f, 1f));
		//scan.scanMap();
	}

	public void update(float delta) {
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.B) && temp1) {
			MapScan scan = new MapScan(map, new Color(0.5f, 0.5f, 0.5f, 1f));
			scan.scanMap2();
			temp1 = !temp1;
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.N) && temp2) {
			MapScan scan = new MapScan(map, new Color(0.5f, 0.5f, 0.5f, 1f));
			scan.scanMap1();
			temp2 = !temp2;
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.M) && temp3) {
			MapScan scan = new MapScan(map, new Color(0.5f, 0.5f, 0.5f, 1f));
			scan.scanMap3();
			temp3 = !temp3;
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.V) && temp4) {
			MapScan scan = new MapScan(map, new Color(0.5f, 0.5f, 0.5f, 1f));
			scan.scanMap4();
			temp4 = !temp4;
		}
		
		if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
			lastClick.x = getX2();
			lastClick.y = getY2();
		}
		
		if (Gdx.input.isButtonPressed(Buttons.RIGHT)) {
			if (hasClicked) {
				map.addObstacle(lastClick.x, lastClick.y, Math.abs(lastClick.x - Gdx.input.getX()), Math.abs(lastClick.x - Gdx.input.getY()));
			}
			hasClicked = false;
		}
		
		fps = (Gdx.graphics.getDeltaTime() == 0) ? 0 : (int) (1 / Gdx.graphics.getDeltaTime());	
		map.update();
		player.update(getX2(), getY2());
		cameraUpdate(delta);
	}
	
	private void cameraUpdate(float delta){		
		
		// constants
		int shiftStart = 0;
		
		// determine camera shift
		Vector2 pPos = new Vector2(player.getBody().getPosition().x * PPM, player.getBody().getPosition().y * PPM);
		Vector2 mPos = new Vector2(getX2(), getY2());
		Vector2 posDiff = new Vector2(mPos.x - pPos.x, mPos.y - pPos.y);
		Vector2 camShift = new Vector2(posDiff.x/2, posDiff.y/2);
		
		// shift when far from player
		if (posDiff.x < shiftStart && posDiff.x > -shiftStart) {
			camShift.x = 0;
		}
		if (posDiff.y < shiftStart && posDiff.y > -shiftStart) {
			camShift.y = 0;
		}
		
		// shift camera only if space pressed
		if (!Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			camShift.x = 0;
			camShift.y = 0;
		}
		
		// set camera location
		Vector3 position = camera.position;	
		position.x = pPos.x + camShift.x;
		position.y = pPos.y + camShift.y;
		camera.position.set(position);
		camera.update();
	}

	@Override
	public void render() {
		update(Gdx.graphics.getDeltaTime());

		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();		
		map.getMapTexture().draw(batch, 1f);
		
		Vector3 screenTop = camera.unproject(new Vector3(0, 0, 0));		
		font.draw(batch, "FPS: " + String.valueOf(fps), screenTop.x, screenTop.y);
		font.draw(batch, "Cursor X: " + String.valueOf(getX2()), screenTop.x, screenTop.y - 15);
		font.draw(batch, "Cursor Y: " + String.valueOf(getY2()), screenTop.x, screenTop.y - 30);		
		font.draw(batch, "Player Rotation: " + String.valueOf(player.getRotation()), screenTop.x, screenTop.y - 45);		
		font.draw(batch, String.valueOf(player.getSprite().getRotation() + 180), screenTop.x, screenTop.y - 60);
		font.draw(batch, player.getPlayerName() + ": $" + String.valueOf(player.getMoney()), screenTop.x, screenTop.y - 75);
		font.draw(batch, "Player Pos (px): " + String.valueOf(player.getX()) + ", " + String.valueOf(player.getY()), screenTop.x, screenTop.y - 90);
		font.draw(batch, "Body Pos (m): " + String.valueOf(player.getBody().getPosition()), screenTop.x, screenTop.y - 105);
		font.draw(batch, "Bodies: " + map.getWorld().getBodyCount(), screenTop.x, screenTop.y - 120);
		batch.end();
		
		ShapeRenderer shapes = new ShapeRenderer();
		shapes.setProjectionMatrix(camera.combined);
		shapes.begin(ShapeType.Filled);
		shapes.setColor(new Color(0, 1, 0, 1));
		float playerX = player.getBody().getPosition().x * PPM;
		float playerY = player.getBody().getPosition().y * PPM;
		shapes.rect(playerX - player.getSize()/2, playerY - player.getSize()/2, player.getSize()/2, player.getSize()/2, player.getSize(), player.getSize(), 1f, 1f, player.getRotation());
		shapes.setColor(new Color(1, 0, 0, 1));
		shapes.line(getX2(), getY2(), playerX, playerY);
		shapes.end(); 
		
		b2dr.render(map.getWorld(), camera.combined.scl(PPM));
	}

	@Override
	public void resize(int width, int height){
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void dispose() {
		batch.dispose();
		b2dr.dispose();
		map.getWorld().dispose();
	}
	
	private int getX2() {
		return (int) (camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).x);
	}
	
	private int getY2() {
		return (int) ((camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).y));
	}

	public float getImageScale() {
		return imageScale;
	}

	public void setImageScale(float imageScale) {
		this.imageScale = imageScale;
	}
}
