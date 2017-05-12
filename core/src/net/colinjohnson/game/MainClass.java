package net.colinjohnson.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import utils.Constants;
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
	GlyphLayout layout;
	private OrthographicCamera camera;
	private Box2DDebugRenderer b2dr;	
	ShapeRenderer shapes;
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
		layout = new GlyphLayout();
		player = new PlayerEntity("Player_1", map);
		map.getPlayers().add(player);
		player.setWeapon(new Weapon(0, 0, Weapon.Gun.ak47));
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		b2dr = new Box2DDebugRenderer();
		shapes = new ShapeRenderer();
		
		// add a bot
		map.getPlayers().add(new BotEntity("BOT", map));
		
		// scan map
		MapScan scan = new MapScan(map, new Color(0.5f, 0.5f, 0.5f, 1f));
		scan.scanMap4();
	}

	public void update(float delta) {	
		
		// FPS calculation
		fps = (Gdx.graphics.getDeltaTime() == 0) ? 0 : (int) (1 / Gdx.graphics.getDeltaTime());	
		
		// update map	
		map.update();
		
		// update input
		inputUpdate();
		
		// update currently controlled player
		player.update(delta, getX2(), getY2());
		
		// update camera position
		cameraUpdate(delta);
	}
	
	private void inputUpdate(){
		// Input
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
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
		
		// update game world
		update(Gdx.graphics.getDeltaTime());

		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();		
		map.getMapTexture().draw(batch, 1f);
		
		// draw debug info
		Vector3 screenTop = camera.unproject(new Vector3(0, 0, 0));	
		font.setColor(Color.WHITE);
		font.getData().setScale(1f);
		font.draw(batch, "FPS: " + String.valueOf(fps), screenTop.x, screenTop.y);
		font.draw(batch, "Cursor X: " + String.valueOf(getX2()), screenTop.x, screenTop.y - 15);
		font.draw(batch, "Cursor Y: " + String.valueOf(getY2()), screenTop.x, screenTop.y - 30);		
		font.draw(batch, "Player Rotation: " + String.valueOf(player.getRotation()), screenTop.x, screenTop.y - 45);		
		font.draw(batch, String.valueOf(player.getSprite().getRotation() + 180), screenTop.x, screenTop.y - 60);
		font.draw(batch, player.getPlayerName() + ": $" + String.valueOf(player.getMoney()), screenTop.x, screenTop.y - 75);
		font.draw(batch, "Player Pos (px): " + String.valueOf(player.getX()) + ", " + String.valueOf(player.getY()), screenTop.x, screenTop.y - 90);
		font.draw(batch, "Body Pos (m): " + String.valueOf(player.getBody().getPosition()), screenTop.x, screenTop.y - 105);
		font.draw(batch, "Bodies: " + map.getWorld().getBodyCount(), screenTop.x, screenTop.y - 120);

		// draw player names		
		font.getData().setScale(0.75f);
		for (PlayerEntity target: map.getPlayers()) {
			if (target instanceof BotEntity) {
				font.setColor(Color.RED);
			} else {
				font.setColor(Color.BLUE);
			}			
			layout.setText(font, target.getPlayerName());
			font.draw(batch, layout, target.getX() - layout.width/2, target.getY() + target.getSize() + 10);
		}
		batch.end();		
		
		// ShapeRenderer to draw non-image shapes		
		shapes.setProjectionMatrix(camera.combined);
		shapes.begin(ShapeType.Filled);
		
		// draw players
		for (PlayerEntity target: map.getPlayers()) {
			if (target instanceof BotEntity) {
				shapes.setColor(Color.RED);
			} else {
				shapes.setColor(Color.BLUE);
			}			
			shapes.rect(target.getX() - target.getSize()/2, target.getY() - target.getSize()/2, target.getSize()/2, target.getSize()/2, target.getSize(), target.getSize(), 1f, 1f, target.getRotation());
		}
		
		// draw view lines
		for (PlayerEntity toDraw : map.getPlayers()) {
			if (toDraw.equals(player)) {
				shapes.setColor(new Color(0, 0, 1, 1));
				shapes.line(getX2(), getY2(), player.getX(), player.getY());
			} else if (toDraw instanceof BotEntity) {
				if (((BotEntity) toDraw).hasLOS) {
					shapes.setColor(new Color(0, 1, 0, 1));
				} else {
					shapes.setColor(new Color(1, 0, 0, 1));
				}				
				shapes.line(toDraw.getX(), toDraw.getY(), player.getX(), player.getY());
			}
		}
		
		// draw projectile rays
		for (Projectile toDraw : map.getProjectiles()) {	
			shapes.setColor(Color.GRAY);
			shapes.line(toDraw.p1W.x, toDraw.p1W.y, toDraw.p2W.x, toDraw.p2W.y);
			shapes.setColor(Color.GOLD);
			shapes.line(toDraw.p1W.x, toDraw.p1W.y, toDraw.stopW.x, toDraw.stopW.y);		
		}
		
		shapes.end();
		
		// render test Box2D shapes
		if (Constants.DEBUG_DRAW)b2dr.render(map.getWorld(), camera.combined.scl(PPM));
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
		font.dispose();
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
