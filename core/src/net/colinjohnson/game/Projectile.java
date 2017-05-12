package net.colinjohnson.game;

import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.RayCastCallback;

import utils.Constants;

public class Projectile {
	private static final int CAST_LENGTH = 5000;	
	private Map mapRef;
	private PlayerEntity player;
	private Weapon source;
	private Body body;
	private TreeMap<Float, Vector2> tMap = new TreeMap<Float, Vector2>();
	public long timeCreated;
	Vector2 p1 = new Vector2();
	Vector2 p1W = new Vector2();
	Vector2 p2 = new Vector2();
	Vector2 p2W = new Vector2();
	Vector2 stop = new Vector2();
	Vector2 stopW = new Vector2();
	
	public Projectile(Map mapRef, PlayerEntity player, Weapon source){
		this.mapRef = mapRef;
		this.player = player;
		this.source = source;
		this.timeCreated = System.currentTimeMillis();
		fire();
	}
	
	public void fire(){
		
		float rotation = player.getRotation() - 90;	
		
		// ray cast callback to register impacts
		RayCastCallback rcc = new RayCastCallback() {		
			
			@Override
			public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
				tMap.put(fraction, point.cpy());
				return 1;
			}
		};
			
		// ray start & end pts in world coordinates
		p1W.set(player.getX(), player.getY());
		Vector2 ray = new Vector2(p1W);
		ray.setLength(CAST_LENGTH);
		ray.setAngle(rotation);
		p2W.set(p1W.x + ray.x, p1W.y + ray.y);
		
		// ray start & end pts in box2D coordinates
		p1.set(p1W.x / Constants.PPM, p1W.y / Constants.PPM);
		p2.set(p2W.x / Constants.PPM, p2W.y / Constants.PPM);	
		
		// default stop point as end of ray
		stop.set(p2);
		stopW.set(p2W);
		
		// ray cast
		mapRef.getWorld().rayCast(rcc, p1, p2);
		
		// stop ray at closest obstacle
		if (!tMap.isEmpty()) {
			stop.set(tMap.get(tMap.firstKey()));
			stopW.set(stop.x * Constants.PPM, stop.y * Constants.PPM);
		}	
	}
	
	private void dropShell(){
		Body projBody;
		BodyDef def = new BodyDef();
		def.type = BodyDef.BodyType.DynamicBody;
		def.position.set(player.getX() / Constants.PPM, player.getY() / Constants.PPM);
		def.fixedRotation = false;
		projBody = mapRef.getWorld().createBody(def);			
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(1 / Constants.PPM, 1 / Constants.PPM); // NOTE: width & height measured from center		
		projBody.createFixture(shape, 1.0f);
		shape.dispose();
		this.body = projBody; 
	}
}
